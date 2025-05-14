package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class ApiClient {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String post(String endpoint, String requestBody, String token) {
        try {
            String apiUrl = Config.getBaseApiUrl() + endpoint;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token) // Add the token.txt here
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "Request failed: " + e.getMessage();
        }
    }

    // GET method
    public static String get(String endpoint, String token) {
        try {
            String apiUrl = Config.getBaseApiUrl() + endpoint;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token) // Add the token.txt here
                    .GET() // Use the GET method
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();
        } catch (Exception e) {
            return "Request failed: " + e.getMessage();
        }
    }

    public static String extract(String response, String key) {
        int contentIndex = response.indexOf("\"" + key + "\"");
        if (contentIndex != -1) {
            int colonIndex = response.indexOf(":", contentIndex);
            int quoteStart = response.indexOf("\"", colonIndex + 1);
            int quoteEnd = response.indexOf("\"", quoteStart + 1);
            return response.substring(quoteStart + 1, quoteEnd);
        }

        return null;
    }
}
