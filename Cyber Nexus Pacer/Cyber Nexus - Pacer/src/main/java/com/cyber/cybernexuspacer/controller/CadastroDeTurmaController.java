package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CadastroTurmaDao;
import com.cyber.cybernexuspacer.dao.ConexaoDao;
import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static com.mysql.cj.conf.PropertyKey.logger;
//---------------------

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
    private TableColumn<?, ?> coluna_Aluno;

    @FXML
    private TableColumn<?, ?> coluna_Email;

    @FXML
    private TableColumn<?, ?> coluna_Grupo;

    @FXML
    private ImageView logo_fatec;

    @FXML
    private TableView<AreaDoAluno> tabela;

    private ObservableList<AreaDoAluno> listaAluno = FXCollections.observableArrayList();


    @FXML
    void onClickbtnConfirmarAlunos(ActionEvent event) throws IOException, SQLException {

        try {
            // Inicia a transação
            ConexaoDao.getConnection().setAutoCommit(false);

            for (AreaDoAluno aluno : listaAluno) {
                // Verifica se o aluno já existe
                if (!CadastroTurmaDao.alunoExists(aluno)) {
                    CadastroTurmaDao.CadastrarAlunos(aluno);

                } else {
                    // Informar o usuário sobre a duplicata de forma mais amigável
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Aluno Duplicado");
                    alert.setHeaderText("O aluno " + aluno.getNomeAluno() + " já está cadastrado.");
                    alert.setContentText("Verifique a lista de alunos.");
                    alert.showAndWait();
                    System.out.println("Aluno já existe: " + aluno.getNomeAluno());
                }
            }
            // Mensagem de sucesso
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText("Alunos cadastrados com sucesso!");
            alert.showAndWait();

            // Confirma a transação
            ConexaoDao.getConnection().commit();
        } catch ( SQLException e) {

            // Informar o usuário sobre o erro de forma mais amigável
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Cadastrar Alunos");
            alert.setHeaderText("Ocorreu um erro ao cadastrar os alunos.");
            alert.setContentText("Por favor, verifique os dados e tente novamente.");
            alert.showAndWait();

            // Se ocorrer um erro, reverte a transação
            ConexaoDao.getConnection().rollback();
            e.printStackTrace();
        } finally {
            // Restaura o modo de auto-commit
            ConexaoDao.getConnection().setAutoCommit(true);
            ConexaoDao.getConnection().close();

        }
    }

    @FXML
    public void initialize() {
        // Configura as colunas com os dados da classe Pessoa
        coluna_Aluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        coluna_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        coluna_Grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));


        // Configura a tabela para exibir a lista de pessoas
        tabela.setItems(listaAluno);
    }

    @FXML
    public void onClickCarregarPlanilha(javafx.event.ActionEvent actionEvent) {
        // Abre o explorador de arquivos para a escolha do arquivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectFile = fileChooser.showOpenDialog(button_carregarDocumento.getScene().getWindow());

        if (selectFile != null) {
            try (Scanner scanner = new Scanner(selectFile)) {
                listaAluno.clear();  // Limpa a lista antes de adicionar novos dados

                // Ignora a linha do cabeçalho
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                // Lê o arquivo linha por linha e adiciona cada pessoa na lista
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String[] dados = linha.split(",");


                    if (dados.length >= 1) {
                        String nome = dados[0];
                        String email = dados[1];
                        String grupo = dados[2];
                        AreaDoAluno pessoa = new AreaDoAluno(nome, email, grupo, "fatec2024", "Aluno");
                        listaAluno.add(pessoa);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onClickSair(ActionEvent event) throws IOException {
        Main.setRoot("TelaMenu-view");
    }


}







