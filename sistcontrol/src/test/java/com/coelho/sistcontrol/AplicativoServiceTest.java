import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.servicos.AplicativoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class AplicativoServiceTest {

    @Mock
    private IAplicativoRepository aplicativoRepository;

    @InjectMocks
    private AplicativoService aplicativoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarAplicativos_shouldReturnList() {
        // Test listing all applications
    }

    @Test
    void salvarAplicativo_shouldSaveAndReturnAplicativo() {
        AplicativoModel mockAplicativo = new AplicativoModel();
        when(aplicativoRepository.save(mockAplicativo)).thenReturn(mockAplicativo);
        
        AplicativoModel result = aplicativoService.salvarAplicativo(mockAplicativo);
        
        assertNotNull(result);
        verify(aplicativoRepository).save(mockAplicativo);
    }

    @Test
    void atualizarCustoMensal_shouldUpdateCustoMensal() {
        AplicativoModel mockAplicativo = new AplicativoModel();
        mockAplicativo.setCustoMensal(new BigDecimal("100.00"));
        when(aplicativoRepository.findById(1L)).thenReturn(Optional.of(mockAplicativo));

        AplicativoModel updated = aplicativoService.atualizarCustoMensal(1L, new BigDecimal("120.00"));

        assertEquals(new BigDecimal("120.00"), updated.getCustoMensal());
        verify(aplicativoRepository).save(mockAplicativo);
    }

    // Add additional tests for editarAplicativo, etc.
}
