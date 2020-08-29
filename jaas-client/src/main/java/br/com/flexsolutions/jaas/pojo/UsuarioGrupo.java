/**  
 * admin-ejb  - UsuarioGrupo.java
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_USUARIO_GRUPO")
@NamedQuery(name = "UsuarioGrupo.findAll", query = "SELECT u FROM UsuarioGrupo u")
public class UsuarioGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_USUARIOGRUPO_UGPID_GENERATOR", sequenceName = "ADMIN_USUARIOGRUPO_UGP_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_USUARIOGRUPO_UGPID_GENERATOR")
	@Column(name = "ugp_id")
	private Integer ugpId;

	@ManyToOne
	@JoinColumn(name = "usu_id")
	private Usuario ugpUsuario;

	@ManyToOne
	@JoinColumn(name = "gpo_id")
	private Grupo ugpGrupo;

	public UsuarioGrupo() {
	}

	public UsuarioGrupo(Integer ugpId) {
		this.ugpId = ugpId;
	}

	public UsuarioGrupo(Integer ugpId, Integer usuId, String usuLogin,
			String usuEmail) {
	}

	public UsuarioGrupo(Integer ugpId, Integer gpoId, String gpoNome,
			Integer usuId, String usuNome) {

		this.ugpId = ugpId;

		Grupo grupo = new Grupo();
		grupo.setGpoId(gpoId);
		grupo.setGpoNome(gpoNome);

		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		usuario.setUsuNome(usuNome);

		this.ugpGrupo = grupo;
		this.ugpUsuario = usuario;

	}

	/**
	 * @return the ugpId
	 */
	public Integer getUgpId() {
		return ugpId;
	}

	/**
	 * @param ugpId
	 *            the ugpId to set
	 */
	public void setUgpId(Integer ugpId) {
		this.ugpId = ugpId;
	}

	/**
	 * @return the ugpUsuario
	 */
	public Usuario getUgpUsuario() {
		return ugpUsuario;
	}

	/**
	 * @param ugpUsuario
	 *            the ugpUsuario to set
	 */
	public void setUgpUsuario(Usuario ugpUsuario) {
		this.ugpUsuario = ugpUsuario;
	}

	/**
	 * @return the ugpGrupo
	 */
	public Grupo getUgpGrupo() {
		return ugpGrupo;
	}

	/**
	 * @param ugpGrupo
	 *            the ugpGrupo to set
	 */
	public void setUgpGrupo(Grupo ugpGrupo) {
		this.ugpGrupo = ugpGrupo;
	}

}
