package com.FSPH.Project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class Lamina {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idLamina;
    private int numLaminas;
    private String municipio;
    private Date dataEnvio;

    //getters e setters
    public UUID getIdLamina() {
        return idLamina;
    }
    public void setIdLamina(UUID idLamina) {
        this.idLamina = idLamina;
    }
    public int getNumLaminas() {
        return numLaminas;
    }
    public void setNumLaminas(int numLaminas) {
        this.numLaminas = numLaminas;
    }
    public String getMunicipio() {
        return municipio;
    }
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    public Date getDataEnvio() {
        return dataEnvio;
    }
    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
