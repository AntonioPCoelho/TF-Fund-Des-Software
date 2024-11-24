package com.coelho.sistcontrol.dominio.servicos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.aplicacao.dtos.PagamentoDTO;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
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
        BigDecimal valorEsperado = assinatura.getApp().getCustoMensal();
        if (pagamentoDTO.getValorPago().compareTo(valorEsperado) != 0) {
            throw new IllegalArgumentException("Valor pago está incorreto. Esperado: " + valorEsperado);
        }

        // Criar a entidade PagamentoModel e salvar
        PagamentoModel novoPagamento = new PagamentoModel(
                pagamentoDTO.getId(),
                pagamentoDTO.getValorPago(),
                pagamentoDTO.getDataPagamento(),
                pagamentoDTO.getPromocao(),
                assinatura
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

    public void registrarPagamento(PagamentoRequestDTO request, AssinaturaModel assinatura) {
        // Criar o pagamento utilizando o construtor correto
        PagamentoModel pagamento = new PagamentoModel(
            0, // O ID será gerado automaticamente
            request.getValorPago(),
            createDate(request.getAno(), request.getMes(), request.getDia()),
            null, // Aqui você pode passar uma promoção, se aplicável
            assinatura
        );
        
        pagamentoRepository.save(pagamento);
    }

    // Método auxiliar para criar um objeto Date a partir de ano, mês e dia
    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // O mês em Calendar começa de 0 (janeiro é 0)
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
}
