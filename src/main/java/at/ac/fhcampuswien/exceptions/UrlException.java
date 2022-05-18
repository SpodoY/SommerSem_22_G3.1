package at.ac.fhcampuswien.exceptions;

public class UrlException extends Exception{
    public UrlException(){
        super("The Url was not assembled correctly pls try valid parameters");
    }
    UrlException(String message){
        super(message);
    }
}
