package com.cyber.cybernexuspacer.entity;

public class Criterio {
    private int id;
    private String titulo;
    private String descricao;

    // Construtores, Getters e Setters
    public Criterio( String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public String geetTitulo() { return titulo; }
    public String getDescricao() { return descricao; }

    public void setId(int id) { this.id = id; }
    public void settitulo(String titulo) { this.titulo = titulo; }
    public void setEmail(String descricao) { this.descricao = descricao; }
}
