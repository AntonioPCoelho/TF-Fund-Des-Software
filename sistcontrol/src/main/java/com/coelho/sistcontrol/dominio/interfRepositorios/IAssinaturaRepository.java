package com.coelho.sistcontrol.dominio.interfRepositorios;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;


public interface IAssinaturaRepository {
    <AssinaturaModel> Assinatura save();
    Optional<AssinaturaModel> findByClienteIdAndAplicativoId();
    Optional<AplicativoModel> findBy();

    
}
