/**  
 * admin-war - TelaMenu.java
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

import br.com.flexsolutions.admin.control.viewBean.TelaMenuViewBean;
import br.com.flexsolutions.admin.control.viewparam.TelaMenuDataModel;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanLocal;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class TelaMenuMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "telaMenuBean")
	private ITelaMenuBeanLocal tmeBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	private TelaMenuViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public TelaMenuViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(TelaMenuViewBean viewBean) {
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

		viewBean = new TelaMenuViewBean();

		// instancia um novo DataModel
		TelaMenuDataModel tmeDM = new TelaMenuDataModel(tmeBean);

		// chama o meotodo de pesquisa passando uma string vazia
		tmeDM.realizarPesquisa("", null);

		// seta o DataModel dentro da viewBean
		getViewBean().setTelaMenuDataModel(tmeDM);

		carregarListaSistema();
		carregarListaTelaMenu();

	}

	/**
	 * 
	 */
	private void carregarListaTelaMenu() {
		ListarTelaMenuOut out = tmeBean.listar();

		if (out.isResultado()) {
			viewBean.setListaTelaMenuTO(out.getListaTelaMenuTO());
		} else {
			addMessageError(
					getResourceBundle().getString(
							"telamenu.listar.naolocalizado"), null);
		}

	}

	/**
	 * 
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
	 * Executa a pesquisa.
	 */
	public void pesquisar() {

		// instancia um novo DataModel e recebe o valor da viewBean
		TelaMenuDataModel tmeDM = viewBean.getTelaMenuDataModel();

		// chama o metodo pesquisar passando a string de pesquisa
		tmeDM.realizarPesquisa(viewBean.getStringPesquisa(), viewBean
				.getSistemaSelecionado().getSisId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto
		TelaMenuTO tmeTO = new TelaMenuTO();

		tmeTO.setTmeSistemaTO(new SistemaTO());
		tmeTO.setTmeMenuSuperior(new TelaMenuTO());

		// seta o objeto para o viewBean
		viewBean.setTelaMenuTO(tmeTO);

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
	public void salvarTelaMenu() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getTelaMenuTO().getTmeId() == null) {
				CadastrarTelaMenuIn in = new CadastrarTelaMenuIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setTelaMenuTO(viewBean.getTelaMenuTO());

				out = tmeBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarTelaMenuIn in = new AlterarTelaMenuIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setTelaMenuTO(viewBean.getTelaMenuTO());

				out = tmeBean.alterar(in);

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
		ExcluirTelaMenuIn in = new ExcluirTelaMenuIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setTelaMenuTO(viewBean.getTelaMenuTO());

			ExcluirTelaMenuOut out = tmeBean.excluir(in);

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
