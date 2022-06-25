package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsApi;
import at.ac.fhcampuswien.apiHandling.NewsResponse;
import at.ac.fhcampuswien.apiHandling.PopUp;
import at.ac.fhcampuswien.downloader.Downloader;
import at.ac.fhcampuswien.enums.Category;
import at.ac.fhcampuswien.enums.Country;
import at.ac.fhcampuswien.enums.Endpoint;
import at.ac.fhcampuswien.exceptions.NewsAPIExceptionLeo;
import com.google.gson.Gson;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;

public class SingletonAppController {
    // the Datastructures for containing all our Articles


    // The field must be declared volatile so that double check lock would work correctly.
    // volatile -> everytime the variable is accessed,it is read directly from the main memory
    // Singleton - only one instance is created
    private static volatile SingletonAppController instance = null;

    private static final List<Article> articles = new ArrayList<>();
    private static final NewsApi newsApi = new NewsApi();
    private static final Gson gsonParser = new Gson();
    public static NewsResponse answer(String json) {
        NewsResponse data = gsonParser.fromJson(json, NewsResponse.class);
        return data;
    }

    //constructor to create an object - private!!!!
    private SingletonAppController() {
    }

    public static SingletonAppController getInstance() {
        // The approach taken here is called double-checked locking (DCL). It
        // exists to prevent race condition between multiple threads that may
        // attempt to get singleton instance at the same time, creating separate
        // instances as a result.
        //
        // It may seem that having the `result` variable here is completely
        // pointless. There is, however, a very important caveat when
        // implementing double-checked locking in Java, which is solved by
        // introducing this local variable.
        //
        // You can read more info DCL issues in Java here:
        // https://refactoring.guru/java-dcl-issue


        SingletonAppController result = instance;
        //even if the instance was created, every thread has to wait before returning it
        //-> therefore extra if (instance != null) immediately return instance/result
        if (result != null) {
            return result;
        }
        synchronized(SingletonAppController.class) {
            if (instance == null) {
                instance = new SingletonAppController();
            }
            return instance;
        }
    }


    public static void setArticles(List<Article> newArticles) {
        // clears list for new usage
        if (newArticles.size() == 0) PopUp.createAlert("Your handed query has no results");
        articles.clear();
        articles.addAll(newArticles);
    }

    public static void passCustomeNewsString(List<Enum> params) {
        try {
            Enum[] allParams = params.toArray(new Enum[0]);
            Enum[] withoutFirst = IntStream.range(1, allParams.length).mapToObj(i -> allParams[i]).toArray(Enum[]::new);

            NewsResponse custom = answer(newsApi.checkIfAllConditionsAreFulfilled(newsApi.urlAssembly(params.get(0), withoutFirst)));
            setArticles(custom.getArticles());
            if (articles.size() == 0) PopUp.createAlert("Your handed query has no results");
        } catch (Exception e) {
            PopUp.createAlert("Your handed query has no results");
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
            NewsResponse austria = answer(newsApi.runRequest(newsApi.urlAssembly(Endpoint.TOP_HEADLINES, Country.AT)));
            setArticles(austria.getArticles());
            return austria.getArticles();
        } catch (Exception e) {
            return new ArrayList<Article>();
        }
    }

    //gets all the articles with the query "bitcoin" and returns the new list
    public List<Article> getAllNewsBitcoin() {
        try {
            NewsResponse bitcoin = answer(newsApi.runRequest(newsApi.urlAssembly(Endpoint.EVERYTHING, Category.BITCOIN)));
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

    public List<Article> guiAuthorLength() {
        return articles.stream().filter(e -> e.getAuthorLength() == authorLength().length()).collect(Collectors.toList());
    }

    public int sourceNewYorkTimes() {
        return (int) articles.stream().filter(e -> e.getSource().getName().contains("New York Times")).count();
    }

    public List<Article> guiSourceNewYorkTimes() {
        return articles.stream().filter(e -> e.getSource().getName().contains("New York Times")).collect(Collectors.toList());
    }

    public String sourceMostArticles() {
        return articles.stream()
                .filter(a -> !a.getAuthor().equals("Not found"))
                .map(Article::getSourceName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue()).map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<Article> guiSourceMostArticles() {
        return articles.stream().filter(e -> e.getSourceName().contains(sourceMostArticles())).collect(Collectors.toList());
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

    public int downloadURLs(Downloader downloader) throws NewsAPIExceptionLeo {
        if (articles == null) {
            throw new NewsAPIExceptionLeo();
        }

        List<String> urls = new ArrayList<>();

        // TODO extract urls from articles with java stream
        articles.forEach(e->{
            urls.add(e.getUrlToImage());
            urls.add(e.getUrl());

            //Christna added this:
            String sentUrlImage = e.getUrlToImage();
            String sentUrl = e.getUrl();
            System.out.println(sentUrlImage + sentUrl);

        });
        return downloader.process(urls);
    }

}
