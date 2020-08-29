/**  
 * ejbutils - AppException.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.exception;

import javax.ejb.ApplicationException;

/**
 * @author Thiago Augusto
 *
 */
@ApplicationException(rollback = true)
public class AppException extends Exception {
	private static final long serialVersionUID = 1L;

	public AppException() {
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}
}
