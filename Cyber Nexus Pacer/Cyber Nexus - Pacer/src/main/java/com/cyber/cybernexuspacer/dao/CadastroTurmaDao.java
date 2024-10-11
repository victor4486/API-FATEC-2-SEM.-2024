package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.CadastroTurma;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroTurmaDao {

    public void CadastrarAlunos(CadastroTurma CadastroTurma) throws SQLException {
        String sql = "INSERT INTO USUARIO (NOME, SENHA, EMAIL) VALUES(?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setString(1, CadastroTurma.getNome());
            stmt.setString(2, CadastroTurma.getSenha());
            stmt.setString(3, CadastroTurma.getEmail());
            stmt.executeUpdate();
            stmt.close();





        } catch (SQLException e) {
            e.printStackTrace();

        }



    }
}
