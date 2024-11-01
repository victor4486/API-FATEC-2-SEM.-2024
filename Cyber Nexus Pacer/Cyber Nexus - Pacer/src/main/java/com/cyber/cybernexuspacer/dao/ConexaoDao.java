package com.cyber.cybernexuspacer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDao {
    //private static final String url = "jdbc:postgresql://aws-0-sa-east-1.pooler.supabase.com:6543/postgres"; // Substitua pelos seus dados do Supabase
    //private static final String user = "postgres.avytuuhwmswsbshvwipz"; // Substitua pelo usuário do Supabase
    //private static final String password = "CyberNexusFatec"; // Substitua pela senha do Supabase



    private static final String url = "jdbc:mysql://localhost:3306/pacerapi?useTimezone=true&serverTimezone=UTC"; // Substitua pelos seus dados do Supabase
    private static final String user = "root"; // Substitua pelo usuário do Supabase
    private static final String password = "*******"; // Substitua pela senha do Supabase

    private static Connection conn;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Class.forName("org.postgresql.Driver"); // Atualizado para o driver PostgreSQL
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            } else {
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
