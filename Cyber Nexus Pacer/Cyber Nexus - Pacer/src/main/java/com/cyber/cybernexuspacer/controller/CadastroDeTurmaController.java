package com.cyber.cybernexuspacer.controller;

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
import java.util.Scanner;
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
    private TableView<Aluno> tabela;

    private ObservableList<Aluno> listaAluno = FXCollections.observableArrayList();


    @FXML
    void onClickbtnConfirmarAlunos(ActionEvent event) throws IOException {

    }

    @FXML
    public void initialize() {
        // Configura as colunas com os dados da classe Pessoa
        coluna_Aluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        coluna_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        coluna_Grupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));


        // Configura a tabela para exibir a lista de pessoas
        tabela.setItems(listaAluno);
    }

    @FXML
    public void carregarPlanilha(javafx.event.ActionEvent actionEvent) {
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

                    if (dados.length >= 3) {
                        String aluno = dados[0];
                        String email = dados[1];
                        String grupo = dados[2];
                        Aluno pessoa = new Aluno(aluno, email, grupo);
                        listaAluno.add(pessoa);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------------------------------------------------------

    public void tabela(String arquivoCSV) {
        String linha;
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            br.readLine(); // Ignora a primeira linha (cabeçalho)
            ObservableList<Aluno> listaAlunos = null;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String aluno = dados[0];
                String email = dados[1];
                String grupo = dados[2];

                // Cria o objeto Pessoa e adiciona à lista observável
                listaAlunos.add(new Aluno(aluno, email, grupo));
            }
            tabela.setItems(listaAlunos); // Associa a lista ao TableView
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}







