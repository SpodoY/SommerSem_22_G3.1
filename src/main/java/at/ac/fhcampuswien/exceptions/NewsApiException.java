package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.NewsErrorAlert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NewsApiException extends Exception{

    private final static String MESSAGE = "A problem occurred getting your articles";

    NewsApiException(){
        super(MESSAGE);
        NewsErrorAlert.createAlert(MESSAGE);
    }

    NewsApiException(String message) {
        super(message);
    }

}


