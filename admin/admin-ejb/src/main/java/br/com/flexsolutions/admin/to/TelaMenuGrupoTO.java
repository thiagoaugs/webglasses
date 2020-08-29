/**  
 * admin-ejb - TelaMenuGrupoTO.java
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
public class TelaMenuGrupoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tmgId;

	private GrupoTO tmgGrupo;

	private TelaMenuTO tmgTelaMenu;

	/**
	 * @return the tmgId
	 */
	public Integer getTmgId() {
		return tmgId;
	}

	/**
	 * @param tmgId
	 *            the tmgId to set
	 */
	public void setTmgId(Integer tmgId) {
		this.tmgId = tmgId;
	}

	/**
	 * @return the tmgGrupo
	 */
	public GrupoTO getTmgGrupo() {
		return tmgGrupo;
	}

	/**
	 * @param tmgGrupo
	 *            the tmgGrupo to set
	 */
	public void setTmgGrupo(GrupoTO tmgGrupo) {
		this.tmgGrupo = tmgGrupo;
	}

	/**
	 * @return the tmgTelaMenu
	 */
	public TelaMenuTO getTmgTelaMenu() {
		return tmgTelaMenu;
	}

	/**
	 * @param tmgTelaMenu
	 *            the tmgTelaMenu to set
	 */
	public void setTmgTelaMenu(TelaMenuTO tmgTelaMenu) {
		this.tmgTelaMenu = tmgTelaMenu;
	}

}
