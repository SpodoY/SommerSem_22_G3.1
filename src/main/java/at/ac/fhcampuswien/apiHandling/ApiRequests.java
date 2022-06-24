package at.ac.fhcampuswien.apiHandling;

import java.io.IOException;

/**
 * Template Pattern
 * is here for further extension to make more classes for more apis
 * basically  a blueprint class for making new requests
 * https://www.javatpoint.com/template-pattern
 */

public abstract class ApiRequests {

    /**
     * This method reads the api key from a static file, which isn't pushed to git
     */
    public abstract  String getKey();

    /**
     * This method gets all parameters for the url and calls the request function
     *
     * @param category headlines or everything
     * @param args     the filtering word, default is keyword
     * @return returns the http message body as string
     */
    public abstract String urlAssembly(Enum category, Enum... args);


    public abstract String checkIfAllConditionsAreFulfilled(String url);

    /**
     * This method handles Get request and should be called from the response function
     *
     * @param url the url from the Response function
     * @return the message body to string from the http request
     * @throws IOException
     */
    public abstract String runRequest(String url);

}
