package com.cortex.dataprovider;

import com.cortex.dataprovider.feign.LocalidadeFeign;
import com.cortex.dataprovider.gateway.LocalidadeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocalidadeDataProvider implements LocalidadeGateway {

    private final LocalidadeFeign localidadeFeign;

    @Autowired
    public LocalidadeDataProvider(final LocalidadeFeign localidadeFeign) {
        this.localidadeFeign = localidadeFeign;
    }

    public List<Object> listaRegioes() {
        return localidadeFeign.listaRegioes();
    }

    public List<Object> listaEstadosPorRegiao(Integer regiaoId) {
        return localidadeFeign.listaEstadosPorRegiao(regiaoId);
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        return localidadeFeign.listaMesorregioesPorEstado(estadoSigla);
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        return localidadeFeign.listaMicrorregioesPorMesorregiao(mesorregiaoId);
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        return localidadeFeign.listaMunicipiosPorMicrorregiao(microrregiaoId);
    }
}
