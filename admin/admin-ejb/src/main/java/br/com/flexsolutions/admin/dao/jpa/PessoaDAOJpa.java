/**  
 * admin-ejb - PessoaDAOJpa.java
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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.flexsolutions.admin.dao.PessoaDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Pessoa;
import br.com.flexsolutions.security.pojo.Pessoa_;

/**
 * @author Thiago Augusto
 *
 */
public class PessoaDAOJpa extends GenericDAOJpa<Pessoa, Serializable> implements
		PessoaDAO {

	public PessoaDAOJpa() {
		super();
	}

	public PessoaDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.PessoaDAO#retornarCount(java.lang.String)
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

		Root<Pessoa> root = cq.from(Pessoa.class);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(Pessoa_.pesId)));

		// WHERE
		Predicate predicate = cb.disjunction();

		// busca por nome fantasia
		predicate = cb.or(
				predicate,
				cb.like(cb.lower(root.get(Pessoa_.pesNomeFantasia)), "%"
						+ textoPesquisa.toLowerCase() + "%"));

		cq.where(predicate);

		// Execucao SELECT
		TypedQuery<Long> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.PessoaDAO#filtrarPag(java.lang.String,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Pessoa> filtrarPag(String textoPesquisa, Integer firstResult,
			Integer maxResults) {

		// Se textoPesquisa nao informado, atribuir vazio para fazer o count de
		// todos os registros
		if (textoPesquisa == null) {
			textoPesquisa = "";
		}

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);

		// FROM e JOIN's
		Root<Pessoa> root = cq.from(Pessoa.class);

		// WHERE
		Predicate predicate = cb.disjunction();

		// descricao do grupo
		predicate = cb.or(cb.like(cb.lower(root.get(Pessoa_.pesNomeFantasia)),
				"%" + textoPesquisa.toLowerCase() + "%"));

		cq.where(predicate);
		cq.orderBy(cb.asc(root.get(Pessoa_.pesId)));

		// Execucao SELECT
		TypedQuery<Pessoa> tq = entityManager.createQuery(cq);

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
	 * @see br.com.flexsolutions.admin.dao.PessoaDAO#buscarPor(java.lang.String)
	 */
	@Override
	public Pessoa buscarPor(String cpfCnpj) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);

		// FROM e JOIN's
		Root<Pessoa> root = cq.from(Pessoa.class);

		// Extensao do tipo arquivo digital
		Predicate predicate = cb.equal(cb.upper(root.get(Pessoa_.pesCpfCnpj)),
				cpfCnpj.toUpperCase());

		// WHERE
		cq.where(predicate);
		// Execucao SELECT
		TypedQuery<Pessoa> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

}
