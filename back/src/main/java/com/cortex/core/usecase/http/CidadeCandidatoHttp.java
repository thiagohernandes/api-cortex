package com.cortex.core.usecase.http;

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
public class CidadeCandidatoHttp implements Serializable {

    private Integer id;
    private String nome;
    private List<CandidatoHttp> candidatos;

}
