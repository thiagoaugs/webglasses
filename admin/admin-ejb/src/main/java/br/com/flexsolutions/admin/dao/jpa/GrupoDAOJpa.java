/**  
 * jass-ejb - GrupoDAOJpa.java
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

import br.com.flexsolutions.admin.dao.GrupoDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.Grupo_;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoDAOJpa extends GenericDAOJpa<Grupo, Serializable> implements
		GrupoDAO {

	public GrupoDAOJpa() {
		super();
	}

	public GrupoDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.webglass.dao.GrupoDAO#retornarCount(java.lang.String )
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

		// FROM e JOIN's
		Root<Grupo> root = cq.from(Grupo.class);
		Join<Grupo, Sistema> joinSistema = root.join(Grupo_.gpoSistema,
				JoinType.INNER);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(Grupo_.gpoId)));

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(Grupo_.gpoDescricao)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
					idSistema)));

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
	 * br.com.flexsolutions.jass.dao.GrupoDAO#listarPag(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Grupo> filtrarPag(String textoPesquisa, Integer idSistema,
			Integer firstResult, Integer maxResults) {

		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Grupo> cq = cb.createQuery(Grupo.class);

		// FROM e JOIN's
		Root<Grupo> root = cq.from(Grupo.class);
		Join<Grupo, Sistema> joinSistema = root.join(Grupo_.gpoSistema,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(cb.lower(root.get(Grupo_.gpoDescricao)),
				"%" + textoPesquisa.toLowerCase() + "%")));

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
					idSistema)));

		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(Grupo_.gpoId)));

		// Execucao SELECT
		TypedQuery<Grupo> tq = entityManager.createQuery(cq);

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
	 * @see
	 * br.com.sanepar.atlas.dao.BaseDigitalDao#retornarBaseDigital(java.lang
	 * .String, java.lang.Integer)
	 */
	@Override
	public Grupo buscarPor(String nome, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Grupo> cq = cb.createQuery(Grupo.class);

		// FROM e JOIN's
		Root<Grupo> root = cq.from(Grupo.class);
		Join<Grupo, Sistema> joinSistema = root.join(Grupo_.gpoSistema);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		// nome do grupo / id sistema
		predicates.add(cb.and(cb.equal(root.get(Grupo_.gpoNome), nome)));
		predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
				idSistema)));
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Grupo> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.GrupoDAO#listar()
	 */
	@Override
	public List<Grupo> listarPorIdSitema(Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Grupo> cq = cb.createQuery(Grupo.class);

		// FROM e JOIN's
		Root<Grupo> root = cq.from(Grupo.class);
		Join<Grupo, Sistema> joinSistema = root.join(Grupo_.gpoSistema);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(Grupo_.gpoId));
		selections.add(root.get(Grupo_.gpoNome));
		cq.multiselect(selections);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		// id do sistema

		if (idSistema != null && idSistema != 0) {
			predicates
					.add(cb.equal(joinSistema.get(Sistema_.sisId), idSistema));

		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(Grupo_.gpoId)));
		// Execucao SELECT
		TypedQuery<Grupo> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.GrupoDAO#retornarGrupos(java.util.List)
	 */
	@Override
	public List<Grupo> retornarGrupos(List<Integer> listaId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Grupo> cq = cb.createQuery(Grupo.class);

		// FROM e JOIN's
		Root<Grupo> root = cq.from(Grupo.class);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(Grupo_.gpoId));
		selections.add(root.get(Grupo_.gpoNome));
		cq.multiselect(selections);

		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		// lista id do grupo
		predicates.add(cb.and(root.get(Grupo_.gpoId).in(listaId)));

		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(Grupo_.gpoId)));
		// Execucao SELECT
		TypedQuery<Grupo> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

}
