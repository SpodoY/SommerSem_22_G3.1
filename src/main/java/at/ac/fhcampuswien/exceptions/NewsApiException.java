package at.ac.fhcampuswien.exceptions;

public class NewsApiException extends Exception{

    private final static String MESSAGE = "A problem occurred getting your articles";

    NewsApiException(){
        super(MESSAGE);
        //NewsErrorAlert.createAlert(MESSAGE);
    }

    NewsApiException(String message) {
        super(message);
    }

}


