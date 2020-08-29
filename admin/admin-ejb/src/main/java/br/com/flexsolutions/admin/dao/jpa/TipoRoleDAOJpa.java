/**  
 * admin-ejb - TipoRoleDAOJpa.java
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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.flexsolutions.admin.dao.TipoRoleDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.TipoRole;
import br.com.flexsolutions.security.pojo.TipoRole_;

/**
 * @author Thiago Augusto
 *
 */
public class TipoRoleDAOJpa extends GenericDAOJpa<TipoRole, Serializable>
		implements TipoRoleDAO {

	public TipoRoleDAOJpa() {
		super();
	}

	public TipoRoleDAOJpa(EntityManager em) {
		super(em);
	}

	/**
	 * @param pk
	 * @return
	 */
	public TipoRole findByPK(Integer pk) {
		try {
			return this.read(pk);
		} catch (NoResultException n) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.dao.TipoPessoaDAO#retornarListaTipoSituacao(
	 * java.lang.String)
	 */
	@Override
	public List<TipoRole> listar() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoRole> cq = cb.createQuery(TipoRole.class);

		Root<TipoRole> root = cq.from(TipoRole.class);
		cq.orderBy(cb.asc(root.get(TipoRole_.tprId)));

		// Execucao SELECT
		TypedQuery<TipoRole> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

}
