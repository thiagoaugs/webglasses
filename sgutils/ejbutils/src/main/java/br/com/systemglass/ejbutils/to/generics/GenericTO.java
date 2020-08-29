/**  
 * ejbutils - GenericTO.java
 * 
 * Data de criacao 08/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.ejbutils.to.generics;

import java.io.Serializable;

/**
 * @author Thiago Augusto
 *
 */
public abstract class GenericTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract Long getId();
}
