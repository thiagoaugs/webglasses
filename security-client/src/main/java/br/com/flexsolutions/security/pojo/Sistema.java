/**  
 * admin-ejb - Sistema.java
 * 
 * Data de criacao 16/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_SISTEMA")
@NamedQuery(name = "Sistema.findAll", query = "SELECT u FROM Sistema u")
public class Sistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_SISTEMA_SISID_GENERATOR", sequenceName = "ADMIN_SISTEMA_SIS_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_SISTEMA_SISID_GENERATOR")
	@Column(name = "amd_sis_id", nullable = true)
	private Integer sisId;

	@Column(name = "sis_nome", nullable = true, columnDefinition = "varchar(100)")
	private String sisNome;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="tmeSistema")
	private List<TelaMenu> sistemaTelaMenu;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="gpoSistema")
	private List<Grupo> sistemaGrupo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="rolSistema")
	private List<Roles> sistemaRoles;
//	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy="usiSistema")
//	private List<UsuarioSistema> sistemaUsuario;

	public Sistema() {
	}

	/**
	 * @return the sisId
	 */
	public Integer getSisId() {
		return sisId;
	}

	/**
	 * @param sisId the sisId to set
	 */
	public void setSisId(Integer sisId) {
		this.sisId = sisId;
	}

	/**
	 * @return the sisNome
	 */
	public String getSisNome() {
		return sisNome;
	}

	/**
	 * @param sisNome the sisNome to set
	 */
	public void setSisNome(String sisNome) {
		this.sisNome = sisNome;
	}

	/**
	 * @return the sistemaTelaMenu
	 */
	public List<TelaMenu> getSistemaTelaMenu() {
		return sistemaTelaMenu;
	}

	/**
	 * @param sistemaTelaMenu the sistemaTelaMenu to set
	 */
	public void setSistemaTelaMenu(List<TelaMenu> sistemaTelaMenu) {
		this.sistemaTelaMenu = sistemaTelaMenu;
	}

	/**
	 * @return the sistemaGrupo
	 */
	public List<Grupo> getSistemaGrupo() {
		return sistemaGrupo;
	}

	/**
	 * @param sistemaGrupo the sistemaGrupo to set
	 */
	public void setSistemaGrupo(List<Grupo> sistemaGrupo) {
		this.sistemaGrupo = sistemaGrupo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sisId == null) ? 0 : sisId.hashCode());
		result = prime * result + ((sisNome == null) ? 0 : sisNome.hashCode());
		result = prime * result
				+ ((sistemaGrupo == null) ? 0 : sistemaGrupo.hashCode());
		result = prime * result
				+ ((sistemaRoles == null) ? 0 : sistemaRoles.hashCode());
		result = prime * result
				+ ((sistemaTelaMenu == null) ? 0 : sistemaTelaMenu.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Sistema other = (Sistema) obj;
		if (sisId == null) {
			if (other.sisId != null)
				return false;
		} else if (!sisId.equals(other.sisId))
			return false;
		if (sisNome == null) {
			if (other.sisNome != null)
				return false;
		} else if (!sisNome.equals(other.sisNome))
			return false;
		if (sistemaGrupo == null) {
			if (other.sistemaGrupo != null)
				return false;
		} else if (!sistemaGrupo.equals(other.sistemaGrupo))
			return false;
		if (sistemaRoles == null) {
			if (other.sistemaRoles != null)
				return false;
		} else if (!sistemaRoles.equals(other.sistemaRoles))
			return false;
		if (sistemaTelaMenu == null) {
			if (other.sistemaTelaMenu != null)
				return false;
		} else if (!sistemaTelaMenu.equals(other.sistemaTelaMenu))
			return false;
		return true;
	}

	/**
	 * @return the sistemaRoles
	 */
	public List<Roles> getSistemaRoles() {
		return sistemaRoles;
	}

	/**
	 * @param sistemaRoles the sistemaRoles to set
	 */
	public void setSistemaRoles(List<Roles> sistemaRoles) {
		this.sistemaRoles = sistemaRoles;
	}

	/**
	 * @return the sistemaUsuario
	 */
//	public List<UsuarioSistema> getSistemaUsuario() {
//		return sistemaUsuario;
//	}

	/**
	 * @param sistemaUsuario the sistemaUsuario to set
	 */
//	public void setSistemaUsuario(List<UsuarioSistema> sistemaUsuario) {
//		this.sistemaUsuario = sistemaUsuario;
//	}

	
	
}
