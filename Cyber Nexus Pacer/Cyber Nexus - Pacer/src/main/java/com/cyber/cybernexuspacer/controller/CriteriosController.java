package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.entity.Criterio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriteriosController {

    @FXML
    private Button btnAcompSprint;

    @FXML
    private Button adicionarSprintButton;

    @FXML
    private Button btnCriarCriterio;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnSairCriterios;

    @FXML
    private AnchorPane campo_criterios;

    @FXML
    private VBox campo_sprints;

    @FXML
    private TextField tituloCriterio;

    @FXML
    private TextField descricaoCriterio;

    private List<Criterio> criterios = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {
        carregarCriterios();
    }

    // Método para acompanhar Sprints
    @FXML
    void handleAcompSprint(ActionEvent event) {
        System.out.println("Acompanhamento de Sprint acionado.");
        // Lógica para acompanhar sprints
    }

    @FXML
    private void handleAdicionarCriterio() {
        String titulo = tituloCriterio.getText();
        String descricao = descricaoCriterio.getText();

        if (titulo.isEmpty() || descricao.isEmpty()) {
            exibirMensagem("Os campos de título e descrição não podem estar vazios.");
            return;
        }

        criterios.add(new Criterio(titulo, descricao));
        exibirCriterio(titulo, descricao, 0);

        tituloCriterio.clear();
        descricaoCriterio.clear();
    }

    private void exibirCriterio(String titulo, String descricao, int id) {
        Pane novoCriterio = new Pane();
        novoCriterio.setPrefSize(445, 70);
        novoCriterio.setLayoutX(4);
        novoCriterio.setLayoutY(10);
        novoCriterio.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 4;");

        Label tituloLabel = criaLabel(10, 10, "Título: " + titulo, "-fx-text-fill: white; -fx-font-size: 14px;");
        Label descricaoLabel = criaLabel(10, 30, "Descrição: " + descricao, "-fx-text-fill: white; -fx-font-size: 12px;");
        descricaoLabel.setWrapText(true);
        descricaoLabel.setPrefWidth(400);

        Button excluirButton = new Button("Excluir");
        excluirButton.setLayoutX(370);
        excluirButton.setLayoutY(10);
        excluirButton.setOnAction(e -> handleExcluirCriterio(id, novoCriterio));

        novoCriterio.getChildren().addAll(tituloLabel, descricaoLabel, excluirButton);

        int numeroDeCriterios = campo_criterios.getChildren().size();
        double novaPosicaoY = numeroDeCriterios == 0 ? 5 : numeroDeCriterios * 78;

        novoCriterio.setLayoutY(novaPosicaoY);
        campo_criterios.getChildren().add(novoCriterio);
    }

    private void handleExcluirCriterio(int id, Pane criterioPane) {
        CriterioDao criterioDao = new CriterioDao();
        try {
            criterioDao.deletarCriterio(id);
            campo_criterios.getChildren().remove(criterioPane);
            exibirMensagem("Critério excluído com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            exibirMensagem("Erro ao excluir o critério.");
        }
    }

    private void carregarCriterios() throws SQLException {
        CriterioDao criterioDao = new CriterioDao();
        List<Criterio> criteriosBd = criterioDao.listarCriterios();

        for (Criterio criterio : criteriosBd) {
            exibirCriterio(criterio.getTitulo(), criterio.getDescricao(), criterio.getId());
        }
    }

    @FXML
    void handleAdicionarSprint(ActionEvent event) {
        System.out.println("Adicionar Sprint acionado.");
        // Lógica para adicionar sprint
    }

    @FXML
    void onClickConfirmar(ActionEvent event) throws SQLException {
        System.out.println("Confirmação acionada.");
        // Lógica para confirmar ações
    }

    @FXML
    public void handleSair(ActionEvent actionEvent) {
        System.out.println("Voltando ao menu.");
        // Lógica para voltar ao menu
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem);
        alert.showAndWait();
    }

    private Label criaLabel(double layoutX, double layoutY, String descricao, String style) {
        Label label = new Label(descricao);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setStyle(style);
        return label;
    }
}
