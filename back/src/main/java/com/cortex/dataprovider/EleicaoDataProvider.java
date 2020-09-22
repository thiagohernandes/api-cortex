package com.cortex.dataprovider;

import com.cortex.core.usecase.http.CidadeCandidatoHttp;
import com.cortex.core.usecase.http.VotacaoRegiao;
import com.cortex.dataprovider.entity.Votacao;
import com.cortex.dataprovider.feign.EleicaoFeign;
import com.cortex.dataprovider.gateway.EleicaoGateway;
import com.cortex.dataprovider.repository.EleicaoRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EleicaoDataProvider implements EleicaoGateway {

    private final EleicaoFeign eleicaoFeign;
    private final EleicaoRepository eleicaoRepository;

    @Autowired
    public EleicaoDataProvider(final EleicaoFeign eleicaoFeign, final EleicaoRepository eleicaoRepository) {
        this.eleicaoFeign = eleicaoFeign;
        this.eleicaoRepository = eleicaoRepository;
    }

    public List<Object> listaRegioes() {
        return eleicaoFeign.listaRegioes();
    }

    public List<Object> listaEstadosPorRegiao(Integer regiaoId) {
        return eleicaoFeign.listaEstadosPorRegiao(regiaoId);
    }

    public List<Object> listaMesorregioesPorEstado(String estadoSigla) {
        return eleicaoFeign.listaMesorregioesPorEstado(estadoSigla);
    }

    public List<Object> listaMicrorregioesPorMesorregiao(String mesorregiaoId) {
        return eleicaoFeign.listaMicrorregioesPorMesorregiao(mesorregiaoId);
    }

    public List<Object> listaMunicipiosPorMicrorregiao(String microrregiaoId) {
        return eleicaoFeign.listaMunicipiosPorMicrorregiao(microrregiaoId);
    }

    public void importarCandidatos() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<CidadeCandidatoHttp>> typeReference = new TypeReference<>() {};
        final InputStream inputStream = TypeReference.class.getResourceAsStream("/votacao-final.json");

        final List<CidadeCandidatoHttp> cidadeCandidatoHttpList = mapper.readValue(inputStream, typeReference);
        cidadeCandidatoHttpList.stream().forEach(votacao -> {
            final Votacao votacaoInserir = Votacao.builder()
                    .candidato(votacao.getCandidato())
                    .mesorregiaoId(votacao.getMesorregiaoId())
                    .microrregiaoId(votacao.getMicrorregiaoId())
                    .nome(votacao.getNome())
                    .partido(votacao.getPartido())
                    .regiaoId(votacao.getRegiao().getId())
                    .regiaoNome(votacao.getRegiao().getNome())
                    .regiaoSigla(votacao.getRegiao().getSigla())
                    .siglaUf(votacao.getSiglaUf())
                    .votos(votacao.getVotos())
                    .build();
            eleicaoRepository.save(votacaoInserir);
        });
    }

    public List<VotacaoRegiao> votacaoPorRegioes() {
        List<Object> listaVotacaoPorRegiao = eleicaoRepository.votacaoPorRegioes();
        return transformaVotacaoRegiao(listaVotacaoPorRegiao);
    }

    public List<VotacaoRegiao> votacaoPorRegioes(List<String> regioesSiglas) {
        List<Object> listaVotacaoPorRegiao = eleicaoRepository.votacaoPorRegioes(regioesSiglas);
        return transformaVotacaoRegiao(listaVotacaoPorRegiao);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfs(List<String> regioesSiglas, List<String> ufs) {
        List<Object> listaVotacaoPorRegiao = eleicaoRepository.votacaoPorRegioesEUfs(regioesSiglas, ufs);
        return transformaVotacaoRegiao(listaVotacaoPorRegiao);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfsEMesorregioes(List<String> regioesSiglas,
                                                                  List<String> ufs,
                                                                  List<String> mesorregioes) {
        List<Object> listaVotacaoPorRegiao = eleicaoRepository
                .votacaoPorRegioesEUfsEMesorregioes(regioesSiglas, ufs,
                        mesorregioes.stream().map(Integer::valueOf).collect(Collectors.toList()));
        return transformaVotacaoRegiao(listaVotacaoPorRegiao);
    }

    public List<VotacaoRegiao> votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(List<String> regioesSiglas,
                                                                                List<String> ufs,
                                                                                List<String> mesorregioes,
                                                                                List<String> microrregioe) {
        List<Object> listaVotacaoPorRegiao = eleicaoRepository
                .votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(regioesSiglas, ufs,
                        mesorregioes.stream().map(Integer::valueOf).collect(Collectors.toList()),
                        microrregioe.stream().map(Integer::valueOf).collect(Collectors.toList()));
        return transformaVotacaoRegiao(listaVotacaoPorRegiao);
    }

    private List<VotacaoRegiao> transformaVotacaoRegiao(List<Object> listaVotacaoPorRegiao ) {
        List<VotacaoRegiao> listaVotacaoPorRegiaoTransformada = new ArrayList<>();
        for(Object votacao : listaVotacaoPorRegiao) {
            listaVotacaoPorRegiaoTransformada.add(VotacaoRegiao.builder()
                    .regiaoNome(((Object[])votacao)[0].toString())
                    .candidato(((Object[])votacao)[1].toString())
                    .partido(((Object[])votacao)[2].toString())
                    .totalVotos(Integer.parseInt(((Object[])votacao)[3].toString()))
                    .build());
        }
        return listaVotacaoPorRegiaoTransformada;
    }




}
