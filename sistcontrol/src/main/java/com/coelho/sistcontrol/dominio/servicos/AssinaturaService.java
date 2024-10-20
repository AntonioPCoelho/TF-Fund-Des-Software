package com.coelho.sistcontrol.dominio.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Assinatura;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AssinaturaRepository;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public Assinatura criarAssinatura(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public Optional<Assinatura> buscarPorId(Long id) {
        return assinaturaRepository.findById(id);
    }

}
