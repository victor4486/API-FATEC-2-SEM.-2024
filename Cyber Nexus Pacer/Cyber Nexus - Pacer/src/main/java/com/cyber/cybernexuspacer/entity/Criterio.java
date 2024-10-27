package com.cyber.cybernexuspacer.entity;

import com.cyber.cybernexuspacer.dao.ConexaoDao;

public class Criterio {
    private int id;
    private String nome;
    private String descricao;

    // Construtores, Getters e Setters
    public Criterio(int id, String name, String email) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    public void setId(int id) { this.id = id; }
    public void setName(String nome) { this.nome = nome; }
    public void setEmail(String descricao) { this.descricao = descricao; }
}
