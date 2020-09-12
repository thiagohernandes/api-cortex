package com.cortex.dataprovider.feign;

import com.cortex.dataprovider.fallback.EleicaoFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "${feign.ibge.localidades.name}", url = "${feign.ibge.localidades.url}",
             fallback = EleicaoFallback.class)
public interface EleicaoFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/regioes")
    List<Object> listaRegioes();

    @RequestMapping(method = RequestMethod.GET, value = "/regioes/{regiao-id}/estados")
    List<Object> listaEstadosPorRegiao(@PathVariable("regiao-id") Integer regiaoId);

    @RequestMapping(method = RequestMethod.GET, value = "/estados/{estado-sigla}/mesorregioes")
    List<Object> listaMesorregioesPorEstado(@PathVariable("estado-sigla") String estadoSigla);

    @RequestMapping(method = RequestMethod.GET, value = "/mesorregioes/{mesorregiao-id}/microrregioes")
    List<Object> listaMicrorregioesPorMesorregiao(@PathVariable("mesorregiao-id") String mesorregiaoId);

    @RequestMapping(method = RequestMethod.GET, value = "/microrregioes/{microrregiao-id}/municipios")
    List<Object> listaMunicipiosPorMicrorregiao(@PathVariable("microrregiao-id") String microrregiaoId);

}
