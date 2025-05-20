package com.FSPH.Project.controller;

import com.FSPH.Project.model.LoteComum;
import com.FSPH.Project.service.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
