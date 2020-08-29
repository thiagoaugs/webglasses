/**  
 * admin-war - SistemaViewBean.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control.viewBean;

import br.com.flexsolutions.admin.control.viewparam.SistemaDataModel;
import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class SistemaViewBean {

	// instancia do datamodel
	private SistemaDataModel sistemaDataModel;

	// string para a barra de pesquisa de tipo sistema
	private String stringPesquisa;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular os valores do objeto
	private SistemaTO sistemaTO;

	public SistemaViewBean() {
		initValues();
	}

	public void initValues() {
		setStringPesquisa("");
		habilitarEdicao = false;
		habilitarVisualizacao = false;
		sistemaTO = new SistemaTO();

	}

	/**
	 * @return the habilitarEdicao
	 */
	public boolean isHabilitarEdicao() {
		return habilitarEdicao;
	}

	/**
	 * @param habilitarEdicao
	 *            the habilitarEdicao to set
	 */
	public void setHabilitarEdicao(boolean habilitarEdicao) {
		this.habilitarEdicao = habilitarEdicao;
	}

	/**
	 * @return the habilitarVisualizacao
	 */
	public boolean isHabilitarVisualizacao() {
		return habilitarVisualizacao;
	}

	/**
	 * @param habilitarVisualizacao
	 *            the habilitarVisualizacao to set
	 */
	public void setHabilitarVisualizacao(boolean habilitarVisualizacao) {
		this.habilitarVisualizacao = habilitarVisualizacao;
	}

	/**
	 * @return the sistemaTO
	 */
	public SistemaTO getSistemaTO() {
		return sistemaTO;
	}

	/**
	 * @param sistemaTO
	 *            the sistemaTO to set
	 */
	public void setSistemaTO(SistemaTO sistemaTO) {
		this.sistemaTO = sistemaTO;
	}

	/**
	 * @return the sistemaDataModel
	 */
	public SistemaDataModel getSistemaDataModel() {
		return sistemaDataModel;
	}

	/**
	 * @param sistemaDataModel
	 *            the sistemaDataModel to set
	 */
	public void setSistemaDataModel(SistemaDataModel sistemaDataModel) {
		this.sistemaDataModel = sistemaDataModel;
	}

	/**
	 * @return the stringPesquisa
	 */
	public String getStringPesquisa() {
		return stringPesquisa;
	}

	/**
	 * @param stringPesquisa
	 *            the stringPesquisa to set
	 */
	public void setStringPesquisa(String stringPesquisa) {
		this.stringPesquisa = stringPesquisa;
	}

}
