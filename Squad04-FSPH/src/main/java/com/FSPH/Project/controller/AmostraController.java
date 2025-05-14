package com.FSPH.Project.controller;

import com.FSPH.Project.model.Amostra;
import com.FSPH.Project.service.AmostraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/amostras")
public class AmostraController {

    @Autowired
    private AmostraService amostraService;

    // Criar nova amostra
    @PostMapping
    public ResponseEntity<Amostra> criar(@RequestBody Amostra amostra) {
        try {
            // Verificar se o status é "3" (despachada) ao criar a amostra
            if (amostra.getStatusAmostra() != null && "despachada".equals(amostra.getStatusAmostra().getDescricao())) {
                return ResponseEntity.status(400).body(null);  // Não pode criar uma amostra com status "despachada"
            }

            Amostra createdAmostra = amostraService.criarAmostraComTipo(amostra);
            return ResponseEntity.ok(createdAmostra);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Erro genérico
        }
    }

    // Editar amostra existente
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable UUID id, @RequestBody Amostra novaAmostra) {
        if (novaAmostra == null) {
            return ResponseEntity.badRequest().body("Dados da amostra não podem ser nulos.");
        }

        try {
            Optional<Amostra> amostraExistente = amostraService.buscarPorId(id);
            if (amostraExistente.isPresent()) {
                // Verificar se o status da amostra existente é "despachada"
                Amostra amostraAtual = amostraExistente.get();
                if (amostraAtual.getStatusAmostra() != null && "despachada".equals(amostraAtual.getStatusAmostra().getDescricao())) {
                    return ResponseEntity.status(403).body("Amostra com status despachada não pode ser editada.");
                }

                Amostra amostraAtualizada = amostraService.editarAmostra(id, novaAmostra);
                return ResponseEntity.ok(amostraAtualizada);
            } else {
                return ResponseEntity.status(404).body("Amostra não encontrada.");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body("Amostra com status despachada não pode ser editada.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Amostra não encontrada.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor.");
        }
    }

    // Deletar amostra
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        try {
            Optional<Amostra> amostraExistente = amostraService.buscarPorId(id);
            if (amostraExistente.isPresent()) {
                Amostra amostra = amostraExistente.get();
                // Verificar se o status é "despachada" antes de deletar
                if (amostra.getStatusAmostra() != null && "despachada".equals(amostra.getStatusAmostra().getDescricao())) {
                    return ResponseEntity.status(403).body("Amostra com status despachada não pode ser excluída.");
                }

                amostraService.deletarAmostra(id);
                return ResponseEntity.ok("Amostra deletada com sucesso.");
            } else {
                return ResponseEntity.status(404).body("Amostra não encontrada.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro interno do servidor.");
        }
    }

    // Buscar todas as amostras
    @GetMapping
    public ResponseEntity<List<Amostra>> listarTodas() {
        try {
            List<Amostra> amostras = amostraService.listarTodas();
            return ResponseEntity.ok(amostras);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Buscar uma amostra por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable UUID id) {
        Optional<Amostra> amostra = amostraService.buscarPorId(id);
        if (amostra.isPresent()) {
            return ResponseEntity.ok(amostra.get());
        } else {
            return ResponseEntity.status(404).body("Amostra não encontrada.");
        }
    }

    // Buscar por tipo de amostra (ex: larva, escorpiao)
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Amostra>> buscarPorTipo(@PathVariable String tipo) {
        try {
            List<Amostra> amostras = amostraService.buscarPorTipo(tipo);
            return ResponseEntity.ok(amostras);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
