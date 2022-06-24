package at.ac.fhcampuswien;

public class Article implements Comparable<Article> {
    private final Source source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final String publishedAt;
    private final String content;

    private Article(Builder b) {
        this.source = b.source;
        this.author = b.author;
        this.title = b.title;
        this.description = b.description;
        this.url = b.url;
        this.urlToImage = b.urlToImage;
        this.publishedAt = b.publishedAt;
        this.content = b.content;
    }

    public String getAuthor() {
        if (author == null) return "Not found";
        return author;
    }

    public int getAuthorLength() {
        if (author == null) return 0;
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

    @Override
    // overriding to string to get a nice output
    public String toString() {
        return String.format("Title: %s, Author: %s, Description: %s%n", title, author, description);
    }

    public Source getSource() {
        return source;
    }

    public String getSourceName() {
        return source.getName();
    }

    private int getLength() {
        return description != null ? description.length() : 0;
    }

    @Override
    public int compareTo(Article a) {
        if (getLength() == a.getLength()) {
            return String.CASE_INSENSITIVE_ORDER.compare(getTitle(), a.getTitle());
        } else {
            return Integer.compare(a.getLength(), getLength());
        }
    }

    public static class Builder {
        private Source source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;

        public Builder(String author, String title, String description, String url, String publishedAt) {
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.publishedAt = publishedAt;
        }

        public Builder source(Source source) {
            this.source = source;
            return this;
        }

        public Builder urlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }
    /* Example of creating an article with the builder

    Article a = new Article.Builder("Thomas Winter", "In einem Design Pattern vor unserer Zeit",
            "Ein Beispiel für's erstellen eines Builder Articles", "http://google.com", "24.02.2022").build();

     */

}

