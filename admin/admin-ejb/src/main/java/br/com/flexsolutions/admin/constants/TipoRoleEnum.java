/**  
 * admin-ejb - TipoRoleEnum.java
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
 * @author Thiago Augusto
 *
 */
public enum TipoRoleEnum {

	INCLUIR_REGISTRO(1, "Permite_Incluir"), 
	ALTERAR_REGISTRO(2, "Permite_Alterar"), 
	EXCLUIR_REGISTRO(3, "Permite_Excluir"),
	VISUALIZAR_REGISTRO(4, "Permite_Visualizar"),
	DADOS_SIGILOSOS(5, "Visualiza_Dados_Sigilosos");

	private Integer id;
	private String descricao;
	
	
	private TipoRoleEnum(Integer id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id the id to set
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
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoRoleEnum getTipoRoleEnumById(Integer treId) {
		TipoRoleEnum tre = null;

		TipoRoleEnum[] enums = TipoRoleEnum.values();

		for (TipoRoleEnum t : enums) {
			if (t.getId().compareTo(treId) == 0) {
				tre = t;
				break;
			}
		}

		return tre;
	}
}
