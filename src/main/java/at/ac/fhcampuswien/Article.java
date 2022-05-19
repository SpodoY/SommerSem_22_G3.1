package at.ac.fhcampuswien;

public class Article implements Comparable<Article> {
    private Source source;
    private String author;
    private final String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Article(Source source,String author, String title, String description, String url, String urlToImage,
                   String publishedAt, String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorLength(){
        if (author==null) return 0;
        else return author.length();
    }

    public String getTitle() {
        return title;
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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    // overriding to string to get a nice output
    public String toString() {
        return String.format("Title: %s, Author: %s%n", title, author);
    }


    public String getDescription() {
        if (description==null){return description="";}
        else return description;
    }

    public Source getSource() {
        return source;
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

