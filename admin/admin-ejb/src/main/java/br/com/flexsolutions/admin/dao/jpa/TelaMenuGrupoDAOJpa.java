/**  
 * admin-ejb - TelaMenuGrupoDAOOJpa.java
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

import br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.Grupo_;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;
import br.com.flexsolutions.security.pojo.TelaMenu;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo_;
import br.com.flexsolutions.security.pojo.TelaMenu_;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuGrupoDAOJpa extends
		GenericDAOJpa<TelaMenuGrupo, Serializable> implements TelaMenuGrupoDAO {

	public TelaMenuGrupoDAOJpa() {
		super();
	}

	public TelaMenuGrupoDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO#retornarCount()
	 */
	@Override
	public Long retornarCount(Integer idSistema, Integer idTelaMenu,
			Integer idGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<TelaMenuGrupo> root = cq.from(TelaMenuGrupo.class);
		Join<TelaMenuGrupo, Grupo> joinGrupo = root.join(
				TelaMenuGrupo_.tmgGrupo, JoinType.INNER);
		Join<TelaMenuGrupo, TelaMenu> joinTelaMenu = root.join(
				TelaMenuGrupo_.tmgTelaMenu, JoinType.INNER);

		Join<Grupo, Sistema> joinGrupoSistema = joinGrupo.join(
				Grupo_.gpoSistema, JoinType.INNER);
		Join<TelaMenu, Sistema> joinTelaMenuSistema = joinTelaMenu.join(
				TelaMenu_.tmeSistema, JoinType.INNER);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(TelaMenuGrupo_.tmgId)));

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinGrupoSistema.get(Sistema_.sisId), idSistema)));
			predicates.add(cb.and(cb.equal(
					joinTelaMenuSistema.get(Sistema_.sisId), idSistema)));
		}

		// id do grupo
		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		// id da role
		if (idTelaMenu != null && idTelaMenu != 0) {
			predicates.add(cb.and(cb.equal(joinTelaMenu.get(TelaMenu_.tmeId),
					idTelaMenu)));
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
	 * br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO#filtrarPag(java.lang.Integer
	 * , java.lang.Integer)
	 */
	@Override
	public List<TelaMenuGrupo> filtrarPag(Integer idSistema,
			Integer idTelaMenu, Integer idGrupo, Integer firstResult,
			Integer maxResults) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenuGrupo> cq = cb.createQuery(TelaMenuGrupo.class);

		// FROM e JOIN's
		Root<TelaMenuGrupo> root = cq.from(TelaMenuGrupo.class);

		Join<TelaMenuGrupo, Grupo> joinGrupo = root.join(
				TelaMenuGrupo_.tmgGrupo, JoinType.INNER);
		Join<TelaMenuGrupo, TelaMenu> joinTelaMenu = root.join(
				TelaMenuGrupo_.tmgTelaMenu, JoinType.INNER);

		Join<Grupo, Sistema> joinGrupoSistema = joinGrupo.join(
				Grupo_.gpoSistema, JoinType.INNER);
		Join<TelaMenu, Sistema> joinTelaMenuSistema = joinTelaMenu.join(
				TelaMenu_.tmeSistema, JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinGrupoSistema.get(Sistema_.sisId), idSistema)));
			predicates.add(cb.and(cb.equal(
					joinTelaMenuSistema.get(Sistema_.sisId), idSistema)));
		}

		// id do grupo
		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		// id da role
		if (idTelaMenu != null && idTelaMenu != 0) {
			predicates.add(cb.and(cb.equal(joinTelaMenu.get(TelaMenu_.tmeId),
					idTelaMenu)));
		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		cq.orderBy(cb.asc(root.get(TelaMenuGrupo_.tmgId)));

		// Execucao SELECT
		TypedQuery<TelaMenuGrupo> tq = entityManager.createQuery(cq);

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
	 * @see br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO#buscarPor()
	 */
	@Override
	public TelaMenuGrupo buscarPor(Integer idGrupo, Integer idTelaMenu) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenuGrupo> cq = cb.createQuery(TelaMenuGrupo.class);

		// FROM e JOIN's
		Root<TelaMenuGrupo> root = cq.from(TelaMenuGrupo.class);
		Join<TelaMenuGrupo, Grupo> joinGrupo = root.join(
				TelaMenuGrupo_.tmgGrupo, JoinType.INNER);
		Join<TelaMenuGrupo, TelaMenu> joinTelaMenu = root.join(
				TelaMenuGrupo_.tmgTelaMenu, JoinType.INNER);

		// Extensao do tipo arquivo digital
		// RETORNO (Fields)

		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(TelaMenuGrupo_.tmgId));
		// selections.add(joinGrupo.get(Grupo_.gpoId));
		// selections.add(joinGrupo.get(Grupo_.gpoDescricao));
		// selections.add(joinRole.get(Roles_.rolId));
		// selections.add(joinRole.get(Roles_.rolDescricao));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do grupo
		predicates.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		// id da role
		predicates.add(cb.and(cb.equal(joinTelaMenu.get(TelaMenu_.tmeId),
				idTelaMenu)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECTsd
		TypedQuery<TelaMenuGrupo> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	@Override
	public List<TelaMenuGrupo> listarTmgPorGrupo(Integer idGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenuGrupo> cq = cb.createQuery(TelaMenuGrupo.class);

		// FROM e JOIN's
		Root<TelaMenuGrupo> root = cq.from(TelaMenuGrupo.class);
		Join<TelaMenuGrupo, Grupo> joinGrupo = root.join(
				TelaMenuGrupo_.tmgGrupo, JoinType.INNER);
		Join<TelaMenuGrupo, TelaMenu> joinTelaMenu = root.join(
				TelaMenuGrupo_.tmgTelaMenu, JoinType.INNER);

		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(TelaMenuGrupo_.tmgId));
		selections.add(joinTelaMenu.get(TelaMenu_.tmeId));
		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// id do grupo
		predicates.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECTsd
		TypedQuery<TelaMenuGrupo> tq = entityManager.createQuery(cq);
		return tq.getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO#listarTmgPorGrupoSistema
	 * (java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<TelaMenuGrupo> listarTmgPorGrupoSistema(
			List<Integer> listaGrupo, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TelaMenuGrupo> cq = cb.createQuery(TelaMenuGrupo.class);

		// FROM e JOIN's
		Root<TelaMenuGrupo> root = cq.from(TelaMenuGrupo.class);
		Join<TelaMenuGrupo, Grupo> joinGrupo = root.join(
				TelaMenuGrupo_.tmgGrupo, JoinType.INNER);
		Join<TelaMenuGrupo, TelaMenu> joinTelaMenu = root.join(
				TelaMenuGrupo_.tmgTelaMenu, JoinType.INNER);
		Join<TelaMenu, Sistema> joinTelaMenuSistema = joinTelaMenu.join(
				TelaMenu_.tmeSistema, JoinType.INNER);
		

//		List<Selection<?>> selections = new ArrayList<Selection<?>>();
//		selections.add(root.get(TelaMenuGrupo_.tmgId));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeId));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeAcao));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeCamada));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeLegenda));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeNivel));
//		selections.add(joinTelaMenu.get(TelaMenu_.tmeOrdem));
//		//selections.add(joinTelaMenu.get(TelaMenu_.sistemaTelaMenuGrupo));
//		selections.add(joinGrupo.get(Grupo_.gpoId));
//		selections.add(joinGrupo.get(Grupo_.gpoNome));
//		
//		
//		
//		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// lista id do grupo
		predicates.add(cb.and(joinGrupo.get(Grupo_.gpoId).in(listaGrupo)));

		predicates.add(cb.and(cb.equal(joinTelaMenuSistema.get(Sistema_.sisId),
				idSistema)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECTsd
		TypedQuery<TelaMenuGrupo> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

}
