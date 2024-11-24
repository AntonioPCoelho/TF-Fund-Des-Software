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
import java.util.List;
import java.util.Optional;

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

    @Test
    void deveRetornarAssinantesPorAplicativo() {
        AplicativoModel aplicativo = new AplicativoModel(1L, "App Teste", new BigDecimal(10));
        ClienteModel cliente = new ClienteModel(1L, "Cliente Teste", "coelho@gmail.com");

        Mockito.when(aplicativoRepository.findById(1L)).thenReturn(Optional.of(aplicativo));
        Mockito.when(assinaturaRepository.findClientesByAplicativo(1L)).thenReturn(List.of(cliente));

        List<ClienteModel> clientes = assinaturaService.listarAssinantesPorAplicativo(1L);

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals(cliente, clientes.get(0));
    }

    @Test
    void deveAtualizarValidadeAssinatura() {
        AssinaturaModel assinatura = new AssinaturaModel(1L, new Date(), new Date(), null, null, "ATIVA");
        Date novaDataFim = new Date(System.currentTimeMillis() + 100000);

        Mockito.when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        assinaturaService.atualizarValidadeAssinatura(1L, novaDataFim);

        assertEquals(novaDataFim, assinatura.getFimVigencia());
        Mockito.verify(assinaturaRepository).save(assinatura);
    }

    @Test
    void deveRetornarAssinaturasPorCliente() {
        ClienteModel cliente = new ClienteModel(1L, "Cliente Teste", "coelho@gmail.com");
        AssinaturaModel assinatura = new AssinaturaModel(1L, new Date(), new Date(), null, cliente, "ATIVA");

        Mockito.when(assinaturaRepository.findByClienteId(1L)).thenReturn(List.of(assinatura));

        List<AssinaturaModel> assinaturas = assinaturaService.listarAssinaturasPorCliente(1L);

        assertNotNull(assinaturas);
        assertEquals(1, assinaturas.size());
        assertEquals(cliente, assinaturas.get(0).getCliente());
    }
    
    @Test
    void deveRetornarAssinaturasPorStatus() {
        AssinaturaModel assinaturaAtiva = new AssinaturaModel(1L, new Date(), new Date(), null, null, "ATIVA");
        AssinaturaModel assinaturaCancelada = new AssinaturaModel(2L, new Date(), new Date(), null, null, "CANCELADA");

        Mockito.when(assinaturaRepository.findByStatus("ATIVA")).thenReturn(List.of(assinaturaAtiva));
        Mockito.when(assinaturaRepository.findByStatus("CANCELADA")).thenReturn(List.of(assinaturaCancelada));

        List<AssinaturaModel> assinaturasAtivas = assinaturaService.listarAssinaturasPorstatus("ATIVAS");
        List<AssinaturaModel> assinaturasCanceladas = assinaturaService.listarAssinaturasPorstatus("CANCELADAS");

        assertNotNull(assinaturasAtivas);
        assertEquals(1, assinaturasAtivas.size());
        assertEquals("ATIVA", assinaturasAtivas.get(0).getstatus());

        assertNotNull(assinaturasCanceladas);
        assertEquals(1, assinaturasCanceladas.size());
        assertEquals("CANCELADA", assinaturasCanceladas.get(0).getstatus());
    }
}
