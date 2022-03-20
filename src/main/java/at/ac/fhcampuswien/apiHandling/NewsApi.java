package at.ac.fhcampuswien.apiHandling;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class NewsApi {

    // defining fields
    private final String BASEURL = "https://newsapi.org/v2/";
    private final static String API_KEY = readKey();

    private static String readKey() {
        // Instantiating all Classes need for reading from a file
        InputStream file = NewsApi.class.getClassLoader().getResourceAsStream("at/ac/fhcampuswien/keys.txt");
        InputStreamReader fileReader = new InputStreamReader(file);
        BufferedReader bufferdReader = new BufferedReader(fileReader);
        try {
            // reading the api key from file
            return "&apiKey="+ bufferdReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String  Response (String category, String keyword) {
        try {
            // assembling the url and running the request
            return runGetRequest(BASEURL +category+ "?q=" +keyword+ API_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String runGetRequest(String url) throws IOException {

        // initiates the http client
        var client = new OkHttpClient();

        // uses the Request class form ok http to set up a request
        Request request = new Request.Builder().url(url).build();

        // uses the Response class to catch the response after request
        // surrounded with try and catches to ensure the function keeps working, even if the Response fails
        try (Response response = client.newCall(request).execute()) {

            //returns the body of the http request and uses okhttp library to parse it to
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // just the last Backup return if nothing works
        return "no respones";
    }
}
