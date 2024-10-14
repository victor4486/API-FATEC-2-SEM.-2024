package com.cyber.cybernexuspacer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public AnchorPane anchorlogin;
    public Label lblTxtSistemaPacer;
    @FXML
    private Button bottonEntrar;

    @FXML
    private ImageView imgFatec;

    @FXML
    private AnchorPane painelPrincipal;

    @FXML
    private Label txtSistemaPacer;

    @FXML
    private Label lblUsuarioESenhaInvalidos;

    @FXML
    private TextField usuarioLogin;

    @FXML
    private PasswordField usuarioSenha;

    // O método @FXML precisa estar dentro da classe LoginController
    @FXML
    void onClickbtnEntrar(ActionEvent event) throws IOException {
        // Verifica se o login e a senha inseridos são iguais aos valores esperados
        if (Objects.equals(usuarioLogin.getText(), "usuarioLogin")
                && Objects.equals(usuarioSenha.getText(), "usuarioSenha")) {
            // Se estiverem corretos, muda a tela para 'criterios-view'
            Main.setRoot("criterios-view");
        } else {
            // Exibe a mensagem de erro se as credenciais estiverem incorretas
            lblUsuarioESenhaInvalidos.setText("Usuário ou senha incorretos!");
        }
    }

}



