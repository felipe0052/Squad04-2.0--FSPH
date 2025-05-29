package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Escorpiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEscorpiao;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
