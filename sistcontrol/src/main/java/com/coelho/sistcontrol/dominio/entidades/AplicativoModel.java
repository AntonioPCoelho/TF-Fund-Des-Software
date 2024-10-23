package com.coelho.sistcontrol.dominio.entidades;

import java.math.BigDecimal;

public class AplicativoModel {
    private long Id;
    private String nome;
    private BigDecimal custoMensal;
    
    public AplicativoModel(long Id, String nome, BigDecimal custoMensal) {
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

    public BigDecimal getCustoMensal() { 
        return this.custoMensal; 
    }

    public void setCustoMensal(BigDecimal custoMensal) { 
        this.custoMensal = custoMensal; 
    }

    @Override
    public String toString() {
        return "AplicativoModel [Id=" + Id + ", nome=" + nome + ", custoMensal=" + custoMensal + "]";
    }

}
