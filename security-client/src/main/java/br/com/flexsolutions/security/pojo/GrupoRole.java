/**  
 * admin-ejb - GrupoRole.java
 * 
 * Data de criacao 21/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_GRUPO_ROLE")
@NamedQuery(name = "GrupoRole.findAll", query = "SELECT u FROM GrupoRole u")
public class GrupoRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_GRUPO_ROLE_GPRID_GENERATOR", sequenceName = "ADMIN_GRUPO_ROLE_GPR_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_GRUPO_ROLE_GPRID_GENERATOR")
	@Column(name = "adm_gpr_id", nullable = true)
	private Integer gprId;

	@ManyToOne
	private Grupo gprGrupo;

	@ManyToOne
	private Roles gprRole;

	public GrupoRole() {
	}

	public GrupoRole(Integer gprId) {
		this.gprId = gprId;

	}
	
	public GrupoRole(Integer gprId, Integer rolId) {
		this.gprId = gprId;

		Roles rol = new Roles();
		rol.setRolId(rolId);
		this.gprRole = rol;

	}
	
	
	public GrupoRole(String rolNome) {
		Roles rol = new Roles();
		rol.setRolNome(rolNome);
		this.gprRole = rol;
	}
	

	public GrupoRole(Integer gprId, Integer gpoId, String gpoDescricao,
			Integer rolId, String rolDescricao) {
		this.gprId = gprId;

		Grupo gpo = new Grupo();
		gpo.setGpoId(gpoId);
		gpo.setGpoDescricao(gpoDescricao);

		Roles rol = new Roles();
		rol.setRolId(rolId);
		rol.setRolDescricao(rolDescricao);

		this.gprGrupo = gpo;
		this.gprRole = rol;

	}

	/**
	 * @return the gprId
	 */
	public Integer getGprId() {
		return gprId;
	}

	/**
	 * @param gprId
	 *            the gprId to set
	 */
	public void setGprId(Integer gprId) {
		this.gprId = gprId;
	}

	/**
	 * @return the gprGrupo
	 */
	public Grupo getGprGrupo() {
		return gprGrupo;
	}

	/**
	 * @param gprGrupo
	 *            the gprGrupo to set
	 */
	public void setGprGrupo(Grupo gprGrupo) {
		this.gprGrupo = gprGrupo;
	}

	/**
	 * @return the gprRole
	 */
	public Roles getGprRole() {
		return gprRole;
	}

	/**
	 * @param gprRole
	 *            the gprRole to set
	 */
	public void setGprRole(Roles gprRole) {
		this.gprRole = gprRole;
	}

}
