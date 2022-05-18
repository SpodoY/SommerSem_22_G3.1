package at.ac.fhcampuswien.exceptions;

public class ApiKeyExceptions extends Exception {
    public ApiKeyExceptions(){
        super("Your API Key is too short. Please check your API Key again.");
    }
    ApiKeyExceptions(String message){
        super(message);
    }
}
