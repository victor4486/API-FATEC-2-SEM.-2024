package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.CriterioDao;
import com.cyber.cybernexuspacer.dao.PontuacaoGruposDao;
import com.cyber.cybernexuspacer.entity.Criterio;
import com.cyber.cybernexuspacer.entity.PontuacaoGrupo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PontuacaoGruposController {

    @FXML
    private ImageView btn_print_nota_grupo41;

    @FXML
    private ImageView btn_print_nota_individual41;

    @FXML
    private ImageView logofatec;

    @FXML
    private Text nota_grupo41;

    @FXML
    private Text nota_individual41;

    @FXML
    private Button pontos_btn_sprint1;

    @FXML
    private Button pontuacao_btn_confirmar;

    @FXML
    private Button pontuacao_btn_sair;

    @FXML
    private Button pontuacao_btn_sprint2;

    @FXML
    private Button pontuacao_btn_sprint3;

    @FXML
    private Button pontuacao_btn_sprint4;

    @FXML
    private Text pontuacao_label_integrantes;

    @FXML
    private Text pontuacao_label_integrantes1;

    @FXML
    private Text pontuacao_label_integrantes11;

    @FXML
    private Text pontuacao_label_integrantes12;

    @FXML
    private Text pontuacao_label_integrantes13;

    @FXML
    private Text pontuacao_label_integrantes14;

    @FXML
    private Text pontuacao_label_integrantes141;

    @FXML
    private Text pontuacao_label_integrantes1411;

    @FXML
    private Text pontuacao_label_name;

    @FXML
    private Text pontuacao_label_name1;

    @FXML
    private Text pontuacao_label_name11;

    @FXML
    private Text pontuacao_label_name12;

    @FXML
    private Text pontuacao_label_name13;

    @FXML
    private Text pontuacao_label_name14;

    @FXML
    private Text pontuacao_label_name141;

    @FXML
    private Text pontuacao_label_name1411;

    @FXML
    private Text pontuacao_label_nota;

    @FXML
    private Text pontuacao_label_nota1;

    @FXML
    private Text pontuacao_label_nota11;

    @FXML
    private Text pontuacao_label_nota12;

    @FXML
    private Text pontuacao_label_nota13;

    @FXML
    private Text pontuacao_label_nota14;

    @FXML
    private Text pontuacao_label_nota141;

    @FXML
    private Text pontuacao_label_nota1411;

    @FXML
    private AnchorPane pontuacao_scroll_sprints;

    @FXML
    private Label txt_acompanhamento_pacer;

    @FXML
    private Text txt_nota_grupo41;

    @FXML
    private Text txt_nota_individual41;

    @FXML
    private Label txt_ola_professor;

    @FXML
    private Text txtaluno41;

    @FXML
    private Label txtcibernexus;

    @FXML
    private Text txtgrupo41;

    @FXML
    private AnchorPane mostrar_Grupos;// ScrollPane para exibir os critérios

    TextField lblNotasSprint;
    private Map<Integer,TextField> notasMap  = new HashMap<>();

    @FXML
    public void initialize() throws SQLException {
        carregarGrupos();
    }

    private void exibirGrupos(int id, String grupo, int integrantes, double nota) {
        // Criação do campo de exibição do critério
        Pane paneCriterio = new Pane();
        paneCriterio.setPrefSize(579, 60);
        paneCriterio.setLayoutX(4);
        //paneCriterio.setLayoutY(10);
        paneCriterio.setStyle("-fx-background-color: #93DD86; -fx-background-radius: 4;");

        // Adicionando o nomeEquipe e os integrantes
        Label nomeEquipe = criaLabel(7,7,"Nome: " + grupo, "-fx-text-fill: black; -fx-font-size: 14px; -fx-font-family: 'Arial';"  );
        Label integrantesEquipe = criaLabel(7,34,"Integrantes da Equipe: " + String.valueOf(integrantes), "-fx-text-fill: black; -fx-font-size: 12px; -fx-font-family: 'Arial';");
        Label labelNotas = criaLabel(482,10,"Notas da Sprint", "-fx-text-fill: black; -fx-font-size: 12px; -fx-font-family: 'Arial';");


        lblNotasSprint = new TextField(String.valueOf(nota));
        lblNotasSprint.setLayoutX(509);
        lblNotasSprint.setLayoutY(30);
        lblNotasSprint.setPrefWidth(44);
        lblNotasSprint.setStyle("-fx-font-size: 12px; -fx-font-family: 'Arial';");

        // Armazena o TextField associado ao ID do grupo
        notasMap.put(id,lblNotasSprint);

        Rectangle retangNotaSprint = new Rectangle();
        retangNotaSprint.setWidth(44);
        retangNotaSprint.setHeight(23);
        retangNotaSprint.setLayoutX(501);
        retangNotaSprint.setLayoutY(27);
        retangNotaSprint.setFill(Color.web("#d4ffe6"));

        paneCriterio.getChildren().addAll(
                nomeEquipe, integrantesEquipe,
                labelNotas,retangNotaSprint,lblNotasSprint

        );

        // Verifica quantos critérios já existem no campo_criterios
        int numeroDeCriterios = mostrar_Grupos.getChildren().size();

        // Calcula a posição Y para o novo critério
        double novaPosicaoY = numeroDeCriterios == 0 ? 5 : numeroDeCriterios * 70;

        // Define a posição do novo critério
        paneCriterio.setLayoutY(novaPosicaoY);

        // Adiciona o novo critério ao AnchorPane (campo_criterios)
        mostrar_Grupos.getChildren().add(paneCriterio);
    }

    private void carregarGrupos() throws SQLException {
        PontuacaoGruposDao pontuacaoGruposDao = new PontuacaoGruposDao();
        List<PontuacaoGrupo> pontGrupo = pontuacaoGruposDao.pesquisarGrupos();

        for (PontuacaoGrupo pontuacoes : pontGrupo) {
            exibirGrupos(pontuacoes.getId(), pontuacoes.getGrupo(), pontuacoes.getIntegrantes(), pontuacoes.getNota());
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
    void onbtnconfirmar(ActionEvent event) throws SQLException {
        for (Map.Entry<Integer,TextField> entry : notasMap.entrySet()){
            Integer idGrupo = entry.getKey();
            String novaNota = entry.getValue().getText();

            PontuacaoGruposDao.salvarNota(idGrupo, novaNota);
        }
    }

    @FXML
    void onbtnsair(ActionEvent event) throws IOException {
        Main.setRoot("TelaMenu-view");
    }

    @FXML
    void onbtnsprint1(ActionEvent event) {

    }

    @FXML
    void onbtnsprint2(ActionEvent event) {

    }

    @FXML
    void onbtnsprint3(ActionEvent event) {

    }

    @FXML
    void onbtnsprint4(ActionEvent event) {

    }

}
