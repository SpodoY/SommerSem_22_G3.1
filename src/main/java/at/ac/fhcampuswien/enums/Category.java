package at.ac.fhcampuswien.enums;

public enum Category {
    BITCOIN ("q=bitcoin"),
    BUSINESS ("q=business"),
    ENTERTAINMENT ("q=entertainment"),
    GENERAL ("q=general"),
    HEALTH ("q=health"),
    SCIENCE ("q=science"),
    SPORTS ("q=sports"),
    TECHNOLOGY ("q=technology"),
    NONE("");

    private final String partOfUrl;

    Category(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    @Override
    public String toString() {
        return this.partOfUrl;
    }
}
