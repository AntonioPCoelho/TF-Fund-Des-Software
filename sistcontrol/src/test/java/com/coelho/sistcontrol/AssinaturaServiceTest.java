import com.coelho.sistcontrol.dominio.entidades.*;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAssinaturaRepository;
import com.coelho.sistcontrol.dominio.servicos.AssinaturaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssinaturaServiceTest {

    @Mock
    private IAssinaturaRepository assinaturaRepository;

    @Mock
    private IAplicativoRepository aplicativoRepository;

    @InjectMocks
    private AssinaturaService assinaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarNovaAssinatura_shouldCreateAndReturnAssinatura() {
        ClienteModel cliente = new ClienteModel();
        AplicativoModel aplicativo = new AplicativoModel();
        AssinaturaModel novaAssinatura = assinaturaService.criarNovaAssinatura(cliente, aplicativo);

        assertNotNull(novaAssinatura);
        assertEquals("ATIVA", novaAssinatura.getStatus());
        verify(assinaturaRepository).save(any(AssinaturaModel.class));
    }

    @Test
    void isAssinaturaValida_shouldReturnTrueIfValid() {
        Assinatura assinatura = new Assinatura();
        assinatura.setFimVigencia(new Date(System.currentTimeMillis() + 100000)); // Future date

        when(assinaturaRepository.findByid(1L)).thenReturn(Optional.of(assinatura));

        boolean isValid = assinaturaService.isAssinaturaValida(1L);
        assertTrue(isValid);
    }

}
