package com.cortex.core.usecase;

import com.cortex.dataprovider.LocalidadeDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Component
public class LocalidadeUseCase {

    private final LocalidadeDataProvider localidadeDataProvider;

    @Autowired
    public LocalidadeUseCase(final LocalidadeDataProvider localidadeDataProvider) {
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

}
