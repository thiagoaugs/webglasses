/**  
 * jass-ejb - UsuarioSistemaDAOJpa.java
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

import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.Grupo_;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo_;
import br.com.flexsolutions.security.pojo.UsuarioSistema_;
import br.com.flexsolutions.security.pojo.Usuario_;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioSistemaGrupoDAOJpa extends
		GenericDAOJpa<UsuarioSistemaGrupo, Serializable> implements
		UsuarioSistemaGrupoDAO {

	public UsuarioSistemaGrupoDAOJpa() {
		super();
	}

	public UsuarioSistemaGrupoDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#retornarCount(java
	 * .lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public Long retornarCount(Integer idSistema, Integer idGrupo,
			String pesqUsuario) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<UsuarioSistemaGrupo> root = cq.from(UsuarioSistemaGrupo.class);

		Join<UsuarioSistemaGrupo, UsuarioSistema> joinUsuarioSistema = root
				.join(UsuarioSistemaGrupo_.usgUsuarioSistema, JoinType.INNER);

		Join<UsuarioSistema, Usuario> joinUsuarioSistemaUsuario = joinUsuarioSistema
				.join(UsuarioSistema_.usiUsuario, JoinType.INNER);

		Join<UsuarioSistema, Sistema> joinUsuarioSistemaSistema = joinUsuarioSistema
				.join(UsuarioSistema_.usiSistema, JoinType.INNER);

		Join<UsuarioSistemaGrupo, Grupo> joinGrupo = root.join(
				UsuarioSistemaGrupo_.usgGrupo, JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// RETORNO (Fields)
		cq.select(cb.count(root.get(UsuarioSistemaGrupo_.usgId)));

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinUsuarioSistemaSistema.get(Sistema_.sisId), idSistema)));
		}

		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		predicates.add(cb.or(cb.like(
				cb.lower(joinUsuarioSistemaUsuario.get(Usuario_.usuNome)), "%"
						+ pesqUsuario.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#filtrarPag(java.lang
	 * .Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public List<UsuarioSistemaGrupo> filtrarPag(Integer idSistema,
			Integer idGrupo, String pesqUsuario, Integer firstResult,
			Integer maxResults) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistemaGrupo> cq = cb
				.createQuery(UsuarioSistemaGrupo.class);

		Root<UsuarioSistemaGrupo> root = cq.from(UsuarioSistemaGrupo.class);

		Join<UsuarioSistemaGrupo, UsuarioSistema> joinUsuarioSistema = root
				.join(UsuarioSistemaGrupo_.usgUsuarioSistema, JoinType.INNER);

		Join<UsuarioSistema, Usuario> joinUsuarioSistemaUsuario = joinUsuarioSistema
				.join(UsuarioSistema_.usiUsuario, JoinType.INNER);

		Join<UsuarioSistema, Sistema> joinUsuarioSistemaSistema = joinUsuarioSistema
				.join(UsuarioSistema_.usiSistema, JoinType.INNER);

		Join<UsuarioSistemaGrupo, Grupo> joinGrupo = root.join(
				UsuarioSistemaGrupo_.usgGrupo, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistemaGrupo_.usgId));

		selections.add(joinGrupo.get(Grupo_.gpoId));
		selections.add(joinGrupo.get(Grupo_.gpoNome));

		selections.add(joinUsuarioSistema.get(UsuarioSistema_.usiId));

		selections.add(joinUsuarioSistemaSistema.get(Sistema_.sisId));
		selections.add(joinUsuarioSistemaSistema.get(Sistema_.sisNome));
		selections.add(joinUsuarioSistemaUsuario.get(Usuario_.usuId));
		selections.add(joinUsuarioSistemaUsuario.get(Usuario_.usuNome));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinUsuarioSistemaSistema.get(Sistema_.sisId), idSistema)));
		}

		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		predicates.add(cb.or(cb.like(
				cb.lower(joinUsuarioSistemaUsuario.get(Usuario_.usuNome)), "%"
						+ pesqUsuario.toLowerCase() + "%")));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(UsuarioSistemaGrupo_.usgId)));

		TypedQuery<UsuarioSistemaGrupo> tq = entityManager.createQuery(cq);

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
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#buscarPor(java.lang
	 * .Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public UsuarioSistemaGrupo buscarPor(Integer idSistema, Integer idGrupo,
			Integer idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#listarPorIdSistema
	 * (java.lang.Integer)
	 */
	@Override
	public List<UsuarioSistemaGrupo> listarPorIdSistemaGrupo(Integer idSistema,
			Integer idGrupo) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistemaGrupo> cq = cb
				.createQuery(UsuarioSistemaGrupo.class);

		Root<UsuarioSistemaGrupo> root = cq.from(UsuarioSistemaGrupo.class);

		Join<UsuarioSistemaGrupo, UsuarioSistema> joinUsuarioSistema = root
				.join(UsuarioSistemaGrupo_.usgUsuarioSistema, JoinType.INNER);

		Join<UsuarioSistema, Sistema> joinUsuarioSistemaSistema = joinUsuarioSistema
				.join(UsuarioSistema_.usiSistema);

		Join<UsuarioSistema, Usuario> joinUsuarioSistemaUsuario = joinUsuarioSistema
				.join(UsuarioSistema_.usiUsuario);

		Join<UsuarioSistemaGrupo, Grupo> joinUsuarioSistemaGrupo = root.join(
				UsuarioSistemaGrupo_.usgGrupo, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistemaGrupo_.usgId));
		selections.add(joinUsuarioSistemaUsuario.get(Usuario_.usuId));
		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.equal(
					joinUsuarioSistemaSistema.get(Sistema_.sisId), idSistema));
		}

		if (idGrupo != null && idGrupo != 0) {
			predicates.add(cb.equal(joinUsuarioSistemaGrupo.get(Grupo_.gpoId),
					idGrupo));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(UsuarioSistemaGrupo_.usgId)));

		TypedQuery<UsuarioSistemaGrupo> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#listarPorSistemaUsuario
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<UsuarioSistemaGrupo> listarPorSistemaUsuario(String sistema,
			String usuario) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistemaGrupo> cq = cb
				.createQuery(UsuarioSistemaGrupo.class);

		Root<UsuarioSistemaGrupo> root = cq.from(UsuarioSistemaGrupo.class);

		Join<UsuarioSistemaGrupo, UsuarioSistema> joinUsuarioSistema = root
				.join(UsuarioSistemaGrupo_.usgUsuarioSistema, JoinType.INNER);

		Join<UsuarioSistema, Sistema> joinUsuarioSistemaSistema = joinUsuarioSistema
				.join(UsuarioSistema_.usiSistema);

		Join<UsuarioSistema, Usuario> joinUsuarioSistemaUsuario = joinUsuarioSistema
				.join(UsuarioSistema_.usiUsuario);

		// RETORNO (Fields)
