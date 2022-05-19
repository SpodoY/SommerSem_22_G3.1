package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsApi;
import at.ac.fhcampuswien.apiHandling.NewsResponse;
import at.ac.fhcampuswien.enums.Category;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppController {

    private static List<Article> intermediary = new ArrayList<>();

    // the Datastructures for containing all our Articles
    private final List<Article> articles = new ArrayList<>();
    private static final NewsApi newsApi = new NewsApi();
    private final Gson gsonParser = new Gson();

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
        try {
            NewsResponse austria = answer(newsApi.runRequest( newsApi.urlBuilder(Endpoint.TOP_HEADLINES, Country.AT)));
            setArticles(austria.getArticles());
            return austria.getArticles();
        } catch (Exception e) {
            System.out.println("Couldn't get articles ");
            return new ArrayList<Article>();
        }
    }

    //gets all the articles with the query "bitcoin" and returns the new list
    public List<Article> getAllNewsBitcoin() {
        try {
            NewsResponse bitcoin = answer(newsApi.runRequest( newsApi.urlBuilder(Endpoint.EVERYTHING, Category.BITCOIN)));
            setArticles(bitcoin.getArticles());
            return bitcoin.getArticles();
        } catch (Exception e) {
            System.out.println("Couldn't get articles ");
            return new ArrayList<Article>();
        }
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

    public List<Article> headLessThan15() {
        return articles.stream().filter(a -> a.getTitle().length() < 15).collect(Collectors.toList());
    }

    public List<Article> sortByLengthDescending() {
        return articles.stream().sorted().collect(Collectors.toList());
    }

    public void saveHTML(Article a) throws IOException {
        String url = a.getUrl();
        try {
            newsApi.downladAnArticle(url, a);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(url);
            System.out.println("Invalid url created check arguments");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String authorLength(List<Article> articles) {
        return articles.stream()
                .sorted(Comparator
                        .comparing(Article::getAuthorLength))
                .collect(Collectors.toList())
                .get(articles.size()-1)
                .getAuthor();
    }
    public int sourceNewYorkTimes(List<Article> articles) {
        return (int) articles.stream().filter(e -> e.getSource().getName().contains("New York Times")).count();
    }

    //Needs a new implementation Sourcename is now articles.getSource.getName
    /*public String sourceMostArticles(List<Article> articles) {
        return articles.stream().map(Article::getAuthor)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);
    }*/
}
