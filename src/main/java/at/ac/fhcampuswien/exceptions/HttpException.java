package at.ac.fhcampuswien.exceptions;

import at.ac.fhcampuswien.apiHandling.PopUp;

public class HttpException extends Exception {

    private final static String MESSAGE = "Response status is NOT 'ok'!";
    public HttpException() {
        super(MESSAGE);
        PopUp.createAlert(MESSAGE);
    }

    HttpException(String message) {
        super(message);
    }

    public static boolean checkStatus(String responseString) throws HttpException {
        //check if status is "ok"
        String rString = responseString.replaceAll("[^a-zA-Z]+", "").toLowerCase();
        return rString.contains("statusok");
    }
}
