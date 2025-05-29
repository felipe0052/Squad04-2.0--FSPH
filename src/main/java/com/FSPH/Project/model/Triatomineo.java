package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Triatomineo {

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idTriatomineo;

    private String vestigiosEncontrados;
    private String usoInseticida;
    private String condicaoEnviado;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public String getVestigiosEncontrados() {
        return vestigiosEncontrados;
    }

    public void setVestigiosEncontrados(String vestigiosEncontrados) {
        this.vestigiosEncontrados = vestigiosEncontrados;
    }

    public String getUsoInseticida() {
        return usoInseticida;
    }

    public void setUsoInseticida(String usoInseticida) {
        this.usoInseticida = usoInseticida;
    }

    public String getCondicaoEnviado() {
        return condicaoEnviado;
    }

    public void setCondicaoEnviado(String condicaoEnviado) {
        this.condicaoEnviado = condicaoEnviado;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
