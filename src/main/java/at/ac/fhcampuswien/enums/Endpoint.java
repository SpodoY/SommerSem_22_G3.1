package at.ac.fhcampuswien.enums;

/**
 * Use Endpoint:
 * - everything in combination with language and sortBy
 * - top-headlines in combination with country + category
 */
public enum Endpoint {
    EVERYTHING ("everything"),
    TOP_HEADLINES ("top-headlines");

    private String partOfUrl;

    Endpoint(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    public String getPartOfUrl() {
        return this.partOfUrl;
    }

}
