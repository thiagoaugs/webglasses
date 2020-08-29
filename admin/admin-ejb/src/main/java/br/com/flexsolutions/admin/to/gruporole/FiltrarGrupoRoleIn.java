/**  
 * admin-ejb - ListarGrupoRoleIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.gruporole;

import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarGrupoRoleIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;

	private Integer idSistema;

	/**
	 * @return the idSistema
	 */
	public Integer getIdSistema() {
		return idSistema;
	}

	/**
	 * @param idSistema
	 *            the idSistema to set
	 */
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

	/**
	 * @return the idGrupo
	 */
	public Integer getIdGrupo() {
		return idGrupo;
	}

	/**
	 * @param idGrupo
	 *            the idGrupo to set
	 */
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	/**
	 * @return the idRole
	 */
	public Integer getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole
	 *            the idRole to set
	 */
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	private Integer idGrupo;
	private Integer idRole;

}
