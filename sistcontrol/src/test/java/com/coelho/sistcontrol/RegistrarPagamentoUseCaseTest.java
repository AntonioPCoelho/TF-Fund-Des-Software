package com.coelho.sistcontrol;

// Testes Unitários para RegistrarPagamentoUseCase
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.coelho.sistcontrol.aplicacao.casosdeuso.RegistrarPagamentoUseCase;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoRequestDTO;
import com.coelho.sistcontrol.aplicacao.dtos.PagamentoResponseDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.servicos.PagamentoService;

class RegistrarPagamentoUseCaseTest {
    @Mock
    private PagamentoService pagamentoService;
    
    @Mock
    private IAssinaturaRepository assinaturaRepository;
    
    private RegistrarPagamentoUseCase useCase;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RegistrarPagamentoUseCase(pagamentoService, assinaturaRepository);
    }
    
    // Teste parametrizado para valores válidos
    @ParameterizedTest
    @MethodSource("provideValidPaymentData")
    void testExecuteValidPayment(int ano, int mes, int dia, BigDecimal valorPago) {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(dia, mes, ano, 10L, valorPago);
        request.setCodass(1L);
        request.setAno(ano);
        request.setMes(mes);
        request.setDia(dia);
        request.setValorPago(valorPago);
        
        AssinaturaModel assinatura = mock(AssinaturaModel.class);
        AplicativoModel app = mock(AplicativoModel.class);
        when(app.getCustoMensal()).thenReturn(valorPago);
        when(assinatura.getApp()).thenReturn(app);
        when(assinatura.getFimVigencia()).thenReturn(new Date());
        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));
        
        // Act
        PagamentoResponseDTO response = useCase.execute(request);
        
        // Assert
        assertEquals("PAGAMENTO_OK", response.getStatus());
        assertEquals(BigDecimal.ZERO, response.getValorEstornado());
        assertNotNull(response.getNovaDataValidade());
        verify(assinaturaRepository).save(any());
        verify(pagamentoService).registrarPagamento(any(), any());
    }
    
    private static Stream<Arguments> provideValidPaymentData() {
        return Stream.of(
            Arguments.of(2024, 1, 1, new BigDecimal("50.00")),
            Arguments.of(2024, 12, 31, new BigDecimal("99.99")),
            Arguments.of(2024, 6, 15, new BigDecimal("75.50"))
        );
    }
    
    @Test
    void testExecuteAssinaturaNaoEncontrada() {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(1, 1, 2003, 10L, BigDecimal.TEN);
        request.setCodass(999L);
        when(assinaturaRepository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(RuntimeException.class, () -> useCase.execute(request));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidPaymentValues")
    void testExecuteValorIncorreto(BigDecimal valorPago, BigDecimal custoMensal) {
        // Arrange
        PagamentoRequestDTO request = new PagamentoRequestDTO(1, 1, 2003, 10L, BigDecimal.TEN);
        request.setCodass(1L);
        request.setValorPago(valorPago);
        
        AssinaturaModel assinatura = mock(AssinaturaModel.class);
        AplicativoModel app = mock(AplicativoModel.class);
        when(app.getCustoMensal()).thenReturn(custoMensal);
        when(assinatura.getApp()).thenReturn(app);
        when(assinatura.getFimVigencia()).thenReturn(new Date());
        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));
        
        // Act
        PagamentoResponseDTO response = useCase.execute(request);
        
        // Assert
        assertEquals("VALOR_INCORRETO", response.getStatus());
        assertEquals(valorPago, response.getValorEstornado());
    }
    
    private static Stream<Arguments> provideInvalidPaymentValues() {
        return Stream.of(
            Arguments.of(new BigDecimal("49.99"), new BigDecimal("50.00")),
            Arguments.of(new BigDecimal("100.01"), new BigDecimal("100.00")),
            Arguments.of(BigDecimal.ZERO, new BigDecimal("75.00"))
        );
    }
}
