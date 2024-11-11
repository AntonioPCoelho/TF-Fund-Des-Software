package com.coelho.sistcontrol;

public class AplicativoRepositoryTest {
    
}
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios.AplicativoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepositoryJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

class AplicativoRepositoryTest {

    @Mock
    private AplicativoRepositoryJPA aplicativoRepositoryJPA;

    @InjectMocks
    private AplicativoRepository aplicativoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnListOfAplicativoModels() {
        when(aplicativoRepositoryJPA.findAll()).thenReturn(List.of(new Aplicativo()));

        List<AplicativoModel> result = aplicativoRepository.findAll();

        assertNotNull(result);
        verify(aplicativoRepositoryJPA).findAll();
    }

    @Test
    void findById_shouldReturnAplicativoModelWhenExists() {
        Aplicativo app = new Aplicativo();
        when(aplicativoRepositoryJPA.findById(1L)).thenReturn(Optional.of(app));

        Optional<AplicativoModel> result = aplicativoRepository.findById(1L);

        assertTrue(result.isPresent());
        verify(aplicativoRepositoryJPA).findById(1L);
    }

    @Test
    void save_shouldSaveAndReturnAplicativoModel() {
        AplicativoModel model = new AplicativoModel();
        Aplicativo entity = Aplicativo.fromModel(model);
        when(aplicativoRepositoryJPA.save(entity)).thenReturn(entity);

        AplicativoModel result = aplicativoRepository.save(model);

        assertNotNull(result);
        verify(aplicativoRepositoryJPA).save(entity);
    }
}
    