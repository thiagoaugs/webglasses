/**  
 * jaas-war - GrupoViewBean.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control.viewBean;

import java.util.List;

import br.com.flexsolutions.admin.control.viewparam.GrupoDataModel;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoViewBean {

	// instancia do datamodel
	private GrupoDataModel grupoDataModel;

	// string para a barra de pesquisa de tipo grupo
	private String stringPesquisa;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;
	
	private SistemaTO sistemaSelecionado;

	// para popular os valores do objeto
	private GrupoTO grupoTO;

	public GrupoViewBean() {
		initValues();
	}

	public void initValues() {
	
		habilitarEdicao = false;
		habilitarVisualizacao = false;
		
		grupoTO = new GrupoTO();
		grupoTO.setGpoSistemaTO(new SistemaTO());
		
		stringPesquisa = new String();
		sistemaSelecionado = new SistemaTO();
		
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
	 * @return the grupoTO
	 */
	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	/**
	 * @param grupoTO
	 *            the grupoTO to set
	 */
	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}

	/**
	 * @return the grupoDataModel
	 */
	public GrupoDataModel getGrupoDataModel() {
		return grupoDataModel;
	}

	/**
	 * @param grupoDataModel
	 *            the grupoDataModel to set
	 */
	public void setGrupoDataModel(GrupoDataModel grupoDataModel) {
		this.grupoDataModel = grupoDataModel;
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

	/**
	 * @return the listaSistemaTO
	 */
	public List<SistemaTO> getListaSistemaTO() {
		return listaSistemaTO;
	}

	/**
	 * @param listaSistemaTO the listaSistemaTO to set
	 */
	public void setListaSistemaTO(List<SistemaTO> listaSistemaTO) {
		this.listaSistemaTO = listaSistemaTO;
	}

	/**
	 * @return the sistemaSelecionado
	 */
	public SistemaTO getSistemaSelecionado() {
		return sistemaSelecionado;
	}

	/**
	 * @param sistemaSelecionado the sistemaSelecionado to set
	 */
	public void setSistemaSelecionado(SistemaTO sistemaSelecionado) {
		this.sistemaSelecionado = sistemaSelecionado;
	}

}
