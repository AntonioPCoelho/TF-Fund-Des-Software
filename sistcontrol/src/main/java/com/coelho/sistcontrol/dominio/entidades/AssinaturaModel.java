package com.coelho.sistcontrol.dominio.entidades;

import java.util.Date;

public class AssinaturaModel {
    private long Id;
    private Date inicioVigencia;
    private Date fimVigencia;
    private AplicativoModel app;
    private ClienteModel cliente;
    private String status;

    public AssinaturaModel(long Id, Date inicioVigencia, Date fimVigencia, AplicativoModel app, ClienteModel cliente, String status) {
        this.Id = Id;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.app = app;
        this.cliente = cliente;
        this.status = status;
    }

    public AplicativoModel getApp() {
        return app;
    }

    public void setApp(AplicativoModel app) {
        this.app = app;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public long getId() {
        return this.Id;
    }

    public Date getInicioVigencia() {
        return this.inicioVigencia;
    }

    public Date getFimVigencia() {
        return this.fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AssinaturaModel [Id=" + Id + ", inicioVigencia=" + inicioVigencia + ", fimVigencia="
                + fimVigencia + "]";
    }

}
