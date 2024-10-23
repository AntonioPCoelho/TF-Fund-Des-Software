package com.coelho.sistcontrol.dominio.servicos;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
import com.coelho.sistcontrol.dominio.interfRepositorios.IAplicativoRepository;

@Service
public class AplicativoService {

    private final IAplicativoRepository aplicativoRepository;

    public AplicativoService(IAplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    // Listar todos os aplicativos
    public List<AplicativoModel> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    // Salvar um novo aplicativo
    public AplicativoModel salvarAplicativo(AplicativoModel aplicativoModel) {
        AplicativoModel salvo = aplicativoRepository.save(aplicativoModel);
        return salvo;
    }

    // Editar um aplicativo
    public AplicativoModel editarAplicativo(Long id, AplicativoModel aplicativoModel) {
        AplicativoModel entity = aplicativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        AplicativoModel atualizado = aplicativoRepository.save(aplicativoModel);
        return atualizado;
    }

    // Atualizar o custo mensal de um aplicativo
    public AplicativoModel atualizarCustoMensal(Long id, BigDecimal novoCusto) {
        AplicativoModel entity = aplicativoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        entity.setCustoMensal(novoCusto);
        return aplicativoRepository.save(entity); // Retorna o aplicativo atualizado
    }

}