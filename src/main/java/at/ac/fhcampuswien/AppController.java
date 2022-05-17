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
        sortByLengthDescending();
        return this.articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria() {
        try {
            NewsResponse austria = answer(newsApi.urlBuilder(Endpoint.TOP_HEADLINES, Country.AT));
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
            NewsResponse bitcoin = answer(newsApi.urlBuilder(Endpoint.EVERYTHING, Category.BITCOIN));
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
        intermediary.clear();
        intermediary = articles.stream().filter(a -> a.getTitle().length() < 15).collect(Collectors.toList());
        return intermediary;
    }

    public List<Article> sortByLengthDescending() {
        intermediary.clear();

        intermediary = articles.stream().sorted((Article a, Article b) -> {
            try {
                if (a.getDescription().length() == b.getDescription().length()) {
                    return String.CASE_INSENSITIVE_ORDER.compare(a.getTitle(), b.getTitle());
                } else {
                    return Integer.compare(b.getDescription().length(), a.getDescription().length());
                }
            } catch (NullPointerException e) {
                if (a.getDescription() == null) {
                    a.setDescription("Sorry there is no description for this one");
                    return Integer.compare(b.getDescription().length(), a.getDescription().length());
                } else if (b.getDescription() == null) {
                    b.setDescription("Sorry there is no description for that article");
                    return Integer.compare(b.getDescription().length(), a.getDescription().length());
                }
            }
            return Integer.compare(b.getDescription().length(), a.getDescription().length());
        }).collect(Collectors.toList());
        return intermediary;
    }

    public void saveHTML(Article a) throws IOException {
        String url = a.getUrl();
        try {
            newsApi.urlFixed(url);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(url);
            System.out.println("Invalid url created check arguments");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String authorLength(List<Article> articles) {
        return articles.stream().sorted(Comparator.comparing(Article::getAuthorLength)).collect(Collectors.toList()).get(0).getAuthor();
    }

    public int sourceNewYorkTimes(List<Article> articles) {
        return (int) articles.stream().filter(e -> e.getPublishedAt().contains("New York Times")).count();
    }


    public String sourceMostArticles(List<Article> articles) {
        return articles.stream().map(Article::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);

    }
}
