package at.ac.fhcampuswien.apiHandling;

import okhttp3.*;


public class NewsApi {
    private String baseQuery = "https://newsapi.org/v2/";
    private String topHeadlines ="top-headlines";
    private String everything = "everything";

    private void testResponse () {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
    }

}
