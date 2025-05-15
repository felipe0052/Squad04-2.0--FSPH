package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class StatusAmostra {

    @Id
    private UUID id; // mesmo ID da Amostra

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Amostra amostra;

    private String descricao;

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Amostra getAmostra() { return amostra; }
    public void setAmostra(Amostra amostra) { this.amostra = amostra; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }


}

