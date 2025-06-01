package com.FSPH.Project.model;

import com.FSPH.Project.model.TipoLote;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idLote;

    private String protocolo;

    private Date dataCriacao;

    private UUID idUsuario;

    @Enumerated(EnumType.STRING)
    private TipoLote tipoLote;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lote_id_amostra")
    private List<Amostra> amostras;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lote_id_lamina")
    private List<Lamina> laminas;

    // Getters e Setters

    public UUID getIdLote() {
        return idLote;
    }

    public void setIdLote(UUID idLote) {
        this.idLote = idLote;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoLote getTipoLote() {
        return tipoLote;
    }

    public void setTipoLote(TipoLote tipoLote) {
        this.tipoLote = tipoLote;
    }

    public List<Amostra> getAmostras() {
        return amostras;
    }

    public void setAmostras(List<Amostra> amostras) {
        this.amostras = amostras;
    }

    public List<Lamina> getLaminas() {
        return laminas;
    }

    public void setLaminas(List<Lamina> laminas) {
        this.laminas = laminas;
    }
}
