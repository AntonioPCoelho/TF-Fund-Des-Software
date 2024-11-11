import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coelho.sistcontrol.dominio.entidades.AssinaturaModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios.AssinaturaRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AssinaturaRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class AssinaturaRepositoryTest {

    @Mock
    private AssinaturaRepositoryJPA assinaturaRepositoryJPA;

    @InjectMocks
    private AssinaturaRepository assinaturaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldSaveAndReturnAssinaturaModel() {
        AssinaturaModel model = new AssinaturaModel();
        Assinatura entity = Assinatura.fromModel(model);
        when(assinaturaRepositoryJPA.save(entity)).thenReturn(entity);

        AssinaturaModel result = assinaturaRepository.save(model);

        assertNotNull(result);
        verify(assinaturaRepositoryJPA).save(entity);
    }

    @Test
    void findByClienteIdAndAplicativoId_shouldReturnAssinaturaModelIfExists() {
        Assinatura entity = new Assinatura();
        when(assinaturaRepositoryJPA.findByClienteIdAndAplicativoId(1L, 1L)).thenReturn(Optional.of(entity));

        Optional<AssinaturaModel> result = assinaturaRepository.findByClienteIdAndAplicativoId(1L, 1L);

        assertTrue(result.isPresent());
        verify(assinaturaRepositoryJPA).findByClienteIdAndAplicativoId(1L, 1L);
    }

    @Test
    void findAll_shouldReturnListOfAssinaturaModels() {
        when(assinaturaRepositoryJPA.findAll()).thenReturn(List.of(new Assinatura()));

        List<AssinaturaModel> result = assinaturaRepository.findAll();

        assertNotNull(result);
        verify(assinaturaRepositoryJPA).findAll();
    }
}
