package core;

import java.util.Scanner;

public class Evaluator {
    final private User user;

    public Evaluator() {
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

        String choice;
        Scanner scanner = new Scanner(System.in);

        Output.header();
        outer:
        while(true) {
            // Show boot menu to guide the user
            Output.boot();

            choice = scanner.nextLine();
            switch (choice) {
                case "1": {
                    String t = user.login();
                    if(t != null) { // If the login is done, and token is returned, redirect it to auth area
                        auth();
                    }
                    break;
                }
                case "2": {
                    String t = user.signup();
                    if(t != null) {
                        auth();
                    } else {
                        Output.clear();
                        System.out.println("--> Something went wrong during sign up, please scroll to the top to see the error.");
                    }
                    break;
                }
                default:
                    System.out.println("-- Wrong choice, try again !");
                    Output.printInserter();
            }

        }
    }

    public void auth() {
//        if(!user.hasAtLeastOneRole()) {
//            System.out.println("--> This console app is available for admins only");
//            shutdown(403);
//        }

        Output.clear();
        Output.header();
        Scanner sc = new Scanner(System.in);

        outer:
        while(true) {
            Output.authBoot(user);
            int choice = sc.nextInt();
            switch(choice) {
                case 1:

                    break;
                case 5:
                    shutdown(0);
                    break;
                case 6:
                    user.logout();
                    shutdown(0);
                default:
                    shutdown(0);
            }
        }
    }

    public void shutdown(int code) {
        if(code == 0) {
            System.out.print("- -- - SEE YOU LATER - -- -");
        } else {
            System.out.println("--> Something went wrong (" + code + ")");
        }
        System.exit(code);
    }
}
