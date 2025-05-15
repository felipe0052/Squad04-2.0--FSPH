package com.FSPH.Project.controller;

import com.FSPH.Project.model.Amostra;
import com.FSPH.Project.model.TipoAmostra;
import com.FSPH.Project.service.AmostraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/amostras")
public class AmostraController {

    private final AmostraService amostraService;

    public AmostraController(AmostraService amostraService) {
        this.amostraService = amostraService;
    }

    @PostMapping
    public ResponseEntity<Amostra> criarAmostra(@RequestBody Amostra amostra) {
        Amostra salva = amostraService.salvarAmostra(amostra);
        return ResponseEntity.ok(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amostra> editarAmostra(
            @PathVariable UUID id,
            @RequestBody Amostra amostra) {
        amostra.setIdAmostra(id);
        Amostra amostraAtualizada = amostraService.editarAmostra(id, amostra);
        return ResponseEntity.ok(amostraAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amostra> obterAmostra(@PathVariable UUID id) {
        Amostra amostra = amostraService.buscarPorId(id);
        return ResponseEntity.ok(amostra);
    }

    @GetMapping
    public ResponseEntity<List<Amostra>> listarTodas() {
        return ResponseEntity.ok(amostraService.listarTodas());
    }

    @GetMapping("/tipoAmostra")
    public ResponseEntity<List<Amostra>> buscarPorTipo(@RequestParam String tipo) {
        try {
            TipoAmostra tipoEnum = TipoAmostra.valueOf(tipo.toUpperCase());
            List<Amostra> amostras = amostraService.buscarPorTipo(tipoEnum);
            return ResponseEntity.ok(amostras);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAmostra(@PathVariable UUID id) {
        amostraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
