/**  
 * persistenceutils - PersistenceException.java
 * 
 * Data de criacao 09/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.persistenceutils.exception;

/**
 * @author Thiago Augusto
 *
 */
public class PersistenceException extends Exception {
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
