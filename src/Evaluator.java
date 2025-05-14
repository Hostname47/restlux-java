import api.ApiClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Evaluator {
    final private User user;

    Evaluator() {
        user = new User();
    }

    public void boot() {
        // Here first, we need to check in the auth text file, if the token.txt is already there
        // If so we authenticate the user directly without need to take his login and password

        // 1. First, let's try to read the token.txt from the file
        String token = readTokenFile();

        if(token != null && !token.isEmpty()) {
            String response = ApiClient.get("/user", token);

            if (response != null && !response.isEmpty()) {
                if (response.contains("\"user\"")) {
                    System.out.println(" => User is authenticated.");

                    // Optional: extract the full name if you want (very simple string parsing)
                    int fullnameIndex = response.indexOf("\"fullname\"");
                    if (fullnameIndex != -1) {
                        int colonIndex = response.indexOf(":", fullnameIndex);
                        int quoteStart = response.indexOf("\"", colonIndex + 1);
                        int quoteEnd = response.indexOf("\"", quoteStart + 1);
                        String fullname = response.substring(quoteStart + 1, quoteEnd);
                        System.out.println("Welcome, " + fullname + "!");
                    }
                }
            }
        }

        int choice;
        Scanner scanner = new Scanner(System.in);
        outer:
        while(true) {
            // Show the boo content
            Output.boot();
            Output.printInserter();

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String t = user.login();
                    if(t != null) { // If the login is done, and token is returned, redirect it to auth area
                        authArea();
                    }
                    break;
                default:
                    System.out.print("- -- - SEE YOU LATER - -- -");
                    System.exit(0);
                    return;

            }
        }
    }

    public void authArea() {
        System.out.println("I'm here");
        Scanner sc = new Scanner(System.in);
        Output.printInserter();
        int a = sc.nextInt();
    }

    public static String readTokenFile() {
        try {
            // Use the relative path for the file (assuming it's in the same directory as the class)
            Path path = Paths.get("./src/token.txt");

            // Read all lines from the file
            String content = Files.readString(path);

            // Return the first line, which should contain the token
            return content.trim();  // Assuming the token is in the first line
        } catch (IOException e) {
            System.out.println("Could not read auth.txt: " + e.getMessage());
            return null;
        }
    }
}
