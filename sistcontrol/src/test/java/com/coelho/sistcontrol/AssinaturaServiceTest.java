package com.coelho.sistcontrol;

import com.coelho.sistcontrol.dominio.entidades.*;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssinaturaServiceTest {

    @Mock
    private IAssinaturaRepository assinaturaRepository;

    @Mock
    private IAplicativoRepository aplicativoRepository;

    @InjectMocks
    private AssinaturaService assinaturaService;

    @Test
    void deveCriarNovaAssinaturaComSucesso() {
        ClienteModel cliente = new ClienteModel(1L, "Cliente Teste", "coelho@gmail.com");
        AplicativoModel aplicativo = new AplicativoModel(1L, "App Teste", new BigDecimal(10));
        AssinaturaModel assinaturaEsperada = new AssinaturaModel(1L, new Date(), new Date(), aplicativo, cliente,
                "ATIVA");

        Mockito.when(assinaturaRepository.save(Mockito.any())).thenReturn(assinaturaEsperada);

        AssinaturaModel assinaturaCriada = assinaturaService.criarNovaAssinatura(cliente, aplicativo);

        assertNotNull(assinaturaCriada);
        assertEquals("ATIVA", assinaturaCriada.getstatus());
        assertEquals(cliente, assinaturaCriada.getCliente());
        assertEquals(aplicativo, assinaturaCriada.getApp());
    }
}
