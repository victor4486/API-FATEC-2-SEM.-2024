package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.LoginDao;
import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import com.cyber.cybernexuspacer.session.AlunoSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
                if ("Aluno".equals(tipoUsuario) && "aluno".equals(senha)) {
                    redirecionarParaRecuperacaoSenha(nome);
                }
                else if ("Aluno".equals(tipoUsuario)) {
                    System.out.println("email: " + nome);

                    // Aqui, após a validação, busca os detalhes do aluno e armazena na sessão
                    AreaDoAluno aluno = loginDao.buscarAlunoPorEmail(nome);  // Método para buscar detalhes do aluno

                    // Armazena o aluno logado na sessão
                    AlunoSession.setAlunoLogado(aluno);

                    Main.setRoot("AreaDoAluno-view");
                }
                else if ("Admin".equals(tipoUsuario)) {
                    Main.setRoot("TelaMenu-view");
                }
                else if ("Professor".equals(tipoUsuario)) {
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

    private void redirecionarParaRecuperacaoSenha(String nomeUsuario) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cyber/cybernexuspacer/fxml/recuperacaoSenha-view.fxml"));
        Parent root = loader.load();

        // Obtém o controller da tela de recuperação de senha e define o nome do usuário
        RecuperarSenhaController recuperarSenhaController = loader.getController();
        recuperarSenhaController.setNomeUsuario(nomeUsuario);

        // Configura a nova cena
        Stage stage = (Stage) btnEntrar.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
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
