package com.coelho.sistcontrol.dominio.servicos;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.aplicacao.dtos.PagamentoDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;

@Service
public class PagamentoService {

    private final IPagamentoRepository pagamentoRepository;
    private final IAssinaturaRepository assinaturaRepository;

    public PagamentoService(IPagamentoRepository pagamentoRepository, IAssinaturaRepository assinaturaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    // Método para registrar um novo pagamento
    public PagamentoDTO registrarPagamento(PagamentoDTO pagamentoDTO) {
        // Buscar a assinatura associada ao ID
        AssinaturaModel assinatura = assinaturaRepository.findById(pagamentoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Assinatura não encontrada"));

        // Verificar se o valor pago corresponde ao custo mensal da assinatura
        double valorEsperado = assinatura.getApp().getCustoMensal();
        if (pagamentoDTO.getValorPago() != valorEsperado) {
            throw new IllegalArgumentException("Valor pago está incorreto. Esperado: " + valorEsperado);
        }

        // Criar a entidade PagamentoModel e salvar
        PagamentoModel novoPagamento = new PagamentoModel(
                pagamentoDTO.getId(),
                assinatura,
                pagamentoDTO.getValorPago(),
                pagamentoDTO.getDataPagamento(),
                pagamentoDTO.getPromocao()
        );

        PagamentoModel pagamentoSalvo = pagamentoRepository.save(novoPagamento);

        // Retornar o DTO do pagamento salvo
        return new PagamentoDTO(
                pagamentoSalvo.getId(),
                pagamentoSalvo.getValorPago(),
                pagamentoSalvo.getDataPagamento(),
                pagamentoSalvo.getPromocao(),
                pagamentoSalvo.getAssinatura()
        );
    }
}
