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
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "aplicativo_id")
    private Aplicativo aplicativo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date inicioVigencia;

    private Date fimVigencia;

    public Long getCodigo() {
        return codigo;
    }

    public Assinatura(Long codigo, Aplicativo aplicativo, Cliente cliente, Date inicioVigencia,
            Date fimVigencia) {
        this.codigo = codigo;
        this.aplicativo = aplicativo;
        this.cliente = cliente;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public static Assinatura fromModel(AssinaturaModel model) {
        return new Assinatura(model.getCodigo(), Aplicativo.fromModel(model.getApp()),
                Cliente.fromModel(model.getCliente()),
                model.getInicioVigencia(),
                model.getFimVigencia());
    }

    public AssinaturaModel toModel() {
        return new AssinaturaModel(this.codigo, this.inicioVigencia, this.fimVigencia, this.aplicativo.toModel(),
                this.cliente.toModel());

    }

}
