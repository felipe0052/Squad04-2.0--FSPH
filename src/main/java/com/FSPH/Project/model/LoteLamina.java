package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoteLamina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String protocolo;
    private String tipoLote; // exemplo: "Lâmina"
    private Date dataCriacao;
    private UUID idUsuario;

    // Getters e Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getProtocolo() { return protocolo; }
    public void setProtocolo(String protocolo) { this.protocolo = protocolo; }

    public String getTipoLote() { return tipoLote; }
    public void setTipoLote(String tipoLote) { this.tipoLote = tipoLote; }

    public Date getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Date dataCriacao) { this.dataCriacao = dataCriacao; }

    public UUID getIdUsuario() { return idUsuario; }
    public void setIdUsuario(UUID idUsuario) { this.idUsuario = idUsuario; }
}
