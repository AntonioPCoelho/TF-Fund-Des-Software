package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;
import org.springframework.stereotype.Component;

import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Pagamento;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.PagamentoRepositoryJPA;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PagamentoRepository implements IPagamentoRepository {

    private PagamentoRepositoryJPA pagamentos;

    public PagamentoRepository(PagamentoRepositoryJPA pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public List<PagamentoModel> findByid(Long id) {
        return pagamentos.findByid(id);
    }

    @Override
    public Optional<PagamentoModel> findById(Long id) {
        return pagamentos.findById(id).map(Pagamento::toModel);
    }

    @Override
    public List<PagamentoModel> findByDataPagamento(Date dataPagamento) {
        return pagamentos.findByDataPagamento(dataPagamento);
    }

    @Override
    public PagamentoModel save(PagamentoModel pagamento) {
        return pagamentos.save(Pagamento.fromModel(pagamento)).toModel();        
    }

    @Override
    public List<PagamentoModel> findAll() {
        return pagamentos.findAll().stream().map(Pagamento::toModel).toList();
    }
}
