/**  
 * admin-ejb - UsuarioGrupoTO.java
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
public class UsuarioGrupoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ugpId;

	private UsuarioTO ugpUsuario;

	private GrupoTO ugpGrupo;

	/**
	 * @return the ugpId
	 */
	public Integer getUgpId() {
		return ugpId;
	}

	/**
	 * @param ugpId
	 *            the ugpId to set
	 */
	public void setUgpId(Integer ugpId) {
		this.ugpId = ugpId;
	}

	/**
	 * @return the ugpUsuario
	 */
	public UsuarioTO getUgpUsuario() {
		return ugpUsuario;
	}

	/**
	 * @param ugpUsuario
	 *            the ugpUsuario to set
	 */
	public void setUgpUsuario(UsuarioTO ugpUsuario) {
		this.ugpUsuario = ugpUsuario;
	}

	/**
	 * @return the ugpGrupo
	 */
	public GrupoTO getUgpGrupo() {
		return ugpGrupo;
	}

	/**
	 * @param ugpGrupo
	 *            the ugpGrupo to set
	 */
	public void setUgpGrupo(GrupoTO ugpGrupo) {
		this.ugpGrupo = ugpGrupo;
	}

}
