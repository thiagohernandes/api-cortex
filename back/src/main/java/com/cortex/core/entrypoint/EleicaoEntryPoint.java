package com.cortex.core.entrypoint;

import com.cortex.core.usecase.EleicaoUseCase;
import com.cortex.core.usecase.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("${app.api-url-ibge}")
public class EleicaoEntryPoint {

    private final EleicaoUseCase eleicaoUseCase;
    private final String mensagem = "Houve algum erro na chamada HTTP {}";

    @Autowired
    public EleicaoEntryPoint(final EleicaoUseCase eleicaoUseCase) {
        this.eleicaoUseCase = eleicaoUseCase;
    }

    @RequestMapping("/regioes")
    public ResponseEntity<List<RegiaoHttp>> getRegioes() {
        List<RegiaoHttp> listaRegioes = new ArrayList<>();
        try {
            listaRegioes = (List<RegiaoHttp>)(List<?>)this.eleicaoUseCase.listaRegioes();
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaRegioes);
    }

    @RequestMapping("/regioes/{regiao-id}/estados")
    public ResponseEntity<List<EstadoHttp>> getEstadosPorRegiao(@PathVariable("regiao-id") Integer regiaoId) {
        List<EstadoHttp> listaEstadosPorRegiao = new ArrayList<>();
        try {
            listaEstadosPorRegiao = (List<EstadoHttp>)(List<?>)this.eleicaoUseCase.listaEstadosPorRegioes(regiaoId);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaEstadosPorRegiao);
    }

    @RequestMapping("/estados/{estado-sigla}/mesorregioes")
    public ResponseEntity<List<EstadoHttp>> getMesorregioesPorEstado(@PathVariable("estado-sigla") String estadoSigla) {
        List<EstadoHttp> listaMesorregioesPorEstado = new ArrayList<>();
        try {
            listaMesorregioesPorEstado = (List<EstadoHttp>)(List<?>)this.eleicaoUseCase
                    .listaMesorregioesPorEstado(estadoSigla);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaMesorregioesPorEstado);
    }

    @RequestMapping("/mesorregioes/{mesorregiao-id}/microrregioes")
    public ResponseEntity<List<MicrorregiaoHttp>> getMicrorregioesPorMesorregiao(@PathVariable("mesorregiao-id") String mesorregiaoId) {
        List<MicrorregiaoHttp> listaMicroregioesPorMesorregiaoId = new ArrayList<>();
        try {
            listaMicroregioesPorMesorregiaoId = (List<MicrorregiaoHttp>)(List<?>)this.eleicaoUseCase
                    .listaMicrorregioesPorMesorregiao(mesorregiaoId);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaMicroregioesPorMesorregiaoId);
    }

    @RequestMapping("/microrregioes/{microrregiao-id}/municipios")
    public ResponseEntity<List<MunicipioHttp>> getMunicipiosPorMicrorregiao(@PathVariable("microrregiao-id") String microrregiaoId) {
        List<MunicipioHttp> listaMunicipiosPorMicrorregiao = new ArrayList<>();
        try {
            listaMunicipiosPorMicrorregiao = (List<MunicipioHttp>)(List<?>)this.eleicaoUseCase
                    .listaMunicipiosPorMicrorregiao(microrregiaoId);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaMunicipiosPorMicrorregiao);
    }

    @RequestMapping("/cache")
    public ResponseEntity<List<CidadeCandidatoHttp>> getCacheVotacao() {
        List<CidadeCandidatoHttp> cache = new ArrayList<>();
        try {
            cache = this.eleicaoUseCase.cacheVotacaoPresidencial();
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(cache);
    }

}
