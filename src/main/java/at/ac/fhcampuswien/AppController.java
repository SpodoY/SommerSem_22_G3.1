package at.ac.fhcampuswien;

import java.util.ArrayList;
import java.util.List;

public class AppController {

    // a private list for storing intermediary Data
    private static List<Article> intermediary = new ArrayList<>();

    // the Datastructures for containing all our Articles
    private List<Article> articles = new ArrayList<>();

    public AppController() {
        setArticles(generateMockList());
    }

    /**
     * adds an article to the passed threw list if the title and author aren't null
     * @param articles an array list generically binded to the locally created article class
     */
    public void setArticles(List<Article> articles) {
        // clears list for new usage
        this.articles.clear();
        for (Article a : articles) {
            if (!(a.getAuthor() == null || a.getTitle() == null)) this.articles.add(a);
        }
    }

    /**
     *  returns the size of the local article list
     * @return
     */
    public int getArticleCount() {
        return this.articles.size();
    }

    /**
     * returns the article list
     * @return
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * this function returns the passed threw list if the size is bigger than zero else returns a new empty list
     * @return
     */
    public List<Article> getTopHeadlinesAustria() {
        //filters austrian articles - word "austria" in Author and Title - didn't use filterList() because only article's title is filtered
        List<Article> listFiltered = new ArrayList<>();
        String query = "austria";

        for (Article a : articles) {
            if (a.toString().toLowerCase().contains(query)) listFiltered.add(a);
        }

        if (listFiltered.size() == 0) {
            //if no article from Austria - return empty List
            return new ArrayList<>();
        } else {
            //return filtered List
            return listFiltered;
        }
    }

    /**
     * fills the complete list with mock data
     * @return the filled list
     */
    private static List<Article> generateMockList() {
        // generate a new list for return Type
        List<Article> controlList = new ArrayList<>();

        //Mock data insertion
        controlList.add(new Article("Waschbärenverlang", "Die wunderbaren Reisen von Thommy"));
        controlList.add(new Article("Blechdach", "Maxl - Far from home"));
        controlList.add(new Article("Mathe Main", "Mathe - Leid für die ganze Familie"));
        controlList.add(new Article("Audiophile", "Top 10 Headsets in 2022"));
        controlList.add(new Article("Bildungsresistenz Main", "Top 20 schlechteste Lolmates in 2022"));
        controlList.add(new Article("BitCoin Jimbo", "These Bitcoins be hot as f***"));
        controlList.add(new Article("BitCoin Jimbo", "BitCoin is dropping, here is why"));
        controlList.add(new Article("AustrianLover007", "Top 10 Places to visit Austria"));
        controlList.add(new Article("AustrianLover007", "Country Road, take me to Austria"));
        controlList.add(new Article(null, null));
        controlList.add(new Article("Tacocat", null));
        controlList.add(new Article(null, "Pls no kill"));

        // returning the filled list
        return controlList;
    }

    /**
     * filters the list based on a passed query
     * @param query the keyWord for the search
     * @param articles the list to filter
     * @return the new filtered list
     */
    protected static List<Article> filterList(String query, List<Article> articles) {
        //we don´t want case-sensitive query's ->everything to lower case

        String toLower = query.toLowerCase();

        //a intermediary list which gets cleared everytime the function is called so we can reuse it
        intermediary.clear();

        //if the query is empty we return an empty list

        if (toLower.equals("")) return intermediary;

        //a for-each loop which gets every avaiable article
        for (Article a: articles) {

            //checks if a title of an article (in lower case) contains our query. if it does it adds the article to our intermediary list
            if (a.getTitle().toLowerCase().contains(toLower)) intermediary.add(a);

        }

        return intermediary;
    }

    public List<Article> getAllNewsBitcoin() {

        //gets all the articles with the query "bitcoin" and returns the new list
        return filterList("bitcoin",articles);                                                                         }
}
