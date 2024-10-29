package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.entity.Criterio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CriteriosController {

    @FXML
    private Button BtnDeleteSprint;

    @FXML
    private Button BtnDeleteSprint1;

    @FXML
    private Button BtnDeleteSprint11;

    @FXML
    private Button BtnDeleteSprint111;

    @FXML
    private Button BtnDeleteSprint1111;

    @FXML
    private DatePicker DataPickerFIM;

    @FXML
    private DatePicker DataPickerFIM1;

    @FXML
    private DatePicker DataPickerFIM11;

    @FXML
    private DatePicker DataPickerFIM111;

    @FXML
    private DatePicker DataPickerFIM1111;

    @FXML
    private DatePicker DataPickerINICIO;

    @FXML
    private DatePicker DataPickerINICIO1;

    @FXML
    private DatePicker DataPickerINICIO11;

    @FXML
    private DatePicker DataPickerINICIO111;

    @FXML
    private DatePicker DataPickerINICIO1111;

    @FXML
    private Label LblTxtFIM;

    @FXML
    private Label LblTxtFIM1;

    @FXML
    private Label LblTxtFIM11;

    @FXML
    private Label LblTxtFIM111;

    @FXML
    private Label LblTxtFIM1111;

    @FXML
    private Label LblTxtINICIO;

    @FXML
    private Label LblTxtINICIO1;

    @FXML
    private Label LblTxtINICIO11;

    @FXML
    private Label LblTxtINICIO111;

    @FXML
    private Label LblTxtINICIO1111;

    @FXML
    private Label LblTxtSprint;

    @FXML
    private Label LblTxtSprint1;

    @FXML
    private Label LblTxtSprint11;

    @FXML
    private Label LblTxtSprint111;

    @FXML
    private Label LblTxtSprint1111;

    @FXML
    private Pane PaneSprint1;

    @FXML
    private Pane PaneSprint11;

    @FXML
    private Pane PaneSprint111;

    @FXML
    private Pane PaneSprint1111;

    @FXML
    private Pane PaneSprint11111;


    // divisão de fxml


    @FXML
    private Button adicionarSprintButton;

    @FXML
    private Button btnAcompSprint;

    @FXML
    private Button btnCriarCriterio;

    @FXML
    private Button btnSairCriterios;

    @FXML
    private AnchorPane campo_criterios;

    @FXML
    private TextField descricaoCriterio;

    @FXML
    private ScrollPane scrollCriteriosCriados;

    @FXML
    private TextField tituloCriterio;

    @FXML
    public void initialize() throws SQLException {
        carregarCriterios();
    }

    @FXML
    private void handleAdicionarCriterio() {
        String titulo = tituloCriterio.getText();
        String descricao = descricaoCriterio.getText();
        //caso campo vazio
        if (titulo.isEmpty() || descricao.isEmpty()) {
            System.out.println("Os campos de título e descrição não podem estar vazios.");

            return;
        }

        //adicionar ao bd
        CriterioDao criterioDao = new CriterioDao();
        criterioDao.inserirCriterio(titulo, descricao);

        //exibindo no historico
        exibirCriterio(titulo, descricao);

        //limpando campos de entrada
        tituloCriterio.clear();
        descricaoCriterio.clear();
    }

    private void exibirCriterio(String titulo, String descricao) {
        // Criação do campo de exibição do critério
        Pane novoCriterio = new Pane();
        novoCriterio.setPrefSize(445, 70);
        novoCriterio.setLayoutX(4);
        novoCriterio.setLayoutY(10);
        novoCriterio.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 4;");

        // Adicionando o título e a descrição no critério
        Label tituloLabel = new Label("Título:   " + titulo);
        tituloLabel.setLayoutX(10);
        tituloLabel.setLayoutY(10);
        tituloLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        Label descricaoLabel = new Label("Descrição: " + descricao);
        descricaoLabel.setLayoutX(10);
        descricaoLabel.setLayoutY(30);
        descricaoLabel.setWrapText(true);  // Permite quebra de linha
        descricaoLabel.setPrefWidth(400);  // Defina uma largura preferencial
        descricaoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        novoCriterio.getChildren().addAll(tituloLabel, descricaoLabel);

        // Verifica quantos critérios já existem no campo_criterios
        int numeroDeCriterios = campo_criterios.getChildren().size();

        // Calcula a posição Y para o novo critério
        double novaPosicaoY = numeroDeCriterios == 0 ? 0 : numeroDeCriterios * 78;

        // Define a posição do novo critério
        novoCriterio.setLayoutY(novaPosicaoY);

        // Adiciona o novo critério ao AnchorPane (campo_criterios)
        campo_criterios.getChildren().add(novoCriterio);
    }

    private void carregarCriterios() throws SQLException {
        CriterioDao criterioDao = new CriterioDao();
        List<Criterio> criterios = criterioDao.listarCriterios();

        for (Criterio criterio : criterios) {
            exibirCriterio(criterio.geetTitulo(), criterio.getDescricao());
        }
    }

    @FXML
    public void handleSair(ActionEvent actionEvent) throws IOException {
        Main.setRoot("TelaMenu-view");
    }

    @FXML
    void handleAcompSprint(ActionEvent event) throws IOException {
        Main.setRoot("acompanharSprints-view");
    }

    // Victor

    int sprintCount = 0;

    @FXML
    private void handleAdicionarSprint() {
        sprintCount++;

        switch (sprintCount) {
            case 1:
                PaneSprint1.setVisible(true);
                LblTxtSprint.setVisible(true);
                BtnDeleteSprint.setVisible(true);
                DataPickerFIM.setVisible(true);
                DataPickerINICIO.setVisible(true);
                LblTxtFIM.setVisible(true);
                LblTxtINICIO.setVisible(true);
                System.out.println(sprintCount);
                break;
            case 2:
                sprintCount--;
                PaneSprint11.setVisible(true);
                LblTxtSprint1.setVisible(true);
                BtnDeleteSprint1.setVisible(true);
                DataPickerFIM1.setVisible(true);
                DataPickerINICIO1.setVisible(true);
                LblTxtFIM1.setVisible(true);
                LblTxtINICIO1.setVisible(true);
                System.out.println(sprintCount);
                break;
            case 3:
                sprintCount--;
                PaneSprint111.setVisible(true);
                LblTxtSprint11.setVisible(true);
                BtnDeleteSprint11.setVisible(true);
                DataPickerFIM11.setVisible(true);
                DataPickerINICIO11.setVisible(true);
                LblTxtFIM11.setVisible(true);
                LblTxtINICIO11.setVisible(true);
                System.out.println(sprintCount);
                break;
            case 4:
                sprintCount--;
                PaneSprint1111.setVisible(true);
                LblTxtSprint111.setVisible(true);
                BtnDeleteSprint111.setVisible(true);
                DataPickerFIM111.setVisible(true);
                DataPickerINICIO111.setVisible(true);
                LblTxtFIM111.setVisible(true);
                LblTxtINICIO111.setVisible(true);
                System.out.println(sprintCount);
                break;
            case 5:
                sprintCount--;
                PaneSprint11111.setVisible(true);
                LblTxtSprint1111.setVisible(true);
                BtnDeleteSprint1111.setVisible(true);
                DataPickerFIM1111.setVisible(true);
                DataPickerINICIO1111.setVisible(true);
                LblTxtFIM1111.setVisible(true);
                LblTxtINICIO1111.setVisible(true);
                System.out.println(sprintCount);
                break;
        }
     }

    @FXML
    void handleDeleteSprint1(ActionEvent event) {
        sprintCount--;
        PaneSprint1.setVisible(false);
        LblTxtSprint.setVisible(false);
        BtnDeleteSprint.setVisible(false);
        DataPickerFIM.setVisible(false);
        DataPickerINICIO.setVisible(false);
        LblTxtFIM.setVisible(false);
        LblTxtINICIO.setVisible(false);
        System.out.println(sprintCount);
    }

    @FXML
    void handleDeleteSprint2(ActionEvent event) {
        sprintCount--;
        PaneSprint11.setVisible(false);
        LblTxtSprint1.setVisible(false);
        BtnDeleteSprint1.setVisible(false);
        DataPickerFIM1.setVisible(false);
        DataPickerINICIO1.setVisible(false);
        LblTxtFIM1.setVisible(false);
        LblTxtINICIO1.setVisible(false);
        System.out.println(sprintCount);
    }

    @FXML
    void handleDeleteSprint3(ActionEvent event) {
        sprintCount--;
        PaneSprint111.setVisible(false);
        LblTxtSprint11.setVisible(false);
        BtnDeleteSprint11.setVisible(false);
        DataPickerFIM11.setVisible(false);
        DataPickerINICIO11.setVisible(false);
        LblTxtFIM11.setVisible(false);
        LblTxtINICIO11.setVisible(false);
        System.out.println(sprintCount);
    }

    @FXML
    void handleDeleteSprint4(ActionEvent event) {
        sprintCount--;
        PaneSprint1111.setVisible(false);
        LblTxtSprint111.setVisible(false);
        BtnDeleteSprint111.setVisible(false);
        DataPickerFIM111.setVisible(false);
        DataPickerINICIO111.setVisible(false);
        LblTxtFIM111.setVisible(false);
        LblTxtINICIO111.setVisible(false);
        System.out.println(sprintCount);
    }

    @FXML
    void handleDeleteSprint5(ActionEvent event) {
        sprintCount--;
        PaneSprint11111.setVisible(false);
        LblTxtSprint1111.setVisible(false);
        BtnDeleteSprint1111.setVisible(false);
        DataPickerFIM1111.setVisible(false);
        DataPickerINICIO1111.setVisible(false);
        LblTxtFIM1111.setVisible(false);
        LblTxtINICIO1111.setVisible(false);
        System.out.println(sprintCount);
    }

}

