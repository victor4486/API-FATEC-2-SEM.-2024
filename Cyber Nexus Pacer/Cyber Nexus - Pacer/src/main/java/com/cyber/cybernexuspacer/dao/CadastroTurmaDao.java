package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.Aluno;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroTurmaDao {

    public static void CadastrarAlunos(Aluno Aluno) throws SQLException {
        String sql = "INSERT INTO ALUNOS (NOME, EMAIL, GRUPO) VALUES(?,?,?)";

        PreparedStatement stmt = null;

        try {
            stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setString(1, Aluno.getAluno());
            stmt.setString(2, Aluno.getEmail());
            stmt.setString(3, Aluno.getGrupo());
            stmt.executeUpdate();
            stmt.close();


        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static boolean alunoExists(Aluno aluno) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ALUNOS WHERE NOME = ?";
        try (PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql)) {
            stmt.setString(1, aluno.getAluno());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
