package com.FSPH.Project.service;

import com.FSPH.Project.model.*;
import com.FSPH.Project.repository.AmostraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

        if (amostraExistente.getStatusAmostra() != null &&
                "despachada".equalsIgnoreCase(amostraExistente.getStatusAmostra().getDescricao())) {
            throw new RuntimeException("Amostra despachada não pode ser editada");
        }

        amostraExistente.setDataColeta(amostraAtualizada.getDataColeta());
        amostraExistente.setTipoAmostra(amostraAtualizada.getTipoAmostra());
        amostraExistente.setValidade(amostraAtualizada.getValidade());
        amostraExistente.setEstado(amostraAtualizada.getEstado());
        amostraExistente.setIdUsuario(amostraAtualizada.getIdUsuario());

        copiarSubEntidade(amostraExistente, amostraAtualizada);
        validarTipoSubEntidade(amostraExistente);
        setAmostraParaSubEntidade(amostraExistente);

        return amostraRepository.save(amostraExistente);
    }

    @Transactional
    public void deletar(UUID id) {
        Amostra amostra = amostraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amostra não encontrada"));

        if (amostra.getStatusAmostra() != null &&
                "despachada".equalsIgnoreCase(amostra.getStatusAmostra().getDescricao())) {
            throw new RuntimeException("Amostra despachada não pode ser deletada");
        }

        amostraRepository.delete(amostra);
    }

    private void copiarSubEntidade(Amostra destino, Amostra origem) {
        switch (origem.getTipoAmostra()) {
            case LARVA:
                if (destino.getLarva() != null && origem.getLarva() != null) {
                    var d = destino.getLarva();
                    var o = origem.getLarva();
                    d.setNumeroLarvas(o.getNumeroLarvas());
                    d.setTipoLarva(o.getTipoLarva());
                    d.setTipoDeposito(o.getTipoDeposito());
                }
                break;

            case MOLUSCO:
                if (destino.getMolusco() != null && origem.getMolusco() != null) {
                    var d = destino.getMolusco();
                    var o = origem.getMolusco();
                    d.setTipoCaramujo(o.getTipoCaramujo());
                    d.setNumeroVivos(o.getNumeroVivos());
                    d.setNumeroMortos(o.getNumeroMortos());
                }
                break;

            case ESCORPIAO:
                // Sem campos editáveis, mas deixado para possível expansão futura
                break;

            case CARRAPATO:
                if (destino.getCarrapato() != null && origem.getCarrapato() != null) {
                    var d = destino.getCarrapato();
                    var o = origem.getCarrapato();
                    d.setAnimalOrigem(o.getAnimalOrigem());
                }
                break;

            case FLEBOTOMINEO:
                if (destino.getFlebotomineo() != null && origem.getFlebotomineo() != null) {
                    var d = destino.getFlebotomineo();
                    var o = origem.getFlebotomineo();
                    d.setTipoVegetacao(o.getTipoVegetacao());
                    d.setDistanciaVegetacao(o.getDistanciaVegetacao());
                    d.setTemperaturaChegada(o.getTemperaturaChegada());
                    d.setTemperaturaSaida(o.getTemperaturaSaida());
                    d.setUmidadeChegada(o.getUmidadeChegada());
                    d.setUmidadeSaida(o.getUmidadeSaida());
                }
                break;

            case TRIATOMINEO:
                if (destino.getTriatomineo() != null && origem.getTriatomineo() != null) {
                    var d = destino.getTriatomineo();
                    var o = origem.getTriatomineo();
                    d.setVestigiosEncontrados(o.getVestigiosEncontrados());
                    d.setUsoInseticida(o.getUsoInseticida());
                    d.setCondicaoEnviado(o.getCondicaoEnviado());
                }
                break;

            default:
                throw new IllegalArgumentException("Tipo de amostra inválido");
        }
    }

    private void validarTipoSubEntidade(Amostra amostra) {
        TipoAmostra tipo = amostra.getTipoAmostra();
        if (tipo == null) throw new RuntimeException("Tipo de amostra não pode ser nulo");

        switch (tipo) {
            case LARVA:
                if (amostra.getLarva() == null) throw new RuntimeException("Entidade LARVA obrigatória para tipo LARVA");
                break;
            case MOLUSCO:
                if (amostra.getMolusco() == null) throw new RuntimeException("Entidade MOLUSCO obrigatória para tipo MOLUSCO");
                break;
            case ESCORPIAO:
                if (amostra.getEscorpiao() == null) throw new RuntimeException("Entidade ESCORPIAO obrigatória para tipo ESCORPIAO");
                break;
            case CARRAPATO:
                if (amostra.getCarrapato() == null) throw new RuntimeException("Entidade CARRAPATO obrigatória para tipo CARRAPATO");
                break;
            case FLEBOTOMINEO:
                if (amostra.getFlebotomineo() == null) throw new RuntimeException("Entidade FLEBOTOMINEO obrigatória para tipo FLEBOTOMINEO");
                break;
            case TRIATOMINEO:
                if (amostra.getTriatomineo() == null) throw new RuntimeException("Entidade TRIATOMINEO obrigatória para tipo TRIATOMINEO");
                break;
            default:
                throw new IllegalArgumentException("Tipo de amostra inválido");
        }
    }



    private void setAmostraParaSubEntidade(Amostra amostra) {
        if (amostra.getStatusAmostra() != null) {
            amostra.getStatusAmostra().setAmostra(amostra);
        }
        if (amostra.getLarva() != null) {
            amostra.getLarva().setAmostra(amostra);
        }
        if (amostra.getTriatomineo() != null) {
            amostra.getTriatomineo().setAmostra(amostra);
        }
        if (amostra.getMolusco() != null) {
            amostra.getMolusco().setAmostra(amostra);
        }
        if (amostra.getCarrapato() != null) {
            amostra.getCarrapato().setAmostra(amostra);
        }
        if (amostra.getEscorpiao() != null) {
            amostra.getEscorpiao().setAmostra(amostra);
        }
        if (amostra.getFlebotomineo() != null) {
            amostra.getFlebotomineo().setAmostra(amostra);
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
