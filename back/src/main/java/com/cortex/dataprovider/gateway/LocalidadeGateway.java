package com.cortex.dataprovider.gateway;

import java.util.List;

public interface LocalidadeGateway {

    List<Object> listaRegioes();
    List<Object> listaEstadosPorRegiao(Integer regiaoId);
    List<Object> listaMesorregioesPorEstado(String estadoSigla);
    List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId);
    List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId);

}
