/**  
 * persistenceutils - ConstraintViolationException.java
 * 
 * Data de criacao 09/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.persistenceutils.exception;

/**
 * @author Thiago Augusto
 *
 */
public class ConstraintViolationException extends PersistenceException {
	private static final long serialVersionUID = 1L;

	public ConstraintViolationException() {
	}

	public ConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConstraintViolationException(String message) {
		super(message);
	}

	public ConstraintViolationException(Throwable cause) {
		super(cause);
	}
}
