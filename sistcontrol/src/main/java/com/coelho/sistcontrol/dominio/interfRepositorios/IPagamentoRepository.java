package com.coelho.sistcontrol.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;
import java.util.Date;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;

public interface IPagamentoRepository {
    PagamentoModel save(PagamentoModel pagamento);
    List<PagamentoModel> findAll();
    List<PagamentoModel> findByid(Long id);
    Optional<PagamentoModel> findById(Long id);
    List<PagamentoModel> findByDataPagamento(Date dataPagamento);
}
