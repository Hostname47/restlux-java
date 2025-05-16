package core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import api.ApiClient;
import api.Token;
import security.Permission;
import security.Role;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String token;
    private ArrayList<Role> roles = new ArrayList<>();
    private ArrayList<Permission> permissions = new ArrayList<>();

    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getLastname() {
        return lastName;
    }

    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return firstName + " " + lastName;
    }

    public boolean preLogin() {
        String token_ = Token.readTokenFile();
        String response = ApiClient.get("/user", token_);

        if (response != null && !response.isEmpty()) {
            if (response.contains("\"id\"")) {
                setToken(token_);
                setupUserFromResponse(response);
                return true;
            }
        }

        return false;
    }

    public String login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-> Login: (type 0 to quite)");

        while(true) {
            System.out.print("Enter email or username: ");
            String login = scanner.nextLine();
            if(login.equals("0")) { // This is useful for the client if he wants to exit login
                Output.clear();
                return null;
            }

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            String jsonPayload;
            if (login.contains("@")) {
                jsonPayload = "{\"email\":\"" + login + "\",\"password\":\"" + password + "\"}";
            } else {
                jsonPayload = "{\"username\":\"" + login + "\",\"password\":\"" + password + "\"}";
            }

            String response = ApiClient.post("/login", jsonPayload, "");
            String token_ = ApiClient.extract(response, "token");
            if (token_ != null) {
                setToken(token_);
                setupUserFromResponse(response);
                ApiClient.storeToken(token_);

                return token_;

            } else {
                System.out.println("--> Invalid credentials, try again:");
            }
        }
    }

    public void logout() {
        try {
            if (token == null || token.isEmpty()) {
                System.out.println("-e> Error: 404");
                return;
            }

            ApiClient.post("/logout", "", token);
            setToken(null);
            System.out.println("-- core.User logged out");
            /**
             * Please here, we don't want to remove the token from the file
             * As we don't know to do this, because the API is responsible to
             * invalidate the token
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String signup() {
        Scanner scanner = new Scanner(System.in);
        String fullname, username, email, password;

        System.out.println();
        System.out.println("-> Create an account:");
        while(true) {

            System.out.print("-> Fullname: ");
            fullname = scanner.nextLine();

            System.out.print("-> Username: ");
            username = scanner.nextLine();

            System.out.print("-> email: ");
            email = scanner.nextLine();

            System.out.print("-> password: ");
            password = scanner.nextLine();

            if (fullname == null || fullname.trim().isEmpty()) {
                System.out.println("Error: Fullname is required.");
                continue;
            }

            if (username == null || username.length() < 8) {
                System.out.println("Error: Username must be at least 8 characters.");
                continue;
            }

            if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                System.out.println("Error: Invalid email format.");
                continue;
            }

            if (password == null || password.length() < 8 ||
                    !password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$")) {
                System.out.println("Error: Password must be at least 8 characters and include letters, numbers, and symbols.");
                continue;
            }

            break;
        }

        try {
            // Build JSON manually
            String json = "{" +
                    "\"fullname\":\"" + escape(fullname) + "\"," +
                    "\"username\":\"" + escape(username) + "\"," +
                    "\"email\":\"" + escape(email) + "\"," +
                    "\"password\":\"" + escape(password) + "\"" +
                    "}";

            String response = ApiClient.post("/register", json, null);

            if (response.contains("\"errors\"")) {
                // Basic parsing of error messages
                System.out.println("-- Sign-Up Failed:");
                printValidationErrors(response);
                return null;
            }

            // Assume a token is returned on success
            String token = extractString(response, "\"token\":\"");
            if (token != null) {
                setToken(token);
                setupUserFromResponse(response);
                ApiClient.storeToken(token);

                return token;
            } else {
                System.out.println("-- Sign-Up failed: No token returned.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setupUserFromResponse(String response) {
        id = extractInt(response, "\"id\":");
        username = extractString(response, "\"username\":\"");
        email = extractString(response, "\"email\":\"");

        // Full name → First name + Last name
        String fullname = extractString(response, "\"fullname\":\"");
        if (fullname != null) {
            String[] parts = fullname.split(" ", 2);
            firstName = parts[0];
            lastName = parts.length > 1 ? parts[1] : "";
        }

        // Extract Roles JSON array as string
        String rolesJson = extractArray(response, "\"roles\":[", "]");
        roles.clear(); // clear existing roles
        if (rolesJson != null && !rolesJson.isEmpty()) {
            rolesJson = rolesJson.trim();
            if (rolesJson.startsWith("{")) rolesJson = rolesJson.substring(1);
            if (rolesJson.endsWith("}")) rolesJson = rolesJson.substring(0, rolesJson.length() - 1);

            String[] roleEntries = rolesJson.split("\\},\\{");
            for (String entry : roleEntries) {
                int rid = extractInt(entry, "\"id\":");
                String rname = extractString(entry, "\"name\":\"");
                if (rid != -1 && rname != null) {
                    roles.add(new Role(rid, rname));
                }
            }
        }

        // Extract Permissions JSON array as string
        String permissionsJson = extractArray(response, "\"permissions\":[", "]");
        permissions.clear(); // clear existing permissions
        if (permissionsJson != null && !permissionsJson.isEmpty()) {
            permissionsJson = permissionsJson.trim();
            if (permissionsJson.startsWith("{")) permissionsJson = permissionsJson.substring(1);
            if (permissionsJson.endsWith("}")) permissionsJson = permissionsJson.substring(0, permissionsJson.length() - 1);

            String[] permEntries = permissionsJson.split("\\},\\{");
            for (String entry : permEntries) {
                int pid = extractInt(entry, "\"id\":");
                String pname = extractString(entry, "\"name\":\"");
                if (pid != -1 && pname != null) {
                    permissions.add(new Permission(pid, pname));
                }
            }
        }
    }

    private static String extractString(String text, String key) {
        int index = text.indexOf(key);
        if (index == -1) return null;
        index += key.length();
        int end = text.indexOf("\"", index);
        if (end == -1) return null;
        return text.substring(index, end);
    }

    private static String extractNullableString(String text, String key) {
        int index = text.indexOf(key);
        if (index == -1) return null;
        index += key.length();
        if (text.startsWith("null", index)) return null;
        if (text.charAt(index) == '"') {
            int end = text.indexOf("\"", index + 1);
            return text.substring(index + 1, end);
        }
        return null;
    }

    private static int extractInt(String text, String key) {
        int index = text.indexOf(key);
        if (index == -1) return -1;
        index += key.length();
        int end = text.indexOf(",", index);
        if (end == -1) end = text.indexOf("}", index);
        if (end == -1) end = text.length();
        String num = text.substring(index, end).replaceAll("[^0-9]", "");
        return num.isEmpty() ? -1 : Integer.parseInt(num);
    }

    private static String extractArray(String text, String startKey, String endKey) {
        int start = text.indexOf(startKey);
        if (start == -1) return null;
        start += startKey.length();
        int end = text.indexOf(endKey, start);
        if (end == -1) return null;
        return text.substring(start, end);
    }

    // Optional: Getters for debugging
    public void printInfo() {
        System.out.println("User: " + firstName + " " + lastName);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Roles: " + roles);
        System.out.println("Permissions: " + permissions);
    }

    private String escape(String s) {
        return s.replace("\"", "\\\"");
    }

    private void printValidationErrors(String json) {
        String[] fields = {"fullname", "username", "email", "password"};
        for (String field : fields) {
            int i = json.indexOf("\"" + field + "\":[\"");
            if (i != -1) {
                i += field.length() + 4;
                int end = json.indexOf("\"", i);
                if (end != -1) {
                    String message = json.substring(i, end);
                    System.out.println("- " + field + ": " + message);
                }
            }
        }
    }

    public boolean hasAtLeastOneRole() {
        return !roles.isEmpty();
    }
}