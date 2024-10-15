package com.coelho.sistcontrol.dominio.entidades;

public class AplicativoModel {
    private long codigo;
    private String nome;
    private double custoMensal;
    
    public AplicativoModel(long codigo, String nome, double custoMensal) {
        this.codigo = codigo;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public long getCodigo() { 
        return this.codigo; 
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
        return "AplicativoModel [codigo=" + codigo + ", nome=" + nome + ", custoMensal=" + custoMensal + "]";
    }

}
