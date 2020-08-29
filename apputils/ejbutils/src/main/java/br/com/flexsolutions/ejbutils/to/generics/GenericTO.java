/**  
 * ejbutils - GenericTO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.to.generics;

import java.io.Serializable;

/**
 * @author Thiago Augusto
 *
 */
public abstract class GenericTO implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract Long getId();
}
