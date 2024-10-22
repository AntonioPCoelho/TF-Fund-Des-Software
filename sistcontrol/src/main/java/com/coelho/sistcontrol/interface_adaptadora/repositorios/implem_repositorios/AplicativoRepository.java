package com.coelho.sistcontrol.interface_adaptadora.repositorios.implem_repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepositoryJPA;

@Component
public class AplicativoRepository implements IAplicativoRepository{
    private AplicativoRepositoryJPA aplicativos;

    public AplicativoRepository(AplicativoRepositoryJPA aplicativos){
        this.aplicativos = aplicativos;
    }

    @Override
    public List<AplicativoModel> findAll() {
        return this.aplicativos.findAll().stream().map(x -> x.toModel()).toList();
    }

    @Override
    public Optional<AplicativoModel> findById(Long id) {
        return this.aplicativos.findById(id).map(Aplicativo::toModel);
    }

    @Override
    public AplicativoModel save(AplicativoModel app) {
        return this.aplicativos.save(Aplicativo.fromModel(app)).toModel();
    }

}
