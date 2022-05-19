package at.ac.fhcampuswien.exceptions;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsApiException extends Exception{

    NewsApiException(){
        super("A problem occurred getting your articles");
    }

    NewsApiException(String message) {
        super(message);
    }

}


