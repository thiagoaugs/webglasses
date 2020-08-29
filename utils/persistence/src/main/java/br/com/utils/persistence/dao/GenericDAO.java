/**  
 *persistence - GenericDAO.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persistence.dao;

import java.io.Serializable;

import br.com.utils.persistence.exceptions.ConstraintViolationException;

/**
 * @author Thiago Augusto
 * Interface para chamada dos metodos utilizados na camada de persistencia
 * 
 */
public interface GenericDAO<T, PK extends Serializable>{
	public abstract T create(T paramT);

	public abstract T update(T paramT);

	public abstract void delete(PK paramPK) throws ConstraintViolationException;

	public abstract T read(PK paramPK);
}
