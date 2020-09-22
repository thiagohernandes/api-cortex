package com.cortex.dataprovider.repository;

import com.cortex.dataprovider.entity.Votacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EleicaoRepository extends CrudRepository<Votacao, Long> {

    @Query(value = "SELECT v.regiaoNome, " +
            "v.candidato, " +
            "v.partido, " +
            "sum(v.votos) as total_votos " +
            "FROM Votacao v " +
            "GROUP BY v.regiaoNome, v.candidato, v.partido " +
            "ORDER BY 1,4 desc ")
    List<Object> votacaoPorRegioes();

    @Query(value = "SELECT v.regiaoNome, " +
            "v.candidato, " +
            "v.partido, " +
            "sum(v.votos) as total_votos " +
            "FROM Votacao v " +
            "WHERE v.regiaoSigla IN (:siglas) " +
            "GROUP BY v.regiaoNome, v.candidato, v.partido " +
            "ORDER BY 1,4 desc ")
    List<Object> votacaoPorRegioes(@Param("siglas") List<String> siglas);

    @Query(value = "SELECT v.regiaoNome, " +
            "v.candidato, " +
            "v.partido, " +
            "sum(v.votos) as total_votos " +
            "FROM Votacao v " +
            "WHERE v.regiaoSigla IN (:siglas) " +
            "AND v.siglaUf IN (:ufs) " +
            "GROUP BY v.regiaoNome, v.candidato, v.partido " +
            "ORDER BY 1,4 desc ")
    List<Object> votacaoPorRegioesEUfs(@Param("siglas") List<String> siglas,
                                       @Param("ufs") List<String> ufs);

    @Query(value = "SELECT v.regiaoNome, " +
            "v.candidato, " +
            "v.partido, " +
            "sum(v.votos) as total_votos " +
            "FROM Votacao v " +
            "WHERE v.regiaoSigla IN (:siglas) " +
            "AND v.siglaUf IN (:ufs) " +
            "AND v.mesorregiaoId IN (:mesorregioes) " +
            "GROUP BY v.regiaoNome, v.candidato, v.partido " +
            "ORDER BY 1,4 desc ")
    List<Object> votacaoPorRegioesEUfsEMesorregioes(@Param("siglas") List<String> siglas,
                                                    @Param("ufs") List<String> ufs,
                                                    @Param("mesorregioes") List<Integer> mesorregioes);

    @Query(value = "SELECT v.regiaoNome, " +
            "v.candidato, " +
            "v.partido, " +
            "sum(v.votos) as total_votos " +
            "FROM Votacao v " +
            "WHERE v.regiaoSigla IN (:siglas) " +
            "AND v.siglaUf IN (:ufs) " +
            "AND v.mesorregiaoId IN (:mesorregioes) " +
            "AND v.microrregiaoId IN (:microrregioes)" +
            "GROUP BY v.regiaoNome, v.candidato, v.partido " +
            "ORDER BY 1,4 desc ")
    List<Object> votacaoPorRegioesEUfsEMesorregioesEMicrorregioes(@Param("siglas") List<String> siglas,
                                                    @Param("ufs") List<String> ufs,
                                                    @Param("mesorregioes") List<Integer> mesorregioes,
                                                    @Param("microrregioes") List<Integer> microrregioes);
}
