package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {

    public AnchorPane anchorlogin;
    public Label lblTxtSistemaPacer;
    @FXML
    private Button btnEntrar;

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

    private LoginDao loginDao = new LoginDao();

    @FXML
    void onClickbtnEntrar(ActionEvent event) throws IOException {
        String nome = usuarioLogin.getText();
        String senha = usuarioSenha.getText();
        String tipoUsuario = verificarTipoUsuario(nome);

        try {
            // Verifica se o login e a senha estão corretos no banco de dados
            if (loginDao.verificarLogin(nome, senha, tipoUsuario)) {
                // Se estiverem corretos, muda a tela
                if ("Aluno".equals(tipoUsuario)) {
                    Main.setRoot("AreaDoAluno-view");
                } else if ("Admin".equals(tipoUsuario)) {
                    Main.setRoot("AreaAdmin-view");
                } else if ("Professor".equals(tipoUsuario)) {
                    Main.setRoot("AreaDoProfessor-view");
                }
            } else {
                // Exibe a mensagem de erro se as credenciais estiverem incorretas
                lblUsuarioESenhaInvalidos.setText("Usuário ou senha incorretos!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String verificarTipoUsuario(String nome) {
        // Lógica para determinar o tipo de usuário com base no nome ou outra variável
        // Aqui você pode aplicar alguma lógica específica para diferenciar os tipos de usuários
        if (nome.contains("admin")) {
            return "Admin";
        } else if (nome.contains("professor")) {
            return "Professor";
        } else {
            return "Aluno";
        }
    }
}
