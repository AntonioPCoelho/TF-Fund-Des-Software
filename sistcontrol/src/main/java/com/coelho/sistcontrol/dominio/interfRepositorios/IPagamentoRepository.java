package com.coelho.sistcontrol.dominio.interfRepositorios;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;

public interface IPagamentoRepository {
    PagamentoModel save(PagamentoModel pagamento);
}
