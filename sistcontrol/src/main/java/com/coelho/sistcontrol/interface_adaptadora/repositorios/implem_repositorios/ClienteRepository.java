package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.ClienteRepositoryJPA;

public class ClienteRepository {
    private ClienteRepositoryJPA clientes;


    public ClienteRepository(ClienteRepositoryJPA clientes){
        this.clientes = clientes;
    }
}
