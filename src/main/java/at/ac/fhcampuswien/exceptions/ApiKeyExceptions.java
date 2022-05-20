package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.PopUp;

public class ApiKeyExceptions extends Exception {

    private final static String MESSAGE = "Your API Key is too short. Please check your API Key again.";
    public ApiKeyExceptions(){
        super(MESSAGE);
        PopUp.createAlert(MESSAGE);
    }
    ApiKeyExceptions(String message){
        super(message);
    }
}
