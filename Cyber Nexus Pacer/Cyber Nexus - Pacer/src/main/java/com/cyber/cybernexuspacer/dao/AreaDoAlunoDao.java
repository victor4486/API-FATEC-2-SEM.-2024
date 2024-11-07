package com.cyber.cybernexuspacer.dao;

import com.cyber.cybernexuspacer.entity.AreaDoAluno;
import com.cyber.cybernexuspacer.session.AlunoSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDoAlunoDao {

    // Método para listar os alunos do mesmo grupo que o aluno logado
    public List<AreaDoAluno> listarAlunos() throws SQLException {
        List<AreaDoAluno> alunos = new ArrayList<>();

        // Verifica se o aluno está logado
        AreaDoAluno alunoLogado = AlunoSession.getAlunoLogado();
        if (alunoLogado == null) {
            throw new SQLException("Nenhum aluno logado.");
        }

        // Obtém o e-mail do aluno logado
        String emailAlunoLogado = alunoLogado.getEmail();

        // Consulta SQL para obter o id_grupo do aluno
        String sqlGrupo = "SELECT id_grupo FROM alunos WHERE email = ?";
        String sqlAlunosDoGrupo = "SELECT id, nome, email FROM alunos WHERE id_grupo = ?";
        String sqlNomeGrupo = "SELECT grupo FROM grupos WHERE id = ?";

        PreparedStatement stmtGrupo = null;
        PreparedStatement stmtAlunosDoGrupo = null;
        PreparedStatement stmtNomeDoGrupo = null;
        ResultSet rsGrupo = null;
        ResultSet rsAlunosDoGrupo = null;
        ResultSet rsNomeDoGrupo = null;

        try {
            // Conexão com o banco de dados
            Connection connection = ConexaoDao.getConnection();
            assert connection != null;

            // Obtém o id_grupo do aluno logado
            stmtGrupo = connection.prepareStatement(sqlGrupo);
            stmtGrupo.setString(1, emailAlunoLogado);
            rsGrupo = stmtGrupo.executeQuery();

            if (rsGrupo.next()) {
                int idGrupo = rsGrupo.getInt("id_grupo");

                // Consulta para buscar os alunos do mesmo grupo
                stmtAlunosDoGrupo = connection.prepareStatement(sqlAlunosDoGrupo);
                stmtNomeDoGrupo = connection.prepareStatement(sqlNomeGrupo);

                stmtAlunosDoGrupo.setInt(1, idGrupo);
                stmtNomeDoGrupo.setInt(1, idGrupo);

                rsAlunosDoGrupo = stmtAlunosDoGrupo.executeQuery();
                rsNomeDoGrupo = stmtNomeDoGrupo.executeQuery();

                if (rsNomeDoGrupo.next()) {

                    String grupo = rsNomeDoGrupo.getString("grupo");


                    // Itera sobre o ResultSet e preenche a lista de alunos
                    while (rsAlunosDoGrupo.next()) {
                        int idAluno = rsAlunosDoGrupo.getInt("id");
                        String nome = rsAlunosDoGrupo.getString("nome");
                        String email = rsAlunosDoGrupo.getString("email");


                        // Cria e adiciona o aluno à lista
                        AreaDoAluno aluno = new AreaDoAluno(nome, email, grupo, "fatec2024", "Aluno");
                        aluno.setIdAlunoReceptor(idAluno);
                        alunos.add(aluno);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Lança a exceção para ser tratada em um nível superior
        } finally {
            // Fechamento de recursos
            if (rsGrupo != null) rsGrupo.close();
            if (rsAlunosDoGrupo != null) rsAlunosDoGrupo.close();
            if (stmtGrupo != null) stmtGrupo.close();
            if (stmtAlunosDoGrupo != null) stmtAlunosDoGrupo.close();
        }

        return alunos;  // Retorna a lista de alunos
    }

    public double buscaNota() throws SQLException {
        String sql = "SELECT NOTA FROM GRUPOS WHERE GRUPO = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Connection connection = ConexaoDao.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, AlunoSession.getAlunoLogado().getGrupo());
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("nota"); // Retorna a nota do grupo
            } else {
                throw new SQLException("Nota não encontrada para o grupo do aluno.");
            }

        } catch (SQLException e) {
            // Trate a exceção de forma adequada (log, rethrow, etc)
            throw new RuntimeException("Erro ao buscar a nota: " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close(); // Fechar o ResultSet
            if (stmt != null) stmt.close(); // Fechar o PreparedStatement
        }
    }
}
