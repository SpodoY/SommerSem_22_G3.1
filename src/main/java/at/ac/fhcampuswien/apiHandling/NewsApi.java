package at.ac.fhcampuswien.apiHandling;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class NewsApi {

    // defining fields
    private final String BASEURL = "https://newsapi.org/v2/";
    private final static String API_KEY = readKey();

    /**
    This method reads the api key from a static file, which isn't pushed to git
     */
    private static String readKey() {
        // Instantiating all Classes need for reading from a file
        InputStream file = NewsApi.class.getClassLoader().getResourceAsStream("at/ac/fhcampuswien/keys.txt");
        InputStreamReader fileReader = new InputStreamReader(file);
        BufferedReader bufferdReader = new BufferedReader(fileReader);
        String key;
        try {
            // reading the api key from file
            key = "&apiKey="+ bufferdReader.readLine();

            //Custom Exception - check API Key length
            try {
                checkReadKey(key);
                return key;
            } catch (Exception e) {
                System.out.println("A problem in NewsApi occured: " + e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method gets all parameters for the url and calls the request function
     * @param category headlines or everything
     * @param args the filtering word, default is keyword
     * @return returns the http message body as string
     */
    public String urlBuilder(Enum category, Enum  ... args){

        String url = BASEURL +   category.toString();
        for (Enum part : args) {
            url += "?" +  part.toString();
        }
        url += API_KEY;

        System.out.println(url);
        try {

            //Custom Exception - check if URL contains an Endpoint (Top Headlines or Everything)
            try {
                checkURL(url);
            } catch (Exception e) {
                System.out.println("A problem in NewsApi occured: " + e);
            }
            return runGetRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(url);
            System.out.println("Invalid url created check arguments");
            return "";
        }
    }

    public void urlFixed(String url) throws IOException {
        URL urlReal = new URL(url);
        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(urlReal.openStream()));
        String filename = LocalDateTime.now().format(ISO_LOCAL_DATE);
        String directory = String.format("at/ac/fhcampuswien/%s.txt",filename);
        File file = new File("file.txt");
        file.createNewFile();
        FileOutputStream oFile = new FileOutputStream(file, false);

        while((input = in.readLine())!=null){
            System.out.println(input);
            in.close();
        }
    }

    /**
     * This method handles Get request and should be called from the response function
     * @param url the url from the Response function
     * @return the message body to string from the http request
     * @throws IOException
     */
    private String runGetRequest(String url) throws IOException {

        // initiates the http client
        var client = new OkHttpClient();

        // uses the Request class form ok http to set up a request
        Request request = new Request.Builder().url(url).build();

        // uses the Response class to catch the response after request
        // surrounded with try and catches to ensure the function keeps working, even if the Response fails
        try (Response response = client.newCall(request).execute()) {

            //returns the body of the http request and uses okhttp library to parse it to
            /*gson.fromJson(response.body().string(), NewsResponse.class);*/
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // just the last Backup return if nothing works
        return "no respones";
    }


    static void checkReadKey(String key) throws NewsApiException{
        //check key length - should be 40;
        if(key.length() < 40) {
            throw new NewsApiException("\n" + "Your API Key is too short. Please check your API Key again.");
        } else if (key.length() > 40) {
            throw new NewsApiException("\n" + "Your API Key is too long. Please check your API Key again.");
        }
        //else: everything is fine with length of API Key
    }

    static void checkURL(String url) throws NewsApiException{
        //check if url contains an endpoint (top headlines or everything)
        if(!(url.contains("top-headlines") || url.contains("everything"))){
            throw new NewsApiException("\n" + "An Enpoint (->top-headlines, everything<-) in your URL is missing.");
        }
        //else: URL contains an Endpoint
    }

}
