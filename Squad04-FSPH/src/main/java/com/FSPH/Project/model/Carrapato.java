package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrapato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCarrapato;

    private String animalOrigem;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public String getAnimalOrigem() { return animalOrigem; }
    public void setAnimalOrigem(String animalOrigem) { this.animalOrigem = animalOrigem; }

    public Amostra getAmostra() { return amostra; }
    public void setAmostra(Amostra amostra) { this.amostra = amostra; }
}
