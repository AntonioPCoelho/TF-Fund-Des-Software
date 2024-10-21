package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepositoryJPA;

public class AplicativoRepository {
    private AplicativoRepositoryJPA aplicativos;

    public AplicativoRepository(AplicativoRepositoryJPA aplicativos){
        this.aplicativos = aplicativos;
    }
    // Implementar interface do dotti

}
