package com.FSPH.Project.service;

import com.FSPH.Project.model.*;
import com.FSPH.Project.repository.AmostraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.FSPH.Project.model.TipoAmostra.*;

@Service
public class AmostraService {

    private final AmostraRepository amostraRepository;

    public AmostraService(AmostraRepository amostraRepository) {
        this.amostraRepository = amostraRepository;
    }

    @Transactional
    public Amostra salvarAmostra(Amostra amostra) {
        validarTipoSubEntidade(amostra);
        setAmostraParaSubEntidade(amostra);
        return amostraRepository.save(amostra);
    }

    @Transactional
    public Amostra editarAmostra(UUID id, Amostra amostraAtualizada) {
        Amostra amostraExistente = amostraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amostra não encontrada"));

        // Só permite editar se status não for "despachada"
        if (amostraExistente.getStatusAmostra() != null
                && "despachada".equalsIgnoreCase(amostraExistente.getStatusAmostra().getDescricao())) {
            throw new RuntimeException("Amostra despachada não pode ser editada");
        }

        // Atualiza campos básicos (data, tipo, validade, estado, usuario)
        amostraExistente.setDataColeta(amostraAtualizada.getDataColeta());
        amostraExistente.setTipoAmostra(amostraAtualizada.getTipoAmostra());
        amostraExistente.setValidade(amostraAtualizada.getValidade());
        amostraExistente.setEstado(amostraAtualizada.getEstado());
        amostraExistente.setIdUsuario(amostraAtualizada.getIdUsuario());

        // Atualiza subentidades (limpa e seta novamente)
        limparSubEntidades(amostraExistente);
        copiarSubEntidade(amostraExistente, amostraAtualizada);

        validarTipoSubEntidade(amostraExistente);
        setAmostraParaSubEntidade(amostraExistente);

        return amostraRepository.save(amostraExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        Amostra amostra = amostraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amostra não encontrada"));

        if (amostra.getStatusAmostra() != null
                && "despachada".equalsIgnoreCase(amostra.getStatusAmostra().getDescricao())) {
            throw new RuntimeException("Amostra despachada não pode ser deletada");
        }
        amostraRepository.delete(amostra);
    }

    private void validarTipoSubEntidade(Amostra amostra) {
        // Limpa todas subentidades
        limparSubEntidades(amostra);

        // Verifica a subentidade correta conforme tipo
        switch (amostra.getTipoAmostra()) {
            case LARVA:
                if (amostra.getLarva() == null) throw new RuntimeException("Larva deve estar preenchida para tipo LARVA");
                break;
            case MOLUSCO:
                if (amostra.getMolusco() == null) throw new RuntimeException("Molusco deve estar preenchido para tipo MOLUSCO");
                break;
            case ESCORPIAO:
                if (amostra.getEscorpiao() == null) throw new RuntimeException("Escorpiao deve estar preenchido para tipo ESCORPIAO");
                break;
            case CARRAPATO:
                if (amostra.getCarrapato() == null) throw new RuntimeException("Carrapato deve estar preenchido para tipo CARRAPATO");
                break;
            case FLEBOTOMINEO:
                if (amostra.getFlebotomineo() == null) throw new RuntimeException("Flebotomineo deve estar preenchido para tipo FLEBOTOMINEO");
                break;
            case TRIATOMINEO:
                if (amostra.getTriatomineo() == null) throw new RuntimeException("Triatomineo deve estar preenchido para tipo TRIATOMINEO");
                break;
            default:
                throw new IllegalArgumentException("Tipo de amostra inválido");
        }
    }

    private void limparSubEntidades(Amostra amostra) {
        amostra.setLarva(null);
        amostra.setMolusco(null);
        amostra.setEscorpiao(null);
        amostra.setCarrapato(null);
        amostra.setFlebotomineo(null);
        amostra.setTriatomineo(null);
    }

    private void copiarSubEntidade(Amostra destino, Amostra origem) {
        switch (origem.getTipoAmostra()) {
            case LARVA:
                destino.setLarva(origem.getLarva());
                break;
            case MOLUSCO:
                destino.setMolusco(origem.getMolusco());
                break;
            case ESCORPIAO:
                destino.setEscorpiao(origem.getEscorpiao());
                break;
            case CARRAPATO:
                destino.setCarrapato(origem.getCarrapato());
                break;
            case FLEBOTOMINEO:
                destino.setFlebotomineo(origem.getFlebotomineo());
                break;
            case TRIATOMINEO:
                destino.setTriatomineo(origem.getTriatomineo());
                break;
        }
    }

    private void setAmostraParaSubEntidade(Amostra amostra) {
        if (amostra.getLarva() != null) amostra.getLarva().setAmostra(amostra);
        if (amostra.getMolusco() != null) amostra.getMolusco().setAmostra(amostra);
        if (amostra.getEscorpiao() != null) amostra.getEscorpiao().setAmostra(amostra);
        if (amostra.getCarrapato() != null) amostra.getCarrapato().setAmostra(amostra);
        if (amostra.getFlebotomineo() != null) amostra.getFlebotomineo().setAmostra(amostra);
        if (amostra.getTriatomineo() != null) amostra.getTriatomineo().setAmostra(amostra);
    }

    public Amostra buscarPorId(UUID id) {
        return amostraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amostra não encontrada"));

    }

    public List<Amostra> buscarPorTipo(TipoAmostra tipoAmostra) {
        return amostraRepository.findByTipoAmostra(tipoAmostra);
    }


    public List<Amostra> listarTodas() {
        return amostraRepository.findAll();
    }
}
