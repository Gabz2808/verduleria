module com.example.verduleriaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires com.oracle.database.jdbc;

    opens com.example.verduleriaapp to javafx.fxml;
    exports com.example.verduleriaapp;
    exports com.example.verduleriaapp.controllers;
    exports com.example.verduleriaapp.models;
    opens com.example.verduleriaapp.models to javafx.base, javafx.fxml;
    opens com.example.verduleriaapp.controllers to javafx.base, javafx.fxml;
}