package at.ac.fhcampuswien.enums;

public enum Sortby {
    //articles more closely related to q come first.
    RELEVANCY ("sortBy=relevancy"),
    //articles from popular sources and publishers come first.
    POPULARITY ("sortBy=popularity"),
    //newest articles come first.
    PUBLISHED_AT ("sortBy=publishedAt");

    private String partOfUrl;

    Sortby(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    @Override
    public String toString() {
        return this.partOfUrl;
    }
}
