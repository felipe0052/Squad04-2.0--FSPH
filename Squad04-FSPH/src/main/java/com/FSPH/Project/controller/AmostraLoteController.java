package com.FSPH.Project.controller;

import com.FSPH.Project.model.AmostraLote;
import com.FSPH.Project.service.AmostraLoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/amostralote")
public class AmostraLoteController {

    @Autowired
    private AmostraLoteService amostraLoteService;

    // Adicionar Amostra a um Lote
    @PostMapping("/adicionar")
    public ResponseEntity<AmostraLote> adicionarAmostraAoLote(
            @RequestParam UUID idLote,
            @RequestParam UUID idAmostra) {
        AmostraLote amostraLote = amostraLoteService.adicionarAmostraAoLote(idLote, idAmostra);
        return ResponseEntity.ok(amostraLote);
    }

    // Listar Amostras de um Lote
    @GetMapping("/listar/{idLote}")
    public ResponseEntity<List<AmostraLote>> listarAmostrasPorLote(@PathVariable UUID idLote) {
        List<AmostraLote> amostras = amostraLoteService.listarAmostrasPorLote(idLote);
        return ResponseEntity.ok(amostras);
    }

    // Remover a associação usando o id da Amostra
    @DeleteMapping("/remover/amostra/{idAmostra}")
    public ResponseEntity<Void> removerAmostraDoLote(@PathVariable UUID idAmostra) {
        amostraLoteService.removerAmostraDoLote(idAmostra);
        return ResponseEntity.noContent().build();
    }

}


