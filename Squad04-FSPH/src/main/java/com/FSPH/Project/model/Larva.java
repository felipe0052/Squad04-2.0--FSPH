package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Larva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLarva;

    private Integer numeroLarvas;
    private String tipoLarva;
    private String tipoDeposito;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public Integer getNumeroLarvas() {
        return numeroLarvas;
    }

    public void setNumeroLarvas(Integer numeroLarvas) {
        this.numeroLarvas = numeroLarvas;
    }

    public String getTipoLarva() {
        return tipoLarva;
    }

    public void setTipoLarva(String tipoLarva) {
        this.tipoLarva = tipoLarva;
    }

    public String getTipoDeposito() {
        return tipoDeposito;
    }

    public void setTipoDeposito(String tipoDeposito) {
        this.tipoDeposito = tipoDeposito;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
