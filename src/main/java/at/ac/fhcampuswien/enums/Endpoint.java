package at.ac.fhcampuswien.enums;

/**
 * Use Endpoint:
 * - everything in combination with language and sortBy
 * - top-headlines in combination with country + category
 */
public enum Endpoint {
    EVERYTHING ("everything"),
    TOP_HEADLINES ("top-headlines"),
    NONE("");

    private final String partOfUrl;

    Endpoint(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    @Override
    public String toString() {
        return this.partOfUrl;
    }
}
