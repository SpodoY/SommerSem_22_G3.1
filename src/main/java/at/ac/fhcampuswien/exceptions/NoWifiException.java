package at.ac.fhcampuswien.exceptions;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class NoWifiException extends Exception{
    public NoWifiException(){
        super("Please check your internet Connection");
    }

    NoWifiException(String message){
        super(message);
    }



}
