/**  
 * webglass-web - UsuarioViewBean.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control.viewBean;

import br.com.flexsolutions.admin.control.viewparam.UsuarioDataModel;
import br.com.flexsolutions.admin.to.UsuarioTO;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioViewBean {

	// instancia do datamodel
	private UsuarioDataModel usuarioDataModel;

	// string para a barra de pesquisa de tipo usuario
	private String stringPesquisa;

	// para habilitar os modos de cadastro e visualizacao
	private boolean habilitarEdicao;
	private boolean habilitarVisualizacao;

	// para popular os valores do objeto
	private UsuarioTO usuarioTO;
//	private List<PessoaTO> listaPessoaTO;

	public UsuarioViewBean() {
		initValues();
	}

	public void initValues() {
		setStringPesquisa("");
		habilitarEdicao = false;
		habilitarVisualizacao = false;
		usuarioTO = new UsuarioTO();
//		usuarioTO.setUsuPessoaTO(new PessoaTO());

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
	 * @return the usuarioTO
	 */
	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	/**
	 * @param usuarioTO
	 *            the usuarioTO to set
	 */
	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

	/**
	 * @return the usuarioDataModel
	 */
	public UsuarioDataModel getUsuarioDataModel() {
		return usuarioDataModel;
	}

	/**
	 * @param usuarioDataModel
	 *            the usuarioDataModel to set
	 */
	public void setUsuarioDataModel(UsuarioDataModel usuarioDataModel) {
		this.usuarioDataModel = usuarioDataModel;
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
