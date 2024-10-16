package com.cyber.cybernexuspacer.entity;

import com.cyber.cybernexuspacer.dao.ConexaoDao;

public class CadastroTurma {
    private Long id;

    private String nome;

    private String senha;

    private String email;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public static void main(String[] args) {
        // Chama o método de teste de conexão
        ConexaoDao.testConnection();
    }
}
