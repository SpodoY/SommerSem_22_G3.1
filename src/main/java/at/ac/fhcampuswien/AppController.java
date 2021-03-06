package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsApi;
import at.ac.fhcampuswien.apiHandling.NewsResponse;
import at.ac.fhcampuswien.enums.Category;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import at.ac.fhcampuswien.enums.Keywords;
import at.ac.fhcampuswien.gui.NewsLoadingController;
import com.google.gson.Gson;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AppController {

    // the Datastructures for containing all our Articles
    private static final List<Article> articles = new ArrayList<>();
    private static final NewsApi newsApi = new NewsApi();
    private static final Gson gsonParser = new Gson();

    public static NewsResponse answer(String json) {
        NewsResponse data = gsonParser.fromJson(json, NewsResponse.class);
        return data;
    }

    public AppController() {
    }

    public static void setArticles(List<Article> newArticles) {
        // clears list for new usage
        articles.clear();
        articles.addAll(newArticles);
    }

    public static List<Article> passCustomeNewsString(List<Enum> params) {
        try {
            Enum[] allParams = params.toArray(new Enum[0]);
            Enum[] withoutFirst = IntStream.range(1, allParams.length).mapToObj(i -> allParams[i]).toArray(Enum[]::new);

            NewsResponse custom = answer(newsApi.runRequest(newsApi.urlBuilder(params.get(0), withoutFirst)));
            setArticles(custom.getArticles());
            return custom.getArticles();
        }catch (Exception e){
            List<Article> errorList = new ArrayList<>();
            errorList.add(new Article(new Source("1", "one"), "Your News App Team", "No articles match your Requirements", "null", "null", null, "", "null"));
            return errorList;
        }
    }

    public int getArticleCount() {
        return this.articles.size();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria() {
        try {
            NewsResponse austria = answer(newsApi.runRequest(newsApi.urlBuilder(Endpoint.TOP_HEADLINES, Country.AT)));
            setArticles(austria.getArticles());
            return austria.getArticles();
        } catch (Exception e) {
            return new ArrayList<Article>();
        }
    }

    //gets all the articles with the query "bitcoin" and returns the new list
    public List<Article> getAllNewsBitcoin() {
        try {
            NewsResponse bitcoin = answer(newsApi.runRequest(newsApi.urlBuilder(Endpoint.EVERYTHING, Category.BITCOIN)));
            setArticles(bitcoin.getArticles());
            return bitcoin.getArticles();
        } catch (Exception e) {
            return new ArrayList<Article>();
        }
    }

    public List<Article> headLessThan15() {
        //TODO: Check if List Size > 0
        return articles.stream().filter(a -> a.getTitle().length() < 15).collect(Collectors.toList());
    }

    public List<Article> sortByLengthDescending() {
        return articles.stream().sorted().collect(Collectors.toList());
    }


    public String authorLength() {
        return articles.stream()
                .sorted(Comparator.comparing(Article::getAuthorLength).reversed()).toList().get(0).getAuthor();
    }

    public List<Article> guiAuthorLength(){
        return articles.stream().filter(e->e.getAuthorLength() == authorLength().length()).collect(Collectors.toList());
    }

    public int sourceNewYorkTimes() {
        return (int) articles.stream().filter(e -> e.getSource().getName().contains("New York Times")).count();
    }

    public List<Article> guiSourceNewYorkTimes(){
        return articles.stream().filter(e->e.getAuthor().contains("New York Times")).collect(Collectors.toList());
    }



    public String sourceMostArticles() {
        return articles.stream().map(Article::getSourceName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Article> guiSourceMostArticles(){
        return articles.stream().filter(e->e.getSourceName().contains(sourceMostArticles())).collect(Collectors.toList());
    }

    public List<Article> errorList() {
        List<Article> errorList = new ArrayList<>();
        errorList.add(new Article(new Source("1", "one"), "Your News App Team", "No articles match your Requirements", "null", "null", null, "", "null"));
        return errorList;
        }

    public void saveHTML(Article a) throws IOException {
        String url = a.getUrl();
        try {
            newsApi.downladAnArticle(url, a);
        } catch (IOException e) {
            System.out.println(url);
            System.out.println("Invalid url created check arguments");
        } catch (Exception e) {
            System.out.println("Oh no! We did a big fucky wucky! It's time to get in the forever-box owo");
        }
    }
}
