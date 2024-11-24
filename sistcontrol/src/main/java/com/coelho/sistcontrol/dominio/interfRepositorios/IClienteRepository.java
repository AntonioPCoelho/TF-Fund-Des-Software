package com.coelho.sistcontrol.dominio.interfRepositorios;


import java.util.List;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.ClienteModel;

public interface IClienteRepository {
    ClienteModel save(ClienteModel cliente);
    List<ClienteModel> findAll();
    Optional<ClienteModel> findById(Long id); 
    void deleteAll();
}
