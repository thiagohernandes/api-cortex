package com.cortex.core.usecase.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoHttp {

    private Integer id;
    private String sigla;
    private String nome;
    private RegiaoHttp regiao;

}
