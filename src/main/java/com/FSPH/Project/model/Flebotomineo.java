package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Flebotomineo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFlebotomineo;

    private String tipoVegetacao;
    private Float distanciaVegetacao;
    private Float temperaturaChegada;
    private Float temperaturaSaida;
    private Float umidadeChegada;
    private Float umidadeSaida;

    @OneToOne
    @JoinColumn(name = "id_amostra")
    @JsonBackReference
    private Amostra amostra;

    // Getters e Setters
    public String getTipoVegetacao() {
        return tipoVegetacao;
    }

    public void setTipoVegetacao(String tipoVegetacao) {
        this.tipoVegetacao = tipoVegetacao;
    }

    public Float getDistanciaVegetacao() {
        return distanciaVegetacao;
    }

    public void setDistanciaVegetacao(Float distanciaVegetacao) {
        this.distanciaVegetacao = distanciaVegetacao;
    }

    public Float getTemperaturaChegada() {
        return temperaturaChegada;
    }

    public void setTemperaturaChegada(Float temperaturaChegada) {
        this.temperaturaChegada = temperaturaChegada;
    }

    public Float getTemperaturaSaida() {
        return temperaturaSaida;
    }

    public void setTemperaturaSaida(Float temperaturaSaida) {
        this.temperaturaSaida = temperaturaSaida;
    }

    public Float getUmidadeChegada() {
        return umidadeChegada;
    }

    public void setUmidadeChegada(Float umidadeChegada) {
        this.umidadeChegada = umidadeChegada;
    }

    public Float getUmidadeSaida() {
        return umidadeSaida;
    }

    public void setUmidadeSaida(Float umidadeSaida) {
        this.umidadeSaida = umidadeSaida;
    }

    public Amostra getAmostra() {
        return amostra;
    }

    public void setAmostra(Amostra amostra) {
        this.amostra = amostra;
    }
}
