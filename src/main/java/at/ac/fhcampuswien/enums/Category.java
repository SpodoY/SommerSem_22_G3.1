package at.ac.fhcampuswien.enums;

public enum Category {
    //TODO: is bitcoin a category?? what about the other categories - delete them???
    BITCOIN ("q=bitcoin"),
    BUSINESS ("q=business"),
    ENTERTAINMENT ("q=entertainment"),
    GENERAL ("q=general"),
    HEALTH ("q=health"),
    SCIENCE ("q=science"),
    SPORTS ("q=sports"),
    TECHNOLOGY ("q=technology");

    private String partOfUrl;

    Category(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    public String getPartOfUrl() {
        return this.partOfUrl;
    }

}
