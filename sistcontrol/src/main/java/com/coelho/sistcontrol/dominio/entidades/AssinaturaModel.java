package com.coelho.sistcontrol.dominio.entidades;

import java.util.Date;

public class AssinaturaModel {
    private long codigo;
    private Date inicioVigencia;
    private Date fimVigencia;
    private AplicativoModel app;
    private ClienteModel cliente;

    public AssinaturaModel(long codigo, Date inicioVigencia, Date fimVigencia, AplicativoModel app, ClienteModel cliente) {
        this.codigo = codigo;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.app = app;
        this.cliente = cliente;
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

    public long getCodigo() {
        return this.codigo;
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

    @Override
    public String toString() {
        return "AssinaturaModel [codigo=" + codigo + ", inicioVigencia=" + inicioVigencia + ", fimVigencia="
                + fimVigencia + "]";
    }

}
