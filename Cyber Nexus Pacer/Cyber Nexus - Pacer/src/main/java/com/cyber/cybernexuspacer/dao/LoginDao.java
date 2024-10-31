package com.cyber.cybernexuspacer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public boolean verificarLogin(String nome, String senha, String tipoUsuario) throws SQLException {
        String sql = "SELECT * FROM USUARIO WHERE NOME = ? AND SENHA = ? AND TIPO_USUARIO = ?";
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

}
