package com.cyber.cybernexuspacer.entity;

import java.util.Date;

public class Sprint {
    private String numSprint;
    private Date dataInicio;
    private Date dataFim;

    // Construtores, getters e setters
    public Sprint(String numSprint, Date dataInicio, Date dataFim) {
        this.numSprint = numSprint;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getNumSprint() { return numSprint; }
    public java.sql.Date getDataInicio() { return (java.sql.Date) dataInicio; }
    public java.sql.Date getDataFim() { return (java.sql.Date) dataFim; }

    public void setNumSprint(String numSprint) { this.numSprint = numSprint; }
    public void setDataInicio(Date dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(Date dataFim) { this.dataFim = dataFim; }
}
