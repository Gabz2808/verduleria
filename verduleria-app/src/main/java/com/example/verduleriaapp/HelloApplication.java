package com.example.verduleriaapp;

import DB.ConexionOracle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    private ConexionOracle conexionOracle;

    @Override
    public void start(Stage stage) throws IOException {
        conexionOracle = new ConexionOracle();
        conexionOracle.conectar(); // Llamar al método público

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Asegurarse de cerrar la conexión al salir
        if (conexionOracle != null) {
            conexionOracle.cerrarConexion();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
