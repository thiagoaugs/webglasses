/**  
 * admin-war - RolesViewBean.java
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

import br.com.flexsolutions.admin.control.viewparam.RolesDataModel;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class RolesViewBean {

	// instancia do datamodel
	private RolesDataModel rolesDataModel;

	// string para a barra de pesquisa de tipo grupo
	private String stringPesquisa;
	private SistemaTO sistemaSelecionado;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;

	// para popular os valores do objeto
	private RolesTO rolesTO;

	public RolesViewBean() {
		initValues();
	}

	public void initValues() {
		
		habilitarEdicao = false;
		habilitarVisualizacao = false;
	
		rolesTO = new RolesTO();
		rolesTO.setRolSistemaTO(new SistemaTO());

		setStringPesquisa("");
		setSistemaSelecionado(new SistemaTO());
		
	}

	/**
	 * @return the rolesDataModel
	 */
	public RolesDataModel getRolesDataModel() {
		return rolesDataModel;
	}

	/**
	 * @param rolesDataModel the rolesDataModel to set
	 */
	public void setRolesDataModel(RolesDataModel rolesDataModel) {
		this.rolesDataModel = rolesDataModel;
	}

	/**
	 * @return the stringPesquisa
	 */
	public String getStringPesquisa() {
		return stringPesquisa;
	}

	/**
	 * @param stringPesquisa the stringPesquisa to set
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
	 * @param habilitarEdicao the habilitarEdicao to set
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
	 * @param habilitarVisualizacao the habilitarVisualizacao to set
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
	 * @param listaSistemaTO the listaSistemaTO to set
	 */
	public void setListaSistemaTO(List<SistemaTO> listaSistemaTO) {
		this.listaSistemaTO = listaSistemaTO;
	}

	/**
	 * @return the rolesTO
	 */
	public RolesTO getRolesTO() {
		return rolesTO;
	}

	/**
	 * @param rolesTO the rolesTO to set
	 */
	public void setRolesTO(RolesTO rolesTO) {
		this.rolesTO = rolesTO;
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
