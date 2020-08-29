/**  
 * admin-ejb - UsuarioSistemaGrupo.java
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
 * Classe que reprensenta o vinculo de UsuarioSistema com o grupo.
 * 
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_USUARIO_SISTEMA_GRUPO")
@NamedQuery(name = "UsuarioSistemaGrupo.findAll", query = "SELECT u FROM UsuarioSistemaGrupo u")
public class UsuarioSistemaGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_USUARIO_SISTEMA_GRUPO_USGID_GENERATOR", sequenceName = "ADMIN_USUARIO_SISTEMA_GRUPO_USG_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_USUARIO_SISTEMA_GRUPO_USGID_GENERATOR")
	@Column(name = "adm_usg_id", nullable = true)
	private Integer usgId;

	@ManyToOne
	private UsuarioSistema usgUsuarioSistema;

	@ManyToOne
	private Grupo usgGrupo;

	public UsuarioSistemaGrupo() {
	}

	public UsuarioSistemaGrupo(Integer usgId) {
		this.usgId = usgId;
	}
	
	public UsuarioSistemaGrupo(Integer usgId, Integer usuId) {
		this.usgId = usgId;
		
		UsuarioSistema usi = new UsuarioSistema();
		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		
		usi.setUsiUsuario(usuario);
		
		this.usgUsuarioSistema = usi;
	}

	
//	public UsuarioSistemaGrupo(Integer usgId, Integer gpoId) {
//		this.usgId = usgId;
//		
//		Grupo gpo = new Grupo();
//		gpo.setGpoId(gpoId);
//		
//		this.usgGrupo = gpo;
//		
//	}

	public UsuarioSistemaGrupo(Integer usgId, Integer gpoId, String gpoNome,
			Integer usiId, Integer sisId, String sisNome, Integer usuId,
			String usuNome) {

		this.usgId = usgId;

		Grupo grupo = new Grupo();
		grupo.setGpoId(gpoId);
		grupo.setGpoNome(gpoNome);

		this.usgGrupo = grupo;

		UsuarioSistema usi = new UsuarioSistema();

		usi.setUsiId(usiId);
		
		Sistema sistema = new Sistema();
		sistema.setSisId(sisId);
		sistema.setSisNome(sisNome);
		
		Usuario usuario = new Usuario();
		usuario.setUsuId(usuId);
		usuario.setUsuNome(usuNome);

		usi.setUsiUsuario(usuario);
		usi.setUsiSistema(sistema);
		
		this.usgUsuarioSistema = usi;
	}

	/**
	 * @return the usgId
	 */
	public Integer getUsgId() {
		return usgId;
	}

	/**
	 * @param usgId
	 *            the usgId to set
	 */
	public void setUsgId(Integer usgId) {
		this.usgId = usgId;
	}

	/**
	 * @return the usgUsuarioSistema
	 */
	public UsuarioSistema getUsgUsuarioSistema() {
		return usgUsuarioSistema;
	}

	/**
	 * @param usgUsuarioSistema
	 *            the usgUsuarioSistema to set
	 */
	public void setUsgUsuarioSistema(UsuarioSistema usgUsuarioSistema) {
		this.usgUsuarioSistema = usgUsuarioSistema;
	}

	/**
	 * @return the usgGrupo
	 */
	public Grupo getUsgGrupo() {
		return usgGrupo;
	}

	/**
	 * @param usgGrupo
	 *            the usgGrupo to set
	 */
	public void setUsgGrupo(Grupo usgGrupo) {
		this.usgGrupo = usgGrupo;
	}

}
