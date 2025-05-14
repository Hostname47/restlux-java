import api.ApiClient;
import api.Token;

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
        try {
            bootstrap();
        } catch(Exception e) {
            shutdown(1);
        }
    }

    public void bootstrap() {
        // Here first, we need to check in the auth text file, if the token.txt is already there
        // If so we authenticate the user directly without need to take his login and password

        if(user.preLogin()) { // If the user already login (has a valid token already), redirect him directly to auth area
            auth();
            return;
        }

        int choice;
        Scanner scanner = new Scanner(System.in);

        Output.header();
        outer:
        while(true) {
            // Show boot menu to guide the user
            Output.boot();

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String t = user.login();
                    if(t != null) { // If the login is done, and token is returned, redirect it to auth area
                        auth();
                    } else {
                        Output.clear();
                        System.out.println("--> Invalid credentials, please try again.");
                    }
                    break;
                case 2:
                    break;
                default:
                    shutdown(0);
                    return;

            }

        }
    }

    public void auth() {
        Output.clear();
        Output.header();
        Scanner sc = new Scanner(System.in);

        outer:
        while(true) {
            Output.authBoot(user);
            int choice = sc.nextInt();
            switch(choice) {
                case 5:
                    shutdown(0);
                    break;
                case 6:
                    user.logout();
                    shutdown(0);
                default:
                    break outer;
            }
        }


    }

    public void shutdown(int code) {
        if(code == 0) {
            System.out.print("- -- - SEE YOU LATER - -- -");
        } else {
            System.out.println("-- Something went wrong");
        }
        System.exit(code);
    }
}
