package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AssinaturaRepositoryJPA;

public class AssinaturaRepository  { // implements interfaces do dotti
    private AssinaturaRepositoryJPA assinaturas;

    public AssinaturaRepository(AssinaturaRepositoryJPA assinaturas){
        this.assinaturas = assinaturas;
    }
    // metodos dotti
}
