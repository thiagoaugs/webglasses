/**  
 * jaas-war - GrupoMB.java
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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.flexsolutions.admin.control.viewBean.GrupoViewBean;
import br.com.flexsolutions.admin.control.viewparam.GrupoDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class GrupoMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "grupoBean")
	private IGrupoBeanLocal gpoBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	private GrupoViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public GrupoViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(GrupoViewBean viewBean) {
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

		viewBean = new GrupoViewBean();

		// instancia um novo DataModel
		GrupoDataModel gpoDM = new GrupoDataModel(gpoBean);

		// chama o meotodo de pesquisa passando uma string vazia
		gpoDM.realizarPesquisa("", null);

		// seta o DataModel dentro da viewBean
		getViewBean().setGrupoDataModel(gpoDM);

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
	 * Executa a pesquisa.
	 */
	public void pesquisar() {
		// instancia um novo DataModel e recebe o valor da viewBean
		GrupoDataModel gpoDM = viewBean.getGrupoDataModel();

		// chama o metodo pesquisar passando a string de pesquisa
		gpoDM.realizarPesquisa(viewBean.getStringPesquisa(), viewBean
				.getSistemaSelecionado().getSisId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	// @RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto bem como seus filhos
		GrupoTO gpoTO = new GrupoTO();
		gpoTO.setGpoSistemaTO(new SistemaTO());

		// seta o objeto para o viewBean
		viewBean.setGrupoTO(gpoTO);

		// limpa o campo de pesquisa
		viewBean.setStringPesquisa(null);

		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);
	}

	/**
	 * Metodo responsavel por requisitar uma chamada para o servico
	 */
	public void salvarGrupo() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getGrupoTO().getGpoId() == null) {
				CadastrarGrupoIn in = new CadastrarGrupoIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setGrupoTO(viewBean.getGrupoTO());

				out = gpoBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarGrupoIn in = new AlterarGrupoIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setGrupoTO(viewBean.getGrupoTO());

				out = gpoBean.alterar(in);

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
		ExcluirGrupoIn in = new ExcluirGrupoIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setGrupoTO(viewBean.getGrupoTO());

			ExcluirGrupoOut out = gpoBean.excluir(in);

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
