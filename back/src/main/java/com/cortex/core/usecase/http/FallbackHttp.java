package com.cortex.core.usecase.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FallbackHttp {

    @JsonProperty("mensagem")
    private String mensagem;
    @JsonProperty("dataHora")
    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime dataHora;

}

