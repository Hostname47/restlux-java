package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Token {
    public static String readTokenFile() {
        try {
            Path path = Paths.get("./src/api/token.txt");
            String content = Files.readString(path);
            return content.trim();
        } catch (IOException e) {
            System.out.println("Could not read auth.txt: " + e.getMessage());
            return null;
        }
    }
}
