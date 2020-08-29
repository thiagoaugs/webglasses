/**  
 * admin-ejb - TelaMenuGrupo.java
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
 * Classe que representa o vinculo de TelaMenu com o Grupo
 * 
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_TELA_MENU_GRUPO")
@NamedQuery(name = "TelaMenuGrupo.findAll", query = "SELECT u FROM TelaMenuGrupo u")
public class TelaMenuGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_TELA_MENU_GRUPO_TMGID_GENERATOR", sequenceName = "ADMIN_TELA_MENU_GRUPO_TMG_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_TELA_MENU_GRUPO_TMGID_GENERATOR")
	@Column(name = "adm_tmg_id", nullable = true)
	private Integer tmgId;

	@ManyToOne
	private Grupo tmgGrupo;

	@ManyToOne
	private TelaMenu tmgTelaMenu;

	public TelaMenuGrupo() {
	}

	public TelaMenuGrupo(Integer tmgId) {
		this.tmgId = tmgId;
	}

	public TelaMenuGrupo(Integer tmgId, Integer tmeId) {
		this.tmgId = tmgId;

		TelaMenu tme = new TelaMenu();
		tme.setTmeId(tmeId);
		this.tmgTelaMenu = tme;
	}

	public TelaMenuGrupo(Integer tmgId, Integer tmeId, String tmeAcao,
			String tmeCamada, String tmeLegenda, Integer tmeNivel,
			Integer tmeOrdem, Integer gpoId, String gpoNome) {
		this.tmgId = tmgId;

		TelaMenu tme = new TelaMenu();
		tme.setTmeId(tmeId);
		tme.setTmeAcao(tmeAcao);
		tme.setTmeCamada(tmeCamada);
		tme.setTmeLegenda(tmeLegenda);
		tme.setTmeNivel(tmeNivel);
		tme.setTmeOrdem(tmeOrdem);
		this.tmgTelaMenu = tme;
		
		Grupo gpo = new Grupo();
		gpo.setGpoId(gpoId);
		gpo.setGpoNome(gpoNome);
		this.tmgGrupo = gpo;
		
		
		// TelaMenu tmeSuperior = new TelaMenu();
		// tmeSuperior.setTmeId(tmeSuperiorId);
		//
		// tme.setTmeSuperior(tmeSuperior);
		
	
	}

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
	public Grupo getTmgGrupo() {
		return tmgGrupo;
	}

	/**
	 * @param tmgGrupo
	 *            the tmgGrupo to set
	 */
	public void setTmgGrupo(Grupo tmgGrupo) {
		this.tmgGrupo = tmgGrupo;
	}

	/**
	 * @return the tmgTelaMenu
	 */
	public TelaMenu getTmgTelaMenu() {
		return tmgTelaMenu;
	}

	/**
	 * @param tmgTelaMenu
	 *            the tmgTelaMenu to set
	 */
	public void setTmgTelaMenu(TelaMenu tmgTelaMenu) {
		this.tmgTelaMenu = tmgTelaMenu;
	}

}
