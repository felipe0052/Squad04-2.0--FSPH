package com.FSPH.Project.controller;

import com.FSPH.Project.model.Lote;
import com.FSPH.Project.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    // Criar Lote
    @PostMapping
    public ResponseEntity<Lote> criarLote(@RequestBody Lote lote) {
        Lote novoLote = loteService.criarLote(lote);
        return ResponseEntity.ok(novoLote);
    }

    // Editar Lote
    @PutMapping("/{id}")
    public ResponseEntity<Lote> atualizarLote(@PathVariable UUID id, @RequestBody Lote loteAtualizado) {
        Optional<Lote> loteExistente = loteService.buscarPorId(id);

        if (loteExistente.isPresent()) {
            Lote lote = loteExistente.get();
            // Atualizando apenas campos básicos e listas
            lote.setProtocolo(loteAtualizado.getProtocolo());
            lote.setDataCriacao(loteAtualizado.getDataCriacao());
            lote.setIdUsuario(loteAtualizado.getIdUsuario());
            lote.setTipoLote(loteAtualizado.getTipoLote());
            lote.setAmostras(loteAtualizado.getAmostras());
            lote.setLaminas(loteAtualizado.getLaminas());

            Lote atualizado = loteService.criarLote(lote);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar Lote
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLote(@PathVariable UUID id) {
        Optional<Lote> lote = loteService.buscarPorId(id);

        if (lote.isPresent()) {
            loteService.deletarPorId(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar Lote específico
    @GetMapping("/{id}")
    public ResponseEntity<Lote> buscarLotePorId(@PathVariable UUID id) {
        Optional<Lote> lote = loteService.buscarPorId(id);

        if (lote.isPresent()) {
            return ResponseEntity.ok(lote.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
