/**  
 * admin-war - GrupoRoleViewBean.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control.viewBean;

import java.util.ArrayList;
import java.util.List;

import br.com.flexsolutions.admin.control.viewparam.GrupoRoleDataModel;
import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoRoleViewBean {

	// instancia do datamodel
	private GrupoRoleDataModel grupoRoleDataModel;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;
	private List<GrupoTO> listaGrupoTO;
	private List<RolesTO> listaRoleTO;

	// pesquisa
	private List<GrupoTO> listaPesqGrupoTO;
	private List<RolesTO> listaPesqRoleTO;

	// para selecionar as combo pesquisa
	private SistemaTO sistemaSelecionado;
	private GrupoTO grupoSelecionado;
	private RolesTO roleSelecionada;

	// para popular os valores do objeto
	private GrupoRoleTO grupoRoleTO;

	// para fazer a pesquisa tipada por sistema
	private SistemaTO sistemaTO;

	public GrupoRoleViewBean() {
		initValues();
	}

	public void initValues() {
		habilitarEdicao = false;

		grupoRoleTO = new GrupoRoleTO();
		grupoRoleTO.setGprGrupo(new GrupoTO());
		grupoRoleTO.getGprGrupo().setGpoSistemaTO(new SistemaTO());
		grupoRoleTO.setGprRole(new RolesTO());

		limparFiltros();
	}

	public void limparFiltros() {
		sistemaSelecionado = new SistemaTO();
		grupoSelecionado = new GrupoTO();
		roleSelecionada = new RolesTO();
		
		listaPesqGrupoTO = new ArrayList<GrupoTO>();
		listaPesqRoleTO = new ArrayList<RolesTO>();
		
		listaGrupoTO = new ArrayList<GrupoTO>();
		listaRoleTO = new ArrayList<RolesTO>();
		
	}

	/**
	 * @return the grupoRoleDataModel
	 */
	public GrupoRoleDataModel getGrupoRoleDataModel() {
		return grupoRoleDataModel;
	}

	/**
	 * @param grupoRoleDataModel
	 *            the grupoRoleDataModel to set
	 */
	public void setGrupoRoleDataModel(GrupoRoleDataModel grupoRoleDataModel) {
		this.grupoRoleDataModel = grupoRoleDataModel;
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
	 * @return the listaGrupoTO
	 */
	public List<GrupoTO> getListaGrupoTO() {
		return listaGrupoTO;
	}

	/**
	 * @param listaGrupoTO
	 *            the listaGrupoTO to set
	 */
	public void setListaGrupoTO(List<GrupoTO> listaGrupoTO) {
		this.listaGrupoTO = listaGrupoTO;
	}

	/**
	 * @return the listaRoleTO
	 */
	public List<RolesTO> getListaRoleTO() {
		return listaRoleTO;
	}

	/**
	 * @param listaRoleTO
	 *            the listaRoleTO to set
	 */
	public void setListaRoleTO(List<RolesTO> listaRoleTO) {
		this.listaRoleTO = listaRoleTO;
	}

	/**
	 * @return the grupoRoleTO
	 */
	public GrupoRoleTO getGrupoRoleTO() {
		return grupoRoleTO;
	}

	/**
	 * @param grupoRoleTO
	 *            the grupoRoleTO to set
	 */
	public void setGrupoRoleTO(GrupoRoleTO grupoRoleTO) {
		this.grupoRoleTO = grupoRoleTO;
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
	 * @return the sistemaSelecionado
	 */
	public SistemaTO getSistemaSelecionado() {
		return sistemaSelecionado;
	}

	/**
	 * @param sistemaSelecionado
	 *            the sistemaSelecionado to set
	 */
	public void setSistemaSelecionado(SistemaTO sistemaSelecionado) {
		this.sistemaSelecionado = sistemaSelecionado;
	}

	/**
	 * @return the grupoSelecionado
	 */
	public GrupoTO getGrupoSelecionado() {
		return grupoSelecionado;
	}

	/**
	 * @param grupoSelecionado
	 *            the grupoSelecionado to set
	 */
	public void setGrupoSelecionado(GrupoTO grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	/**
	 * @return the roleSelecionada
	 */
	public RolesTO getRoleSelecionada() {
		return roleSelecionada;
	}

	/**
	 * @param roleSelecionada
	 *            the roleSelecionada to set
	 */
	public void setRoleSelecionada(RolesTO roleSelecionada) {
		this.roleSelecionada = roleSelecionada;
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
	 * @return the listaPesqGrupoTO
	 */
	public List<GrupoTO> getListaPesqGrupoTO() {
		return listaPesqGrupoTO;
	}

	/**
	 * @param listaPesqGrupoTO
	 *            the listaPesqGrupoTO to set
	 */
	public void setListaPesqGrupoTO(List<GrupoTO> listaPesqGrupoTO) {
		this.listaPesqGrupoTO = listaPesqGrupoTO;
	}

	/**
	 * @return the listaPesqRoleTO
	 */
	public List<RolesTO> getListaPesqRoleTO() {
		return listaPesqRoleTO;
	}

	/**
	 * @param listaPesqRoleTO
	 *            the listaPesqRoleTO to set
	 */
	public void setListaPesqRoleTO(List<RolesTO> listaPesqRoleTO) {
		this.listaPesqRoleTO = listaPesqRoleTO;
	}

}
