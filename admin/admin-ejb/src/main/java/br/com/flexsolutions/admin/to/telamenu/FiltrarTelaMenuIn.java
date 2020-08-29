/**  
 * jass-ejb - ListarTelaMenuIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenu;

import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarTelaMenuIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;

	private String pesquisa;

	private Integer idSistema;

	/**
	 * @return the pesquisaDescricao
	 */
	public String getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisaDescricao the pesquisaDescricao to set
	 */
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

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
