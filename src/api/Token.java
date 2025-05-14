package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Token {
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
