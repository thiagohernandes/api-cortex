package com.cortex.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MicrorregiaoHttp {

    private Integer id;
    private String nome;
    private MesorregiaoHttp mesorregiao;

}
