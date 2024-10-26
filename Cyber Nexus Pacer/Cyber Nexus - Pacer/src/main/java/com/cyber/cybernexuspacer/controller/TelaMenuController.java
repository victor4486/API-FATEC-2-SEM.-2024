package com.cyber.cybernexuspacer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class TelaMenuController {

    @FXML
    private ImageView logo_fatec;

    @FXML
    private Button menu_btn_cadastrar_critérios;

    @FXML
    private Button menu_btn_cadastrar_turma;

    @FXML
    private Button menu_btn_gerenciar_alunos;

    @FXML
    private Button menu_btn_gerenciar_grupos;

    @FXML
    private Button menu_btn_sair;

    @FXML
    private Text txt_acompanhamento_pacer;

    @FXML
    private Text txt_cibernexus;

    @FXML
    private Text txt_ola_professor;

    @FXML
    void on_btn_sair(ActionEvent event) throws IOException {
        Main.setRoot("login-view");

    }

    @FXML
    void onbtn_tela_cadastrar_critérios(ActionEvent event) throws IOException {
        Main.setRoot("criterios-view");

    }

    @FXML
    void onbtn_tela_cadastrar_turma(ActionEvent event) throws IOException {
            Main.setRoot("cadastroTurma-view");

    }

    @FXML
    void onbtn_tela_gerenciar_alunos(ActionEvent event) throws IOException {
        Main.setRoot("acompanharSprints-view");
    }

    @FXML
    void onbtn_tela_gerenciar_grupos(ActionEvent event) throws IOException {
        Main.setRoot("pontuacaoGrupos-view");
    }
}
