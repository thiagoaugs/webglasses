/**  
 * admin-ejb  - Grupo.java
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

/**
 * Classe que sera utilizada para manter os grupos de usuarios do sistema
 * 
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_GRUPO")
@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_GRUPO_GPOID_GENERATOR", sequenceName = "ADMIN_GRUPO_GPO_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_GRUPO_GPOID_GENERATOR")
	@Column(name = "adm_gpo_id")
	private Integer gpoId;

	@Column(name = "adm_gpo_descricao", nullable = true, columnDefinition = "varchar(100)")
	private String gpoDescricao;

	@Column(name = "adm_gpo_nome", nullable = true, columnDefinition = "varchar(15)")
	private String gpoNome;

	@Column(name = "adm_gpo_de_menu")
	private Boolean gpoDeMenu;

	@ManyToOne(fetch = FetchType.EAGER)
	private Sistema gpoSistema;

	public Grupo() {
	}

	public Grupo(Integer gpoId, String gpoNome) {
		this.gpoId = gpoId;
		this.gpoNome = gpoNome;

	}

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
	 * @return the gpoSistema
	 */
	public Sistema getGpoSistema() {
		return gpoSistema;
	}

	/**
	 * @param gpoSistema
	 *            the gpoSistema to set
	 */
	public void setGpoSistema(Sistema gpoSistema) {
		this.gpoSistema = gpoSistema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gpoId == null) ? 0 : gpoId.hashCode());
		result = prime * result + ((gpoNome == null) ? 0 : gpoNome.hashCode());
		result = prime * result
				+ ((gpoSistema == null) ? 0 : gpoSistema.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (gpoId == null) {
			if (other.gpoId != null)
				return false;
		} else if (!gpoId.equals(other.gpoId))
			return false;
		if (gpoNome == null) {
			if (other.gpoNome != null)
				return false;
		} else if (!gpoNome.equals(other.gpoNome))
			return false;
		if (gpoSistema == null) {
			if (other.gpoSistema != null)
				return false;
		} else if (!gpoSistema.equals(other.gpoSistema))
			return false;
		return true;
	}

}