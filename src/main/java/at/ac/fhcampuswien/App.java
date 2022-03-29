package at.ac.fhcampuswien;

import at.ac.fhcampuswien.apiHandling.NewsApi;

public class App {

    public static void main(String[] args) {
        // launches application
        Menu menu = new Menu();
        menu.start();
        System.out.println("Tacocat");

        var news = new NewsApi();
        String test = news.Response("everything","keyword");
        System.out.println(test);
    }

}
