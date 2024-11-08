package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.dao.SprintDao;
import com.cyber.cybernexuspacer.entity.Criterio;
import com.cyber.cybernexuspacer.entity.Sprint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CriteriosController {

    @FXML
    private Button BtnDeleteSprint;

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
    private VBox campo_sprints;

    @FXML
    private TextField descricaoCriterio;

    @FXML
    private ScrollPane scrollCriteriosCriados;

    @FXML
    private TextField tituloCriterio;

    private int sprintCount = 0;
    private List<Sprint> sprints = new ArrayList<>();
    private List<Criterio> criterios = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException {
        carregarCriterios();
        carregarSprints();
    }

    private void exibirMensagem(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem);
        alert.showAndWait();
    }

    @FXML
    private void handleAdicionarCriterio() {
        String titulo = tituloCriterio.getText();
        String descricao = descricaoCriterio.getText();

        if (titulo.isEmpty() || descricao.isEmpty()) {
            exibirMensagem("Os campos de título e descrição não podem estar vazios.");
            return;
        }

        int id = 0;
        Criterio novoCriterio = new Criterio(id, titulo, descricao);
        criterios.add(novoCriterio);
        exibirCriterio(novoCriterio);

        tituloCriterio.clear();
        descricaoCriterio.clear();
    }
    

    private void exibirCriterio(Criterio criterio) {
        Pane novoCriterio = new Pane();
        novoCriterio.setPrefSize(445, 70);
        novoCriterio.setLayoutX(4);
        novoCriterio.setLayoutY(10);
        novoCriterio.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 4;");

        Label tituloLabel = criaLabel(10, 10, "Título:   " + criterio.getTitulo(), "-fx-text-fill: white; -fx-font-size: 14px;");
        Label descricaoLabel = criaLabel(10, 30, "Descrição: " + criterio.getDescricao(), "-fx-text-fill: white; -fx-font-size: 12px;");
        descricaoLabel.setWrapText(true);
        descricaoLabel.setPrefWidth(400);

        Button btnExcluir = new Button("Excluir");
        btnExcluir.setLayoutX(370);
        btnExcluir.setLayoutY(10);
        btnExcluir.setStyle("-fx-background-color: #ff6666; -fx-text-fill: white;");
        btnExcluir.setOnAction(event -> {
            try {
                handleExcluirCriterio(criterio.getId(), novoCriterio);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        novoCriterio.getChildren().addAll(tituloLabel, descricaoLabel, btnExcluir);

        int numeroDeCriterios = campo_criterios.getChildren().size();
        double novaPosicaoY = numeroDeCriterios == 0 ? 5 : numeroDeCriterios * 78;
        novoCriterio.setLayoutY(novaPosicaoY);

        campo_criterios.getChildren().add(novoCriterio);
    }

    private void carregarCriterios() throws SQLException {
        CriterioDao criterioDao = new CriterioDao();
        List<Criterio> criteriosBd = criterioDao.listarCriterios();

        for (Criterio criterio : criteriosBd) {
            exibirCriterio(criterio);
        }
    }

    @FXML
    private void handleAdicionarSprint() {
        sprintCount++;

        DatePicker dataInicioPicker = new DatePicker();
        DatePicker dataFimPicker = new DatePicker();

        Sprint novoSprint = new Sprint(0, "Sprint " + sprintCount, null, null);
        sprints.add(novoSprint);

        HBox novoSprintBox = new HBox(10);
        novoSprintBox.getChildren().addAll(new Label(novoSprint.getNumSprint()), dataInicioPicker, dataFimPicker);

        campo_sprints.getChildren().add(novoSprintBox);

        dataInicioPicker.setOnAction(e -> {
            if (dataInicioPicker.getValue() != null) {
                novoSprint.setDataInicio(Date.valueOf(dataInicioPicker.getValue()));
            }
        });

        dataFimPicker.setOnAction(e -> {
            if (dataFimPicker.getValue() != null) {
                novoSprint.setDataFim(Date.valueOf(dataFimPicker.getValue()));
            }
        });
    }

    private void carregarSprints() throws SQLException {
        SprintDao sprintDao = new SprintDao();
        List<Sprint> sprintsBd = sprintDao.listarSprints();

        campo_sprints.getChildren().clear();

        for (Sprint sprint : sprintsBd) {
            DatePicker dataInicioPicker = new DatePicker();
            DatePicker dataFimPicker = new DatePicker();

            if (sprint.getDataInicio() != null) {
                dataInicioPicker.setValue(sprint.getDataInicio().toLocalDate());
            }
            if (sprint.getDataFim() != null) {
                dataFimPicker.setValue(sprint.getDataFim().toLocalDate());
            }

            exibirSprint(sprint, dataInicioPicker, dataFimPicker);
        }
    }

    private void exibirSprint(Sprint sprint, DatePicker dataInicioPicker, DatePicker dataFimPicker) {
        Pane pane = criarPaneSprint(sprint, dataInicioPicker, dataFimPicker);
        campo_sprints.getChildren().add(pane);
    }

    private Pane criarPaneSprint(Sprint sprint, DatePicker dataInicioPicker, DatePicker dataFimPicker) {
        Pane pane = new Pane();
        pane.setPrefSize(220, 120);
        pane.setLayoutX(6);
        pane.setLayoutY(7);
        pane.setStyle("-fx-background-color: #86B6DD; -fx-background-radius: 5;");

        Label numSprintLabel = criaLabel(25, 13, sprint.getNumSprint(), "-fx-text-fill: white; -fx-font-size: 14px;");
        Label dataInicioLabel = criaLabel(12, 50, "Data de Início:", "-fx-text-fill: white; -fx-font-size: 14px;");
        Label dataFimLabel = criaLabel(12, 85, "Data de Fim:", "-fx-text-fill: white; -fx-font-size: 14px;");

        Button btnDeleteSprint = new Button("X");
        btnDeleteSprint.setLayoutX(160);
        btnDeleteSprint.setLayoutY(14);
        btnDeleteSprint.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Deleção");
            alert.setHeaderText("Você realmente deseja excluir esta Sprint?");
            alert.setContentText("Esta ação não pode ser desfeita.");

            ButtonType buttonTypeYes = new ButtonType("Sim");
            ButtonType buttonTypeNo = new ButtonType("Não");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                sprint.setMarkedForDeletion();
                try {
                    handleDeleteSprint(sprint.getId());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        dataInicioPicker.setLayoutX(114);
        dataInicioPicker.setLayoutY(48);
        dataFimPicker.setLayoutX(114);
        dataFimPicker.setLayoutY(81);

        pane.getChildren().addAll(numSprintLabel, dataInicioLabel, dataFimLabel, dataInicioPicker, dataFimPicker, btnDeleteSprint);

        int numeroDeCriterios = campo_sprints.getChildren().size();
        double novaPosicaoY = numeroDeCriterios == 0 ? 5 : numeroDeCriterios * 130;
        pane.setLayoutY(novaPosicaoY);

        return pane;
    }

    private void handleDeleteSprint(int index) throws SQLException {
        SprintDao sprintDao = new SprintDao();

        for (int i = sprints.size() - 1; i >= 0; i--) {
            Sprint sprint = sprints.get(i);
            if (sprint.isMarkedForDeletion()) {
                sprints.remove(i);
            }
        }

        sprintDao.deletarSprint(index);
        carregarSprints();
    }

    private void handleExcluirCriterio(int criterioId, Pane criterioPane) throws SQLException {
        CriterioDao criterioDao = new CriterioDao();
        criterioDao.excluirCriterio(criterioId);
        campo_criterios.getChildren().remove(criterioPane);

        for (int i = 0; i < campo_criterios.getChildren().size(); i++) {
            Pane pane = (Pane) campo_criterios.getChildren().get(i);
            pane.setLayoutY(i * 78);
        }
    }

    private Label criaLabel(double layoutX, double layoutY, String descricao, String style) {
        Label label = new Label(descricao);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setStyle(style);
        return label;
    }

    @FXML
    public void handleSair(ActionEvent actionEvent) throws IOException {
        Main.setRoot("TelaMenu-view");
    }

    @FXML
    void handleAcompSprint(ActionEvent event) throws IOException {
        Main.setRoot("acompanharSprints-view");
    }
    @FXML
    private void onClickConfirmar(ActionEvent event) {
        // Implementação do que deve acontecer ao clicar em "Confirmar"
        exibirMensagem("Operação confirmada com sucesso!");
    }
}

