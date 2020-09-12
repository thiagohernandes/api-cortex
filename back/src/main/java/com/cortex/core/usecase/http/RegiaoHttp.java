package com.cortex.core.usecase.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegiaoHttp {

    private Integer id;
    private char sigla;
    private String nome;

}
