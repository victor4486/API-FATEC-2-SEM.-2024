package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.entity.Criterio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AreaDoAlunoController {
    @FXML
    private Label welcomeText;

    @FXML
    private Pane alunoSprintsBox;

    @FXML
    private Pane alunosSprintsBtns;

    @FXML
    private AnchorPane areaAlunoContainer;

    @FXML
    private Pane areaAlunoTitulo;

    @FXML
    private Button btnConfirmaCriterios;

    @FXML
    private Button btnSairNotas;

    @FXML
    private AnchorPane campo_criterios;

    @FXML
    private Button btnSprint1;

    @FXML
    private Button btnSprint111;

    @FXML
    private Button btnSprint2;

    @FXML
    private Button btnSprint3;

    @FXML
    private Button btnSprint4;

    @FXML
    private Pane cabecalhoCyber;

    @FXML
    private CheckBox checkBoxDois;

    @FXML
    private CheckBox checkBoxDois1;

    @FXML
    private CheckBox checkBoxDois11;

    @FXML
    private CheckBox checkBoxDois111;

    @FXML
    private CheckBox checkBoxQuatro;

    @FXML
    private CheckBox checkBoxQuatro1;

    @FXML
    private CheckBox checkBoxQuatro11;

    @FXML
    private CheckBox checkBoxQuatro111;

    @FXML
    private CheckBox checkBoxTres;

    @FXML
    private CheckBox checkBoxTres1;

    @FXML
    private CheckBox checkBoxTres11;

    @FXML
    private CheckBox checkBoxTres111;

    @FXML
    private CheckBox checkBoxUm;

    @FXML
    private CheckBox checkBoxUm1;

    @FXML
    private CheckBox checkBoxUm11;

    @FXML
    private CheckBox checkBoxUm111;

    @FXML
    private ScrollPane criteriosNotas;

    @FXML
    private Pane grupoAlunoInfo;

    @FXML
    private Label lblTxtDescricaoDocriterio1;

    @FXML
    private Label lblTxtDescricaoDocriterio11;

    @FXML
    private Label lblTxtDescricaoDocriterio111;

    @FXML
    private Label lblTxtDescricaoDocriterio1111;

    @FXML
    private Label lblTxtNumberFour;

    @FXML
    private Label lblTxtNumberFour1;

    @FXML
    private Label lblTxtNumberFour11;

    @FXML
    private Label lblTxtNumberFour111;

    @FXML
    private Label lblTxtNumberOne;

    @FXML
    private Label lblTxtNumberOne1;

    @FXML
    private Label lblTxtNumberOne11;

    @FXML
    private Label lblTxtNumberOne111;

    @FXML
    private Label lblTxtNumberTree;

    @FXML
    private Label lblTxtNumberTree1;

    @FXML
    private Label lblTxtNumberTree11;

    @FXML
    private Label lblTxtNumberTree111;

    @FXML
    private Label lblTxtNumberTwo;

    @FXML
    private Label lblTxtNumberTwo1;

    @FXML
    private Label lblTxtNumberTwo11;

    @FXML
    private Label lblTxtNumberTwo111;

    @FXML
    private Label lblTxtTitulodocriterio1;

    @FXML
    private Label lblTxtTitulodocriterio11;

    @FXML
    private Label lblTxtTitulodocriterio111;

    @FXML
    private Label lblTxtTitulodocriterio1111;

    @FXML
    private Pane paneSprint1;

    @FXML
    private Pane paneSprint11;

    @FXML
    private Pane paneSprint111;

    @FXML
    private Pane paneSprint1111;

    @FXML
    public void initialize() throws SQLException {
        carregarCriterios();
    }

    private void exibirCriterio(String titulo, String descricao) {
        // Criação do campo de exibição do critério
        Pane paneCriterio = new Pane();
        paneCriterio.setPrefSize(476, 75);
        paneCriterio.setLayoutX(4);
        //paneCriterio.setLayoutY(10);
        paneCriterio.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 4;");

        // Adicionando o título e a descrição no critério
        Label tituloLabel = new Label( titulo);
        tituloLabel.setLayoutX(7);
        tituloLabel.setLayoutY(7);
        tituloLabel.setStyle("-fx-text-fill: black; -fx-font-size: 14px; -fx-font-family: 'Arial'; ");

        Label descricaoLabel = new Label(descricao);
        descricaoLabel.setPrefWidth(300);
        descricaoLabel.setLayoutX(7);
        descricaoLabel.setLayoutY(34);
        descricaoLabel.setWrapText(true);  // Permite quebra de linha
        descricaoLabel.setStyle("-fx-text-fill: black; -fx-font-size: 12px; -fx-font-family: 'Arial';");

        CheckBox checkboxZero = criaCheckbox(355,10);
        CheckBox checkboxUm = criaCheckbox(385,10);
        CheckBox checkboxDois = criaCheckbox(415,10);
        CheckBox checkboxTres = criaCheckbox(445,10);

        Label lblNotaZero = criaLabel(355,33,"0");
        Label lblNotaUm = criaLabel(385,33,"1");
        Label lblNotaDois = criaLabel(415,33,"2");
        Label lblNotaTres = criaLabel(445,33,"3");

        paneCriterio.getChildren().addAll(
                tituloLabel, descricaoLabel,
                checkboxZero,checkboxUm,checkboxDois,checkboxTres,
                lblNotaZero ,lblNotaUm, lblNotaDois, lblNotaTres

        );

        // Verifica quantos critérios já existem no campo_criterios
        int numeroDeCriterios = campo_criterios.getChildren().size();

        // Calcula a posição Y para o novo critério
        double novaPosicaoY = numeroDeCriterios == 0 ? 5 : numeroDeCriterios * 85;

        // Define a posição do novo critério
        paneCriterio.setLayoutY(novaPosicaoY);

        // Adiciona o novo critério ao AnchorPane (campo_criterios)
        campo_criterios.getChildren().add(paneCriterio);
    }

    private void carregarCriterios() throws SQLException {
        CriterioDao criterioDao = new CriterioDao();
        List<Criterio> criterios = criterioDao.listarCriterios();

        for (Criterio criterio : criterios) {
            exibirCriterio(criterio.geetTitulo(), criterio.getDescricao());
        }
    }

    private CheckBox criaCheckbox(double layoutX, double layoutY) {
        CheckBox checkBox = new CheckBox();
        checkBox.setLayoutX(layoutX);
        checkBox.setLayoutY(layoutY);
        return checkBox;
    }

    private Label criaLabel(double layoutX, double layoutY, String descricao) {
        Label label = new Label(descricao);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setStyle("-fx-text-fill: black; -fx-font-size: 15px; -fx-font-family: 'Arial';");
        return label;
    }

    @FXML
    void onClickbtnSair(ActionEvent event) throws IOException {
        Main.setRoot("login-view");
    }
}
