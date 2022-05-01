package at.ac.fhcampuswien;

public class Article implements Comparable<Article> {
    private String author;
    private String title;
    private String name;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;


    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Article(String author, String title, String name, String description, String url, String urlToImage,
                   String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.name = name;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    @Override
    // overriding to string to get a nice output
    public String toString() {
        return String.format("Title: %s, Author: %s%n", title, author);
    }


    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Article a) {
        if (getDescription().length() == a.getDescription().length()) {
            return String.CASE_INSENSITIVE_ORDER.compare(getTitle(), a.getTitle());
        } else {
            return Integer.compare(a.getDescription().length(), getDescription().length());
        }
    }
}

