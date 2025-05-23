package core;

public class Output {
    public static void header() {
        System.out.println("-- -- --      WELCOME BACK TO     -- -- --");
        System.out.println("-- -- -- Restlux Delux Restaurant -- -- --");
        System.out.println("              -- -- G37 -- --");
        System.out.println("              -- -- --- -- --");
    }

    public static void boot() {
        System.out.println("-> 1. Login");
        System.out.println("-> 2. Register");
        System.out.println("-> 3. Forgot password");
        System.out.println("-> 4. Quite");
        System.out.println("===");
        printInserter();
    }

    public static void authBoot(User user) {
        System.out.println("=== User: " + user.getFullname());
        System.out.println("-> 1. Product managent");
        System.out.println("-> 2. Menus management");
        System.out.println("-> 3. Categories management");
        System.out.println("-> 4. Permissions management");
        System.out.println("-> 5. Quit");
        System.out.println("-> 6. Logout");
        System.out.println("===");
        printInserter();
    }

    public static void categoriesManagementBoot() {
        System.out.println("-> 1. List all categories");
        System.out.println("-> 2. Search for a product");
        System.out.println("-> 3. Edit a product");
        System.out.println("-> 4. Delete a product");
        System.out.println("-> 0. Go back");
        System.out.println("===");
        printInserter();
    }

    public static void printInserter() {
        System.out.print("-> ");
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
