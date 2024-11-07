package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroTurmaDao {

    public static void CadastrarAlunos(AreaDoAluno aluno) throws SQLException {

        String sqlUsuario = "INSERT INTO USUARIO (EMAIL, SENHA, TIPO_USUARIO) VALUES(?,?,?) ";
        String sqlGrupo = "INSERT IGNORE INTO GRUPOS (GRUPO,SPRINT,NOTA) VALUES(?,0.00,0.00) "; // Use "ON CONFLICT (EMAIL) DO NOTHING" se estiver usando PostgreSQL
        String sqlAluno = "INSERT INTO ALUNOS (NOME,EMAIL, ID_GRUPO) VALUES(?, ?, (SELECT ID FROM GRUPOS WHERE GRUPO = ?))";


        PreparedStatement stmt = null;
        Connection connection = null;
        PreparedStatement stmtGrupo = null;
        PreparedStatement stmtAluno = null;
        PreparedStatement stmtUsuario = null;

        try {

            connection = ConexaoDao.getConnection();
            connection.setAutoCommit(false); // Começar uma transação

            //Inserir usuario
            stmtUsuario = connection.prepareStatement(sqlUsuario);
            stmtUsuario.setString(1, aluno.getEmail());
            stmtUsuario.setString(2, aluno.getSenha());
            stmtUsuario.setString(3, aluno.getTipo_usuario());
            stmtUsuario.executeUpdate();

            // Insert grupo
            stmtGrupo = connection.prepareStatement(sqlGrupo);
            stmtGrupo.setString(1, aluno.getGrupo());
            stmtGrupo.executeUpdate();

            // Inserir aluno
            stmtAluno = connection.prepareStatement(sqlAluno);
            stmtAluno.setString(1, aluno.getNomeAluno());
            stmtAluno.setString(2, aluno.getEmail());
            stmtAluno.setString(3, aluno.getGrupo());
            stmtAluno.executeUpdate();


            connection.commit(); // Confirmar a transação

            System.out.println("Alunos Cadastrados com sucesso!");

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Desfazer a transação em caso de erro
            }
            e.printStackTrace();
        } finally {
            // Fechar os statements e a conexão
            if (stmtGrupo != null) stmtGrupo.close();
            if (stmtAluno != null) stmtAluno.close();
            if (stmtUsuario != null) stmtUsuario.close();

        }

    }

    public static boolean alunoExists(AreaDoAluno aluno) throws SQLException {
        String sql = "SELECT COUNT(*) FROM ALUNOS WHERE NOME = ?";
        try (PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql)) {
            stmt.setString(1, aluno.getNomeAluno());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
