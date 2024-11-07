package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean verificarLogin(String nome, String senha, String tipoUsuario) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE EMAIL = ? AND SENHA = ? AND TIPO_USUARIO = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, tipoUsuario);

            rs = stmt.executeQuery();

            return rs.next();  // Retorna verdadeiro se houver correspondência
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    public boolean atualizarSenha(String nome, String novaSenha) throws SQLException {
        String sql = "UPDATE USUARIO SET SENHA = ? WHERE NOME = ?";
        PreparedStatement stmt = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, novaSenha);
            stmt.setString(2, nome);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;  // Retorna verdadeiro se a atualização foi bem-sucedida
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) stmt.close();
        }
    }

    // Método para buscar os detalhes do aluno por email
    public AreaDoAluno buscarAlunoPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM alunos WHERE EMAIL = ?";
        String sqlNomeGrupo = "SELECT grupo FROM grupos WHERE id = ?";

        PreparedStatement stmt = null;
        PreparedStatement stmtNomeDoGrupo = null;
        ResultSet rs = null;
        ResultSet rsNomeDoGrupo = null;
        AreaDoAluno areaDoAluno = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);

            rs = stmt.executeQuery();


            if (rs.next()) {
                String nome = rs.getString("nome");
                String idGrupo = rs.getString("id_grupo");

                // Consulta para buscar os alunos do mesmo grupo
                stmtNomeDoGrupo = connection.prepareStatement(sqlNomeGrupo);

                stmtNomeDoGrupo.setInt(1, Integer.parseInt(idGrupo));

                rsNomeDoGrupo = stmtNomeDoGrupo.executeQuery();

                if (rsNomeDoGrupo.next()) {

                    String nomeGrupo = rsNomeDoGrupo.getString("grupo");

                    // Cria o objeto AreaDoAluno e retorna
                    areaDoAluno = new AreaDoAluno(nome, email, nomeGrupo, "fatec2024", "Aluno");
                }

            } else {
                return null;  // Retorna null se não encontrar o aluno
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (stmtNomeDoGrupo != null) stmtNomeDoGrupo.close();
            if (rsNomeDoGrupo != null) rsNomeDoGrupo.close();
        }
        return areaDoAluno;
    }

}
