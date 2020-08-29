/**  
 * jass-ejb - TelaMenu.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Classe que representa as telas do sistema para que seja montado o menu
 * dinamicamente.
 * 
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_TELA_MENU")
@NamedQuery(name = "TelaMenu.findAll", query = "SELECT u FROM TelaMenu u")
public class TelaMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_TELA_MENU_TMEID_GENERATOR", sequenceName = "ADMIN_TELA_MENU_TME_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_TELA_MENU_TMEID_GENERATOR")
	@Column(name = "adm_tme_id", nullable = true)
	private Integer tmeId;

	@Column(name = "adm_tme_legenda", nullable = false, columnDefinition = "varchar(100)")
	private String tmeLegenda;

	@Column(name = "adm_tme_acao", nullable = false, columnDefinition = "varchar(100)")
	private String tmeAcao;

	@Column(name = "adm_tme_nivel")
	private Integer tmeNivel;

	@Transient
	private Boolean tmeAtivado = Boolean.valueOf(false);

	@Column(name = "adm_tme_utilizado")
	private Boolean tmeUtilizado = Boolean.valueOf(false);

	@Column(name = "adm_tme_ordem")
	private Integer tmeOrdem;

	@Column(name = "adm_tme_camada", columnDefinition = "varchar(50)")
	private String tmeCamada;

	@ManyToOne
	@JoinColumn(name = "adm_tme_menu_superior")
	private TelaMenu tmeMenuSuperior;

	@ManyToOne
	private Sistema tmeSistema;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tmeMenuSuperior", targetEntity = TelaMenu.class)
	@OrderBy("tmeOrdem")
	private Set<TelaMenu> tmeMenuFilhos;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tmgTelaMenu")
	private Set<TelaMenuGrupo> tmeSistemaTelaMenuGrupo;

	public TelaMenu() {
	}


	public TelaMenu(Integer tmeId, String tmeLegenda) {
		this.tmeId = tmeId;
		this.tmeLegenda = tmeLegenda;
	}

	/**
	 * @return the tmeId
	 */
	public Integer getTmeId() {
		return tmeId;
	}

	/**
	 * @param tmeId
	 *            the tmeId to set
	 */
	public void setTmeId(Integer tmeId) {
		this.tmeId = tmeId;
	}

	/**
	 * @return the tmeLegenda
	 */
	public String getTmeLegenda() {
		return tmeLegenda;
	}

	/**
	 * @param tmeLegenda
	 *            the tmeLegenda to set
	 */
	public void setTmeLegenda(String tmeLegenda) {
		this.tmeLegenda = tmeLegenda;
	}

	/**
	 * @return the tmeAcao
	 */
	public String getTmeAcao() {
		return tmeAcao;
	}

	/**
	 * @param tmeAcao
	 *            the tmeAcao to set
	 */
	public void setTmeAcao(String tmeAcao) {
		this.tmeAcao = tmeAcao;
	}

	/**
	 * @return the tmeNivel
	 */
	public Integer getTmeNivel() {
		return tmeNivel;
	}

	/**
	 * @param tmeNivel
	 *            the tmeNivel to set
	 */
	public void setTmeNivel(Integer tmeNivel) {
		this.tmeNivel = tmeNivel;
	}

	/**
	 * @return the tmeAtivado
	 */
	public Boolean getTmeAtivado() {
		return tmeAtivado;
	}

	/**
	 * @param tmeAtivado
	 *            the tmeAtivado to set
	 */
	public void setTmeAtivado(Boolean tmeAtivado) {
		this.tmeAtivado = tmeAtivado;
	}

	/**
	 * @return the tmeUtilizado
	 */
	public Boolean getTmeUtilizado() {
		return tmeUtilizado;
	}

	/**
	 * @param tmeUtilizado
	 *            the tmeUtilizado to set
	 */
	public void setTmeUtilizado(Boolean tmeUtilizado) {
		this.tmeUtilizado = tmeUtilizado;
	}

	/**
	 * @return the tmeOrdem
	 */
	public Integer getTmeOrdem() {
		return tmeOrdem;
	}

	/**
	 * @param tmeOrdem
	 *            the tmeOrdem to set
	 */
	public void setTmeOrdem(Integer tmeOrdem) {
		this.tmeOrdem = tmeOrdem;
	}

	/**
	 * @return the tmeCamada
	 */
	public String getTmeCamada() {
		return tmeCamada;
	}

	/**
	 * @param tmeCamada
	 *            the tmeCamada to set
	 */
	public void setTmeCamada(String tmeCamada) {
		this.tmeCamada = tmeCamada;
	}

	/**
	 * @return the tmeSuperior
	 */
	public TelaMenu getTmeSuperior() {
		return tmeMenuSuperior;
	}

	/**
	 * @param tmeSuperior
	 *            the tmeSuperior to set
	 */
	public void setTmeSuperior(TelaMenu tmeSuperior) {
		this.tmeMenuSuperior = tmeSuperior;
	}

	/**
	 * @return the tmeSistema
	 */
	public Sistema getTmeSistema() {
		return tmeSistema;
	}

	/**
	 * @param tmeSistema
	 *            the tmeSistema to set
	 */
	public void setTmeSistema(Sistema tmeSistema) {
		this.tmeSistema = tmeSistema;
	}

	/**
	 * @return the tmeFilhos
	 */
	public Set<TelaMenu> getTmeFilhos() {
		return tmeMenuFilhos;
	}

	/**
	 * @param tmeFilhos
	 *            the tmeFilhos to set
	 */
	public void setTmeFilhos(Set<TelaMenu> tmeFilhos) {
		this.tmeMenuFilhos = tmeFilhos;
	}

	/**
	 * @return the sistemaTelaMenuGrupo
	 */
	public Set<TelaMenuGrupo> getSistemaTelaMenuGrupo() {
		return tmeSistemaTelaMenuGrupo;
	}

	/**
	 * @param sistemaTelaMenuGrupo
	 *            the sistemaTelaMenuGrupo to set
	 */
	public void setSistemaTelaMenuGrupo(Set<TelaMenuGrupo> sistemaTelaMenuGrupo) {
		this.tmeSistemaTelaMenuGrupo = sistemaTelaMenuGrupo;
	}

}
