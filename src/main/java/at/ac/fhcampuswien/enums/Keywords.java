package at.ac.fhcampuswien.enums;

public enum Keywords {

    CRYPTO("q=crypto"),
    APPLE("q=apple"),
    SAMSUNG("q=samsung"),
    TESLA("q=tesla"),
    STOCKS("q=stocks"),
    HUAWEI("q=huawei"),
    TWITTER("q=twitter"),
    AMAZON("q=amazon"),
    GOOGEL("q=googel"),
    NONE("");


    private final String partOfUrl;

    Keywords (final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    @Override
    public String toString() {
        return this.partOfUrl;
    }

}
