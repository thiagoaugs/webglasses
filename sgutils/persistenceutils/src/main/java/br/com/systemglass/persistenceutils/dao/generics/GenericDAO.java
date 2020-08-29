/**  
 * webglass-ejb - GenericDao.java
 * 
 * Data de criação 28/05/2015
 *
 * Criado por DESENV10
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.persistenceutils.dao.generics;

import java.io.Serializable;

import br.com.systemglass.persistenceutils.exception.ConstraintViolationException;

/**
 * Interface para chamada dos metodos utilizados na camada de persistencia
 * 
 * @author DESENV10
 * 
 */
public interface GenericDAO<T, PK extends Serializable> {

	public abstract T create(T paramT);

	public abstract T update(T paramT);

	public abstract void delete(PK paramPK) throws ConstraintViolationException;

	public abstract T read(PK paramPK);

}
