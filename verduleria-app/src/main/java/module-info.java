module com.example.verduleriaapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.verduleriaapp to javafx.fxml;
    exports com.example.verduleriaapp;
}