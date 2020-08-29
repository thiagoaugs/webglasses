/**  
 * jass-ejb - GrupoTO.java
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
public class GrupoTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer gpoId;

	private String gpoDescricao;

	private String gpoNome;

	private Boolean gpoDeMenu;

	private SistemaTO gpoSistemaTO;

	/**
	 * @return the gpoId
	 */
	public Integer getGpoId() {
		return gpoId;
	}

	/**
	 * @param gpoId
	 *            the gpoId to set
	 */
	public void setGpoId(Integer gpoId) {
		this.gpoId = gpoId;
	}

	/**
	 * @return the gpoDescricao
	 */
	public String getGpoDescricao() {
		return gpoDescricao;
	}

	/**
	 * @param gpoDescricao
	 *            the gpoDescricao to set
	 */
	public void setGpoDescricao(String gpoDescricao) {
		this.gpoDescricao = gpoDescricao;
	}

	/**
	 * @return the gpoNome
	 */
	public String getGpoNome() {
		return gpoNome;
	}

	/**
	 * @param gpoNome
	 *            the gpoNome to set
	 */
	public void setGpoNome(String gpoNome) {
		this.gpoNome = gpoNome;
	}

	/**
	 * @return the gpoDeMenu
	 */
	public Boolean getGpoDeMenu() {
		return gpoDeMenu;
	}

	/**
	 * @param gpoDeMenu
	 *            the gpoDeMenu to set
	 */
	public void setGpoDeMenu(Boolean gpoDeMenu) {
		this.gpoDeMenu = gpoDeMenu;
	}

	/**
	 * @return the gpoSistemaTO
	 */
	public SistemaTO getGpoSistemaTO() {
		return gpoSistemaTO;
	}

	/**
	 * @param gpoSistemaTO
	 *            the gpoSistemaTO to set
	 */
	public void setGpoSistemaTO(SistemaTO gpoSistemaTO) {
		this.gpoSistemaTO = gpoSistemaTO;
	}

}
