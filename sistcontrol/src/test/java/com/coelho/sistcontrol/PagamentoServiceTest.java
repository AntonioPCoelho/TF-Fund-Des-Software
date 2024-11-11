import com.coelho.sistcontrol.aplicacao.dtos.PagamentoDTO;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.PagamentoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IPagamentoRepository;
import com.coelho.sistcontrol.dominio.servicos.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private IPagamentoRepository pagamentoRepository;

    @Mock
    private IAssinaturaRepository assinaturaRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarPagamento_shouldThrowExceptionForIncorrectAmount() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO(1L, new BigDecimal("50.00"), new Date(), null, null);
        AssinaturaModel assinatura = new AssinaturaModel();
        assinatura.setApp(new AplicativoModel());
        assinatura.getApp().setCustoMensal(new BigDecimal("100.00"));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                pagamentoService.registrarPagamento(pagamentoDTO));

        assertEquals("Valor pago está incorreto. Esperado: 100.00", exception.getMessage());
    }

    // Additional tests for registrarPagamento with correct values, etc.
}
