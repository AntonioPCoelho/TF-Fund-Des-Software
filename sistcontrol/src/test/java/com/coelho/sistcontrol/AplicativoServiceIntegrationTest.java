package com.coelho.sistcontrol;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.servicos.AplicativoService;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepositoryJPA;

@SpringBootTest
@Transactional
@Rollback
public class AplicativoServiceIntegrationTest {


    @Autowired
    private AplicativoService aplicativoService;

    @Autowired
    private AplicativoRepositoryJPA aplicativoRepository;

    @Test
    void deveSalvarEListarAplicativos() {
        // Arrange
        AplicativoModel app = new AplicativoModel(1923L, "App Teste", new BigDecimal("99.99"));

        // Act
        AplicativoModel salvo = aplicativoService.salvarAplicativo(app);
        List<AplicativoModel> aplicativos = aplicativoService.listarAplicativos();

        // Assert
        assertNotNull(salvo.getId());
        assert (aplicativos.size() >= 1);
        Boolean found = false;
        for(AplicativoModel db_app: aplicativos){
            if (db_app.getNome() == app.getNome()){
                found = true;
            }
        }
        assert(found);
        
    }

    @Test
    void deveAtualizarCustoMensal() {
        // Arrange
        AplicativoModel app = new AplicativoModel(213L, "App Atualizável", new BigDecimal("49.99"));
        AplicativoModel salvo = aplicativoService.salvarAplicativo(app);

        // Act
        AplicativoModel atualizado = aplicativoService.atualizarCustoMensal(salvo.getId(), new BigDecimal("59.99"));

        // Assert
        assertEquals(new BigDecimal("59.99"), atualizado.getCustoMensal());
    }

    @Test
    void deveEditarAplicativo() {
        // Arrange
        AplicativoModel app = new AplicativoModel(34L, "App Editável", new BigDecimal("19.99"));
        AplicativoModel salvo = aplicativoService.salvarAplicativo(app);

        AplicativoModel alterado = new AplicativoModel(salvo.getId(), "App Editado", new BigDecimal("29.99"));

        // Act
        AplicativoModel editado = aplicativoService.editarAplicativo(salvo.getId(), alterado);

        // Assert
        assertEquals("App Editado", editado.getNome());
        assertEquals(new BigDecimal("29.99"), editado.getCustoMensal());
    }
    
   @Test
    void deveLancarExcecaoQuandoAplicativoNaoEncontrado() {
        // Arrange
        Long idInexistente = 999L;

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            aplicativoRepository.findById(idInexistente)
                    .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));
        });
    }
}
