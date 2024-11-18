package com.coelho.sistcontrol.aplicacao.casosdeuso;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoResponseDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.servicos.PagamentoService;

@Service
public class RegistrarPagamentoPromocaoUseCase {

    private final PagamentoService pagamentoService;
    private final IAssinaturaRepository assinaturaRepository;

    public RegistrarPagamentoPromocaoUseCase(PagamentoService pagamentoService,
            IAssinaturaRepository assinaturaRepository) {
        this.pagamentoService = pagamentoService;
        this.assinaturaRepository = assinaturaRepository;
    }

    public PagamentoResponseDTO execute(PagamentoRequestDTO request, String promocao) {
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(request.getCodass());

        if (assinaturaOpt.isEmpty()) {
            throw new RuntimeException("Assinatura não encontrada");
        }

        AssinaturaModel assinatura = assinaturaOpt.get();
        BigDecimal valorAssinatura = assinatura.getApp().getCustoMensal();
        Date dataPagamento = createDate(request.getAno(), request.getMes(), request.getDia());
        Date novaDataValidade;

        // Aplica as promoções com base no tipo fornecido
        if ("EXTENSAO_45_DIAS".equalsIgnoreCase(promocao)) {
            novaDataValidade = assinatura.getFimVigencia().before(dataPagamento)
                    ? addDays(dataPagamento, 45)
                    : addDays(assinatura.getFimVigencia(), 45);
        } else if ("DESCONTO_5".equalsIgnoreCase(promocao)) {
            if (request.getValorPago().compareTo(valorAssinatura.multiply(new BigDecimal("0.95"))) != 0) {
                return new PagamentoResponseDTO("VALOR_INCORRETO", assinatura.getFimVigencia(), request.getValorPago());
            }
            novaDataValidade = assinatura.getFimVigencia().before(dataPagamento)
                    ? addDays(dataPagamento, 30)
                    : addDays(assinatura.getFimVigencia(), 30);
        } else if ("DESCONTO_10".equalsIgnoreCase(promocao)) { // Promoção personalizada
            if (request.getValorPago().compareTo(valorAssinatura.multiply(new BigDecimal("0.90"))) != 0) {
                return new PagamentoResponseDTO("VALOR_INCORRETO", assinatura.getFimVigencia(), request.getValorPago());
            }
            novaDataValidade = assinatura.getFimVigencia().before(dataPagamento)
                    ? addDays(dataPagamento, 30)
                    : addDays(assinatura.getFimVigencia(), 30);
        } else {
            throw new IllegalArgumentException("Promoção inválida");
        }

        // Atualiza a validade da assinatura
        assinatura.setFimVigencia(novaDataValidade);
        assinaturaRepository.save(assinatura);

        // Registra o pagamento
        pagamentoService.registrarPagamento(request, assinatura);

        return new PagamentoResponseDTO("PAGAMENTO_OK", novaDataValidade, BigDecimal.ZERO);
    }

    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}