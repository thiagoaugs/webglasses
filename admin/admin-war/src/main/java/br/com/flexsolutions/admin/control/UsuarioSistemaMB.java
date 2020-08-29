/**  
 * admin-war - UsuarioSistemaMB.java
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

import br.com.flexsolutions.admin.control.viewBean.UsuarioSistemaViewBean;
import br.com.flexsolutions.admin.control.viewparam.UsuarioSistemaDataModel;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanLocal;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanLocal;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioOut;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class UsuarioSistemaMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "usuarioSistemaBean")
	private IUsuarioSistemaBeanLocal usuSistemaBean;

	@EJB(name = "sistemaBean")
	private ISistemaBeanLocal sisBean;

	@EJB(name = "usuarioBean")
	private IUsuarioBeanLocal usuBean;

	private UsuarioSistemaViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public UsuarioSistemaViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(UsuarioSistemaViewBean viewBean) {
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

		viewBean = new UsuarioSistemaViewBean();

		// instancia um novo DataModel
		UsuarioSistemaDataModel usiDM = new UsuarioSistemaDataModel(
				usuSistemaBean);

		// chama o meotodo de pesquisa passando uma string vazia
		usiDM.realizarPesquisa("", null);

		// seta o DataModel dentro da viewBean
		getViewBean().setUsuarioSistemaDataModel(usiDM);
		carregarListaSistema();
	}

	/**
	 * metodo que lista os sistemas cadastrados para ser usado no combo
	 */
	private void carregarListaSistema() {

		ListarSistemaOut out = sisBean.listar();

		if (out.isResultado()) {
			viewBean.setListaSistemaTO(new ArrayList<SistemaTO>());
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
	public void carregarCombos(AjaxBehaviorEvent event) {

		if (viewBean.getSistemaSelecionado().getSisId() != null
				&& viewBean.getSistemaSelecionado().getSisId() != 0) {
			carregarListaUsuario();
		}

	}

	/**
	 * metodo que lista os usuarios cadastrados para ser usado na picklist
	 */
	private void carregarListaUsuario() {
		ListarUsuarioIn in = new ListarUsuarioIn();

		in.setIdSistema(viewBean.getSistemaSelecionado().getSisId());

		ListarUsuarioOut out = usuBean.listar(in);

		if (out.isResultado()) {
			viewBean.getUsuarios().setSource(out.getListaUsuarioTO());

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
		UsuarioSistemaDataModel usiDM = viewBean.getUsuarioSistemaDataModel();

		// chama o metodo pesquisar
		usiDM.realizarPesquisa(viewBean.getPesquisa(), viewBean
				.getSistemaSelecionado().getSisId());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto
		UsuarioSistemaTO usiTO = new UsuarioSistemaTO();

		usiTO.setUsiSistema(new SistemaTO());
		usiTO.setUsiUsuario(new UsuarioTO());

		// seta o objeto para o viewBean
		viewBean.setUsuarioSistemaTO(usiTO);
		viewBean.setSistemaSelecionado(new SistemaTO());

		// habilita a edicao
		viewBean.setHabilitarEdicao(true);

		// desabilita o modo de visualizacao
		viewBean.setHabilitarVisualizacao(false);
	}

	/**
	 * Metodo responsavel por requisitar uma chamada para o servico
	 */
	public void salvar() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			CadastrarUsuarioSistemaIn in = new CadastrarUsuarioSistemaIn();

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setSisTO(viewBean.getSistemaSelecionado());
			in.setListaUsuario(viewBean.getUsuarios().getTarget());

			out = usuSistemaBean.cadastrar(in);

			if (out.isResultado()) {
				initPage();
				addMessageInfo(
						bundle.getString("genericmessages.managedbean.salvar.sucesso"),
						null);
			} else {
				addMessageError(out.getMensagem(), null);
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
		ExcluirUsuarioSistemaIn in = new ExcluirUsuarioSistemaIn();

		try {

			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setUsiTO(viewBean.getUsuarioSistemaTO());

			ExcluirUsuarioSistemaOut out = usuSistemaBean.excluir(in);

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
