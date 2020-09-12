package com.cortex.core.usecase;

import com.cortex.core.usecase.http.CidadeCandidatoHttp;
import com.cortex.dataprovider.EleicaoDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class EleicaoUseCase {

    private final EleicaoDataProvider localidadeDataProvider;

    @Autowired
    public EleicaoUseCase(final EleicaoDataProvider localidadeDataProvider) {
        this.localidadeDataProvider = localidadeDataProvider;
    }

    public List<Object> listaRegioes() {
        return localidadeDataProvider.listaRegioes();
    }

    public List<Object> listaEstadosPorRegioes(Integer regiaoId) {
        return localidadeDataProvider.listaEstadosPorRegiao(regiaoId);
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        return localidadeDataProvider.listaMesorregioesPorEstado(estadoSigla);
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        return localidadeDataProvider.listaMicrorregioesPorMesorregiao(mesorregiaoId);
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        return localidadeDataProvider.listaMunicipiosPorMicrorregiao(microrregiaoId);
    }

    public List<CidadeCandidatoHttp> cacheVotacaoPresidencial() throws IOException {
        return localidadeDataProvider.cacheVotacaoPresidencial();
    }

}
