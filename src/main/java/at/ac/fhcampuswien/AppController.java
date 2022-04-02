package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsApi;
import at.ac.fhcampuswien.apiHandling.NewsResponse;
import at.ac.fhcampuswien.enums.Category;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private static List<Article> intermediary = new ArrayList<>();

    // the Datastructures for containing all our Articles
    private List<Article> articles = new ArrayList<>();
    private static NewsApi newsApi = new NewsApi();
    private Gson gsonParser = new Gson();

    public NewsResponse answer(String json) {
        NewsResponse data = gsonParser.fromJson(json, NewsResponse.class);
        return data;
    }

    public AppController() {
    }


    public void setArticles(List<Article> articles) {
        // clears list for new usage
        this.articles.clear();
        this.articles.addAll(articles);
    }

    public int getArticleCount() {
        return this.articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria() {
        NewsResponse austria = answer(newsApi.urlBuilder(Endpoint.TOP_HEADLINES.getPartOfUrl(), Country.AT.getPartOfUrl()));
        setArticles(austria.getArticles());
        return austria.getArticles();
    }

    public List<Article> getAllNewsBitcoin() {
        NewsResponse bitcoin = answer(newsApi.urlBuilder(Endpoint.EVERYTHING.getPartOfUrl(),Category.BITCOIN.getPartOfUrl()));
        setArticles(bitcoin.getArticles());
        return bitcoin.getArticles();                                                                     //gets all the articles with the query "bitcoin" and returns the new list
    }

    protected static List<Article> filterList(String query, List<Article> articles) {
        String toLower = query.toLowerCase();                                                                           //we don´t want case sensitive querys ->everything to lower case
        intermediary.clear();                                                                                           //a intermediary list which gets cleared everytime the function is called so we can reuse it
        if (toLower.equals(""))
            return intermediary;                                                                                        //if the query is empty we return a empty list (duuhhhh)
        for (Article a : articles) {                                                                                    //a for-each loop which gets every avaiable article
            if (a.getTitle().toLowerCase().contains(toLower))
                intermediary.add(a);                                                                                    //checks if a title of an article (in lower case) contains our query. if it dooes it adds the article to our intermediary list
        }
        return intermediary;
    }


}
