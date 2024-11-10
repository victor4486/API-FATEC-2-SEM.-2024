package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.AreaDoAlunoDao;
import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.dao.SprintDao;
import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import com.cyber.cybernexuspacer.entity.Criterio;
import com.cyber.cybernexuspacer.entity.Sprint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaDoAlunoController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label lblNomeGrupo;

    @FXML
    private Text lblNotaGrupo;

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
    private Button btnSprint2;

    @FXML
    private Button btnSprint3;

    @FXML
    private Button btnSprint4;

    @FXML
    private Pane cabecalhoCyber;

    @FXML
    private ScrollPane criteriosNotas;

    @FXML
    private Pane grupoAlunoInfo;

    @FXML
    private VBox vBoxSprintsBtns;

    @FXML
    private ComboBox<String> alunosComboBox;  // Referência para o ComboBox de alunos

    private Map<String, Integer> alunosMap = new HashMap<>();  // Mapeamento nome -> ID do aluno


    @FXML
    public void initialize() throws SQLException {
        carregarCriterios();
        carregarAlunos();
        carregarSprints();
        exibirNota();
    }

    private void carregarSprints() throws SQLException {
        SprintDao sprintDao = new SprintDao();
        List<Sprint> sprints = sprintDao.listarSprints();  // Busca todas as sprints no banco
        vBoxSprintsBtns.getChildren().clear();  // Limpa o conteúdo anterior


        // Cria um botão para cada sprint
        for (Sprint sprint : sprints) {
            Button sprintButton = new Button("Sprint " + sprint.getNumSprint());
            sprintButton.setPrefSize(188, 40);
            sprintButton.setLayoutX(6);
            sprintButton.setLayoutY(7);
            sprintButton.setStyle("-fx-background-color: #86B6DD; -fx-font-weight: bolder; -fx-padding: 10 20 10 20;");
            sprintButton.setOnAction(event -> {
                // Ação quando a sprint for clicada
                System.out.println("Sprint " + sprint.getNumSprint() + " selecionada.");
                // Você pode adicionar a lógica para lidar com o clique na sprint
            });
            vBoxSprintsBtns.getChildren().add(sprintButton);  // Adiciona o botão à interface
        }

    }


    private void carregarAlunos() throws SQLException {
        AreaDoAlunoDao areaDoAlunoDao = new AreaDoAlunoDao();
        List<AreaDoAluno> alunos = areaDoAlunoDao.listarAlunos();

        // Preenche o ComboBox com os nomes dos alunos
        alunosComboBox.getItems().setAll(
                alunos.stream()
                        .map(AreaDoAluno::getNomeAluno)  // Nome do aluno
                        .toList()
        );

        // Armazenar o ID de cada aluno no map
        for (AreaDoAluno aluno : alunos) {
            alunosMap.put(aluno.getNomeAluno(), aluno.getIdAlunoReceptor());  // Pegando id do receptor da nota para salvar no banco
        }

        // Adiciona um listener para capturar a seleção
        alunosComboBox.setOnAction(event -> {
            String alunoSelecionado = alunosComboBox.getValue();
            if (alunoSelecionado != null) {
                Integer idAlunoReceptor = alunosMap.get(alunoSelecionado);
                System.out.println("ID do aluno selecionado: " + idAlunoReceptor);
                // Agora você pode salvar o idAluno em uma variável ou usá-lo para outras operações
            }
        });
    }

    public void exibirNota() {
        try {
            AreaDoAlunoDao areaDoAlunoDao = new AreaDoAlunoDao();
            double nota = areaDoAlunoDao.buscaNota();
            List<AreaDoAluno> alunos = areaDoAlunoDao.listarAlunos();


            // Armazenar o ID de cada aluno no map
            for (AreaDoAluno aluno : alunos) {
                lblNomeGrupo.setText(aluno.getGrupo());
            }
            // Define o texto da Label com o valor da nota
            lblNotaGrupo.setText(String.valueOf(nota)); // Formata para 2 casas decimais


        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            exibirCriterio(criterio.getTitulo(), criterio.getDescricao());
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
