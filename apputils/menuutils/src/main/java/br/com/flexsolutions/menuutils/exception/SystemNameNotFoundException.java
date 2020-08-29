/**  
 *menuutils - SystemNameNotFoundException.java
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
public class SystemNameNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public SystemNameNotFoundException() {
	}

	public SystemNameNotFoundException(String arg0) {
		super(arg0);
	}

	public SystemNameNotFoundException(Throwable arg0) {
		super(arg0);
	}
}