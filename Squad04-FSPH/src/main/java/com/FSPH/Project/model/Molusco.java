package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Molusco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMolusco;

    private String tipoCaramujo;
    private Integer numeroVivos;
    private Integer numeroMortos;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public String getTipoCaramujo() {
        return tipoCaramujo;
    }

    public void setTipoCaramujo(String tipoCaramujo) {
        this.tipoCaramujo = tipoCaramujo;
    }

    public Integer getNumeroVivos() {
        return numeroVivos;
    }

    public void setNumeroVivos(Integer numeroVivos) {
        this.numeroVivos = numeroVivos;
    }

    public Integer getNumeroMortos() {
        return numeroMortos;
    }

    public void setNumeroMortos(Integer numeroMortos) {
        this.numeroMortos = numeroMortos;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
