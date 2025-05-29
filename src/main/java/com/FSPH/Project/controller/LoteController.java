package com.FSPH.Project.controller;

import com.FSPH.Project.model.LoteComum;
import com.FSPH.Project.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @PostMapping
    public LoteComum criarLote(@RequestBody LoteComum lote) {
        return loteService.criarLote(lote);
    }

    @GetMapping
    public List<LoteComum> listarLotes() {
        return loteService.listarLotes();
    }

    @PutMapping("/{id}")
    public LoteComum atualizarLote(@PathVariable UUID idLoteComum, @RequestBody LoteComum loteAtualizado) {
        return loteService.atualizarLote(idLoteComum, loteAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarLote(@PathVariable UUID idLoteComum) {
        loteService.deletarLote(idLoteComum);
    }

    @GetMapping("/{id}")
    public LoteComum buscarLotePorId(@PathVariable UUID idLoteComum) {
        return loteService.buscarLotePorId(idLoteComum);
    }



}
