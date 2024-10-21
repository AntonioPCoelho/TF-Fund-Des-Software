package com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Cliente;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {

    List<Assinatura> findByCliente(Cliente cliente);

    @Query("SELECT a.cliente FROM Assinatura a WHERE a.aplicativo = :aplicativo")
    List<Cliente> findClientesByAplicativo(@Param("aplicativo") Aplicativo aplicativo);

    Optional<Assinatura> findByClienteIdAndAplicativoId(Long clienteId, Long aplicativoId);
    List<Assinatura> findByAplicativoId(Long aplicativoId);

    List<Assinatura> findByClienteId(Long clienteId);
    List<Assinatura> findByStatus(String status);
}
