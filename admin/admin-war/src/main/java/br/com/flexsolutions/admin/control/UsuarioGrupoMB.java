/**  
 * admin-war - UsuarioGrupoMB.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.flexsolutions.admin.control.viewBean.UsuarioGrupoViewBean;
import br.com.flexsolutions.admin.control.viewparam.UsuarioGrupoDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanLocal;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioGrupoMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "usuarioGrupoBean")
	private IUsuarioGrupoBeanLocal usuGrupoBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	// @EJB(name = "usuarioSistemaBean")
	// private IUsuarioSistemaBeanRemote usiBean;

	@EJB(name = "grupoBean")
	private IGrupoBeanLocal gpoBean;

	private UsuarioGrupoViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public UsuarioGrupoViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(UsuarioGrupoViewBean viewBean) {
		this.viewBean = viewBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.webutils.control.BaseMB#initPage()
	 */
	@Override
	@PostConstruct
	public void initPage() {

		viewBean = new UsuarioGrupoViewBean();

		// instancia um novo DataModel
		UsuarioGrupoDataModel ugpDM = new UsuarioGrupoDataModel(usuGrupoBean);

		// chama o meotodo de pesquisa passando uma string vazia
		ugpDM.realizarPesquisa(null, null, "");

		// seta o DataModel dentro da viewBean
		getViewBean().setUsuarioGrupoDataModel(ugpDM);

		carregarListaSistema();

	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaSistema() {

		ListarSistemaOut out = sisBean.listar();

		if (out.isResultado()) {
			viewBean.setListaSistemaTO(out.getListaSistemaTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	/**
	 * carrega a combo de
	 * 
	 * @param event
	 */
	public void carregarCombosPesq(AjaxBehaviorEvent event) {

		if (viewBean.getSistemaSelecionado().getSisId() != null
				&& viewBean.getSistemaSelecionado().getSisId() != 0) {
			carregarListaPesqGrupo();
		} else {

			List<GrupoTO> lista = new ArrayList<GrupoTO>();
			viewBean.setListaPesqGrupoTO(lista);
		}

	}

	/**
	 * metodo que lista os grupos cadastrados com o id do sistema selecionado,
	 * para ser usado no combo
	 */
	private void carregarListaPesqGrupo() {

		ListarGrupoIn in = new ListarGrupoIn();
		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());
		ListarGrupoOut out = gpoBean.listarPorIdSitema(in);

		if (out.isResultado()) {
			viewBean.setListaPesqGrupoTO(out.getListaGrupoTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	/**
	 * Executa a pesquisa.
	 */
	public void pesquisar() {

		// instancia um novo DataModel e recebe o valor da viewBean
		UsuarioGrupoDataModel ugpDM = viewBean.getUsuarioGrupoDataModel();

		// chama o metodo pesquisar
		ugpDM.realizarPesquisa(viewBean.getSistemaSelecionado().getSisId(),
				viewBean.getGrupoPesqSelecionado().getGpoId(),
				viewBean.getPesqUsuario());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {
		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);

		SistemaTO sistemaTO = new SistemaTO();
		viewBean.setSistemaSelecionado(sistemaTO);
	}

	/**
	 * carrega a combo de
	 * 
	 * @param event
	 */
	public void carregarCombos(AjaxBehaviorEvent event) {

		if (viewBean.getSistemaSelecionado().getSisId() != null
				&& viewBean.getSistemaSelecionado().getSisId() != 0) {
			carregarListaGrupo();
		}

	}

	/**
	 * metodo que lista os grupos cadastrados com o id do sistema selecionado,
	 * para ser usado no combo
	 */
	private void carregarListaGrupo() {

		ListarGrupoIn in = new ListarGrupoIn();
		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());
		ListarGrupoOut out = gpoBean.listarPorIdSitema(in);

		if (out.isResultado()) {
			viewBean.setListaGrupoTO(out.getListaGrupoTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	/**
	 * metodo que lista os usuarios cadastrados com o id do sistema selecionado,
	 * e que ja nao tenha sido configurado para este sistema e grupo para ser
	 * usado no combo
	 */
	public void carregarListaUsuario(AjaxBehaviorEvent event) {

		FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn in = new FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn();
		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());
		in.setIdGrupo(viewBean.getGrupoSelecionado().getGpoId());
		FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut out = usuGrupoBean
				.filtrarUsuarioPorSistemaNaoConfigurado(in);

		if (out.isResultado()) {
			viewBean.getUsuariosSistema().setSource(
					out.getListaUsuarioSistemaTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	/**
	 * Metodo responsavel por requisitar uma chamada para o servico
	 */
	public void salvar() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			CadastrarUsuarioGrupoIn in = new CadastrarUsuarioGrupoIn();

			in.setSisTO(viewBean.getSistemaSelecionado());
			in.setGpoTO(viewBean.getGrupoSelecionado());
			in.setListaUsuarioSistema(viewBean.getUsuariosSistema().getTarget());

			in.setLocale(getLocale());
			// in.setLogin(getLogin());

			out = usuGrupoBean.cadastrar(in);

			if (out.isResultado()) {
				initPage();
				addMessageInfo(
						bundle.getString("genericmessages.managedbean.salvar.sucesso"),
						null);
			} else {
				String msg = bundle
						.getString("genericmessages.managedbean.salvar.erro")
						+ " " + out.getMensagem();
				addMessageError(msg, null);
			}
		} catch (Exception e) {
			String msg = bundle
					.getString("genericmessages.managedbean.salvar.erro");
			setStackTrace(e);
			addMessageError(msg, null);
		}
	}

	/**
	 * Inicializa os campos da tela de cadastro de configuracao de dia de
	 * execucao
	 */
	public void cancelarCadastro() {
		viewBean.initValues();
	}

	/**
	 * Remove uma configuracao de dia de execucao
	 */
	public void excluir() {

		ResourceBundle bundle = getResourceBundle();
		ExcluirUsuarioGrupoIn in = new ExcluirUsuarioGrupoIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setUsgTO(viewBean.getUsuarioSistemaGrupoTO());

			ExcluirUsuarioGrupoOut out = usuGrupoBean.excluir(in);

			if (out.isResultado()) {
				initPage();
				addMessageInfo(
						bundle.getString("genericmessages.managedbean.excluir.sucesso"),
						null);
			} else {
				String msg = bundle
						.getString("genericmessages.managedbean.excluir.erro");
				addMessageError(msg, null);
			}
		} catch (Exception e) {
			String msg = bundle
					.getString("genericmessages.managedbean.excluir.erro");
			setStackTrace(e);
			addMessageError(msg, null);
		}
	}

}
