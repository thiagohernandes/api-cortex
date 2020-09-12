package com.cortex.dataprovider.gateway;

import com.cortex.core.usecase.http.CidadeCandidatoHttp;

import java.io.IOException;
import java.util.List;

public interface EleicaoGateway {

    List<Object> listaRegioes();
    List<Object> listaEstadosPorRegiao(Integer regiaoId);
    List<Object> listaMesorregioesPorEstado(String estadoSigla);
    List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId);
    List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId);
    List<CidadeCandidatoHttp> cacheVotacaoPresidencial() throws IOException;

}
