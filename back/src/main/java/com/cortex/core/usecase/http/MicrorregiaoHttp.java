package com.cortex.core.usecase.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MicrorregiaoHttp {

    private Integer id;
    private String nome;
    private MesorregiaoHttp mesorregiao;

}
