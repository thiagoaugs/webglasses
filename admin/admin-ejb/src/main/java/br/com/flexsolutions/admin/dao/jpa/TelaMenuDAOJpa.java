/**  
 * jass-ejb - TelaMenuDAOJpa.java
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

import br.com.flexsolutions.admin.dao.TelaMenuDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;
import br.com.flexsolutions.security.pojo.TelaMenu;
import br.com.flexsolutions.security.pojo.TelaMenu_;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuDAOJpa extends GenericDAOJpa<TelaMenu, Serializable>
		implements TelaMenuDAO {

	public TelaMenuDAOJpa() {
		super();
	}

	public TelaMenuDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.dao.TelaMenuDAO#retornarCount(java.lang
	 * .String)
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

		Root<TelaMenu> root = cq.from(TelaMenu.class);
		Join<TelaMenu, Sistema> joinSistema = root.join(TelaMenu_.tmeSistema,
				JoinType.INNER);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(TelaMenu_.tmeId)));

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(TelaMenu_.tmeLegenda)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null  && idSistema != 0) {
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
	 * @see
	 * br.com.flexsolutions.jass.dao.TelaMenuDAO#listarPag(java.lang.String
	 * , java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<TelaMenu> filtrarPag(String textoPesquisa, Integer idSistema,
			Integer firstResult, Integer maxResults) {

		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenu> cq = cb.createQuery(TelaMenu.class);

		// FROM e JOIN's
		Root<TelaMenu> root = cq.from(TelaMenu.class);
		Join<TelaMenu, Sistema> joinSistema = root.join(TelaMenu_.tmeSistema,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(TelaMenu_.tmeLegenda)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinSistema.get(Sistema_.sisId), idSistema)));

		}
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(TelaMenu_.tmeId)));

		// Execucao SELECT
		TypedQuery<TelaMenu> tq = entityManager.createQuery(cq);

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
	 * @see br.com.flexsolutions.admin.dao.TelaMenuDAO#buscarPor(java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public TelaMenu buscarPor(String acao, Integer sisID) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenu> cq = cb.createQuery(TelaMenu.class);

		// FROM e JOIN's
		Root<TelaMenu> root = cq.from(TelaMenu.class);
		Join<TelaMenu, Sistema> joinSistema = root.join(TelaMenu_.tmeSistema);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(joinSistema.get(Sistema_.sisId), sisID));

		predicates.add(cb.equal(cb.lower(root.get(TelaMenu_.tmeAcao)),
				acao.toLowerCase()));

		// WHERE
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<TelaMenu> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.TelaMenuDAO#listar()
	 */
	@Override
	public List<TelaMenu> listar() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenu> cq = cb.createQuery(TelaMenu.class);

		// FROM e JOIN's
		Root<TelaMenu> root = cq.from(TelaMenu.class);

		cq.orderBy(cb.asc(root.get(TelaMenu_.tmeId)));

		// Execucao SELECT
		TypedQuery<TelaMenu> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.TelaMenuDAO#listarPorIdSistema(java.lang
	 * .Integer)
	 */
	@Override
	public List<TelaMenu> listarPorIdSistema(List<Integer> listaId, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenu> cq = cb.createQuery(TelaMenu.class);

		// FROM e JOIN's
		Root<TelaMenu> root = cq.from(TelaMenu.class);
		Join<TelaMenu, Sistema> joinSistema = root.join(TelaMenu_.tmeSistema);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(TelaMenu_.tmeId));
		selections.add(root.get(TelaMenu_.tmeLegenda));
		cq.multiselect(selections);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		//idssitema 
		
		if(idSistema != null && idSistema != 0){
			predicates.add(cb.equal(joinSistema.get(Sistema_.sisId), idSistema));
		}

		if(listaId != null){
			predicates.add((cb.and (cb.not(root.get(TelaMenu_.tmeId).in(listaId)))));
		}
		
		
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(TelaMenu_.tmeId)));
		// Execucao SELECT
		TypedQuery<TelaMenu> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

}
