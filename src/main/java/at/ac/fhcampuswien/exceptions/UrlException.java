package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.NewsErrorAlert;

public class UrlException extends Exception {

    private final static String MESSAGE = "The Url was not assembled correctly pls try valid parameters";

    public UrlException(){
        super(MESSAGE);
        NewsErrorAlert.createAlert(MESSAGE);
    }
    UrlException(String message){
        super(message);
    }
}
