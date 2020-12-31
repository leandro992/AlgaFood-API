package com.exercicio.algaworks.algafoodapi.infrastructure.repository;

import com.exercicio.algaworks.algafoodapi.domain.model.Restaurante;
import com.exercicio.algaworks.algafoodapi.repository.RestauranteRepository;
import com.exercicio.algaworks.algafoodapi.repository.RestauranteRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.List;

import static com.exercicio.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpec.comFreteGratis;
import static com.exercicio.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpec.comNomeSemelhande;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){

        var jpql = "from Restaurante where nome like :nome " +
                "and taxaFrete between :taxaInicial and :taxaFinal";

        StringUtils.hasLength(nome);
        return entityManager.createQuery(jpql,Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaFreteInicial)
                .setParameter("taxaFinal",  taxaFreteFinal)
                .getResultList();
    }

    public List<Restaurante> findCriteria(){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        criteria.from(Restaurante.class);

        TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
        return query.getResultList();

    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhande(nome)));
    }


}
