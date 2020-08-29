/**  
 * admin-war - SistemaMB.java
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

import br.com.flexsolutions.admin.control.viewBean.SistemaViewBean;
import br.com.flexsolutions.admin.control.viewparam.SistemaDataModel;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaIn;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class SistemaMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	private SistemaViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public SistemaViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(SistemaViewBean viewBean) {
		this.viewBean = viewBean;
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.webutils.control.BaseMB#initPage()
	 */
	@Override
	@PostConstruct
	public void initPage() {

		viewBean = new SistemaViewBean();

		// instancia um novo DataModel
		SistemaDataModel sisDM = new SistemaDataModel(sisBean);

		// chama o meotodo de pesquisa passando uma string vazia
		sisDM.realizarPesquisa("");

		// seta o DataModel dentro da viewBean
		getViewBean().setSistemaDataModel(sisDM);
	}

	/**
	 * Executa a pesquisa.
	 */
	public void pesquisar() {

		// instancia um novo DataModel e recebe o valor da viewBean
		SistemaDataModel sisDM = viewBean.getSistemaDataModel();

		// chama o metodo pesquisar passando a string de pesquisa
		sisDM.realizarPesquisa(viewBean.getStringPesquisa());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto
		SistemaTO sisTO = new SistemaTO();

		// seta o objeto para o viewBean
		viewBean.setSistemaTO(sisTO);

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
	public void salvarSistema() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getSistemaTO().getSisId()== null) {
				CadastrarSistemaIn in = new CadastrarSistemaIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setSistemaTO(viewBean.getSistemaTO());

				out = sisBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarSistemaIn in = new AlterarSistemaIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setSistemaTO(viewBean.getSistemaTO());

				out = sisBean.alterar(in);

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
		ExcluirSistemaIn in = new ExcluirSistemaIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setSistemaTO(viewBean.getSistemaTO());

			ExcluirSistemaOut out = sisBean.excluir(in);

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
