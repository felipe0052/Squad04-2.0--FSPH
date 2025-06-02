package com.FSPH.Project.model;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class AmostraLote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAmostraLote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id")
    private Lote lote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amostra_id")
    private Amostra amostra;

    // Getters e Setters
    public UUID getIdAmostraLote() {
        return idAmostraLote;
    }

    public void setIdAmostraLote(UUID idAmostraLote) {
        this.idAmostraLote = idAmostraLote;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
