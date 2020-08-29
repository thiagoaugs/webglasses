/**  
 * admin-war - PessoaViewBean.java
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

import br.com.flexsolutions.admin.control.viewparam.PessoaDataModel;
import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class PessoaViewBean {

	// instancia do datamodel
	private PessoaDataModel pessoaDataModel;

	// string para a barra de pesquisa de tipo pessoa
	private String stringPesquisa;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;

	// para popular os valores do objeto
	private PessoaTO pessoaTO;

	public PessoaViewBean() {
		initValues();
	}

	public void initValues() {
		setStringPesquisa("");
		habilitarEdicao = false;
		habilitarVisualizacao = false;
		pessoaTO = new PessoaTO();
	}

	/**
	 * @return the pessoaDataModel
	 */
	public PessoaDataModel getPessoaDataModel() {
		return pessoaDataModel;
	}

	/**
	 * @param pessoaDataModel
	 *            the pessoaDataModel to set
	 */
	public void setPessoaDataModel(PessoaDataModel pessoaDataModel) {
		this.pessoaDataModel = pessoaDataModel;
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
	 * @return the pessoaTO
	 */
	public PessoaTO getPessoaTO() {
		return pessoaTO;
	}

	/**
	 * @param pessoaTO
	 *            the pessoaTO to set
	 */
	public void setPessoaTO(PessoaTO pessoaTO) {
		this.pessoaTO = pessoaTO;
	}

}
