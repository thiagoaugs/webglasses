/**  
 * admin-ejb - TipoPessoaDAOJpa.java
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

import br.com.flexsolutions.admin.dao.TipoPessoaDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.TipoPessoa;
import br.com.flexsolutions.security.pojo.TipoPessoa_;

/**
 * @author Thiago Augusto
 *
 */
public class TipoPessoaDAOJpa extends GenericDAOJpa<TipoPessoa, Serializable>
		implements TipoPessoaDAO {

	public TipoPessoaDAOJpa() {
		super();
	}

	public TipoPessoaDAOJpa(EntityManager em) {
		super(em);
	}
	
	
	/**
	 * @param pk
	 * @return
	 */
	public TipoPessoa findByPK(Integer pk) {
		try {
			return this.read(pk);
		} catch (NoResultException n) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.admin.dao.TipoPessoaDAO#retornarListaTipoSituacao(java.lang.String)
	 */
	@Override
	public List<TipoPessoa> listar() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoPessoa> cq = cb.createQuery(TipoPessoa.class);

		Root<TipoPessoa> root = cq.from(TipoPessoa.class);
		cq.orderBy(cb.asc(root.get(TipoPessoa_.tpsId)));
		
		// Execucao SELECT
		TypedQuery<TipoPessoa> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

}
