package com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades;

import java.util.Date;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "aplicativo_Id")
    private Aplicativo aplicativo;

    @ManyToOne
    @JoinColumn(name = "cliente_Id")
    private Cliente cliente;

    private String status;

    private Date inicioVigencia;

    private Date fimVigencia;

    public Long getId() {
        return Id;
    }

    public Assinatura(Long Id, Aplicativo aplicativo, Cliente cliente, Date inicioVigencia,
            Date fimVigencia, String status) {
        this.Id = Id;
        this.aplicativo = aplicativo;
        this.cliente = cliente;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.status = status;
    }
    protected Assinatura(){}


    public void setId(Long Id) {
        this.Id = Id;
    }

    public Aplicativo getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(Aplicativo aplicativo) {
        this.aplicativo = aplicativo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getstatus() {
        return this.status;
    }

    public void setstatus(String status) {
        this.status = status;
    } 

    public static Assinatura fromModel(AssinaturaModel model) {
        return new Assinatura(model.getId(), Aplicativo.fromModel(model.getApp()),
                Cliente.fromModel(model.getCliente()),
                model.getInicioVigencia(),
                model.getFimVigencia(),
                model.getstatus());
    }

    public AssinaturaModel toModel() {
        return new AssinaturaModel(this.Id, this.inicioVigencia, this.fimVigencia, this.aplicativo.toModel(),
                this.cliente.toModel(), this.status);

    }

}
