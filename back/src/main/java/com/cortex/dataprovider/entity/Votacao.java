package com.cortex.dataprovider.entity;

import com.cortex.core.usecase.http.RegiaoHttp;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Builder
public class Votacao {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "microrregiaoid")
    private Integer microrregiaoId;
    @Column(name = "mesorregiaoid")
    private Integer mesorregiaoId;
    @Column(name = "regiaoid")
    private Integer regiaoId;
    @Column(name = "regiaosigla")
    private String regiaoSigla;
    @Column(name = "regiaonome")
    private String regiaoNome;
    @Column(name = "siglauf")
    private String siglaUf;
    @Column(name = "votos")
    private Integer votos;
    @Column(name = "candidato")
    private String candidato;
    @Column(name = "partido")
    private String partido;

}
