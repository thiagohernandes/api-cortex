package com.cortex.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CidadeCandidatoHttp implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("microrregiao")
    private Integer microrregiaoId;
    @JsonProperty("mesorregiao")
    private Integer mesorregiaoId;
    @JsonProperty("regiao")
    private RegiaoHttp regiao;
    @JsonProperty("sigla_uf")
    private String siglaUf;
    @JsonProperty("votos")
    private Integer votos;
    @JsonProperty("partido")
    private String partido;
    @JsonProperty("candidato")
    private String candidato;


}
