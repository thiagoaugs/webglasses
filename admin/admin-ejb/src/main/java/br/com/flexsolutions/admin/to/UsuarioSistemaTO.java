/**  
 * admin-ejb - UsuarioSistemaTO.java
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
public class UsuarioSistemaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer usiId;

	private SistemaTO usiSistema;

	private UsuarioTO usiUsuario;

//	private List<UsuarioSistemaGrupo> usiUsuarioSistemaGrupo;

	private Character usiTipoUsuario;

	private Boolean usiSemRestricao;

	/**
	 * @return the usiId
	 */
	public Integer getUsiId() {
		return usiId;
	}

	/**
	 * @param usiId
	 *            the usiId to set
	 */
	public void setUsiId(Integer usiId) {
		this.usiId = usiId;
	}

	/**
	 * @return the usiSistema
	 */
	public SistemaTO getUsiSistema() {
		return usiSistema;
	}

	/**
	 * @param usiSistema
	 *            the usiSistema to set
	 */
	public void setUsiSistema(SistemaTO usiSistema) {
		this.usiSistema = usiSistema;
	}

	/**
	 * @return the usiUsuario
	 */
	public UsuarioTO getUsiUsuario() {
		return usiUsuario;
	}

	/**
	 * @param usiUsuario
	 *            the usiUsuario to set
	 */
	public void setUsiUsuario(UsuarioTO usiUsuario) {
		this.usiUsuario = usiUsuario;
	}

	/**
	 * @return the usiUsuarioSistemaGrupo
	 */
//	public List<UsuarioSistemaGrupo> getUsiUsuarioSistemaGrupo() {
//		return usiUsuarioSistemaGrupo;
//	}

	/**
	 * @param usiUsuarioSistemaGrupo
	 *            the usiUsuarioSistemaGrupo to set
	 */
//	public void setUsiUsuarioSistemaGrupo(
//			List<UsuarioSistemaGrupo> usiUsuarioSistemaGrupo) {
//		this.usiUsuarioSistemaGrupo = usiUsuarioSistemaGrupo;
//	}

	/**
	 * @return the usiTipoUsuario
	 */
	public Character getUsiTipoUsuario() {
		return usiTipoUsuario;
	}

	/**
	 * @param usiTipoUsuario
	 *            the usiTipoUsuario to set
	 */
	public void setUsiTipoUsuario(Character usiTipoUsuario) {
		this.usiTipoUsuario = usiTipoUsuario;
	}

	/**
	 * @return the usiSemRestricao
	 */
	public Boolean getUsiSemRestricao() {
		return usiSemRestricao;
	}

	/**
	 * @param usiSemRestricao
	 *            the usiSemRestricao to set
	 */
	public void setUsiSemRestricao(Boolean usiSemRestricao) {
		this.usiSemRestricao = usiSemRestricao;
	}

}
