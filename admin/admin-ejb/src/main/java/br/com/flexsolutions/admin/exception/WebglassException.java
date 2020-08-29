/**  
* jass-ejb - WebglassException.java
* 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
* 
* Copyright flexsolutions - Todos os direitos reservados.
*
*/
package br.com.flexsolutions.admin.exception;

/**
 * @author Thiago Augusto
 * 
 * Excecao generica para tratamento das excessões da aplicação webglass.
 *
 */
public class WebglassException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public WebglassException() {
		super();
	}

	public WebglassException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebglassException(String message) {
		super(message);
	}

	public WebglassException(Throwable cause) {
		super(cause);
	}

}
