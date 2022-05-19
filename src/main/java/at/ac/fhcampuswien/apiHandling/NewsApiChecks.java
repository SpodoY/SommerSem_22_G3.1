package at.ac.fhcampuswien.apiHandling;

import at.ac.fhcampuswien.exceptions.NewsApiException;
import at.ac.fhcampuswien.exceptions.NoWifiException;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class NewsApiChecks {

    static boolean checkReadKey(String key) throws NewsApiException {
        //check key length - should be 40;
        if(key.length() < 40) {
            return false;
        } else return key.length() <= 40;
        //else: everything is fine with length of API Key
    }

    static boolean checkURL(String url){
        //check if url contains an endpoint (top headlines or everything)
        if(!(url.contains("top-headlines") || url.contains("everything"))){
            //throw new NewsApiException("\n" + "An Enpoint (->top-headlines, everything<-) in your URL is missing.");
            return false;
        }
        return true;
        //else: URL contains an Endpoint
    }


    public static boolean netIsAvailable() throws NoWifiException {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
