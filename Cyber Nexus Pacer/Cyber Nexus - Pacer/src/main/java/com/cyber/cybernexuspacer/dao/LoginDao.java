package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean verificarLogin(String nome, String senha, String tipoUsuario) throws SQLException {
        String sql = "SELECT * FROM USUARIOS WHERE EMAIL = ? AND SENHA = ? AND TIPO_USUARIO = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, tipoUsuario);
            System.out.println(stmt);

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
        String sql = "UPDATE USUARIOS SET SENHA = ? WHERE EMAIL = ?";
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
        String sql = "SELECT * FROM ALUNOS WHERE EMAIL = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        AreaDoAluno areaDoAluno = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);

            rs = stmt.executeQuery();


            if (rs.next()) {
                int idAluno = rs.getInt("id");
                String nome = rs.getString("nome");
                String nomeGrupo = rs.getString("grupo");


                // Cria o objeto AreaDoAluno e retorna
                areaDoAluno = new AreaDoAluno(idAluno,nome, email, nomeGrupo, "fatec2024", "Aluno");


            } else {
                return null;  // Retorna null se não encontrar o aluno
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return areaDoAluno;
    }
}
