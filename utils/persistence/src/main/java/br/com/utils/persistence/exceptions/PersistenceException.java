/**  
 *persistence - PersistenceException.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persistence.exceptions;

/**
 * @author Thiago Augusto
 *
 */
public class PersistenceException  extends Exception {
	private static final long serialVersionUID = 1L;

	public PersistenceException() {
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}


}
