package com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aplicativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public Aplicativo(Long Id, String nome, double custoMensal) {
        this.Id = Id;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    private String nome;

    private double custoMensal;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCustoMensal() {
        return custoMensal;
    }

    public void setCustoMensal(double custoMensal) {
        this.custoMensal = custoMensal;
    }
    public static Aplicativo fromModel(AplicativoModel model) {
        
        return new Aplicativo(model.getId(), model.getNome(), model.getCustoMensal());
        
    }
    
    public AplicativoModel toModel() {
        return new AplicativoModel(this.Id, this.nome, this.custoMensal);
    }

    
}
