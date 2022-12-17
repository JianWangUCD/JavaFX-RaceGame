module com.example.comp20300javafxproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    //requires org.junit.jupiter;//

    opens com.example.comp20300javafxproject to javafx.fxml;
    exports com.example.comp20300javafxproject;
}