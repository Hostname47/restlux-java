import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import api.ApiClient;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String token;

    public String login() {
        Scanner scanner = new Scanner(System.in);

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

        // Extract token using native String methods
        int tokenKeyIndex = response.indexOf("\"token\"");
        if (tokenKeyIndex != -1) {
            int colonIndex = response.indexOf(":", tokenKeyIndex);
            int quoteStart = response.indexOf("\"", colonIndex + 1);
            int quoteEnd = response.indexOf("\"", quoteStart + 1);
            String extractedToken = response.substring(quoteStart + 1, quoteEnd);

            setToken(extractedToken);
            // Write to ./src/token.txt
            try (FileWriter writer = new FileWriter("src/token.txt")) {
                writer.write(extractedToken);
            } catch (IOException e) {
                System.out.println("Failed to write token: " + e.getMessage());
            }

            return extractedToken;

        } else {
            System.out.println("Token not found in response.");
        }

        return null;
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
}