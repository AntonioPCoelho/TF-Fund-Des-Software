package com.coelho.sistcontrol.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;


public interface IAplicativoRepository {
    List<AplicativoModel> findAll();
    Optional<AplicativoModel> findById(Long id);
    AplicativoModel save(AplicativoModel app);
    void deleteAll();
}
