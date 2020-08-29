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

import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistema_;
import br.com.flexsolutions.security.pojo.Usuario_;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioSistemaDAOJpa extends
		GenericDAOJpa<UsuarioSistema, Serializable> implements
		UsuarioSistemaDAO {

	public UsuarioSistemaDAOJpa() {
		super();
	}

	public UsuarioSistemaDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaDAO#retornarCount(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public Long retornarCount(String nomeUsuario, Integer idSistema) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Sistema> joinSistema = root.join(
				UsuarioSistema_.usiSistema, JoinType.INNER);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// RETORNO (Fields)
		cq.select(cb.count(root.get(UsuarioSistema_.usiId)));

		predicates.add(cb.or(cb.like(
				cb.lower(joinUsuario.get(Usuario_.usuNome)),
				"%" + nomeUsuario.toLowerCase() + "%")));

		if (idSistema != null) {
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
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaDAO#filtrarPag(java.lang.Integer
	 * , java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<UsuarioSistema> filtrarPag(String nomeUsuario,
			Integer idSistema, Integer firstResult, Integer maxResults) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistema> cq = cb.createQuery(UsuarioSistema.class);

		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Sistema> joinSistema = root.join(
				UsuarioSistema_.usiSistema, JoinType.INNER);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistema_.usiId));
		selections.add(joinSistema.get(Sistema_.sisId));
		selections.add(joinSistema.get(Sistema_.sisNome));
		selections.add(joinUsuario.get(Usuario_.usuId));
		selections.add(joinUsuario.get(Usuario_.usuNome));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();

		predicates.add(cb.or(cb.like(
				cb.lower(joinUsuario.get(Usuario_.usuNome)),
				"%" + nomeUsuario.toLowerCase() + "%")));

		if (idSistema != null) {
			predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
					idSistema)));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(UsuarioSistema_.usiId)));

		TypedQuery<UsuarioSistema> tq = entityManager.createQuery(cq);

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
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaDAO#buscarPor(java.lang.Integer
	 * , java.lang.Integer)
	 */
	@Override
	public UsuarioSistema buscarPor(Integer idUsuario, Integer idSistema) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistema> cq = cb.createQuery(UsuarioSistema.class);

		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Sistema> joinSistema = root.join(
				UsuarioSistema_.usiSistema, JoinType.INNER);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistema_.usiId));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do usuario
		predicates.add(cb.and(cb.equal(joinUsuario.get(Usuario_.usuId),
				idUsuario)));
		// id do sistema
		predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
				idSistema)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(root.get(UsuarioSistema_.usiId)));

		TypedQuery<UsuarioSistema> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.UsuarioDAO#listar()
	 */
	@Override
	public List<UsuarioSistema> listarPorSistema(Integer idSistema) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistema> cq = cb.createQuery(UsuarioSistema.class);

		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Sistema> joinSistema = root.join(
				UsuarioSistema_.usiSistema, JoinType.INNER);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistema_.usiId));
		selections.add(joinUsuario.get(Usuario_.usuId));
		selections.add(joinUsuario.get(Usuario_.usuNome));
		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do grupo
		predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
				idSistema)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<UsuarioSistema> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.UsuarioDAO#listar()
	 */
	@Override
	public List<UsuarioSistema> listarIdUsuariosPorSistema(Integer idSistema) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistema> cq = cb.createQuery(UsuarioSistema.class);

		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Sistema> joinSistema = root.join(
				UsuarioSistema_.usiSistema, JoinType.INNER);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(UsuarioSistema_.usiId));
		selections.add(joinUsuario.get(Usuario_.usuId));
		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do grupo
		predicates.add(cb.and(cb.equal(joinSistema.get(Sistema_.sisId),
				idSistema)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<UsuarioSistema> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioSistemaDAO#listarUsuariosNaoConfigurados
	 * (java.util.List)
	 */
	@Override
	public List<UsuarioSistema> listarUsuariosNaoConfigurados(
			List<Integer> listaUsuariosJaCadastrados, Integer idSistema) {

		List<Integer> lista = null;

		if (listaUsuariosJaCadastrados != null
				&& listaUsuariosJaCadastrados.size() != 0) {
			lista = new ArrayList<Integer>();
			lista = listaUsuariosJaCadastrados;
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioSistema> cq = cb.createQuery(UsuarioSistema.class);

		// FROM e JOIN's
		Root<UsuarioSistema> root = cq.from(UsuarioSistema.class);
		Join<UsuarioSistema, Usuario> joinUsuario = root.join(
				UsuarioSistema_.usiUsuario, JoinType.INNER);
		
		Join<UsuarioSistema, Sistema> joinSistema = root.join(UsuarioSistema_.usiSistema, JoinType.INNER);
	
		// WHERE
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(idSistema != null && idSistema != 0){
			predicates.add((cb.and (cb.equal(joinSistema.get(Sistema_.sisId), idSistema))));
		}
		
		if (lista != null) {
			predicates.add((cb.and (cb.not(joinUsuario.get(Usuario_.usuId).in(lista)))));
			
		}
		cq.where(predicates.toArray(new Predicate[predicates.size()]));
		cq.orderBy(cb.asc(joinUsuario.get(Usuario_.usuNome)));

		// Execucao SELECT
		TypedQuery<UsuarioSistema> tq = entityManager.createQuery(cq);

		return tq.getResultList();

	}
}
