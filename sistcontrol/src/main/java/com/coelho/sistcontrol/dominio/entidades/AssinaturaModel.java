package com.coelho.sistcontrol.dominio.entidades;

import java.sql.Date;

public class AssinaturaModel {
    private long codigo;
    private Date inicioVigencia;
    private Date fimVigencia;

    public AssinaturaModel(long codigo, Date inicioVigencia, Date fimVigencia) {
        this.codigo = codigo;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
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
