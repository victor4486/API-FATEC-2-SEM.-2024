package com.cyber.cybernexuspacer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
    private static final String url = "jdbc:mysql://localhost:3306/pacerapi?useTimezone=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "your_password_here";

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Atualizado para o driver MySQL
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão estabelecida com sucesso!");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            return null;
        }
    }

    public static void testConnection() {
        Connection connection = getConnection();
        if (connection != null) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } else {
            System.out.println("Falha na conexão com o banco de dados.");
        }
    }
}
