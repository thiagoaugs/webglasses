/**  
 * admin-ejb - TipoPessoaTO.java
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
public class TipoPessoaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tpsId;

	private String tpsDescricao;

	/**
	 * @return the tpsId
	 */
	public Integer getTpsId() {
		return tpsId;
	}

	/**
	 * @param tpsId
	 *            the tpsId to set
	 */
	public void setTpsId(Integer tpsId) {
		this.tpsId = tpsId;
	}

	/**
	 * @return the tpsDescricao
	 */
	public String getTpsDescricao() {
		return tpsDescricao;
	}

	/**
	 * @param tpsDescricao
	 *            the tpsDescricao to set
	 */
	public void setTpsDescricao(String tpsDescricao) {
		this.tpsDescricao = tpsDescricao;
	}

}
