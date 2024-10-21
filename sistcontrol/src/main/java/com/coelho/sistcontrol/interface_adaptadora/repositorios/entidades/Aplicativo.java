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
    private Long codigo;

    public Aplicativo(Long codigo, String nome, double custoMensal) {
        this.codigo = codigo;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    private String nome;

    private double custoMensal;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
        
        return new Aplicativo(model.getCodigo(), model.getNome(), model.getCustoMensal());
        
    }
    
    public AplicativoModel toModel() {
        return new AplicativoModel(this.codigo, this.nome, this.custoMensal);
    }

    
}
