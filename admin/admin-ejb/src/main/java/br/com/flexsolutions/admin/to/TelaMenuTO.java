/**  
 * jass-ejb - TelaMenuTO.java
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
import java.util.Set;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer tmeId;

	private String tmeLegenda;

	private String tmeAcao;

	private Integer tmeNivel;

	private Boolean tmeAtivado = Boolean.valueOf(false);

	private Boolean tmeUtilizado = Boolean.valueOf(false);

	private Integer tmeOrdem;

	private String tmeCamada;

	private TelaMenuTO tmeMenuSuperior;

	private SistemaTO tmeSistemaTO;

	private Set<TelaMenuTO> tmeMenuFilhos;

	private Set<TelaMenuGrupoTO> sistemaTelaMenuGrupo;

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
	 * @return the tmeMenuSuperior
	 */
	public TelaMenuTO getTmeMenuSuperior() {
		return tmeMenuSuperior;
	}

	/**
	 * @param tmeMenuSuperior
	 *            the tmeMenuSuperior to set
	 */
	public void setTmeMenuSuperior(TelaMenuTO tmeMenuSuperior) {
		this.tmeMenuSuperior = tmeMenuSuperior;
	}

	/**
	 * @return the tmeSistemaTO
	 */
	public SistemaTO getTmeSistemaTO() {
		return tmeSistemaTO;
	}

	/**
	 * @param tmeSistemaTO
	 *            the tmeSistemaTO to set
	 */
	public void setTmeSistemaTO(SistemaTO tmeSistemaTO) {
		this.tmeSistemaTO = tmeSistemaTO;
	}

	/**
	 * @return the tmeMenuFilhos
	 */
	public Set<TelaMenuTO> getTmeMenuFilhos() {
		return tmeMenuFilhos;
	}

	/**
	 * @param tmeMenuFilhos
	 *            the tmeMenuFilhos to set
	 */
	public void setTmeMenuFilhos(Set<TelaMenuTO> tmeMenuFilhos) {
		this.tmeMenuFilhos = tmeMenuFilhos;
	}

	/**
	 * @return the sistemaTelaMenuGrupo
	 */
	public Set<TelaMenuGrupoTO> getSistemaTelaMenuGrupo() {
		return sistemaTelaMenuGrupo;
	}

	/**
	 * @param sistemaTelaMenuGrupo
	 *            the sistemaTelaMenuGrupo to set
	 */
	public void setSistemaTelaMenuGrupo(
			Set<TelaMenuGrupoTO> sistemaTelaMenuGrupo) {
		this.sistemaTelaMenuGrupo = sistemaTelaMenuGrupo;
	}

}
