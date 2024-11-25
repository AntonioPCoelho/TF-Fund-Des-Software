package com.coelho.sistcontrol;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.dominio.entidades.ClienteModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IClienteRepository;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AssinaturaServiceIntegrationTest {

    @Autowired
    private AssinaturaService assinaturaService;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IAplicativoRepository aplicativoRepository;

    @Autowired
    private IAssinaturaRepository assinaturaRepository;

    @Test
    void deveSalvarAssinaturaNoBanco() {
        ClienteModel cliente = clienteRepository.save(new ClienteModel(1L, "Cliente Integração", "fake@email.com"));
        AplicativoModel aplicativo = aplicativoRepository.save(new AplicativoModel(1L, "App Integração", new BigDecimal(10)));

        AssinaturaModel assinaturaCriada = assinaturaService.criarNovaAssinatura(cliente, aplicativo);

        Optional<AssinaturaModel> assinaturaSalva = assinaturaRepository.findById(assinaturaCriada.getId());

        assertTrue(assinaturaSalva.isPresent());
        assertEquals(cliente.getId(), assinaturaSalva.get().getCliente().getId());
        assertEquals(aplicativo.getId(), assinaturaSalva.get().getApp().getId());
    }
    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        AplicativoModel aplicativo = aplicativoRepository.save(new AplicativoModel(1L, "App Integração", new BigDecimal(10)));

        assertThrows(RuntimeException.class, () -> {
            assinaturaService.criarNovaAssinatura(null, aplicativo);
        });
    }

    @Test
    void deveLancarExcecaoQuandoAplicativoNaoExistir() {
        ClienteModel cliente = clienteRepository.save(new ClienteModel(1L, "Cliente teste", "fake234@email.com"));

        assertThrows(RuntimeException.class, () -> {
            assinaturaService.criarNovaAssinatura(cliente, null);
        });
    }
}
