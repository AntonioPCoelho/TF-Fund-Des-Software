package com.coelho.sistcontrol.dominio.entidades;

public class AplicativoModel {
    private long Id;
    private String nome;
    private double custoMensal;
    
    public AplicativoModel(long Id, String nome, double custoMensal) {
        this.Id = Id;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public long getId() { 
        return this.Id; 
    }

    public String getNome() { 
        return this.nome; 
    }

    public double getCustoMensal() { 
        return this.custoMensal; 
    }

    public void setCustoMensal(double custoMensal) { 
        this.custoMensal = custoMensal; 
    }

    @Override
    public String toString() {
        return "AplicativoModel [Id=" + Id + ", nome=" + nome + ", custoMensal=" + custoMensal + "]";
    }

}
