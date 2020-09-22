package com.cortex.dataprovider.gateway;

import com.cortex.core.usecase.http.VotacaoRegiao;

import java.io.IOException;
import java.util.List;

public interface EleicaoGateway {

    List<Object> listaRegioes();
    List<Object> listaEstadosPorRegiao(Integer regiaoId);
    List<Object> listaMesorregioesPorEstado(String estadoSigla);
    List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId);
    List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId);
    void importarCandidatos() throws IOException;
    List<VotacaoRegiao> votacaoPorRegioes();
    List<VotacaoRegiao> votacaoPorRegioes(List<String> regioesSiglas);
    List<VotacaoRegiao> votacaoPorRegioesEUfs(List<String> regioesSiglas, List<String> ufs);
    List<VotacaoRegiao> votacaoPorRegioesEUfsEMesorregioes(List<String> regioesSiglas, List<String> ufs, List<String> mesorregioes);
}
