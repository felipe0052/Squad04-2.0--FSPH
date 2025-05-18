package com.FSPH.Project.controller;

import com.FSPH.Project.model.Lote;
import com.FSPH.Project.service.LoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService loteService;


    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<Lote>> listarTodos() {
        return ResponseEntity.ok(loteService.listarTodos());
    }

}
