import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import api.ApiClient;
import api.Token;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String token;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public boolean preLogin() {
        String token_ = Token.readTokenFile();
        String response = ApiClient.get("/user", token_);

        if (response != null && !response.isEmpty()) {
            if (response.contains("\"id\"")) {
                setToken(token_);
                setFullname(response);
                return true;
            }

        }

        return false;
    }

    public String login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-> Login:");

        System.out.print("Enter email or username: ");
        String login = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String jsonPayload;
        if (login.contains("@")) {
            jsonPayload = "{\"email\":\"" + login + "\",\"password\":\"" + password + "\"}";
        } else {
            jsonPayload = "{\"username\":\"" + login + "\",\"password\":\"" + password + "\"}";
        }

        String response = ApiClient.post("/login", jsonPayload, "");
        setFullname(response);
        // Extract token using native String methods
        String token_ = ApiClient.extract(response, "token");
        if (token_ != null) {
            setToken(token_);

            // Write to ./src/token.txt
            try (FileWriter writer = new FileWriter("src/token.txt")) {
                writer.write(token_);
            } catch (IOException e) {

            }

            return token_;

        } else {

        }

        return null;
    }

    public void logout() {
        try {
            if (token == null || token.isEmpty()) {
                System.out.println("-e> Error: 404");
                return;
            }

            ApiClient.post("/logout", "", token);
            setToken(null);
            System.out.println("-- User logged out");
            /**
             * Please here, we don't want to remove the token from the file
             * As we don't know to do this, because the API is responsible to
             * invalidate the token
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setFullname(String response) {
        String fullname = ApiClient.extract(response, "fullname");
        if(fullname != null) {
            String[] names = fullname.split(" ");
            setFirstname(names[0]);
            setLastname(names[1]);
        }
    }
}