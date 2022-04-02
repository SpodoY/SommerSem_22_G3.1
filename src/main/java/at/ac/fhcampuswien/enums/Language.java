package at.ac.fhcampuswien.enums;

public enum Language {
    AR ("language=ar"),
    DE ("language=de"),
    EN ("language=en"),
    ES ("language=es"),
    FR ("language=fr"),
    HE ("language=he"),
    IT ("language=it"),
    NL ("language=nl"),
    NO ("language=no"),
    PT ("language=pt"),
    RU ("language=ru"),
    SE ("language=se"),
    UD ("language=ud"),
    ZH ("language=zh");

    private String partOfUrl;

    Language(final String partOfUrl){
        this.partOfUrl = partOfUrl;
    }

    @Override
    public String toString() {
        return this.partOfUrl;
    }
}
