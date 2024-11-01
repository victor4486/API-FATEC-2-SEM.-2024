package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CriteriosController {

    @FXML
    private Button adicionarSprintButton;

    @FXML
    private Button btnCriarCriterio;

    @FXML
    private Button btnSairCriterios;

    @FXML
    private VBox sprintContainer; // VBox onde as Sprints serão adicionadas

    @FXML
    private ScrollPane scrollCriteriosCriados;

    @FXML
    private Pane campoParaCriterio;

    @FXML
    private Pane campoParaCriterio1;

    @FXML
    private Pane campoParaCriterio11;

    @FXML
    private Pane campoParaCriterio12;

    @FXML
    private VBox sprintVBox; // VBox que contém o botão Adicionar Sprint

    @FXML
    private AnchorPane campo_criterios;// ScrollPane para exibir os critérios



    private int sprintCount = 0; // Contador para o número de sprints

    @FXML
    private void handleAdicionarSprint() {
        sprintCount++; // Incrementa o contador
    }

    @FXML
    private TextField tituloCritério; // TextField para o título do critério

    @FXML
    private TextField descricaoCritério; // TextField para a descrição do critério

    @FXML
    private void handleAdicionarCriterio() {
        String titulo = tituloCritério.getText();
        String descricao = descricaoCritério.getText();
        //caso campo vazio
        if (titulo.isEmpty() || descricao.isEmpty()) {
            System.out.println("Os campos de título e descrição não podem estar vazios.");

            return;
        }

        //adicionar ao bd
        CriterioDao criterioDao = new CriterioDao();
        criterioDao.inserirCriterio(titulo, descricao);

        //criação do campo de exibição do critério
        Pane novoCriterio = new Pane();
        novoCriterio.setPrefSize(445, 50);
        novoCriterio.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 4;");
        novoCriterio.setLayoutX(4);
        novoCriterio.setLayoutY(4);

        // Adicionando o título e a descrição no critério
        Label tituloLabel = new Label("Título:   " + titulo);
        tituloLabel.setLayoutX(10);
        tituloLabel.setLayoutY(10);
        tituloLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        Label descricaoLabel = new Label("Descrição: " + descricao);
        descricaoLabel.setLayoutX(10);
        descricaoLabel.setLayoutY(30);
        descricaoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        novoCriterio.getChildren().addAll(tituloLabel, descricaoLabel);

        // Verifica quantos critérios já existem no campo_criterios
        int numeroDeCriterios = campo_criterios.getChildren().size();

        // Calcula a posição Y para o novo critério
        double novaPosicaoY = numeroDeCriterios == 0 ? 0 : numeroDeCriterios * 58;

        // Define a posição do novo critério
        novoCriterio.setLayoutY(novaPosicaoY);

        // Adiciona o novo critério ao AnchorPane (campo_criterios)
        campo_criterios.getChildren().add(novoCriterio);

        // Limpa os campos de texto para adicionar novos critérios
        tituloCritério.clear();
        descricaoCritério.clear();
    }
    @FXML
    public void handleSair(ActionEvent actionEvent) throws IOException {
        Main.setRoot("TelaMenu-view");
    }

    @FXML
    void handleAcompSprint(ActionEvent event) throws IOException {
        Main.setRoot("acompanharSprints-view");
    }
}
