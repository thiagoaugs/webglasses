/**  
 * admin-ejb - RoleTO.java
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
public class RolesTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer rolId;

	private Boolean rolAtivo = true;

	private String rolDescricao;

	private String rolNome;

	// private List<GrupoRoleTO> rolGrupoRoles;

	private SistemaTO rolSistemaTO;

	private Boolean rolSelect;

	/**
	 * @return the rolId
	 */
	public Integer getRolId() {
		return rolId;
	}

	/**
	 * @param rolId
	 *            the rolId to set
	 */
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	/**
	 * @return the rolAtivo
	 */
	public Boolean getRolAtivo() {
		return rolAtivo;
	}

	/**
	 * @param rolAtivo
	 *            the rolAtivo to set
	 */
	public void setRolAtivo(Boolean rolAtivo) {
		this.rolAtivo = rolAtivo;
	}

	/**
	 * @return the rolDescricao
	 */
	public String getRolDescricao() {
		return rolDescricao;
	}

	/**
	 * @param rolDescricao
	 *            the rolDescricao to set
	 */
	public void setRolDescricao(String rolDescricao) {
		this.rolDescricao = rolDescricao;
	}

	/**
	 * @return the rolNome
	 */
	public String getRolNome() {
		return rolNome;
	}

	/**
	 * @param rolNome
	 *            the rolNome to set
	 */
	public void setRolNome(String rolNome) {
		this.rolNome = rolNome;
	}

	/**
	 * @return the rolSistemaTO
	 */
	public SistemaTO getRolSistemaTO() {
		return rolSistemaTO;
	}

	/**
	 * @param rolSistemaTO
	 *            the rolSistemaTO to set
	 */
	public void setRolSistemaTO(SistemaTO rolSistemaTO) {
		this.rolSistemaTO = rolSistemaTO;
	}

	/**
	 * @return the rolSelect
	 */
	public Boolean getRolSelect() {
		return rolSelect;
	}

	/**
	 * @param rolSelect
	 *            the rolSelect to set
	 */
	public void setRolSelect(Boolean rolSelect) {
		this.rolSelect = rolSelect;
	}

}
