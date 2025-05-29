package com.FSPH.Project.service;

import com.FSPH.Project.model.LoteComum;
import com.FSPH.Project.repository.LoteComumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class LoteService {

    @Autowired
    private LoteComumRepository loteComumRepository;

    public LoteComum criarLote(LoteComum lote) {
        String protocolo = gerarProtocoloLote();
        lote.setProtocolo(protocolo);
        lote.setDataCriacao(java.sql.Date.valueOf(LocalDate.now())); // define a data de criação
        return loteComumRepository.save(lote);
    }

    public List<LoteComum> listarLotes() {
        return loteComumRepository.findAll();
    }

    public void deletarLote(UUID idLoteComum) {
        LoteComum lote = loteComumRepository.findById(idLoteComum)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com id: " + idLoteComum));

        loteComumRepository.delete(lote);
    }

    public LoteComum buscarLotePorId(UUID idLoteComum) {
        return loteComumRepository.findById(idLoteComum)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com id: " + idLoteComum));
    }


    private String gerarProtocoloLote() {
        String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = loteComumRepository.countByProtocoloStartingWith(data);
        String sequencial = String.format("%05d", count + 1);
        return data + sequencial;
    }

    public LoteComum atualizarLote(UUID idLoteComum, LoteComum loteAtualizado) {
        LoteComum loteExistente = loteComumRepository.findById(idLoteComum)
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com id: " + idLoteComum));

        loteExistente.setProtocolo(loteAtualizado.getProtocolo());
        loteExistente.setTipoLote(loteAtualizado.getTipoLote());
        loteExistente.setDataCriacao(loteAtualizado.getDataCriacao());
        loteExistente.setIdUsuario(loteAtualizado.getIdUsuario());

        return loteComumRepository.save(loteExistente);
    }



}
