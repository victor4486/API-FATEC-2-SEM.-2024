package com.cyber.cybernexuspacer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
    private static final String url = "jdbc:mysql://localhost:3306/pacerapi";
    private static final String user = "root";
    private static final String password = "Tiago123";

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,password);
                return conn;
            }
            else{
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
