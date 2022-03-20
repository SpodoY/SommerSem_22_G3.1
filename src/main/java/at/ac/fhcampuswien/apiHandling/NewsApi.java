package at.ac.fhcampuswien.apiHandling;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class NewsApi {

    private String baseQuery = "https://newsapi.org/v2/";
    private String topHeadlines ="top-headlines";
    private String everything = "everything";
    private final static String API_KEY = readKey();

    private static String readKey() {
        InputStream is = NewsApi.class.getClassLoader().getResourceAsStream("at/ac/fhcampuswien/keys.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
