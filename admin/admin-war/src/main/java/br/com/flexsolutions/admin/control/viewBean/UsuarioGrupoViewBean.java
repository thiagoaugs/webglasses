/**  
 * admin-war - UsuarioGrupoViewBean.java
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

import org.primefaces.model.DualListModel;

import br.com.flexsolutions.admin.control.viewparam.UsuarioGrupoDataModel;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;

/**
 * @author Thiago Augusto
 *
 */
public class UsuarioGrupoViewBean {
	// instancia do datamodel
	private UsuarioGrupoDataModel usuarioGrupoDataModel;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;

	// objeto para tela
	private UsuarioSistemaGrupoTO usuarioSistemaGrupoTO;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;
	private List<GrupoTO> listaGrupoTO;

	// para selecionar as combos
	private SistemaTO sistemaSelecionado;
	private GrupoTO grupoSelecionado;

	private DualListModel<UsuarioSistemaTO> usuariosSistema;

	private List<UsuarioSistemaTO> usuarioSistemaSource = new ArrayList<UsuarioSistemaTO>();
	private List<UsuarioSistemaTO> usuarioSistemaTarget = new ArrayList<UsuarioSistemaTO>();

	// para a pesquisa
	private List<GrupoTO> listaPesqGrupoTO;

	private GrupoTO grupoPesqSelecionado;
	private String pesqUsuario;

	public void initValues() {
		habilitarEdicao = false;
		habilitarVisualizacao = false;

		usuariosSistema = new DualListModel<UsuarioSistemaTO>(
				usuarioSistemaSource, usuarioSistemaTarget);

		limparFiltros();
	}

	public void limparFiltros() {
		sistemaSelecionado = new SistemaTO();
		grupoSelecionado = new GrupoTO();
		
		listaPesqGrupoTO = new ArrayList<GrupoTO>();
		grupoPesqSelecionado = new GrupoTO();
		pesqUsuario = new String();
		
	}

	/**
	 * @return the usuarioGrupoDataModel
	 */
	public UsuarioGrupoDataModel getUsuarioGrupoDataModel() {
		return usuarioGrupoDataModel;
	}

	/**
	 * @param usuarioGrupoDataModel
	 *            the usuarioGrupoDataModel to set
	 */
	public void setUsuarioGrupoDataModel(
			UsuarioGrupoDataModel usuarioGrupoDataModel) {
		this.usuarioGrupoDataModel = usuarioGrupoDataModel;
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

	private boolean habilitarVisualizacao;

	public UsuarioGrupoViewBean() {
		initValues();
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
	 * @return the usuariosSistema
	 */
	public DualListModel<UsuarioSistemaTO> getUsuariosSistema() {
		return usuariosSistema;
	}

	/**
	 * @param usuariosSistema
	 *            the usuariosSistema to set
	 */
	public void setUsuariosSistema(
			DualListModel<UsuarioSistemaTO> usuariosSistema) {
		this.usuariosSistema = usuariosSistema;
	}

	/**
	 * @return the usuarioSistemaSource
	 */
	public List<UsuarioSistemaTO> getUsuarioSistemaSource() {
		return usuarioSistemaSource;
	}

	/**
	 * @param usuarioSistemaSource
	 *            the usuarioSistemaSource to set
	 */
	public void setUsuarioSistemaSource(
			List<UsuarioSistemaTO> usuarioSistemaSource) {
		this.usuarioSistemaSource = usuarioSistemaSource;
	}

	/**
	 * @return the usuarioSistemaTarget
	 */
	public List<UsuarioSistemaTO> getUsuarioSistemaTarget() {
		return usuarioSistemaTarget;
	}

	/**
	 * @param usuarioSistemaTarget
	 *            the usuarioSistemaTarget to set
	 */
	public void setUsuarioSistemaTarget(
			List<UsuarioSistemaTO> usuarioSistemaTarget) {
		this.usuarioSistemaTarget = usuarioSistemaTarget;
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
	 * @return the usuarioSistemaGrupoTO
	 */

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
	 * @return the usuarioSistemaGrupoTO
	 */
	public UsuarioSistemaGrupoTO getUsuarioSistemaGrupoTO() {
		return usuarioSistemaGrupoTO;
	}

	/**
	 * @param usuarioSistemaGrupoTO
	 *            the usuarioSistemaGrupoTO to set
	 */
	public void setUsuarioSistemaGrupoTO(
			UsuarioSistemaGrupoTO usuarioSistemaGrupoTO) {
		this.usuarioSistemaGrupoTO = usuarioSistemaGrupoTO;
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
	 * @return the grupoPesqSelecionado
	 */
	public GrupoTO getGrupoPesqSelecionado() {
		return grupoPesqSelecionado;
	}

	/**
	 * @param grupoPesqSelecionado
	 *            the grupoPesqSelecionado to set
	 */
	public void setGrupoPesqSelecionado(GrupoTO grupoPesqSelecionado) {
		this.grupoPesqSelecionado = grupoPesqSelecionado;
	}

	/**
	 * @return the pesqUsuario
	 */
	public String getPesqUsuario() {
		return pesqUsuario;
	}

	/**
	 * @param pesqUsuario
	 *            the pesqUsuario to set
	 */
	public void setPesqUsuario(String pesqUsuario) {
		this.pesqUsuario = pesqUsuario;
	}
}
