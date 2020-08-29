/**  
 *persistence - ConstraintViolationException.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persistence.exceptions;

import javax.persistence.PersistenceException;

/**
 * @author Thiago Augusto
 *
 */
public class ConstraintViolationException  extends PersistenceException {
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
