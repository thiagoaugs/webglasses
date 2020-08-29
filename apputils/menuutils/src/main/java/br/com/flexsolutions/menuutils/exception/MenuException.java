/**  
 *menuutils - MenuException.java
 * 
 * Data de criacao 16/12/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.menuutils.exception;

/**
 * @author Thiago Augusto
 *
 */
public class MenuException extends Exception {
	private static final long serialVersionUID = 1L;

	public MenuException() {
	}

	public MenuException(String message, Throwable cause) {
		super(message, cause);
	}

	public MenuException(String message) {
		super(message);
	}

	public MenuException(Throwable cause) {
		super(cause);
	}

}
