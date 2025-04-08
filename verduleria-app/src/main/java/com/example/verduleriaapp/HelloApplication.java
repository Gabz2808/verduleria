package com.example.verduleriaapp;

import com.example.verduleriaapp.controllers.LoginController;
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

        conexionOracle = new ConexionOracle();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        fxmlLoader.setControllerFactory(param -> {
            if (param == LoginController.class) {
                return new LoginController(conexionOracle.getConnection());
            }
            try {
                return param.getDeclaredConstructor().newInstance(); // fallback
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.setTitle("Verdulería");
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
        if (conexionOracle != null) {
            conexionOracle.cerrarConexion();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}