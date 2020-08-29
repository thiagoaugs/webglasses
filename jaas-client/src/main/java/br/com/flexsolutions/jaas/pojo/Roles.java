/**  
 * admin-ejb - Role.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_ROLES")
@NamedQuery(name = "Roles.findAll", query = "SELECT u FROM Roles u")
public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_ROLE_ROLID_GENERATOR", sequenceName = "ADMIN_ROLE_ROL_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_ROLE_ROLID_GENERATOR")
	@Column(name = "adm_rol_id", nullable = true)
	private Integer rolId;

	@Column(name = "adm_rol_ativo", nullable = true)
	private Boolean rolAtivo;

	@Column(name = "adm_rol_descricao", columnDefinition = "varchar(150)")
	private String rolDescricao;

	@Column(name = "adm_rol_nome", columnDefinition = "varchar(40)")
	private String rolNome;

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "gprRole")
	// private List<GrupoRole> rolGrupoRoles;

	@ManyToOne(fetch = FetchType.EAGER)
	private Sistema rolSistema;

	@Transient
	private Boolean rolSelect;

	public Roles() {
	}

	// quando houver pesquisa com selection deve-se implementar um constructor
	// com as variaves selecionadas
	public Roles(Integer rolId, String rolNome) {
		this.rolId = rolId;
		this.rolNome = rolNome;

	}

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
	 * @return the rolSistema
	 */
	public Sistema getRolSistema() {
		return rolSistema;
	}

	/**
	 * @param rolSistema
	 *            the rolSistema to set
	 */
	public void setRolSistema(Sistema rolSistema) {
		this.rolSistema = rolSistema;
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
