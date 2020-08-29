/**  
 * admin-war - RolesMB.java
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

import br.com.flexsolutions.admin.control.viewBean.RolesViewBean;
import br.com.flexsolutions.admin.control.viewparam.RolesDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IRolesBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.roles.AlterarRolesIn;
import br.com.flexsolutions.admin.to.roles.AtivaRolesIn;
import br.com.flexsolutions.admin.to.roles.AtivaRolesOut;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesIn;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesIn;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class RolesMB extends BaseMB {

	private static final long serialVersionUID = 1L;

	@EJB(name = "rolesBean")
	private IRolesBeanLocal rolBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	private RolesViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public RolesViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(RolesViewBean viewBean) {
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

		viewBean = new RolesViewBean();

		// instancia um novo DataModel
		RolesDataModel rolDM = new RolesDataModel(rolBean);

		// chama o meotodo de pesquisa passando uma string vazia
		rolDM.realizarPesquisa("", null);

		// seta o DataModel dentro da viewBean
		getViewBean().setRolesDataModel(rolDM);

		carregarListaSistemas();
	}

	/**
	 * metodo que lista os roless cadastrados para ser usado no combo
	 */
	private void carregarListaSistemas() {

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
	 * Executa a pesquisa.
	 */
	public void pesquisar() {

		// instancia um novo DataModel e recebe o valor da viewBean
		RolesDataModel rolDM = viewBean.getRolesDataModel();

		// chama o metodo pesquisar passando a string de pesquisa
		rolDM.realizarPesquisa(viewBean.getStringPesquisa(), viewBean
				.getSistemaSelecionado().getSisId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto bem como seus filhos
		RolesTO rolTO = new RolesTO();
		rolTO.setRolSistemaTO(new SistemaTO());

		// seta o objeto para o viewBean
		viewBean.setRolesTO(rolTO);

		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// limpa o campo de pesquisa
		viewBean.setStringPesquisa(null);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);
	}

	/**
	 * Metodo responsavel por requisitar uma chamada para o servico
	 */
	public void salvarRoles() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getRolesTO().getRolId() == null) {
				CadastrarRolesIn in = new CadastrarRolesIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setRolesTO(viewBean.getRolesTO());

				out = rolBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarRolesIn in = new AlterarRolesIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setRolesTO(viewBean.getRolesTO());

				out = rolBean.alterar(in);

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
		ExcluirRolesIn in = new ExcluirRolesIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setRolesTO(viewBean.getRolesTO());

			ExcluirRolesOut out = rolBean.excluir(in);

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

	public void ativarDesativar() {

		AtivaRolesIn in = new AtivaRolesIn();
		ResourceBundle bundle = getResourceBundle();

		try {

			if (viewBean.getRolesTO().getRolAtivo())
				viewBean.getRolesTO().setRolAtivo(false);
			else
				viewBean.getRolesTO().setRolAtivo(true);

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setRolTO(viewBean.getRolesTO());

			AtivaRolesOut out = rolBean.controlaAtivacao(in);

			if (out.isResultado()) {
				initPage();

				if (out.getRolesTO().getRolAtivo()) {

					addMessageInfo(
							bundle.getString("roles.managedbean.ativado.sucesso"),
							null);
				} else {
					addMessageInfo(
							bundle.getString("roles.managedbean.desativado.sucesso"),
							null);
				}

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

}
