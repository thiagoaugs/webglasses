/**  
 * admin-ejb - FiltrarUsuarioPorSistemaNaoConfiguradoIn.java
 * 
 * Data de criacao 25/09/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private Integer idSistema;
	private Integer idGrupo;

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

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

}
