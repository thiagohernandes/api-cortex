package com.cortex.core.entrypoint;

import com.cortex.core.usecase.EleicaoUseCase;
import com.cortex.core.usecase.http.*;
import com.cortex.core.util.ApiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Eleições/Votação presidencial")
public class EleicaoEntryPoint {

    private final EleicaoUseCase eleicaoUseCase;
    private final String mensagem = "Houve algum erro na chamada HTTP {}";

    @Autowired
    private ApiUtil apiUtil;

    @Autowired
    public EleicaoEntryPoint(final EleicaoUseCase eleicaoUseCase) {
        this.eleicaoUseCase = eleicaoUseCase;
    }

    @ApiOperation(value = "Retorna as regiões")
    @RequestMapping("${app.api-url-ibge}/regioes")
    public ResponseEntity<List<RegiaoHttp>> getRegioes() {
        List<RegiaoHttp> listaRegioes = new ArrayList<>();
        try {
            listaRegioes = (List<RegiaoHttp>)(List<?>)this.eleicaoUseCase.listaRegioes();
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaRegioes);
    }

    @ApiOperation(value = "Retorna os estados por código de região")
    @RequestMapping("${app.api-url-ibge}/regioes/{regiao-id}/estados")
    public ResponseEntity<List<EstadoHttp>> getEstadosPorRegiao(@PathVariable("regiao-id") Integer regiaoId) {
        List<EstadoHttp> listaEstadosPorRegiao = new ArrayList<>();
        try {
            listaEstadosPorRegiao = (List<EstadoHttp>)(List<?>)this.eleicaoUseCase.listaEstadosPorRegioes(regiaoId);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaEstadosPorRegiao);
    }

    @ApiOperation(value = "Retorna as mesorregiões por sigla do estado")
    @RequestMapping("${app.api-url-ibge}/estados/{estado-sigla}/mesorregioes")
    public ResponseEntity<List<MesorregiaoHttp>> getMesorregioesPorEstado(@PathVariable("estado-sigla") String estadoSigla) {
        List<MesorregiaoHttp> listaMesorregioesPorEstado = new ArrayList<>();
        try {
            listaMesorregioesPorEstado = (List<MesorregiaoHttp>)(List<?>)this.eleicaoUseCase
                    .listaMesorregioesPorEstado(estadoSigla);
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaMesorregioesPorEstado);
    }

    @ApiOperation(value = "Retorna as microrregiões por código da mesorregião")
    @RequestMapping("${app.api-url-ibge}/mesorregioes/{mesorregiao-id}/microrregioes")
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

    @ApiOperation(value = "Retorna os municípios por código da microrregião")
    @RequestMapping("${app.api-url-ibge}/microrregioes/{microrregiao-id}/municipios")
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

    @ApiOperation(value = "Retorna a votação por região (geral)")
    @RequestMapping("${app.api-url-eleicao}")
    public ResponseEntity<List<VotacaoRegiao>> getVotacaoPorRegiao() {
        List<VotacaoRegiao> listaVotacaoPorRegiao = new ArrayList<>();
        try {
            listaVotacaoPorRegiao = this.eleicaoUseCase.votacaoPorRegioes();
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaVotacaoPorRegiao);
    }

    @ApiOperation(value = "Retorna a votação por regiões")
    @RequestMapping("${app.api-url-eleicao}/{regioes}")
    public ResponseEntity<List<VotacaoRegiao>> getVotacaoPorRegiaoUfs(@PathVariable("regioes") String regioes) {
        List<VotacaoRegiao> listaVotacao = new ArrayList<>();
        try {
            listaVotacao = this.eleicaoUseCase.votacaoPorRegioes(apiUtil.formataPipeString(regioes));
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaVotacao);
    }

    @ApiOperation(value = "Retorna a votação por regiões e ufs")
    @RequestMapping("${app.api-url-eleicao}/{regioes}/{ufs}")
    public ResponseEntity<List<VotacaoRegiao>> getVotacaoPorRegioesEUfs(@PathVariable("regioes") String regioes,
                                                                        @PathVariable("ufs") String ufs) {
        List<VotacaoRegiao> listaVotacao = new ArrayList<>();
        try {
            listaVotacao = this.eleicaoUseCase.votacaoPorRegioesEUfs(apiUtil.formataPipeString(regioes),
                                                                               apiUtil.formataPipeString(ufs));
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaVotacao);
    }

    @ApiOperation(value = "Retorna a votação por regiões, ufs e mesorregiões")
    @RequestMapping("${app.api-url-eleicao}/{regioes}/{ufs}/{mesorregioes}")
    public ResponseEntity<List<VotacaoRegiao>> getVotacaoPorRegioesEUfsEMEsorregioes(@PathVariable("regioes") String regioes,
                                                                        @PathVariable("ufs") String ufs,
                                                                        @PathVariable("mesorregioes") String mesorregioes) {
        List<VotacaoRegiao> listaVotacao = new ArrayList<>();
        try {
            listaVotacao = this.eleicaoUseCase.votacaoPorRegioesEUfsEMesorregioes(apiUtil.formataPipeString(regioes),
                    apiUtil.formataPipeString(ufs), apiUtil.formataPipeString(mesorregioes));
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaVotacao);
    }

    @ApiOperation(value = "Retorna a votação por regiões, ufs, mesorregiões e microrregiões")
    @RequestMapping("${app.api-url-eleicao}/{regioes}/{ufs}/{mesorregioes}/{microrregioes}")
    public ResponseEntity<List<VotacaoRegiao>> getVotacaoPorRegioesEUfsEMesorregioesEMicrorregioes(
                                                                        @PathVariable("regioes") String regioes,
                                                                        @PathVariable("ufs") String ufs,
                                                                        @PathVariable("mesorregioes") String mesorregioes,
                                                                        @PathVariable("microrregioes") String microrregioes) {
        List<VotacaoRegiao> listaVotacao = new ArrayList<>();
        try {
            listaVotacao = this.eleicaoUseCase.votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(apiUtil.formataPipeString(regioes),
                    apiUtil.formataPipeString(ufs), apiUtil.formataPipeString(mesorregioes), apiUtil.formataPipeString(microrregioes));
        } catch (Exception e) {
            log.error(mensagem, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaVotacao);
    }
}
