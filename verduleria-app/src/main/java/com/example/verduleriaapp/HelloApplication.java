package com.example.verduleriaapp;

import com.example.verduleriaapp.utils.ConexionOracle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;

public class HelloApplication extends Application {
    private ConexionOracle conexionOracle;

    @Override
    public void start(Stage stage) throws IOException {
        // Inicializar conexión a la base de datos
        conexionOracle = new ConexionOracle();
        conexionOracle.conectar();

        // Cargar FXML
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Establecer tamaño fijo

        stage.setTitle("Verdulería");

        // Cargar ícono
        InputStream iconStream = getClass().getResourceAsStream("/assets/fruits.png");
        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        } else {
            System.out.println("❌ No se encontró el archivo de icono.");
        }

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        // Cerrar conexión al salir
        if (conexionOracle != null) {
            conexionOracle.cerrarConexion();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
