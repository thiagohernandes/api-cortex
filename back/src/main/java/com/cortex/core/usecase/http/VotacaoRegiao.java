package com.cortex.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotacaoRegiao {

    @JsonProperty("regiaoNome")
    private String regiaoNome;
    @JsonProperty("candidato")
    private String candidato;
    @JsonProperty("partido")
    private String partido;
    @JsonProperty("totalVotos")
    private Integer totalVotos;

}
