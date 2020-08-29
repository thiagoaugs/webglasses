/**  
 *persistence - GenericDAOJpa.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persistence.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.utils.persistence.dao.GenericDAO;
import br.com.utils.persistence.exceptions.ConstraintViolationException;
import br.com.utils.persistence.util.EntityUtils;

/**
 * @author Thiago Augusto
 *
 */
public abstract class GenericDAOJpa<T, PK extends Serializable> implements
		GenericDAO<T, PK> {
	protected Class<T> entityClass;
	protected EntityManager entityManager;

	public GenericDAOJpa() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = ((Class) genericSuperclass.getActualTypeArguments()[0]);
	}

	public GenericDAOJpa(EntityManager entityManager) {
		this.entityManager = entityManager;

		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = ((Class) genericSuperclass.getActualTypeArguments()[0]);
	}

	public T create(T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T read(PK id) {
		T t = this.entityManager.find(this.entityClass, id);
		if (t == null) {
			throw new NoResultException();
		}
		return t;
	}

	public T update(T t) {
		t = this.entityManager.merge(t);
		return t;
	}

	public void delete(PK id) throws ConstraintViolationException {
		T t = this.entityManager.find(this.entityClass, id);

		List<String> registrosAssociados = checkFKConstraint(id);
		if ((registrosAssociados != null) && (!registrosAssociados.isEmpty())) {
			StringBuilder registros = new StringBuilder();
			for (String registro : registrosAssociados) {
				if (!registros.toString().equals("")) {
					registros.append(",");
				}
				registros.append(registro);
			}
			throw new ConstraintViolationException(registros.toString());
		}
		this.entityManager.remove(t);
	}

	protected List<String> checkFKConstraint(PK id) {
		List<String> nomesEntidadesRelacionadas = null;

		Map<Class<?>, String> relacionamentos = EntityUtils
				.retornarRelacionamentosEntidade(this.entityClass);
		if (relacionamentos != null) {
			Iterator<Map.Entry<Class<?>, String>> it = relacionamentos
					.entrySet().iterator();

			String pkNameEntity = EntityUtils
					.retornarPrimaryKeyEntidade(this.entityClass);
			while (it.hasNext()) {
				Map.Entry<Class<?>, String> entry = (Map.Entry) it.next();

				String entityName = ((Class) entry.getKey()).getSimpleName();
				String columnName = (String) entry.getValue();

				StringBuilder queryString = new StringBuilder();
				queryString.append("SELECT e FROM ").append(entityName)
						.append(" AS e ").append("\tINNER JOIN e.")
						.append(columnName).append(" AS a ")
						.append("         WHERE a.").append(pkNameEntity)
						.append(" = :id");

				Query query = this.entityManager.createQuery(queryString
						.toString());
				query.setParameter("id", id);
				query.setMaxResults(1);
				Object resultado;
				try {
					resultado = query.getSingleResult();
				} catch (NoResultException e) {
					resultado = null;
				}
				if (resultado != null) {
					if (nomesEntidadesRelacionadas == null) {
						nomesEntidadesRelacionadas = new ArrayList();
					}
					String entityFKName = ((Class) entry.getKey())
							.getSimpleName();
					nomesEntidadesRelacionadas.add(entityFKName);
				}
			}
		}
		return nomesEntidadesRelacionadas;
	}
}