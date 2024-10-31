package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
                System.out.println(tipoUsuario);
                // Se estiverem corretos, muda a tela
                if ("Aluno".equals(tipoUsuario)) {
                    // verifica se a senha e aluno para identificar primeiro login e redefinir a senha
                    if ("aluno".equals(senha)) {
                        // Carregar a tela de recuperação de senha e passar o nome do usuário
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("recuperacaoSenha-view.fxml"));
                        AnchorPane root = loader.load();

                        // Obter o controller da tela de recuperação e passar o nome do usuário
                        RecuperarSenhaController recuperarSenhaController = loader.getController();
                        recuperarSenhaController.setNomeUsuario(nome);

                        // Mostrar a nova tela
                        painelPrincipal.getChildren().setAll(root);
                    } else {
                        Main.setRoot("AreaDoAluno-view");
                    }
                } else if ("Admin".equals(tipoUsuario)) {
                    Main.setRoot("TelaMenu-view");
                } else if ("Professor".equals(tipoUsuario)) {
                    Main.setRoot("TelaMenu-view");
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
