module at.ac.fhcampuswien {
    requires javafx.controls;
    requires javafx.fxml;

    exports at.ac.fhcampuswien;
    exports at.ac.fhcampuswien.gui;
    opens at.ac.fhcampuswien;
    opens at.ac.fhcampuswien.gui;
    exports at.ac.fhcampuswien.apiHandling;
    opens at.ac.fhcampuswien.apiHandling;
}