package com.cortex.dataprovider.fallback;

import com.cortex.core.usecase.http.FallbackHttp;
import com.cortex.dataprovider.feign.EleicaoFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class EleicaoFallback implements EleicaoFeign {

    private final String mensagemFallback = "Dados indisponíveis momentaneamente!";
    private String metodoRequisitado = "Método requisitado {}";

    public List<Object> listaRegioes() {
        log.warn(metodoRequisitado, "listaRegioes()");
        return mensagemFallbak();
    }

    public List<Object> listaEstadosPorRegiao(Integer regiaoId) {
        log.warn(metodoRequisitado, "listaEstadosPorRegiao()");
        return mensagemFallbak();
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        log.warn(metodoRequisitado, "listaMesorregioesPorEstado()");
        return mensagemFallbak();
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        log.warn(metodoRequisitado, "listaMicrorregioesPorMesorregiao()");
        return mensagemFallbak();
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        log.warn(metodoRequisitado, "listaMunicipiosPorMicrorregiao()");
        return mensagemFallbak();
    }

    public List<Object> listaMunicipios() {
        log.warn(metodoRequisitado, "listaMunicipios()");
        return mensagemFallbak();
    }

    private final List<Object> mensagemFallbak() {
        return Arrays.asList(new FallbackHttp().builder()
                .dataHora(LocalDateTime.now())
                .mensagem(mensagemFallback)
                .build());
    }
}
