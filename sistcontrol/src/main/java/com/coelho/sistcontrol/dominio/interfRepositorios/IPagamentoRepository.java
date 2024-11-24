package com.coelho.sistcontrol.dominio.interfRepositorios;
import java.util.Optional;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;

public interface IPagamentoRepository {
    PagamentoModel save(PagamentoModel pagamento);
    void deleteAll();
    Optional<PagamentoModel> findByAssinaturaId(long ass_id);
}
