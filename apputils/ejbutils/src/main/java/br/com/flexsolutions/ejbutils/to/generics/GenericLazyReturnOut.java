/**  
 * webglass-ejb - GenericLazyReturn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.to.generics;

/**
 * @author Thiago Augusto
 *
 */
public class GenericLazyReturnOut extends GenericOut {
	private static final long serialVersionUID = 1L;
	protected Long countTotal;

	public Long getCountTotal() {
		return this.countTotal;
	}

	public void setCountTotal(Long countTotal) {
		this.countTotal = countTotal;
	}
}
