/**  
 * admin-war - TelaMenuViewBean.java
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

import br.com.flexsolutions.admin.control.viewparam.TelaMenuDataModel;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuViewBean {

	// instancia do datamodel
	private TelaMenuDataModel telaMenuDataModel;

	// string para a barra de pesquisa de tipo telaMenu
	private String stringPesquisa;
	private SistemaTO sistemaSelecionado;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular os valores do objeto
	private TelaMenuTO telaMenuTO;

	//para popular as combos
	private List<SistemaTO> listaSistemaTO;
	private List<TelaMenuTO> listaTelaMenuTO;
	
	public TelaMenuViewBean() {
		initValues();
	}

	public void initValues() {
		habilitarEdicao = false;
		habilitarVisualizacao = false;
		
		telaMenuTO = new TelaMenuTO();
		telaMenuTO.setTmeSistemaTO(new SistemaTO());
		telaMenuTO.setTmeMenuSuperior(new TelaMenuTO());

		stringPesquisa = new String();
		setSistemaSelecionado(new SistemaTO());
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
	 * @return the telaMenuTO
	 */
	public TelaMenuTO getTelaMenuTO() {
		return telaMenuTO;
	}

	/**
	 * @param telaMenuTO
	 *            the telaMenuTO to set
	 */
	public void setTelaMenuTO(TelaMenuTO telaMenuTO) {
		this.telaMenuTO = telaMenuTO;
	}

	/**
	 * @return the telaMenuDataModel
	 */
	public TelaMenuDataModel getTelaMenuDataModel() {
		return telaMenuDataModel;
	}

	/**
	 * @param telaMenuDataModel
	 *            the telaMenuDataModel to set
	 */
	public void setTelaMenuDataModel(TelaMenuDataModel telaMenuDataModel) {
		this.telaMenuDataModel = telaMenuDataModel;
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
	 * @param listaSistemaTO
	 *            the listaSistemaTO to set
	 */
	public void setListaSistemaTO(List<SistemaTO> listaSistemaTO) {
		this.listaSistemaTO = listaSistemaTO;
	}

	/**
	 * @return the listaTelaMenuTO
	 */
	public List<TelaMenuTO> getListaTelaMenuTO() {
		return listaTelaMenuTO;
	}

	/**
	 * @param listaTelaMenuTO the listaTelaMenuTO to set
	 */
	public void setListaTelaMenuTO(List<TelaMenuTO> listaTelaMenuTO) {
		this.listaTelaMenuTO = listaTelaMenuTO;
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
