/**  
 * admin-war - TelaMenuGrupoMB.java
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
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.flexsolutions.admin.control.viewBean.TelaMenuGrupoViewBean;
import br.com.flexsolutions.admin.control.viewparam.TelaMenuGrupoDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanLocal;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class TelaMenuGrupoMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "telaMenuGrupoBean")
	private ITelaMenuGrupoBeanLocal tmgBean;

	@EJB(name = "grupoBean")
	private IGrupoBeanLocal gpoBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	@EJB(name = "telaMenuBean")
	private ITelaMenuBeanLocal tmeBean;

	private TelaMenuGrupoViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public TelaMenuGrupoViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(TelaMenuGrupoViewBean viewBean) {
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

		setViewBean(new TelaMenuGrupoViewBean());

		// instancia um novo DataModel
		TelaMenuGrupoDataModel tmgDM = new TelaMenuGrupoDataModel(tmgBean);

		// chama o meotodo de pesquisa
		tmgDM.realizarPesquisa(viewBean.getSistemaSelecionado().getSisId(),
				viewBean.getGrupoSelecionado().getGpoId(), viewBean
						.getTelaMenuSelecionada().getTmeId());

		// seta o DataModel dentro da viewBean
		getViewBean().setTmgDataModel(tmgDM);

		carregarListaSistema();

	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaSistema() {

		ListarSistemaOut out = sisBean.listar();

		if (out.isResultado()) {
			getViewBean().setListaSistemaTO(new ArrayList<SistemaTO>());
			getViewBean().setListaSistemaTO(out.getListaSistemaTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	public void carregarCombosPesq(AjaxBehaviorEvent event) {

		if (getViewBean().getSistemaSelecionado().getSisId() != null
				&& getViewBean().getSistemaSelecionado().getSisId() != 0) {

			carregarListaPesqGrupo();
			carregarListaPesqTelaMenu();

		}
	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
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
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaPesqTelaMenu() {

		ListarTelaMenuIn in = new ListarTelaMenuIn();
		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());

		ListarTelaMenuOut out = tmeBean.listarPorIdSistemaGrupo(in);

		if (out.isResultado()) {
			viewBean.setListaPesqTelaMenuTO(out.getListaTelaMenuTO());
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
		TelaMenuGrupoDataModel tmgDM = viewBean.getTmgDataModel();

		// chama o metodo pesquisar
		tmgDM.realizarPesquisa(viewBean.getSistemaSelecionado().getSisId(),
				viewBean.getGrupoSelecionado().getGpoId(), viewBean
						.getTelaMenuSelecionada().getTmeId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto bem como seus filhos

		TelaMenuGrupoTO tmgTO = new TelaMenuGrupoTO();
		tmgTO.setTmgGrupo(new GrupoTO());
		tmgTO.getTmgGrupo().setGpoSistemaTO(new SistemaTO());
		tmgTO.setTmgTelaMenu(new TelaMenuTO());

		// seta o objeto para o viewBean
		viewBean.setTmgTO(tmgTO);

		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);
	}

	public void carregarCombos(AjaxBehaviorEvent event) {


		if (getViewBean().getTmgTO().getTmgGrupo().getGpoSistemaTO().getSisId() != null
				&& getViewBean().getTmgTO().getTmgGrupo().getGpoSistemaTO()
						.getSisId() != 0) {

			carregarListaGrupo();

		}
	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaGrupo() {

		ListarGrupoIn in = new ListarGrupoIn();
		in.setIdSistema(viewBean.getTmgTO().getTmgGrupo().getGpoSistemaTO()
				.getSisId());

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
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	public void carregarListaTelaMenu(AjaxBehaviorEvent event) {

		ListarTelaMenuIn in = new ListarTelaMenuIn();
	
		in.setIdSistema(viewBean.getTmgTO().getTmgGrupo().getGpoSistemaTO()
				.getSisId());
		in.setIdGrupo(viewBean.getTmgTO().getTmgGrupo().getGpoId());
	
		ListarTelaMenuOut out = tmeBean.listarPorIdSistemaGrupo(in);

		if (out.isResultado()) {
			viewBean.setListaTelaMenuTO(out.getListaTelaMenuTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"sistema.listar.naolocalizado"), null);
		}

	}

	/**
	 * Metodo responsavel por requisitar uma chamada para o servico
	 */
	public void salvarGrupoRole() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getTmgTO().getTmgId() == null) {
				CadastrarTelaMenuGrupoIn in = new CadastrarTelaMenuGrupoIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setTmgTO(viewBean.getTmgTO());

				out = tmgBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarTelaMenuGrupoIn in = new AlterarTelaMenuGrupoIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setTmgTO(viewBean.getTmgTO());

				out = tmgBean.alterar(in);

			}

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
		ExcluirTelaMenuGrupoIn in = new ExcluirTelaMenuGrupoIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setTmgTO(viewBean.getTmgTO());

			ExcluirTelaMenuGrupoOut out = tmgBean.excluir(in);

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
