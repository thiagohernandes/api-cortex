package com.cortex.core.usecase;

import com.cortex.core.usecase.http.VotacaoRegiao;
import com.cortex.dataprovider.EleicaoDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EleicaoUseCase {

    private final EleicaoDataProvider eleicaoDataProvider;

    @Autowired
    public EleicaoUseCase(final EleicaoDataProvider eleicaoDataProvider) {
        this.eleicaoDataProvider = eleicaoDataProvider;
    }

    public List<Object> listaRegioes() {
        return eleicaoDataProvider.listaRegioes();
    }

    public List<Object> listaEstadosPorRegioes(Integer regiaoId) {
        return eleicaoDataProvider.listaEstadosPorRegiao(regiaoId);
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        return eleicaoDataProvider.listaMesorregioesPorEstado(estadoSigla);
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        return eleicaoDataProvider.listaMicrorregioesPorMesorregiao(mesorregiaoId);
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        return eleicaoDataProvider.listaMunicipiosPorMicrorregiao(microrregiaoId);
    }

    public void importaCandidatos() throws IOException {
        this.eleicaoDataProvider.importarCandidatos();
    }

    public List<VotacaoRegiao> votacaoPorRegioes() {
        return this.eleicaoDataProvider.votacaoPorRegioes();
    }

    public List<VotacaoRegiao> votacaoPorRegioes(List<String> regioesSiglas) {
        return this.eleicaoDataProvider.votacaoPorRegioes(regioesSiglas);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfs(List<String> regioesSiglas, List<String> ufs){
        return this.eleicaoDataProvider.votacaoPorRegioesEUfs(regioesSiglas, ufs);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfsEMesorregioes(List<String> regioesSiglas,
                                                                  List<String> ufs,
                                                                  List<String> mesorregioes){
        return this.eleicaoDataProvider.votacaoPorRegioesEUfsEMesorregioes(regioesSiglas, ufs, mesorregioes);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(List<String> regioesSiglas,
                                                                                List<String> ufs,
                                                                                List<String> mesorregioes,
                                                                                List<String> microrregioes){
        return this.eleicaoDataProvider.votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(regioesSiglas, ufs, mesorregioes, microrregioes);
    }

}
