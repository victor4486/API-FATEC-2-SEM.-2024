package com.cyber.cybernexuspacer.entity;

public class Criterio {
    private int id;
    private String nome;
    private String descricao;

    // Construtores, Getters e Setters
    public User(int id, String name, String email) {
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
