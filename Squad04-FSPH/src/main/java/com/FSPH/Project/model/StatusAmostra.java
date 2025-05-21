package com.FSPH.Project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class StatusAmostra {

    @Id
    private UUID id; // mesmo ID da Amostra

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Amostra amostra;

    private String descricao;


}

