/**  
 * admin-ejb - GrupoRoleDAOJpa.java
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

import br.com.flexsolutions.admin.dao.GrupoRoleDAO;
import br.com.flexsolutions.persistenceutils.dao.generics.impl.GenericDAOJpa;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.GrupoRole;
import br.com.flexsolutions.security.pojo.GrupoRole_;
import br.com.flexsolutions.security.pojo.Grupo_;
import br.com.flexsolutions.security.pojo.Roles;
import br.com.flexsolutions.security.pojo.Roles_;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.Sistema_;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoRoleDAOJpa extends GenericDAOJpa<GrupoRole, Serializable>
		implements GrupoRoleDAO {

	public GrupoRoleDAOJpa() {
		super();
	}

	public GrupoRoleDAOJpa(EntityManager em) {
		super(em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.dao.GrupoRoleDAO#retornarCount()
	 */
	@Override
	public Long retornarCount(Integer idSistema, Integer idGrupo, Integer idRole) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<GrupoRole> root = cq.from(GrupoRole.class);
		Join<GrupoRole, Grupo> joinGrupo = root.join(GrupoRole_.gprGrupo,
				JoinType.INNER);
		Join<GrupoRole, Roles> joinRole = root.join(GrupoRole_.gprRole,
				JoinType.INNER);

		Join<Grupo, Sistema> joinGrupoSistema = joinGrupo.join(
				Grupo_.gpoSistema, JoinType.INNER);
		Join<Roles, Sistema> joinRoleSistema = joinRole.join(Roles_.rolSistema,
				JoinType.INNER);

		// RETORNO (Fields)
		cq.select(cb.count(root.get(GrupoRole_.gprId)));

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinGrupoSistema.get(Sistema_.sisId), idSistema)));
			predicates.add(cb.and(cb.equal(joinRoleSistema.get(Sistema_.sisId),
					idSistema)));
		}

		// id do grupo
		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		// id da role
		if (idRole != null && idRole != 0) {
			predicates
					.add(cb.and(cb.equal(joinRole.get(Roles_.rolId), idRole)));
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
	 * br.com.flexsolutions.admin.dao.GrupoRoleDAO#filtrarPag(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public List<GrupoRole> filtrarPag(Integer idSistema, Integer idGrupo,
			Integer idRole, Integer firstResult, Integer maxResults) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GrupoRole> cq = cb.createQuery(GrupoRole.class);

		// FROM e JOIN's
		Root<GrupoRole> root = cq.from(GrupoRole.class);
		Join<GrupoRole, Grupo> joinGrupo = root.join(GrupoRole_.gprGrupo,
				JoinType.INNER);
		Join<GrupoRole, Roles> joinRole = root.join(GrupoRole_.gprRole,
				JoinType.INNER);

		Join<Grupo, Sistema> joinGrupoSistema = joinGrupo.join(
				Grupo_.gpoSistema, JoinType.INNER);
		Join<Roles, Sistema> joinRoleSistema = joinRole.join(Roles_.rolSistema,
				JoinType.INNER);

		cq.select(root);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// id sistema
		if (idSistema != null && idSistema != 0) {
			predicates.add(cb.and(cb.equal(
					joinGrupoSistema.get(Sistema_.sisId), idSistema)));
			predicates.add(cb.and(cb.equal(joinRoleSistema.get(Sistema_.sisId),
					idSistema)));
		}

		// id do grupo
		if (idGrupo != null && idGrupo != 0) {
			predicates
					.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		}

		// id da role
		if (idRole != null && idRole != 0) {
			predicates
					.add(cb.and(cb.equal(joinRole.get(Roles_.rolId), idRole)));
		}

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<GrupoRole> tq = entityManager.createQuery(cq);

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
	 * @see br.com.flexsolutions.admin.dao.GrupoRoleDAO#buscarPor()
	 */
	@Override
	public GrupoRole buscarPor(Integer idGrupo, Integer idRole) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GrupoRole> cq = cb.createQuery(GrupoRole.class);

		// FROM e JOIN's
		Root<GrupoRole> root = cq.from(GrupoRole.class);
		Join<GrupoRole, Grupo> joinGrupo = root.join(GrupoRole_.gprGrupo,
				JoinType.INNER);
		Join<GrupoRole, Roles> joinRole = root.join(GrupoRole_.gprRole,
				JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(GrupoRole_.gprId));
		selections.add(joinGrupo.get(Grupo_.gpoId));
		selections.add(joinGrupo.get(Grupo_.gpoDescricao));
		selections.add(joinRole.get(Roles_.rolId));
		selections.add(joinRole.get(Roles_.rolDescricao));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do grupo
		predicates.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));
		// id da role
		predicates.add(cb.and(cb.equal(joinRole.get(Roles_.rolId), idRole)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<GrupoRole> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}

	@Override
	public List<GrupoRole> listarPorGrupo(Integer idGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GrupoRole> cq = cb.createQuery(GrupoRole.class);

		// FROM e JOIN's
		Root<GrupoRole> root = cq.from(GrupoRole.class);
		Join<GrupoRole, Grupo> joinGrupo = root.join(GrupoRole_.gprGrupo,
				JoinType.INNER);
		Join<GrupoRole, Roles> joinRole = root.join(GrupoRole_.gprRole,
				JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(root.get(GrupoRole_.gprId));
		selections.add(joinRole.get(Roles_.rolId));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
		// id do grupo

		predicates.add(cb.and(cb.equal(joinGrupo.get(Grupo_.gpoId), idGrupo)));

		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<GrupoRole> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	public List<GrupoRole> retornarRolesGrupos(List<Integer> listaIdGrupo) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<GrupoRole> cq = cb.createQuery(GrupoRole.class);

		// FROM e JOIN's
		Root<GrupoRole> root = cq.from(GrupoRole.class);
		Join<GrupoRole, Roles> joinRole = root.join(GrupoRole_.gprRole,
				JoinType.INNER);

		// RETORNO (Fields)
		List<Selection<?>> selections = new ArrayList<Selection<?>>();
		selections.add(joinRole.get(Roles_.rolNome));

		cq.multiselect(selections);

		List<Predicate> predicates = new ArrayList<Predicate>();
	
		// lista de id do grupo

		predicates.add(cb.and(root.get(GrupoRole_.gprId).in(listaIdGrupo)));
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		// Execucao SELECT
		TypedQuery<GrupoRole> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

}
