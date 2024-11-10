package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroTurmaDao {

    public static void CadastrarAlunos(AreaDoAluno aluno) throws SQLException {

        String sqlUsuario = "INSERT INTO USUARIOS (EMAIL, SENHA, TIPO_USUARIO) VALUES(?,?,?) ";
        String sqlGrupo = "INSERT IGNORE INTO GRUPOS (GRUPO) VALUES(?) "; // Use "ON CONFLICT (EMAIL) DO NOTHING" se estiver usando PostgreSQL
        String sqlAluno = "INSERT INTO ALUNOS (NOME,EMAIL, GRUPO) VALUES(?, ?, ?)";
        String sqlNotasGrupos =
                "INSERT INTO notas_grupos (grupo, num_sprint, nota_grupo) " +
                        "SELECT g.grupo, s.num_sprint, 0 " +
                        "FROM grupos g " +
                        "CROSS JOIN sprints s " +
                        "WHERE NOT EXISTS ( " +
                        "SELECT 1 FROM notas_grupos ng " +
                        "WHERE ng.grupo = g.grupo " +
                        "AND ng.num_sprint = s.num_sprint " +
                        ");";


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

            //Inserir Notas dos Grupos
            PreparedStatement stmt = connection.prepareStatement(sqlNotasGrupos);
            stmt.executeUpdate();

            connection.commit(); // Confirmar a transação

            System.out.println("Alunos Cadastrados com sucesso!");

        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback(); // Desfazer a transação em caso de erro
            }
            e.printStackTrace();
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
