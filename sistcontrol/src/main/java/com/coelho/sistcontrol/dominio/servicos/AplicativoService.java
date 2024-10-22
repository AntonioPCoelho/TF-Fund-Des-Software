package com.coelho.sistcontrol.dominio.servicos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.entidades.Aplicativo;
import com.coelho.sistcontrol.interface_adaptadora.repositorios.interface_jpa.AplicativoRepository;

@Service
public class AplicativoService {

    private final AplicativoRepository aplicativoRepository;

    public AplicativoService(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    // Listar todos os aplicativos
    public List<AplicativoModel> listarAplicativos() {
        return aplicativoRepository.findAll().stream()
                .map(Aplicativo::toModel)  // Conversão de entity para model
                .toList();
    }

    // Atualizar o custo mensal de um aplicativo
    public void atualizarCustoMensal(Long id, double novoCusto) {
        Aplicativo entity = aplicativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));
        
        entity.setCustoMensal(novoCusto);  // Atualiza o custo mensal
        aplicativoRepository.save(entity);
    }
    
}