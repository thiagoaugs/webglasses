/**  
 * ejbutils - AppException.java
 * 
 * Data de criacao 08/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.ejbutils.exception;

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
