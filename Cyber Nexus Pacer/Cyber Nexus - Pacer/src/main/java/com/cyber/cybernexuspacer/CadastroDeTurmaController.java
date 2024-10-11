package com.cyber.cybernexuspacer;

import com.cyber.cybernexuspacer.controller.Main;
import com.cyber.cybernexuspacer.dao.CadastroTurmaDao;
import com.cyber.cybernexuspacer.entity.CadastroTurma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;

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
    private ScrollBar scrollBar;

    @FXML
    void onClickbtnConfirmarAlunos(ActionEvent event) throws IOException, SQLException {

        CadastroTurma cadastroTurma = new CadastroTurma();
        cadastroTurma.setNome("Tiago");
        cadastroTurma.setSenha("fatec2024");
        cadastroTurma.setEmail("tiag.santos159@fatec.sp.gov.br");

        new CadastroTurmaDao().CadastrarAlunos(cadastroTurma);


        //Main.setRoot("criterios-view");
    }

}
