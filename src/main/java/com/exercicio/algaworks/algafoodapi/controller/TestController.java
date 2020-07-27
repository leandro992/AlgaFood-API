package com.exercicio.algaworks.algafoodapi.controller;

import com.exercicio.algaworks.algafoodapi.domain.model.Cozinha;
import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import com.exercicio.algaworks.algafoodapi.repository.CozinhaRepository;
import com.exercicio.algaworks.algafoodapi.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    /**
     *
     * @param nome
     * @return Lista Cozinha metodo fazendo Like query Methods
     */
   @GetMapping("/cozinhas/por-nome")
    public List<Cozinha> cozinhaPorNome(String nome){
        return cozinhaRepository.findByNomeContaining(nome);
    }

    /**
     *
     * @param nome
     * @param id
     * @return Usando a queries JPQLs com o @Query
     */
    @GetMapping("/restaurantes/por-nome-query")
    public List<Restaurante> consultarPorNome(String nome, Long id){
        return restauranteRepository.consultarPorNome(nome, id );
    }

    /**
     *
     * @param nome
     * @param id
     * @return Usando a queries JPQLs no arquivo externo na pasta META-INF, orm.xml
     *  para querys muito grandes
     */
    @GetMapping("/restaurantes/por-nome-arquivo-externo")
    public List<Restaurante> consultarPorNomeJPQLArquivoExterno(String nome, Long id){
        return restauranteRepository.consultarPorNomeArquivoExterno(nome, id );
    }

    /**
     *
     * @param nome
     * @param taxaFreteInicial
     * @param taxaFreteFinal
     * @return Query dinamica com JPQL com o repositório SDJ customizado
     */
    @GetMapping("/restaurantes/por-taxa-frete-query-customizado")
    public List<Restaurante> repositoryCustomizadoSDJ(String nome, BigDecimal taxaFreteInicial,
                                                      BigDecimal taxaFreteFinal){
        return restauranteRepository.find(nome, taxaFreteInicial,taxaFreteFinal);
    }

    /**
     *
     * @return Lista de restaurante feito com CriteriaQuery
     * RestauranteRepositoryImpl
     */
    @GetMapping("/restaurantes/por-taxa-frete-query-queryCriteria")
    public List<Restaurante> repositoryCustomizadoComQueryCriteria(){
        return restauranteRepository.findCriteria();
    }

    /**
     *
     * @return Trabalhando com Specification do Spring com SDJ
     */
    @GetMapping("/restaurantes/com-frete-gratis")
    public List<Restaurante> restaurantesComFreteGratis(String nome){

        return restauranteRepository.findComFreteGratis(nome);
    }

    /**
     *
     * @param taxaInicial
     * @param taxaFinal
     * @return Lista de taxaFrete usando o Between
     */
    @GetMapping("/restaurantes/por-taxa-frete")
    public List<Restaurante> cozinhaPorNome(BigDecimal taxaInicial, BigDecimal taxaFinal){
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }


    /**
     *
     * @param nome
     * @param cozinhaId
     * @return  Lista de restaurantes com Like e And
     */
    @GetMapping("/restaurantes/por-nome")
    public List<Restaurante> restaurantesPorTaxaFrete(
            String nome, Long cozinhaId) {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
    }

    /**
     *
     * @param taxaInicial
     * @param taxaFinal
     * @return Lista de taxaFrete usando o Between e o prefixo query
     */
    @GetMapping("/restaurantes/por-taxa-frete-prefixo-query")
    public List<Restaurante> restaurantesPorTaxaFrete(
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    /**
     *
     * @param nome
     * @return lista trazendo o primeiro nome do resultado
     */
    @GetMapping("/restaurantes/primeiro-por-nome")
    public Optional<Restaurante> restaurantePrimeiroPorNome(String nome){
       return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    /**
     *
     * @param nome
     * @return lista trazendo os 2 primeiros objetos por nome
     */
    @GetMapping("/restaurantes/top2-por-nome")
    public List<Restaurante> restaurantesTop2PorNome(String nome){
        return restauranteRepository.findTop2ByNomeContaining(nome);
    }

    /**
     *
     * @param nome
     * @return verificando se existe cozinha pelo nome
     */
    @GetMapping("/restaurantes/exists")
    public Boolean cozinhaExists(String nome){
        return cozinhaRepository.existsByNome(nome);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/restaurantes/count-por-cozinha")
    public Long cozinhaCount(Long id){
        return restauranteRepository.countByCozinhaId(id);
    }

    /**
     *
     * @return
     *  Criando metodo no repositorio base do Spring, estendendo o JpaRepository
     *  CustomJpaRepository e a implemetação CustomJpaRepositoryImpl
     *
     */
    @GetMapping("restaurantes/primeiro")
    public Optional<Restaurante> restaurantePrimeiro(){
        return restauranteRepository.buscarPrimeiro();
    }


}
