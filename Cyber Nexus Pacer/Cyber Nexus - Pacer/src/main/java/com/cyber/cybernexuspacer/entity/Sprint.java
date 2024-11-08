package com.cyber.cybernexuspacer.entity;

import java.util.Date;

public class Sprint {
    private int id;
    private String numSprint;
    private Date dataInicio;
    private Date dataFim;
    private boolean markedForDeletion;

    // Construtores
    public Sprint(int id, String numSprint, Date dataInicio, Date dataFim) {
        this.id = id;
        this.numSprint = numSprint;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.markedForDeletion = false; // Por padrão, não está marcada para exclusão
    }


    //getters
    public int getId() { return id; }
    public String getNumSprint() { return numSprint; }
    public java.sql.Date getDataInicio() { return (java.sql.Date) dataInicio; }
    public java.sql.Date getDataFim() { return (java.sql.Date) dataFim; }
    public boolean isMarkedForDeletion() { return markedForDeletion; }

    //setters
    public void setId(int id) { this.id = id; }
    public void setNumSprint(String numSprint) { this.numSprint = numSprint; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }
    public void setMarkedForDeletion() {
        this.markedForDeletion = true;
    }
}
