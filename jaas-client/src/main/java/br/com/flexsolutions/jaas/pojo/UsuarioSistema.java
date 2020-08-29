/**  
 * admin-ejb - UsuarioSistema.java
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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe que reprensenta o vinculo de usuario com sistema.
 * 
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_USUARIO_SISTEMA")
@NamedQuery(name = "UsuarioSistema.findAll", query = "SELECT u FROM UsuarioSistema u")
public class UsuarioSistema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_USUARIO_SISTEMA_USIID_GENERATOR", sequenceName = "ADMIN_USUARIO_SISTEMA_USI_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_USUARIO_SISTEMA_USIID_GENERATOR")
	@Column(name = "adm_usi_id", nullable = true)
	private Integer usiId;

	@ManyToOne
	private Sistema usiSistema;

	@ManyToOne
	private Usuario usiUsuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usgUsuarioSistema")
	private List<UsuarioSistemaGrupo> usiUsuarioSistemaGrupo;

	@Column(name = "adm_usi_tipo_usuario")
	private Character usiTipoUsuario;

	@Column(name = "adm_usi_sem_restricao")
	private Boolean usiSemRestricao;

	public UsuarioSistema() {
	}

	public UsuarioSistema(Integer usiId) {
		this.usiId = usiId;
	}

	public UsuarioSistema(Integer usiId, Integer usuId) {
		this.usiId = usiId;
		
		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		
		this.usiUsuario = usuario;
	}
	

	public UsuarioSistema(Integer usiId, Integer usuId, String usuNome) {
		this.usiId = usiId;

		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		usuario.setUsuNome(usuNome);

		this.usiUsuario = usuario;
	}
	
	public UsuarioSistema(Integer usiId, Integer sisId, String sisNome,
			Integer usuId, String usuNome) {
		this.usiId = usiId;

		Sistema sistema = new Sistema();
		sistema.setSisId(sisId);
		sistema.setSisNome(sisNome);

		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		usuario.setUsuNome(usuNome);

		this.usiSistema = sistema;
		this.usiUsuario = usuario;
	}

	/**
	 * @return the usiId
	 */
	public Integer getUsiId() {
		return usiId;
	}

	/**
	 * @param usiId
	 *            the usiId to set
	 */
	public void setUsiId(Integer usiId) {
		this.usiId = usiId;
	}

	/**
	 * @return the usiSistema
	 */
	public Sistema getUsiSistema() {
		return usiSistema;
	}

	/**
	 * @param usiSistema
	 *            the usiSistema to set
	 */
	public void setUsiSistema(Sistema usiSistema) {
		this.usiSistema = usiSistema;
	}

	/**
	 * @return the usiUsuario
	 */
	public Usuario getUsiUsuario() {
		return usiUsuario;
	}

	/**
	 * @param usiUsuario
	 *            the usiUsuario to set
	 */
	public void setUsiUsuario(Usuario usiUsuario) {
		this.usiUsuario = usiUsuario;
	}

	/**
	 * @return the usiUsuarioSistemaGrupo
	 */
	public List<UsuarioSistemaGrupo> getUsiUsuarioSistemaGrupo() {
		return usiUsuarioSistemaGrupo;
	}

	/**
	 * @param usiUsuarioSistemaGrupo
	 *            the usiUsuarioSistemaGrupo to set
	 */
	public void setUsiUsuarioSistemaGrupo(
			List<UsuarioSistemaGrupo> usiUsuarioSistemaGrupo) {
		this.usiUsuarioSistemaGrupo = usiUsuarioSistemaGrupo;
	}

	/**
	 * @return the usiTipoUsuario
	 */
	public Character getUsiTipoUsuario() {
		return usiTipoUsuario;
	}

	/**
	 * @param usiTipoUsuario
	 *            the usiTipoUsuario to set
	 */
	public void setUsiTipoUsuario(Character usiTipoUsuario) {
		this.usiTipoUsuario = usiTipoUsuario;
	}

	/**
	 * @return the usiSemRestricao
	 */
	public Boolean getUsiSemRestricao() {
		return usiSemRestricao;
	}

	/**
	 * @param usiSemRestricao
	 *            the usiSemRestricao to set
	 */
	public void setUsiSemRestricao(Boolean usiSemRestricao) {
		this.usiSemRestricao = usiSemRestricao;
	}

}
