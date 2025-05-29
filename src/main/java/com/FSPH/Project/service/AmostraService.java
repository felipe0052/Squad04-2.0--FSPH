package com.FSPH.Project.service;

import com.FSPH.Project.model.*;
import com.FSPH.Project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AmostraService {

    @Autowired private AmostraRepository amostraRepository;
    @Autowired private LarvaRepository larvaRepository;
    @Autowired private TriatomineoRepository triatomineoRepository;
    @Autowired private MoluscoRepository moluscoRepository;
    @Autowired private CarrapatoRepository carrapatoRepository;
    @Autowired private EscorpiaoRepository escorpiaoRepository;
    @Autowired private FlebotomineoRepository flebotomineoRepository;

    // Criar amostra com atributos específicos
    public Amostra criarAmostraComTipo(Amostra amostra) {
        amostra = amostraRepository.save(amostra);

        switch (amostra.getTipoAmostra().toLowerCase()) {
            case "larva":
                Larva larva = amostra.getLarva();
                if (larva == null) throw new IllegalArgumentException("Dados de larva são obrigatórios.");
                larva.setAmostra(amostra);
                larvaRepository.save(larva);
                break;
            case "triatomineo":
                Triatomineo triatomineo = amostra.getTriatomineo();
                if (triatomineo == null) throw new IllegalArgumentException("Dados de triatomineo são obrigatórios.");
                triatomineo.setAmostra(amostra);
                triatomineoRepository.save(triatomineo);
                break;
            case "molusco":
                Molusco molusco = amostra.getMolusco();
                if (molusco == null) throw new IllegalArgumentException("Dados de molusco são obrigatórios.");
                molusco.setAmostra(amostra);
                moluscoRepository.save(molusco);
                break;
            case "carrapato":
                Carrapato carrapato = amostra.getCarrapato();
                if (carrapato == null) throw new IllegalArgumentException("Dados de carrapato são obrigatórios.");
                carrapato.setAmostra(amostra);
                carrapatoRepository.save(carrapato);
                break;
            case "escorpiao":
                Escorpiao escorpiao = amostra.getEscorpiao();
                if (escorpiao == null) throw new IllegalArgumentException("Dados de escorpião são obrigatórios.");
                escorpiao.setAmostra(amostra);
                escorpiaoRepository.save(escorpiao);
                break;
            case "flebotomineo":
                Flebotomineo flebotomineo = amostra.getFlebotomineo();
                if (flebotomineo == null) throw new IllegalArgumentException("Dados de flebotomíneo são obrigatórios.");
                flebotomineo.setAmostra(amostra);
                flebotomineoRepository.save(flebotomineo);
                break;
            default:
                throw new IllegalArgumentException("Tipo de amostra inválido.");
        }

        return amostra;
    }

    // Editar amostra, se status != 3
    public Amostra editarAmostra(UUID id, Amostra novaAmostra) {
        Optional<Amostra> existente = amostraRepository.findById(id);
        if (existente.isPresent()) {
            Amostra atual = existente.get();
            if (!"3".equals(atual.getStatusAmostra())) {
                novaAmostra.setIdAmostra(id);

                // Verificar e ajustar as entidades dependentes, como larva, triatomineo, etc.
                switch (novaAmostra.getTipoAmostra().toLowerCase()) {
                    case "larva":
                        if (novaAmostra.getLarva() == null) {
                            throw new IllegalArgumentException("Dados de larva são obrigatórios.");
                        }
                        novaAmostra.getLarva().setAmostra(novaAmostra);
                        larvaRepository.save(novaAmostra.getLarva());
                        break;
                    case "triatomineo":
                        if (novaAmostra.getTriatomineo() == null) {
                            throw new IllegalArgumentException("Dados de triatomineo são obrigatórios.");
                        }
                        novaAmostra.getTriatomineo().setAmostra(novaAmostra);
                        triatomineoRepository.save(novaAmostra.getTriatomineo());
                        break;
                    case "molusco":
                        if (novaAmostra.getMolusco() == null) {
                            throw new IllegalArgumentException("Dados de molusco são obrigatórios.");
                        }
                        novaAmostra.getMolusco().setAmostra(novaAmostra);
                        moluscoRepository.save(novaAmostra.getMolusco());
                        break;
                    case "carrapato":
                        if (novaAmostra.getCarrapato() == null) {
                            throw new IllegalArgumentException("Dados de carrapato são obrigatórios.");
                        }
                        novaAmostra.getCarrapato().setAmostra(novaAmostra);
                        carrapatoRepository.save(novaAmostra.getCarrapato());
                        break;
                    case "escorpiao":
                        if (novaAmostra.getEscorpiao() == null) {
                            throw new IllegalArgumentException("Dados de escorpião são obrigatórios.");
                        }
                        novaAmostra.getEscorpiao().setAmostra(novaAmostra);
                        escorpiaoRepository.save(novaAmostra.getEscorpiao());
                        break;
                    case "flebotomineo":
                        if (novaAmostra.getFlebotomineo() == null) {
                            throw new IllegalArgumentException("Dados de flebotomíneo são obrigatórios.");
                        }
                        novaAmostra.getFlebotomineo().setAmostra(novaAmostra);
                        flebotomineoRepository.save(novaAmostra.getFlebotomineo());
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo de amostra inválido.");
                }

                return amostraRepository.save(novaAmostra);
            } else {
                throw new IllegalStateException("Amostra com status 3 não pode ser editada.");
            }
        } else {
            throw new IllegalArgumentException("Amostra não encontrada.");
        }
    }

    // Deletar amostra com tipo específico
    public void deletarAmostra(UUID id) {
        Optional<Amostra> amostraOpt = amostraRepository.findById(id);
        if (amostraOpt.isPresent()) {
            Amostra amostra = amostraOpt.get();
            switch (amostra.getTipoAmostra().toLowerCase()) {
                case "larva":
                    larvaRepository.deleteByAmostraIdAmostra(id);
                    break;
                case "triatomineo":
                    triatomineoRepository.deleteByAmostraIdAmostra(id);
                    break;
                case "molusco":
                    moluscoRepository.deleteByAmostraIdAmostra(id);
                    break;
                case "carrapato":
                    carrapatoRepository.deleteByAmostraIdAmostra(id);
                    break;
                case "escorpiao":
                    escorpiaoRepository.deleteByAmostraIdAmostra(id);
                    break;
                case "flebotomineo":
                    flebotomineoRepository.deleteByAmostraIdAmostra(id);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de amostra não suportado.");
            }
            amostraRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Amostra não encontrada.");
        }
    }

    // Listar todas as amostras
    public List<Amostra> listarTodas() {
        return amostraRepository.findAll();
    }

    // Buscar amostra por ID
    public Optional<Amostra> buscarPorId(UUID id) {
        return amostraRepository.findById(id);
    }

    // Buscar por tipo de amostra
    public List<Amostra> buscarPorTipo(String tipoAmostra) {
        return amostraRepository.findByTipoAmostraIgnoreCase(tipoAmostra);
    }
}
