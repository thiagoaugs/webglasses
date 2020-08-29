/**  
 * admin-war - UsuarioSistemaViewBean.java
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

import br.com.flexsolutions.admin.control.viewparam.UsuarioSistemaDataModel;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;

/**
 * @author Thiago Augusto
 *
 */
public class UsuarioSistemaViewBean {

	// instancia do datamodel
	private UsuarioSistemaDataModel usuarioSistemaDataModel;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular os valores do objeto
	private UsuarioSistemaTO usuarioSistemaTO;

	// para popular as combos
	private List<SistemaTO> listaSistemaTO;

	private DualListModel<UsuarioTO> usuarios;

	private List<UsuarioTO> usuarioSource = new ArrayList<UsuarioTO>();
	private List<UsuarioTO> usuarioTarget = new ArrayList<UsuarioTO>();

	// para selecionar as combos
	private SistemaTO sistemaSelecionado;
	// string para a barra de pesquisa de tipo grupo
	private String pesquisa;

	public UsuarioSistemaViewBean() {
		initValues();
	}

	public void initValues() {
		habilitarEdicao = false;
		habilitarVisualizacao = false;

		usuarioSistemaTO = new UsuarioSistemaTO();
		usuarioSistemaTO.setUsiSistema(new SistemaTO());
		usuarioSistemaTO.setUsiUsuario(new UsuarioTO());

		usuarios = new DualListModel<UsuarioTO>(usuarioSource, usuarioTarget);

		sistemaSelecionado = new SistemaTO();
		pesquisa = new String();

	}

	/**
	 * @return the usuarioSistemaDataModel
	 */
	public UsuarioSistemaDataModel getUsuarioSistemaDataModel() {
		return usuarioSistemaDataModel;
	}

	/**
	 * @param usuarioSistemaDataModel
	 *            the usuarioSistemaDataModel to set
	 */
	public void setUsuarioSistemaDataModel(
			UsuarioSistemaDataModel usuarioSistemaDataModel) {
		this.usuarioSistemaDataModel = usuarioSistemaDataModel;
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
	 * @return the usuarioSistemaTO
	 */
	public UsuarioSistemaTO getUsuarioSistemaTO() {
		return usuarioSistemaTO;
	}

	/**
	 * @param usuarioSistemaTO
	 *            the usuarioSistemaTO to set
	 */
	public void setUsuarioSistemaTO(UsuarioSistemaTO usuarioSistemaTO) {
		this.usuarioSistemaTO = usuarioSistemaTO;
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
	 * @return the usuarios
	 */
	public DualListModel<UsuarioTO> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios
	 *            the usuarios to set
	 */
	public void setUsuarios(DualListModel<UsuarioTO> usuarios) {
		this.usuarios = usuarios;
	}

	/**
	 * @return the usuarioSource
	 */
	public List<UsuarioTO> getUsuarioSource() {
		return usuarioSource;
	}

	/**
	 * @param usuarioSource
	 *            the usuarioSource to set
	 */
	public void setUsuarioSource(List<UsuarioTO> usuarioSource) {
		this.usuarioSource = usuarioSource;
	}

	/**
	 * @return the usuarioTarget
	 */
	public List<UsuarioTO> getUsuarioTarget() {
		return usuarioTarget;
	}

	/**
	 * @param usuarioTarget
	 *            the usuarioTarget to set
	 */
	public void setUsuarioTarget(List<UsuarioTO> usuarioTarget) {
		this.usuarioTarget = usuarioTarget;
	}

	/**
	 * @return the pesquisa
	 */
	public String getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisa the pesquisa to set
	 */
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

}
