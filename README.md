# API - Cortex - Votações

A API contou com importação de dados de eleições presidenciais. Essas importações foram transformadas via serviços REST, gerando arquivos JSON para cruzamento de dados por meio da ferramenta Apache Drill.

Logo após gerar os arquivos JSON para cruzamento de dados, o sistema importa as informações em modo de execução, salvando no H2.

```bash
script utilizado no Apache Drill:

alter session set `store.format`='json';
create table dfs.tmp.json_votacoes as
select municipios.id,
municipios.nome,
municipios.microrregiao.id as microrregiao_id,
municipios.microrregiao.mesorregiao.id as mesorregiao_id,
municipios.microrregiao.mesorregiao.UF.sigla as mesorregao_uf,
municipios.microrregiao.mesorregiao.UF.regiao as regiao_id,
votacao.uf as sigla_uf, 
votacao.candidatos.votos as votos,
votacao.candidatos.partido as partido,
votacao.candidatos.nome as candidato
from cp.`json_files`.`municipios-import.json` as municipios
join cp.`json_files`.`votacao-import.json` as votacao
on municipios.id = votacao.id
order by municipios.nome
```

Após as transformações, foi utilizado o banco de dados em memória (H2) para armazenar as informações para emissão dos relatórios de votação.

## Considerações
- os dados estavam em formato vetorial, e com posicionais complexas para a manipulação de dados;
- as APIs estão protegidas pelo JWT - Webtokens;

## Instruções


```bash
git clone https://github.com/thiagohernandes/api-cortex.git
mvn clean package
mvn spring-boot:run
OBS: antes de realizar a chamada das APIs, deve-se realizar a geração do token (JWT) via: http://localhost:8081/login [POST] com o body:

{
    "username": "admin",
    "password": "123"
}

```

## Endpoints da API RESTful
OBS 1: O pipe para concatenação de parâmetros dever ser enviado como: %7C
OBS 2: Para padronização de envio de parâmetros por endpoint, foram adotados códigos para os parâmetros: /{mesorregioes}/{microrregioes}

```bash
[busca de parâmetros - níveis/subníveis]
http://localhost:8081/api/ibge/regioes
http://localhost:8081/api/ibge/regioes/{regiao-id}/estados
http://localhost:8081/api/ibge/estados/{estado-sigla}/mesorregioes
http://localhost:8081/api/ibge/mesorregioes/{mesorregiao-id}/microrregioes
http://localhost:8081/api/ibge/mesorregioes/{mesorregiao-id}/microrregioes/{microrregiao-id}/municipios

[votações]
[geral]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno

[por regiões]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno/{regioes}

[por regiões e ufs]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno/{regioes}/{ufs}

[por regiões, ufs e mesorregiões]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno/{regioes}/{ufs}/{mesorregioes}

[por regiões, ufs, mesorregiões e microrregiões]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno/{regioes}/{ufs}/{mesorregioes}/{microrregioes}

```

### Exemplo de chamada
```bash
[por regiões, ufs, mesorregiões e microrregiões]
http://localhost:8081/api/eleicao/2014/presidente/primeiro-turno/N/RO/1101/11001%7C11002
```