//		List<Selection<?>> selections = new ArrayList<Selection<?>>();
//		selections.add(root.get(UsuarioSistemaGrupo_.usgId));
//		selections.add(joinUsuarioSistemaGrupo.get(Grupo_.gpoId));
//		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(
				joinUsuarioSistemaSistema.get(Sistema_.sisNome), sistema));

		predicates.add(cb.equal(
				joinUsuarioSistemaUsuario.get(Usuario_.usuLogin), usuario));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<UsuarioSistemaGrupo> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO#
	 * listarUsgPorUsuarioSistema(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<UsuarioSistemaGrupo> listarUsgPorUsuarioSistema(
			Integer idUsuarioSistema) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistemaGrupo> cq = cb
				.createQuery(UsuarioSistemaGrupo.class);

		Root<UsuarioSistemaGrupo> root = cq.from(UsuarioSistemaGrupo.class);

		Join<UsuarioSistemaGrupo, UsuarioSistema> joinUsuarioSistema = root
				.join(UsuarioSistemaGrupo_.usgUsuarioSistema, JoinType.INNER);

		// RETORNO (Fields)
		// List<Selection<?>> selections = new ArrayList<Selection<?>>();
		// selections.add(root.get(UsuarioSistemaGrupo_.usgGrupo));
		// cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.equal(joinUsuarioSistema.get(UsuarioSistema_.usiId),
				idUsuarioSistema));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<UsuarioSistemaGrupo> tq = entityManager.createQuery(cq);
		return tq.getResultList();

	}

}
