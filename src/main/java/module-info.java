module at.ac.fhcampuswien {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;

    exports at.ac.fhcampuswien;
    exports at.ac.fhcampuswien.gui;
    opens at.ac.fhcampuswien;
    opens at.ac.fhcampuswien.gui to javafx.fxml;
    exports at.ac.fhcampuswien.apiHandling;
    opens at.ac.fhcampuswien.apiHandling;
    exports at.ac.fhcampuswien.exceptions;
    opens at.ac.fhcampuswien.exceptions;
}