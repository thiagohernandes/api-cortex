package com.cortex.dataprovider;

import com.cortex.core.usecase.http.CandidatoHttp;
import com.cortex.core.usecase.http.CidadeCandidatoHttp;
import com.cortex.dataprovider.feign.EleicaoFeign;
import com.cortex.dataprovider.gateway.EleicaoGateway;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class EleicaoDataProvider implements EleicaoGateway {

    private final EleicaoFeign eleicaoFeign;

    @Autowired
    public EleicaoDataProvider(final EleicaoFeign eleicaoFeign) {
        this.eleicaoFeign = eleicaoFeign;
    }

    public List<Object> listaRegioes() {
        return eleicaoFeign.listaRegioes();
    }

    public List<Object> listaEstadosPorRegiao(Integer regiaoId) {
        return eleicaoFeign.listaEstadosPorRegiao(regiaoId);
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        return eleicaoFeign.listaMesorregioesPorEstado(estadoSigla);
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        return eleicaoFeign.listaMicrorregioesPorMesorregiao(mesorregiaoId);
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        return eleicaoFeign.listaMunicipiosPorMicrorregiao(microrregiaoId);
    }

    @Cacheable(value = "cachevotacao")
    public List<CidadeCandidatoHttp> cacheVotacaoPresidencial() throws IOException {
        final List<CidadeCandidatoHttp> listaCidadeCandidatoVotacao = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Object>> typeReference = new TypeReference<List<Object>>() {};
        final InputStream inputStream = TypeReference.class.getResourceAsStream("/resultado-1-turno-presidente-2014.json");

        final List<Object> cacheVotacao = mapper.readValue(inputStream, typeReference);

        try {
            cacheVotacao.stream().forEach(item -> {
                ArrayList<Object> cidadePosicoes = (ArrayList<Object>) item;
                if (!cidadePosicoes.get(0).toString().equalsIgnoreCase("UF")
                        && !cidadePosicoes.get(0).toString().equalsIgnoreCase("BR")
                            && !cidadePosicoes.get(0).toString().equalsIgnoreCase("#N/A")) {
                    AtomicInteger countVotosCandidatos = new AtomicInteger();
                    List<CandidatoHttp> listaCandidatos = new ArrayList<>();
                    cidadePosicoes.forEach(candidatosArray -> {
                        if (countVotosCandidatos.get() >= 6) {
                            ArrayList<Object> candidatoPosicoes = (ArrayList<Object>) cidadePosicoes.get(countVotosCandidatos.get());
                            listaCandidatos.add(CandidatoHttp.builder()
                                    .partido(candidatoPosicoes.get(0).toString())
                                    .nome(candidatoPosicoes.get(1).toString())
                                    .votos(Double.parseDouble(candidatoPosicoes.get(2).toString()))
                                    .build());
                        }
                        countVotosCandidatos.set(countVotosCandidatos.get() + 1);
                    });
                    listaCidadeCandidatoVotacao.add(CidadeCandidatoHttp.builder()
                            .candidatos(listaCandidatos)
                            .id(Integer.parseInt(cidadePosicoes.get(1).toString()))
                            .nome(cidadePosicoes.get(0).toString())
                            .build());
                }
            });
        } catch (Exception e) {
            log.error("Erro na transformação dos dados de votação! {}", e.getMessage());
        }
        return listaCidadeCandidatoVotacao;
    }
}
