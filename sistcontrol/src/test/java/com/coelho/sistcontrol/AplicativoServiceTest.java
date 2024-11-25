package com.coelho.sistcontrol;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.servicos.AplicativoService;

@ExtendWith(MockitoExtension.class)
class AplicativoServiceTest {

    @Mock
    private IAplicativoRepository aplicativoRepository;

    @InjectMocks
    private AplicativoService aplicativoService;

    @Test
    void deveSalvarAplicativoComSucesso() {
        AplicativoModel aplicativo = new AplicativoModel(4554, "Spotify", new BigDecimal("19.99"));

        when(aplicativoRepository.save(aplicativo)).thenReturn(new AplicativoModel(1L, "Spotify", new BigDecimal("19.99")));

        AplicativoModel salvo = aplicativoService.salvarAplicativo(aplicativo);

        assertNotNull(salvo.getId());
        assertEquals("Spotify", salvo.getNome());
        assertEquals(new BigDecimal("19.99"), salvo.getCustoMensal());
    }
    @Test
    void deveAtualizarCustoMensal() {
        AplicativoModel aplicativo = new AplicativoModel(1L, "Spotify", new BigDecimal("19.99"));

        when(aplicativoRepository.findById(1L)).thenReturn(Optional.of(aplicativo));
        when(aplicativoRepository.save(aplicativo)).thenReturn(new AplicativoModel(1L, "Spotify", new BigDecimal("29.99")));

        AplicativoModel atualizado = aplicativoService.atualizarCustoMensal(1L, new BigDecimal("29.99"));

        assertEquals(new BigDecimal("29.99"), atualizado.getCustoMensal());
    }
    @Test
    void deveEditarAplicativo() {
        AplicativoModel aplicativo = new AplicativoModel(1L, "Spotify", new BigDecimal("19.99"));
        AplicativoModel alterado = new AplicativoModel(1L, "Spotify Premium", new BigDecimal("29.99"));

        when(aplicativoRepository.findById(1L)).thenReturn(Optional.of(aplicativo));
        when(aplicativoRepository.save(alterado)).thenReturn(alterado);

        AplicativoModel editado = aplicativoService.editarAplicativo(1L, alterado);

        assertEquals("Spotify Premium", editado.getNome());
        assertEquals(new BigDecimal("29.99"), editado.getCustoMensal());
    }
    @Test
    void deveLancarExcecaoQuandoEditarAplicativoNaoExistente() {
        AplicativoModel alterado = new AplicativoModel(999L, "Spotify Premium", new BigDecimal("29.99"));

        when(aplicativoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            aplicativoService.editarAplicativo(999L, alterado);
        });
    }
    @Test
    void deveLancarExcecaoQuandoAtualizarCustoMensalDeAplicativoNaoExistente() {
        when(aplicativoRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            aplicativoService.atualizarCustoMensal(999L, new BigDecimal("29.99"));
        });
    }
}
