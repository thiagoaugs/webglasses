/**  
 * jass-ejb - UsuarioGrupoDAOJpa.java
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

import br.com.flexsolutions.admin.dao.UsuarioGrupoDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.Grupo_;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioGrupo;
import br.com.flexsolutions.security.pojo.UsuarioGrupo_;
import br.com.flexsolutions.security.pojo.Usuario_;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioGrupoDAOJpa extends
		GenericDAOJpa<UsuarioGrupo, Serializable> implements UsuarioGrupoDAO {

	public UsuarioGrupoDAOJpa() {
		super();
	}

	public UsuarioGrupoDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioGrupoDAO#retornarCount(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@Override
	public Long retornarCount(Integer idUsuario, Integer idGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<UsuarioGrupo> root = cq.from(UsuarioGrupo.class);
		Join<UsuarioGrupo, Grupo> joinGrupo = root.join(UsuarioGrupo_.ugpGrupo,
				JoinType.INNER);
		Join<UsuarioGrupo, Usuario> joinUsuario = root.join(
				UsuarioGrupo_.ugpUsuario, JoinType.INNER);


		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idUsuario != null) {
			predicates.add(cb.and(cb.equal(joinUsuario.get(Usuario_.usuId),
					idUsuario)));
		}

		if (idGrupo != null) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
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
	 * br.com.flexsolutions.admin.dao.UsuarioGrupoDAO#filtrarPag(java.lang.Integer
	 * , java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<UsuarioGrupo> filtrarPag(Integer idUsuario, Integer idGrupo,
			Integer firstResult, Integer maxResults) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioGrupo> cq = cb.createQuery(UsuarioGrupo.class);

		Root<UsuarioGrupo> root = cq.from(UsuarioGrupo.class);
		Join<UsuarioGrupo, Grupo> joinGrupo = root.join(UsuarioGrupo_.ugpGrupo,
				JoinType.INNER);
		Join<UsuarioGrupo, Usuario> joinUsuario = root.join(
				UsuarioGrupo_.ugpUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioGrupo_.ugpId));
		selections.add(joinGrupo.get(Grupo_.gpoId));
		selections.add(joinGrupo.get(Grupo_.gpoNome));
		selections.add(joinUsuario.get(Usuario_.usuId));
		selections.add(joinUsuario.get(Usuario_.usuNome));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idUsuario != null) {
			predicates.add(cb.and(cb.equal(joinUsuario.get(Usuario_.usuId),
					idUsuario)));
		}

		if (idGrupo != null) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<UsuarioGrupo> tq = entityManager.createQuery(cq);

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
	 * br.com.flexsolutions.admin.dao.UsuarioGrupoDAO#buscarPor(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public UsuarioGrupo buscarPor(Integer idUsuario, Integer idGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioGrupo> cq = cb.createQuery(UsuarioGrupo.class);

		Root<UsuarioGrupo> root = cq.from(UsuarioGrupo.class);
		Join<UsuarioGrupo, Grupo> joinGrupo = root.join(UsuarioGrupo_.ugpGrupo,
				JoinType.INNER);
		Join<UsuarioGrupo, Usuario> joinUsuario = root.join(
				UsuarioGrupo_.ugpUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioGrupo_.ugpId));
		selections.add(joinGrupo.get(Grupo_.gpoId));
		selections.add(joinGrupo.get(Grupo_.gpoNome));
		selections.add(joinUsuario.get(Usuario_.usuId));
		selections.add(joinUsuario.get(Usuario_.usuNome));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do usuario
		predicates.add(cb.and(cb.equal(joinUsuario.get(Usuario_.usuId),
				idUsuario)));
		// id do grupo
		predicates.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<UsuarioGrupo> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

}
