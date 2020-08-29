/**  
 * admin-ejb - FiltrarUsuarioGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioGrupoIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;

	private Integer idSistema;
	private Integer idGrupo;
	private String pesqUsuario;

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
	 * @return the pesqUsuario
	 */
	public String getPesqUsuario() {
		return pesqUsuario;
	}

	/**
	 * @param pesqUsuario
	 *            the pesqUsuario to set
	 */
	public void setPesqUsuario(String pesqUsuario) {
		this.pesqUsuario = pesqUsuario;
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

}
