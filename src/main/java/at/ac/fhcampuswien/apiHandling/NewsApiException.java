package at.ac.fhcampuswien.apiHandling;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsApiException extends Exception{

    NewsApiException(String message) {
        super(message);
    }

    static public void noWifiConnection(){
         System.out.println("Please check your internet Connection");
    }

    static void checkReadKey(String key) throws NewsApiException{
        //check key length - should be 40;
        if(key.length() < 40) {
            throw new NewsApiException("\n" + "Your API Key is too short. Please check your API Key again.");
        } else if (key.length() > 40) {
            throw new NewsApiException("\n" + "Your API Key is too long. Please check your API Key again.");
        }
        //else: everything is fine with length of API Key
    }

    static void checkURL(String url) throws NewsApiException{
        //check if url contains an endpoint (top headlines or everything)
        if(!(url.contains("top-headlines") || url.contains("everything"))){
            throw new NewsApiException("\n" + "An Enpoint (->top-headlines, everything<-) in your URL is missing.");
        }
        //else: URL contains an Endpoint
    }

    static void checkStatus(String responseString) throws NewsApiException {
        //check if status is "ok"
        String rString = responseString.replaceAll("[^a-zA-Z]+","").toLowerCase();

        if (rString.contains("statusok")) {  //rString.indexOf("statusok") == -1
            // response status = ok
        } else {
            throw new NewsApiException("\n" + "Response status is NOT 'ok'!");
        }

    }
    public static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (Exception e) {
            noWifiConnection();
            return false;
        }
    }
}


