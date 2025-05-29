package com.FSPH.Project.controller;

import com.FSPH.Project.model.LoteLamina;
import com.FSPH.Project.service.LoteLaminaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lotes/lamina")
public class LoteLaminaController {

    @Autowired
    private LoteLaminaService loteLaminaService;

    @PostMapping
    public LoteLamina criar(@RequestBody LoteLamina lote) {
        return loteLaminaService.criarLote(lote);
    }

    @GetMapping
    public List<LoteLamina> listar() {
        return loteLaminaService.listarLotes();
    }
}
