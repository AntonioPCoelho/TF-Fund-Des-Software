package com.coelho.sistcontrol.interface_adaptadora.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coelho.sistcontrol.dominio.servicos.AplicativoService;
import com.coelho.sistcontrol.aplicacao.dtos.AplicativoDTO;
import com.coelho.sistcontrol.dominio.entidades.AplicativoModel;
@RestController
@RequestMapping("/servcad/aplicativos")
public class AplicativoController {

    private final AplicativoService aplicativoService;

    public AplicativoController(AplicativoService aplicativoService) {
        this.aplicativoService = aplicativoService;
    }

    // Endpoint para listar todos os aplicativos
    @GetMapping
    public ResponseEntity<List<AplicativoDTO>> listarTodos() {
        List<AplicativoModel> aplicativos = aplicativoService.listarAplicativos();
        
        // Convertendo os AplicativoModel para AplicativoDTO
        List<AplicativoDTO> aplicativosDTO = aplicativos.stream()
            .map(aplicativo -> new AplicativoDTO(aplicativo.getId(), aplicativo.getNome(), aplicativo.getCustoMensal()))
            .toList();
        
        return ResponseEntity.ok(aplicativosDTO);
    }
    @PostMapping("/atualizacusto/{idAplicativo}")
    public ResponseEntity<AplicativoDTO> atualizarCusto(@PathVariable Long idAplicativo, @RequestBody double custo) {
        // Atualiza o custo mensal e obtém o aplicativo atualizado
        AplicativoModel aplicativoAtualizado = aplicativoService.atualizarCustoMensal(idAplicativo, custo);
        
        // Converte para DTO para resposta
        AplicativoDTO aplicativoDTO = new AplicativoDTO(aplicativoAtualizado.getId(), aplicativoAtualizado.getNome(), aplicativoAtualizado.getCustoMensal());
        
        return ResponseEntity.ok(aplicativoDTO);
    }
}


// @RestController
// @RequestMapping("/servcad/aplicativos")
// public class AplicativoController {

//     private final AplicativoService aplicativoService;

//     public AplicativoController(AplicativoService aplicativoService) {
//         this.aplicativoService = aplicativoService;
//     }

//     // Listar todos os aplicativos cadastrados
//     @GetMapping
//     public List<AplicativoModel> listarAplicativos() {
//         return aplicativoService.listarAplicativos();
//     }

//     // Cadastrar um novo aplicativo
//     @PostMapping
//     public ResponseEntity<AplicativoModel> cadastrarAplicativo(@RequestBody AplicativoModel aplicativoModel) {
//         AplicativoModel novoAplicativo = aplicativoService.salvarAplicativo(aplicativoModel);
//         return ResponseEntity.ok(novoAplicativo);
//     }

//     // Editar um aplicativo
//     @PutMapping("/{id}")
//     public ResponseEntity<AplicativoModel> editarAplicativo(@PathVariable Long id, @RequestBody AplicativoModel aplicativoModel) {
//         AplicativoModel atualizado = aplicativoService.editarAplicativo(id, aplicativoModel);
//         return ResponseEntity.ok(atualizado);
//     }

//     // Atualizar o valor do custo mensal de um aplicativo
//     @PatchMapping("/{id}/custoMensal")
//     public ResponseEntity<Void> atualizarCustoMensal(@PathVariable Long id, @RequestParam double novoCusto) {
//         aplicativoService.atualizarCustoMensal(id, novoCusto);
//         return ResponseEntity.ok().build();
//     }
// }

