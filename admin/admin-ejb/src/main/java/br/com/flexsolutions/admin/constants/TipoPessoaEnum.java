/**  
 * admin-ejb - TipoPessoaEnum.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.constants;

/**
 * Tipos de pessoa utilizado nos apps flexsolutions (cliente / fornecedor /
 * tranportadora)
 * 
 * @author Thiago Augusto
 *
 */
public enum TipoPessoaEnum {

	TIPO_PESSOA_FORNECEDOR(1, "Fornecedor"), 
	TIPO_PESSOA_CLIENTE(2, "Cliente"), 
	TIPO_PESSOA_TRANSPORTADOR(3, "Transportadora");

	private Integer id;
	private String descricao;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private TipoPessoaEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public static TipoPessoaEnum getTipoPessoaEnumById(Integer tpsId) {
		TipoPessoaEnum tpe = null;

		TipoPessoaEnum[] enums = TipoPessoaEnum.values();

		for (TipoPessoaEnum t : enums) {
			if (t.getId().compareTo(tpsId) == 0) {
				tpe = t;
				break;
			}
		}

		return tpe;
	}
}
