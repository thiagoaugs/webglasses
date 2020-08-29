/**  
 * jass-ejb - UsuarioDAOJpa.java
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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.flexsolutions.admin.dao.UsuarioDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.Usuario_;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioDAOJpa extends GenericDAOJpa<Usuario, Serializable>
		implements UsuarioDAO {

	public UsuarioDAOJpa() {
		super();
	}

	public UsuarioDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.webglass.dao.UsuarioDAO#retornarCount(java.lang.String
	 * )
	 */
	@Override
	public Long retornarCount(String textoPesquisa) {
		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<Usuario> root = cq.from(Usuario.class);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(Usuario_.usuId)));

		// WHERE
		Predicate predicate = cb.disjunction();

		// login do usuario
		predicate = cb.or(
				predicate,
				cb.like(cb.lower(root.get(Usuario_.usuLogin)), "%"
						+ textoPesquisa.toLowerCase() + "%"));

		cq.where(predicate);

		// Execucao SELECT
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.dao.UsuarioDAO#listarPag(java.lang.String
	 * , java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Usuario> filtrarPag(String textoPesquisa, Integer firstResult,
			Integer maxResults) {

		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		// FROM e JOIN's
		Root<Usuario> root = cq.from(Usuario.class);

		// WHERE
		Predicate predicate = cb.disjunction();

		// login do usuario
		predicate = cb.or(cb.like(cb.lower(root.get(Usuario_.usuLogin)), "%"
				+ textoPesquisa.toLowerCase() + "%"));

		cq.where(predicate);
		cq.orderBy(cb.asc(root.get(Usuario_.usuId)));

		// Execucao SELECT
		TypedQuery<Usuario> tq = entityManager.createQuery(cq);

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
	 * br.com.flexsolutions.jass.dao.UsuarioDAO#buscarPor(java.lang.String
	 * )
	 */
	@Override
	public Usuario buscarPor(String cpf) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		// FROM e JOIN's
		Root<Usuario> root = cq.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// if (!StringUtils.isBlank(login)) {
		// predicates.add(cb.equal(cb.lower(root.get(Usuario_.usuLogin)), "%" +
		// login.toLowerCase() + "%"));
		// }
		if (!StringUtils.isBlank(cpf)) {
			predicates.add(cb.equal(root.get(Usuario_.usuCpf), cpf));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Usuario> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioDAO#listarPorSistema(java.lang.Integer
	 * )
	 */
	@Override
	public List<Usuario> listarUsuariosNaoVinculadosAoSistema(
			List<Integer> listaId) {
		List<Integer> lista = null;

		if (listaId != null && listaId.size() != 0) {
			lista = new ArrayList<Integer>();
			lista = listaId;
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		// FROM e JOIN's
		Root<Usuario> root = cq.from(Usuario.class);

		// WHERE
		Predicate predicate = cb.disjunction();

		if (lista != null) {
			predicate = cb.not(root.get(Usuario_.usuId).in(lista));
			cq.where(predicate);
		}

		cq.orderBy(cb.asc(root.get(Usuario_.usuNome)));

		// Execucao SELECT
		TypedQuery<Usuario> tq = entityManager.createQuery(cq);

		return tq.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioDAO#validarLoginUsuario(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public Usuario validarLoginUsuario(String usuario, String senhaMD5) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		// FROM e JOIN's
		Root<Usuario> root = cq.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isBlank(usuario)) {
			predicates.add(cb.equal(root.get(Usuario_.usuLogin), usuario));
		}
		if (!StringUtils.isBlank(senhaMD5)) {
			predicates.add(cb.equal(root.get(Usuario_.usuSenha), senhaMD5));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Usuario> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.UsuarioDAO#buscarPorNome(java.lang.String)
	 */
	@Override
	public Usuario buscarPorLogin(String login) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		// FROM e JOIN's
		Root<Usuario> root = cq.from(Usuario.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isBlank(login)) {
			predicates.add(cb.equal(root.get(Usuario_.usuLogin), login));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<Usuario> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

}
