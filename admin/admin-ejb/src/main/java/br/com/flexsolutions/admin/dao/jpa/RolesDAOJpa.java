/**  
 * admin-ejb - RolesDAOJpa.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.dao.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import br.com.flexsolutions.admin.dao.RolesDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Roles;
import br.com.flexsolutions.security.pojo.Roles_;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;

/**
 * @author Thiago Augusto
 *
 */
public class RolesDAOJpa extends GenericDAOJpa<Roles, Serializable> implements
		RolesDAO {

	public RolesDAOJpa() {
		super();
	}

	public RolesDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.RolesDAO#retornarCount(java.lang.String)
	 */
	@Override
	public Long retornarCount(String textoPesquisa, Integer idSistema) {
		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Roles> root = cq.from(Roles.class);
		Join<Roles, Sistema> joinSistema = root.join(Roles_.rolSistema,
				JoinType.INNER);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(Roles_.rolId)));

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(Roles_.rolDescricao)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null) {
			predicates.add(cb.and(cb.equal(
					joinSistema.get(Sistema_.sisId), idSistema)));

		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.RolesDAO#filtrarPag(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Roles> filtrarPag(String textoPesquisa, Integer idSistema,
			Integer firstResult, Integer maxResults) {

		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);

		// FROM e JOIN's
		Root<Roles> root = cq.from(Roles.class);
		Join<Roles, Sistema> joinSistema = root.join(Roles_.rolSistema,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(Roles_.rolDescricao)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null) {
			predicates.add(cb.and(cb.equal(
					joinSistema.get(Sistema_.sisId), idSistema)));

		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(Roles_.rolId)));

		// Execucao SELECT
		TypedQuery<Roles> tq = entityManager.createQuery(cq);

		if (maxResults != null && maxResults.intValue() > 0) {
			tq.setMaxResults(maxResults);

			if (firstResult != null && firstResult.intValue() > 0) {
				tq.setFirstResult(firstResult);
			}
		}

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.RolesDAO#buscarPor(java.lang.String)
	 */
	@Override
	public Roles buscarPor(String nome, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);

		// FROM e JOIN's
		Root<Roles> root = cq.from(Roles.class);
		Join<Roles, Sistema> joinSistema = root.join(Roles_.rolSistema);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		// nome da regra / id sistema
		predicates.add(cb.and(cb.equal(root.get(Roles_.rolNome), nome)));
		predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
				idSistema)));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Roles> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.RolesDAO#listar()
	 */
	@Override
	public List<Roles> listarPorIdSistema(List<Integer> listaId, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Roles> cq = cb.createQuery(Roles.class);

		// FROM e JOIN's
		Root<Roles> root = cq.from(Roles.class);
		Join<Roles, Sistema> joinSistema = root.join(Roles_.rolSistema);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(Roles_.rolId));
		selections.add(root.get(Roles_.rolNome));
		cq.multiselect(selections);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		// id sistema
		if (idSistema != null && idSistema != 0){
			predicates.add(cb.equal(joinSistema.get(Sistema_.sisId), idSistema));
		}
		if(listaId != null){
			predicates.add((cb.and (cb.not(root.get(Roles_.rolId).in(listaId)))));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(Roles_.rolId)));

		// Execucao SELECT
		TypedQuery<Roles> tq = entityManager.createQuery(cq);

		return tq.getResultList();

	}

}
