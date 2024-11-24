package com.coelho.sistcontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
}
