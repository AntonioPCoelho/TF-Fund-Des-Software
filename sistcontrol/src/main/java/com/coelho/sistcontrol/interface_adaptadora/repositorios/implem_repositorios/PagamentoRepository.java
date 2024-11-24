package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Pagamento;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.PagamentoRepositoryJPA;

@Component
public class PagamentoRepository implements IPagamentoRepository {

    private PagamentoRepositoryJPA pagamentos;

    public PagamentoRepository(PagamentoRepositoryJPA pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public PagamentoModel save(PagamentoModel pagamento) {
        return pagamentos.save(Pagamento.fromModel(pagamento)).toModel();        
    }

    @Override
    public void deleteAll() {
        pagamentos.deleteAll();
        
    }
    @Override
    public Optional<PagamentoModel> findByAssinaturaId(long ass_id) {
        return pagamentos.findByAssinaturaId(ass_id).map(Pagamento::toModel);
        
    }
}
