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
public class RegistrarPagamentoUseCase {

    private final PagamentoService pagamentoService;
    private final IAssinaturaRepository assinaturaRepository;

    public RegistrarPagamentoUseCase(PagamentoService pagamentoService, IAssinaturaRepository assinaturaRepository) {
        this.pagamentoService = pagamentoService;
        this.assinaturaRepository = assinaturaRepository;
    }

    public PagamentoResponseDTO execute(PagamentoRequestDTO request) {
        // Encontrar a assinatura pelo código
        Optional<AssinaturaModel> assinaturaOpt = assinaturaRepository.findById(request.getCodass());
        
        if (assinaturaOpt.isEmpty()) {
            throw new RuntimeException("Assinatura não encontrada");
        }

        AssinaturaModel assinatura = assinaturaOpt.get();

        // Obter o custo mensal do aplicativo associado
        BigDecimal valorAssinatura = assinatura.getApp().getCustoMensal(); // Acessando o custo mensal do aplicativo

        // Calcular a nova data de validade
        Date dataPagamento = createDate(request.getAno(), request.getMes(), request.getDia());
        Date novaDataValidade;

        if (assinatura.getFimVigencia().before(dataPagamento)) {
            // Se a assinatura estiver cancelada (data validade anterior), reativar a partir do pagamento
            novaDataValidade = addDays(dataPagamento, 30);
        } else {
            // Caso contrário, estende 30 dias a partir da data de validade atual
            novaDataValidade = addDays(assinatura.getFimVigencia(), 30);
        }

        BigDecimal valorEstornado = BigDecimal.ZERO;

        if (request.getValorPago().compareTo(valorAssinatura) != 0) {
            // Se o valor for incorreto, o pagamento é rejeitado e o valor estornado
            return new PagamentoResponseDTO("VALOR_INCORRETO", assinatura.getFimVigencia(), request.getValorPago());
        }

        // Atualizar a data de validade da assinatura
        assinatura.setFimVigencia(novaDataValidade);
        assinaturaRepository.save(assinatura);

        // Registrar o pagamento
        pagamentoService.registrarPagamento(request, assinatura);

        // Retorna a nova data de validade e status OK
        return new PagamentoResponseDTO("PAGAMENTO_OK", novaDataValidade, valorEstornado);
    }

    // Método auxiliar para criar um objeto Date a partir de ano, mês e dia
    private Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // O mês em Calendar começa de 0 (janeiro é 0)
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    // Método auxiliar para adicionar dias a uma data
    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}