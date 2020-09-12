package com.cortex.core.usecase.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioHttp {

    private Integer id;
    private String nome;
    private MicrorregiaoHttp microrregiao;

}
