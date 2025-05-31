package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class StatusAmostra {

    @Id
    private int idStatusAmostra;

    @OneToOne
    @JsonBackReference
    private Amostra amostra;

    private String descricao;

    // Getters e Setters
    public int getId() { return idStatusAmostra; }
    public void setId(int idStatusAmostra) { this.idStatusAmostra = idStatusAmostra; }

    public Amostra getAmostra() { return amostra; }
    public void setAmostra(Amostra amostra) { this.amostra = amostra; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }


}

