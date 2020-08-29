/**  
 * admin-war - GrupoRoleMB.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.flexsolutions.admin.control.viewBean.GrupoRoleViewBean;
import br.com.flexsolutions.admin.control.viewparam.GrupoRoleDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.IRolesBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleOut;
import br.com.flexsolutions.admin.to.roles.ListarRolesIn;
import br.com.flexsolutions.admin.to.roles.ListarRolesOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class GrupoRoleMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "grupoRoleBean")
	private IGrupoRoleBeanLocal gprBean;

	@EJB(name = "grupoBean")
	private IGrupoBeanLocal gpoBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	@EJB(name = "rolesBean")
	private IRolesBeanLocal rolBean;

	private GrupoRoleViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public GrupoRoleViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(GrupoRoleViewBean viewBean) {
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

		setViewBean(new GrupoRoleViewBean());

		// instancia um novo DataModel
		GrupoRoleDataModel gprDM = new GrupoRoleDataModel(gprBean);

		// chama o meotodo de pesquisa
		gprDM.realizarPesquisa(null, null, null);

		// seta o DataModel dentro da viewBean
		getViewBean().setGrupoRoleDataModel(gprDM);

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

	public void carregarCombosPesq(AjaxBehaviorEvent event) {

		if (viewBean.getSistemaSelecionado().getSisId() != null
				&& viewBean.getSistemaSelecionado().getSisId() != 0) {
			carregarListaPesqGrupo();
			carregarListaPesqRole();
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
	private void carregarListaPesqRole() {

		ListarRolesIn in = new ListarRolesIn();
		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());
		ListarRolesOut out = rolBean.listarPorIdSistema(in);

		if (out.isResultado()) {
			viewBean.setListaPesqRoleTO(out.getListRolesTO());
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
		GrupoRoleDataModel gprDM = viewBean.getGrupoRoleDataModel();

		// chama o metodo pesquisar
		gprDM.realizarPesquisa(viewBean.getSistemaSelecionado().getSisId(),
				viewBean.getGrupoSelecionado().getGpoId(), viewBean
						.getRoleSelecionada().getRolId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto bem como seus filhos
		GrupoRoleTO gprTO = new GrupoRoleTO();
		gprTO.setGprGrupo(new GrupoTO());
		gprTO.getGprGrupo().setGpoSistemaTO(new SistemaTO());
		gprTO.setGprRole(new RolesTO());

		// seta o objeto para o viewBean
		viewBean.setGrupoRoleTO(gprTO);

		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);
	}

	public void carregarCombos(AjaxBehaviorEvent event) {

		if (viewBean.getGrupoRoleTO().getGprGrupo().getGpoSistemaTO()
				.getSisId() != null
				&& viewBean.getGrupoRoleTO().getGprGrupo().getGpoSistemaTO()
						.getSisId() != 0) {

			carregarListaGrupo();
		}
	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaGrupo() {

		ListarGrupoIn in = new ListarGrupoIn();

		// in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());
		in.setIdSistema(viewBean.getGrupoRoleTO().getGprGrupo()
				.getGpoSistemaTO().getSisId());

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
	public void carregarListaRole(AjaxBehaviorEvent event) {

		ListarRolesIn in = new ListarRolesIn();
		// in.setIdSistema( viewBean.getSistemaSelecionado().getSisId());
		in.setIdSistema(viewBean.getGrupoRoleTO().getGprGrupo()
				.getGpoSistemaTO().getSisId());
		in.setIdGrupo(viewBean.getGrupoRoleTO().getGprGrupo().getGpoId());

		ListarRolesOut out = rolBean.listarPorIdSistema(in);

		if (out.isResultado()) {
			viewBean.setListaRoleTO(out.getListRolesTO());
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
			if (viewBean.getGrupoRoleTO().getGprId() == null) {
				CadastrarGrupoRoleIn in = new CadastrarGrupoRoleIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setGprTO(viewBean.getGrupoRoleTO());

				out = gprBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarGrupoRoleIn in = new AlterarGrupoRoleIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setGprTO(viewBean.getGrupoRoleTO());

				out = gprBean.alterar(in);

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
		ExcluirGrupoRoleIn in = new ExcluirGrupoRoleIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setGprTO(viewBean.getGrupoRoleTO());

			ExcluirGrupoRoleOut out = gprBean.excluir(in);

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
