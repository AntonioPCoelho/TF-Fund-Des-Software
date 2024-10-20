package com.coelho.sistcontrol.dominio.entidades;

import java.time.LocalDate;

public class AssinaturaModel {
    private long codigo;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;
    private AplicativoModel app;
    private ClienteModel cliente;

    public AssinaturaModel(long codigo, LocalDate inicioVigencia, LocalDate fimVigencia, AplicativoModel app, ClienteModel cliente) {
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

    public LocalDate getInicioVigencia() {
        return this.inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return this.fimVigencia;
    }

    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    @Override
    public String toString() {
        return "AssinaturaModel [codigo=" + codigo + ", inicioVigencia=" + inicioVigencia + ", fimVigencia="
                + fimVigencia + "]";
    }

}
