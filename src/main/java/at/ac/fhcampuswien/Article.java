package at.ac.fhcampuswien;

public class Article {
    private String author;
    private String title;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    // overriding to string to get a nice output
    public String toString() {
        return String.format("Title: %s, Author: %s%n", title, author);
    }



}
