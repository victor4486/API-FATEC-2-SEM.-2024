package com.cyber.cybernexuspacer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class CadastroDeTurmaController {

    @FXML
    private Button button_carregarDocumento;

    @FXML
    private Button button_confirmarAlunos;

    @FXML
    private Button button_sair;

    @FXML
    private Label label_cadastrodeturma;

    @FXML
    private Label label_olaProfessor;

    @FXML
    private Label label_top;

    @FXML
    private Pane pane_planilha;

    @FXML
    private ScrollBar scrollBar;

    @FXML

    public void carregarPlanilha(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        File selectFile = fileChooser.showOpenDialog(button_carregarDocumento.getScene().getWindow());

    }

}





