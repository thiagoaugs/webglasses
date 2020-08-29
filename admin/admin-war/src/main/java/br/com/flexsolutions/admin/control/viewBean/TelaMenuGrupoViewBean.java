/**  
 * admin-war - TelaMenuGrupoViewBean.java
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

import br.com.flexsolutions.admin.control.viewparam.TelaMenuGrupoDataModel;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuGrupoViewBean {

	// instancia do datamodel
	private TelaMenuGrupoDataModel tmgDataModel;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;
	private List<GrupoTO> listaGrupoTO;
	private List<TelaMenuTO> listaTelaMenuTO;

	// pesquisa
	private List<GrupoTO> listaPesqGrupoTO;
	private List<TelaMenuTO> listaPesqTelaMenuTO;

	// para selecionar as combo pesquisa
	private SistemaTO sistemaSelecionado;
	private GrupoTO grupoSelecionado;
	private TelaMenuTO telaMenuSelecionada;

	// para popular os valores do objeto
	private TelaMenuGrupoTO tmgTO;

	// para fazer a pesquisa tipada por sistema
	private SistemaTO sistemaTO;

	public TelaMenuGrupoViewBean() {
		initValues();
	}

	public void initValues() {
		habilitarEdicao = false;
		tmgTO = new TelaMenuGrupoTO();
		tmgTO.setTmgGrupo(new GrupoTO());
		tmgTO.getTmgGrupo().setGpoSistemaTO(new SistemaTO());
		tmgTO.setTmgTelaMenu(new TelaMenuTO());

		limparFiltros();
	}

	public void limparFiltros() {
		sistemaSelecionado = new SistemaTO();
		grupoSelecionado = new GrupoTO();
		telaMenuSelecionada =new TelaMenuTO();
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
	 * @return the listaPesqTelaMenuTO
	 */
	public List<TelaMenuTO> getListaPesqTelaMenuTO() {
		return listaPesqTelaMenuTO;
	}

	/**
	 * @param listaPesqTelaMenuTO
	 *            the listaPesqTelaMenuTO to set
	 */
	public void setListaPesqTelaMenuTO(List<TelaMenuTO> listaPesqTelaMenuTO) {
		this.listaPesqTelaMenuTO = listaPesqTelaMenuTO;
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
	 * @return the telaMenuSelecionada
	 */
	public TelaMenuTO getTelaMenuSelecionada() {
		return telaMenuSelecionada;
	}

	/**
	 * @param telaMenuSelecionada
	 *            the telaMenuSelecionada to set
	 */
	public void setTelaMenuSelecionada(TelaMenuTO telaMenuSelecionada) {
		this.telaMenuSelecionada = telaMenuSelecionada;
	}

	/**
	 * @return the tmgDataModel
	 */
	public TelaMenuGrupoDataModel getTmgDataModel() {
		return tmgDataModel;
	}

	/**
	 * @param tmgDataModel
	 *            the tmgDataModel to set
	 */
	public void setTmgDataModel(TelaMenuGrupoDataModel tmgDataModel) {
		this.tmgDataModel = tmgDataModel;
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
	 * @return the listaTelaMenuTO
	 */
	public List<TelaMenuTO> getListaTelaMenuTO() {
		return listaTelaMenuTO;
	}

	/**
	 * @param listaTelaMenuTO
	 *            the listaTelaMenuTO to set
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
	 * @param sistemaSelecionado
	 *            the sistemaSelecionado to set
	 */
	public void setSistemaSelecionado(SistemaTO sistemaSelecionado) {
		this.sistemaSelecionado = sistemaSelecionado;
	}

	/**
	 * @return the tmgTO
	 */
	public TelaMenuGrupoTO getTmgTO() {
		return tmgTO;
	}

	/**
	 * @param tmgTO
	 *            the tmgTO to set
	 */
	public void setTmgTO(TelaMenuGrupoTO tmgTO) {
		this.tmgTO = tmgTO;
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

}
