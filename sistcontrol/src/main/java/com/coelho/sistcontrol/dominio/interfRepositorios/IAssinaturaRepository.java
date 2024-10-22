package com.coelho.sistcontrol.dominio.interfRepositorios;
import java.util.List;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;


public interface IAssinaturaRepository {
    AssinaturaModel save(AssinaturaModel assinatura);
    Optional<AssinaturaModel> findByClienteIdAndAplicativoId(Long clienteId, Long aplicativoId);
    Optional<AssinaturaModel> findById(Long id);

    //Optional<AplicativoModel> findBy();
    List<AssinaturaModel> findAll();
    List<AssinaturaModel> findByClienteId(Long clienteId);
    List<AssinaturaModel> findByStatus(String status);
    List<ClienteModel> findClientesByAplicativo(Long appId);
    
}
