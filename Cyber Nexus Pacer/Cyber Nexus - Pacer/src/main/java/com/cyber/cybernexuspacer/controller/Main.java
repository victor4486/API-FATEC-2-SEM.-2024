package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.ConexaoDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //ConexaoDao.testConnection();
        // Carregar o arquivo FXML corretamente
        scene = new Scene(loadFXML("pontuacaoGrupos-view"), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    //Metodo para carregar o arquivo FXML e garantir que retorna um Parent
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/cyber/cybernexuspacer/fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();  // Isso garante que o nó raiz é do tipo Parent
    }

    public static void main(String[] args) {
        launch(args);
    }
}
