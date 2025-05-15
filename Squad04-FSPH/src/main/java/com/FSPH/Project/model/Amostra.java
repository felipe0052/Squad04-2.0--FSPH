package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Amostra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAmostra;

    private Date dataColeta;

    @Enumerated(EnumType.STRING)
    private TipoAmostra tipoAmostra;

    private Date validade;
    private String estado;
    private UUID idUsuario;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private StatusAmostra statusAmostra;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Larva larva;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Triatomineo triatomineo;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Molusco molusco;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Carrapato carrapato;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Escorpiao escorpiao;

    @OneToOne(mappedBy = "amostra", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    private Flebotomineo flebotomineo;

    // Getters e Setters
    public UUID getIdAmostra() { return idAmostra; }
    public void setIdAmostra(UUID idAmostra) { this.idAmostra = idAmostra; }

    public Date getDataColeta() { return dataColeta; }
    public void setDataColeta(Date dataColeta) { this.dataColeta = dataColeta; }
    
    public Date getValidade() { return validade; }
    public void setValidade(Date validade) { this.validade = validade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public UUID getIdUsuario() { return idUsuario; }
    public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }

    public StatusAmostra getStatusAmostra() { return statusAmostra; }
    public void setStatusAmostra(StatusAmostra statusAmostra) { this.statusAmostra = statusAmostra; }

    public Larva getLarva() { return larva; }
    public void setLarva(Larva larva) { this.larva = larva; }

    public Triatomineo getTriatomineo() { return triatomineo; }
    public void setTriatomineo(Triatomineo triatomineo) { this.triatomineo = triatomineo; }

    public Molusco getMolusco() { return molusco; }
    public void setMolusco(Molusco molusco) { this.molusco = molusco; }

    public Carrapato getCarrapato() { return carrapato; }
    public void setCarrapato(Carrapato carrapato) { this.carrapato = carrapato; }

    public Escorpiao getEscorpiao() { return escorpiao; }
    public void setEscorpiao(Escorpiao escorpiao) { this.escorpiao = escorpiao; }

    public Flebotomineo getFlebotomineo() { return flebotomineo; }
    public void setFlebotomineo(Flebotomineo flebotomineo) { this.flebotomineo = flebotomineo; }



// Método helper para limpar subentidades para validação
    public void clearSubEntidades() {
        this.larva = null;
        this.triatomineo = null;
        this.molusco = null;
        this.carrapato = null;
        this.escorpiao = null;
        this.flebotomineo = null;
    }

    public TipoAmostra getTipoAmostra() {
        return tipoAmostra;
    }

    public void setTipoAmostra(TipoAmostra tipoAmostra) {
        this.tipoAmostra = tipoAmostra;
    }
}
