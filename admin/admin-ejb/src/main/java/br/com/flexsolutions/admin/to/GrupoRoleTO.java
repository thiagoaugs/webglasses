/**  
 * admin-ejb - GrupoRoleTO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to;

import java.io.Serializable;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoRoleTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer gprId;

	private GrupoTO gprGrupo;

	private RolesTO gprRole;

	/**
	 * @return the gprId
	 */
	public Integer getGprId() {
		return gprId;
	}

	/**
	 * @param gprId
	 *            the gprId to set
	 */
	public void setGprId(Integer gprId) {
		this.gprId = gprId;
	}

	/**
	 * @return the gprGrupo
	 */
	public GrupoTO getGprGrupo() {
		return gprGrupo;
	}

	/**
	 * @param gprGrupo
	 *            the gprGrupo to set
	 */
	public void setGprGrupo(GrupoTO gprGrupo) {
		this.gprGrupo = gprGrupo;
	}

	/**
	 * @return the gprRole
	 */
	public RolesTO getGprRole() {
		return gprRole;
	}

	/**
	 * @param gprRole
	 *            the gprRole to set
	 */
	public void setGprRole(RolesTO gprRole) {
		this.gprRole = gprRole;
	}
}
