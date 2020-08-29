/**  
 * jass-ejb - ListarGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.grupo;

import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarGrupoIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;

	private String pesquisaDescricao;
	
	private Integer idSistema;
	

	/**
	 * @return the pesquisaDescricao
	 */
	public String getPesquisaDescricao() {
		return pesquisaDescricao;
	}

	/**
	 * @param pesquisaDescricao
	 *            the pesquisaDescricao to set
	 */
	public void setPesquisaDescricao(String pesquisaDescricao) {
		this.pesquisaDescricao = pesquisaDescricao;
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