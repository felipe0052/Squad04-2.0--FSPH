package com.FSPH.Project.service;

import com.FSPH.Project.model.LoteLamina;
import com.FSPH.Project.repository.LoteLaminaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class LoteLaminaService {

    @Autowired
    private LoteLaminaRepository loteLaminaRepository;

    public LoteLamina criarLote(LoteLamina lote) {
        lote.setProtocolo(gerarProtocoloLote());
        lote.setDataCriacao(new Date());
        return loteLaminaRepository.save(lote);
    }

    public List<LoteLamina> listarLotes() {
        return loteLaminaRepository.findAll();
    }

    private String gerarProtocoloLote() {
        String data = new SimpleDateFormat("yyyyMMdd").format(new Date());
        int numero = new Random().nextInt(100000);
        return data + String.format("%05d", numero);
    }
}
