/**  
 * admin-ejb - ListarGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.grupo;

import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class ListarGrupoIn  extends GenericIn{
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
