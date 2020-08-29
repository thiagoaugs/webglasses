/**  
 * jaasmenu - FAASMenuException.java
 * 
 * Data de criacao 15/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menu.exception;

/**
 * @author Thiago Augusto
 *
 */
public class JAASMenuException extends Exception {
	private static final long serialVersionUID = 1L;

	public JAASMenuException() {
	}

	public JAASMenuException(String message, Throwable cause) {
		super(message, cause);
	}

	public JAASMenuException(String message) {
		super(message);
	}

	public JAASMenuException(Throwable cause) {
		super(cause);
	}
}
