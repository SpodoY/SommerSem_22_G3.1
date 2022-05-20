package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.PopUp;

public class UrlException extends Exception {

    private final static String MESSAGE = "The Url was not assembled correctly pls try valid parameters";

    public UrlException(){
        super(MESSAGE);
        PopUp.createAlert(MESSAGE);
    }
    UrlException(String message){
        super(message);
    }
}
