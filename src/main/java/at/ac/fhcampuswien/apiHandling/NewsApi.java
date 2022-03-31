package at.ac.fhcampuswien.apiHandling;

import at.ac.fhcampuswien.Article;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class NewsApi {

    // defining fields
    private final String BASEURL = "https://newsapi.org/v2/";
    private final static String API_KEY = readKey();

    /**
    This method reads the api key from a static file, which isn't pushed to git
     */
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


    public String urlBuilder(String category, String  ... args){

        String url = BASEURL + category;
        for (String part : args) {
            url += "?" + part;
        }
        url += API_KEY;

        try {
            return runGetRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Invalid url created check arguments");
        }
        return "";
    }

    /**
     * This method gets all parameters for the url and calls the request function
     * @param category headlines or everything
     * @param keyword the filtering word, default is keyword
     * @return returns the http message body as string
     */


    /**
     * This method handles Get request and should be called from the response function
     * @param url the url from the Response function
     * @return the message body to string from the http request
     * @throws IOException
     */
    private String runGetRequest(String url) throws IOException {

        // initiates the http client
        var client = new OkHttpClient();

        // uses the Request class form ok http to set up a request
        Request request = new Request.Builder().url(url).build();

        // uses the Response class to catch the response after request
        // surrounded with try and catches to ensure the function keeps working, even if the Response fails
        try (Response response = client.newCall(request).execute()) {

            //returns the body of the http request and uses okhttp library to parse it to
            /*gson.fromJson(response.body().string(), NewsResponse.class);*/
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // just the last Backup return if nothing works
        return "no respones";
    }

}
