package com.cyber.cybernexuspacer.controller;

import com.cyber.cybernexuspacer.dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class RecuperarSenhaController {
    @FXML
    private AnchorPane anchorFundo;

    @FXML
    private Button btnClickEnviar;

    @FXML
    private ImageView imgFatec;

    @FXML
    private Label lblTxtFatec;

    @FXML
    private Label lblTxtSistemaPacer;

    @FXML
    private PasswordField fieldNovaSenha;

    @FXML
    private PasswordField fieldRepetirSenha;

    @FXML
    private Text textInsiraEnail;

    @FXML
    private Text txtDigiteNovamente;

    @FXML
    private Text txtDigiteSenhha;

    //@FXML
    //void onClickEnviar(ActionEvent event) throws IOException {
    //
    //    Main.setRoot("AreaDoAluno-view");
    //}

    private LoginDao loginDao = new LoginDao();
    private String nomeUsuario;

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    @FXML
    public void onClickEnviar() throws SQLException {

        String novaSenha = fieldNovaSenha.getText();
        String repetirSenha = fieldRepetirSenha.getText();

        // Verifica se as senhas coincidem
        if (novaSenha.equals(repetirSenha)) {
            try {
                // Atualiza a senha no banco de dados
                boolean sucesso = loginDao.atualizarSenha(nomeUsuario, novaSenha);
                if (sucesso) {
                    System.out.println("Senha atualizada com sucesso!");
                } else {
                    System.out.println("Erro ao atualizar senha!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro de conexão com o banco de dados.");
            }
        } else {
            System.out.println("As senhas não coincidem!");
        }

        boolean sucesso = loginDao.atualizarSenha(nomeUsuario, novaSenha);

        if (sucesso) {
            System.out.println("Senha atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar a senha.");
        }
    }

}


