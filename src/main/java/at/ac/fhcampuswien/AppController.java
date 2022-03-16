package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    private static List<Article> intermediary = new ArrayList<>();

    // the Datastructures for containing all our Articles
    private List<Article> articles = new ArrayList<>();

    public AppController() {
        setArticles(generateMockList());
    }

    public void setArticles(List<Article> articles) {
        // clears list for new usage
        this.articles.clear();
        for (Article a : articles) {
            if (!(a.getAuthor() == null || a.getTitle() == null)) this.articles.add(a);
        }
    }

    public int getArticleCount(){return  0;}

    public List<Article> getArticles() {
        return articles;
    }

    public List<Article> getTopHeadlinesAustria(){
        //filters austrian articles - word "austria" in Author and Title - didn't use filterList() because only article's title is filtered
        List<Article> listFiltered = new ArrayList<>();
        String query = "austria";

        for (Article a: articles) {
            if (a.toString().toLowerCase().contains(query)) listFiltered.add(a);
        }

        if(listFiltered.size() == 0) {
            //if no article from Austria - return empty List
            return new ArrayList<>();
        } else {
            //return filtered List
            return listFiltered;
        }
    }

    public List<Article> getAllNewsBitcoin() {
        return filterList("bitcoin",articles);

    }

    protected static List<Article> filterList(String query, List<Article> articles) {
        String toLower = query.toLowerCase();
        intermediary.clear();
        if (toLower.equals("")) return intermediary;
        for (Article a: articles) {
            if (a.getTitle().toLowerCase().contains(toLower)) intermediary.add(a);
        } return intermediary;
    }


}
