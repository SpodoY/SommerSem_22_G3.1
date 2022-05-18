package at.ac.fhcampuswien.exceptions;

public class HttpException extends Exception{
    public HttpException(){super("Response status is NOT 'ok'!");}

    HttpException(String message){super(message);}

    public static boolean checkStatus(String responseString) throws HttpException {
        //check if status is "ok"
        String rString = responseString.replaceAll("[^a-zA-Z]+","").toLowerCase();


        if (rString.contains("statusok")) {  //rString.indexOf("statusok") == -1
            // response status = ok
            return true;
        } else {
            return false;
        }

    }
}
