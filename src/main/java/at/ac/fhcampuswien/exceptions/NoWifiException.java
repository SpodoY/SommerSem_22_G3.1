package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.NewsErrorAlert;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class NoWifiException extends Exception{

    private final static String MESSAGE = "Please check your internet Connection";

    public NoWifiException(){
        super(MESSAGE);
        NewsErrorAlert.createAlert(MESSAGE);
    }

    NoWifiException(String message){
        super(message);
    }



}
