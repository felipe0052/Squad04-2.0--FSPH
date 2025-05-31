package com.FSPH.Project.controller;

import com.FSPH.Project.model.LoteComum;
import com.FSPH.Project.service.LoteComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteComumService loteComumService;

    @PostMapping
    public LoteComum criarLote(@RequestBody LoteComum lote) {
        return loteComumService.criarLote(lote);
    }

    @GetMapping
    public List<LoteComum> listarLotes() {
        return loteComumService.listarLotes();
    }
}
