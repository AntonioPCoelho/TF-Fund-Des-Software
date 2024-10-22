package com.coelho.sistcontrol.dominio.interfRepositorios;
import java.util.List;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;


public interface IAssinaturaRepository {
    <AssinaturaModel> Assinatura save();
    Optional<AssinaturaModel> findByClienteIdAndAplicativoId();
    List<Assinatura> findByClienteId();
    List<Cliente> findClientesByAplicativo();
    Optional<Assinatura> findById();
    List<Assinatura> findByStatus();
    List<Assinatura> findAll();

    
}
