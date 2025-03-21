package com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Pagamento;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public interface PagamentoRepositoryJPA extends JpaRepository<Pagamento, Long> {
    List<PagamentoModel> findByid(Long id);
    List<PagamentoModel> findByDataPagamento(Date dataPagamento);

    @Query("SELECT p FROM Pagamento p WHERE p.assinatura.id = :assinaturaId")
    Optional<Pagamento> findByAssinaturaId(@Param("assinaturaId") Long assinaturaId);

}
