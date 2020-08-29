/**  
 * admin-ejb - ListarUsuarioIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuario;

import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class ListarUsuarioIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;
	
	
	private Integer idSistema;


	/**
	 * @return the idSistema
	 */
	public Integer getIdSistema() {
		return idSistema;
	}


	/**
	 * @param idSistema the idSistema to set
	 */
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}
	
	

}
