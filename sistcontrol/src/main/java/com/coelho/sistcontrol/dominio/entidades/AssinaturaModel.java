package com.coelho.sistcontrol.dominio.entidades;

import java.time.LocalDate;

public class AssinaturaModel {
    private long codigo;
    private LocalDate inicioVigencia;
    private LocalDate fimVigencia;

    public AssinaturaModel(long codigo, LocalDate inicioVigencia, LocalDate fimVigencia) {
        this.codigo = codigo;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
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
