/**  
 * admin-ejb - UsuarioSistemaGrupoTO.java
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
public class UsuarioSistemaGrupoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer usgId;

	private UsuarioSistemaTO usgUsuarioSistema;

	private GrupoTO usgGrupo;

	/**
	 * @return the usgId
	 */
	public Integer getUsgId() {
		return usgId;
	}

	/**
	 * @param usgId
	 *            the usgId to set
	 */
	public void setUsgId(Integer usgId) {
		this.usgId = usgId;
	}

	/**
	 * @return the usgUsuarioSistema
	 */
	public UsuarioSistemaTO getUsgUsuarioSistema() {
		return usgUsuarioSistema;
	}

	/**
	 * @param usgUsuarioSistema
	 *            the usgUsuarioSistema to set
	 */
	public void setUsgUsuarioSistema(UsuarioSistemaTO usgUsuarioSistema) {
		this.usgUsuarioSistema = usgUsuarioSistema;
	}

	/**
	 * @return the usgGrupo
	 */
	public GrupoTO getUsgGrupo() {
		return usgGrupo;
	}

	/**
	 * @param usgGrupo
	 *            the usgGrupo to set
	 */
	public void setUsgGrupo(GrupoTO usgGrupo) {
		this.usgGrupo = usgGrupo;
	}

}
