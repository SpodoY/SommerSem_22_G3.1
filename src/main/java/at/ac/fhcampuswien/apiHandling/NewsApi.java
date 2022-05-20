package at.ac.fhcampuswien.apiHandling;

import at.ac.fhcampuswien.Article;
import at.ac.fhcampuswien.exceptions.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class NewsApi {

    // defining fields
    private final String BASEURL = "https://newsapi.org/v2/";
    private final static String API_KEY = readKey();

    /**
     * This method reads the api key from a static file, which isn't pushed to git
     */
    private static String readKey() {
        // Instantiating all Classes need for reading from a file
        InputStream file = NewsApi.class.getClassLoader().getResourceAsStream("at/ac/fhcampuswien/keys.txt");
        InputStreamReader fileReader = new InputStreamReader(file);
        BufferedReader bufferdReader = new BufferedReader(fileReader);
        String key;
        try {
            // reading the api key from file
            key = "&apiKey=" + bufferdReader.readLine();
            if (!NewsApiChecks.checkReadKey(key)) {
                throw new ApiKeyExceptions();
            } else {
                return key;
            }
        } catch (IOException | NewsApiException | ApiKeyExceptions e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method gets all parameters for the url and calls the request function
     *
     * @param category headlines or everything
     * @param args     the filtering word, default is keyword
     * @return returns the http message body as string
     */
    public String urlBuilder(Enum category, Enum... args) {

        String url = BASEURL + category.toString();
        for (Enum part : args) {
            if(!part.toString().equals("")){
                System.out.println(part);
                url += "?" + part.toString();
            }
            else {
                continue;
            }
        }

        if (API_KEY == null) {
            return "";
        } else {
            url += API_KEY;
        }

        return url;
    }

    public String runRequest(String url) {
        try {
            if (Objects.equals(url, "")) {
                throw new UrlException();
            }
        } catch (UrlException e) {
            System.out.println(e.getMessage());
            return "";
        }
        try {
            //Custom Exception - check if URL contains an Endpoint (Top Headlines or Everything)
            if (!NewsApiChecks.checkURL(url)) {
                throw new UrlException();
            }
            if (!NewsApiChecks.netIsAvailable()) {
                throw new NoWifiException();
            } else {
                System.out.println(url);
                return getRequest(url);
            }
        } catch (NoWifiException | UrlException | IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }


    public void downladAnArticle(String url, Article a) throws IOException {
        URL urlReal = new URL(url);
        String input;
        BufferedReader in = new BufferedReader(new InputStreamReader(urlReal.openStream()));
        String filename = a.getTitle() + " " + LocalDateTime.now().format(ISO_LOCAL_DATE) +".html";

        final File parentDir = new File("download");
        parentDir.mkdir();
        final File file = new File(parentDir, filename);
        String path = file.getAbsolutePath();
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

        while ((input = in.readLine()) != null) {
            writer.append((input));
        }
        in.close();
        writer.close();
    }

    /**
     * This method handles Get request and should be called from the response function
     *
     * @param url the url from the Response function
     * @return the message body to string from the http request
     * @throws IOException
     */
    private String getRequest(String url) throws IOException {

        // initiates the http client
        var client = new OkHttpClient();

        // uses the Request class form ok http to set up a request
        Request request = new Request.Builder().url(url).build();

        // uses the Response class to catch the response after request
        // surrounded with try and catches to ensure the function keeps working, even if the Response fails
        try (Response response = client.newCall(request).execute()) {

            //returns the body of the http request and uses okhttp library to parse it to
            /*gson.fromJson(response.body().string(), NewsResponse.class);*/
            String responseString = response.body().string();

            //Custom Exception - check if response status is ok

            if (!HttpException.checkStatus(responseString)) {
                throw new HttpException();
            } else {
                return responseString;
            }

        } catch (IOException| HttpException e) {
            System.out.println(e.getMessage());
        }
        // just the last Backup return if nothing works
        return "no respones";
    }

}
