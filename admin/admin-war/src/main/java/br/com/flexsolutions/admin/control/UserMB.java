/**  
 * webglass-web - UsuarioMB.java
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

import br.com.flexsolutions.admin.control.viewBean.UsuarioViewBean;
import br.com.flexsolutions.admin.control.viewparam.UsuarioDataModel;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanLocal;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioOut;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;
import br.com.flexsolutions.webutils.control.BaseMB;

/**
 * ManagedBean para a manutencao de tipo de usuario
 *
 * @author Thiago Augusto
 *
 */
@ManagedBean
@ViewScoped
public class UserMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	@EJB(name = "usuarioBean")
	private IUsuarioBeanLocal usuBean;

	private UsuarioViewBean viewBean;

	/**
	 * @return the viewBean
	 */
	public UsuarioViewBean getViewBean() {
		return viewBean;
	}

	/**
	 * @param viewBean
	 *            the viewBean to set
	 */
	public void setViewBean(UsuarioViewBean viewBean) {
		this.viewBean = viewBean;
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.webutils.control.BaseMB#initPage()
	 */
	@Override
	@PostConstruct
	public void initPage() {

		viewBean = new UsuarioViewBean();

		// instancia um novo DataModel
		UsuarioDataModel usuDM = new UsuarioDataModel(usuBean);

		// chama o meotodo de pesquisa passando uma string vazia
		usuDM.realizarPesquisa("");

		// seta o DataModel dentro da viewBean
		getViewBean().setUsuarioDataModel(usuDM);
	}

	/**
	 * Executa a pesquisa.
	 */
	public void pesquisar() {

		// instancia um novo DataModel e recebe o valor da viewBean
		UsuarioDataModel usuDM = viewBean.getUsuarioDataModel();

		// chama o metodo pesquisar passando a string de pesquisa
		usuDM.realizarPesquisa(viewBean.getStringPesquisa());
	}

	/**
	 * Habilita cadastro e instancia um novo objeto para realizacao do cadastro.
	 */
	@RolesAllowed("INCLUIR_REGISTRO")
	public void novoCadastro() {

		// instancia um novo objeto
		UsuarioTO usuTO = new UsuarioTO();

		// seta o objeto para o viewBean
		viewBean.setUsuarioTO(usuTO);

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
	public void salvarUsuario() {

		GenericOut out;
		ResourceBundle bundle = getResourceBundle();
		try {

			// novo registro
			if (viewBean.getUsuarioTO().getUsuId() == null) {
				CadastrarUsuarioIn in = new CadastrarUsuarioIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setUsuarioTO(viewBean.getUsuarioTO());

				out = usuBean.cadastrar(in);

			}
			// alteracao do registro
			else {
				AlterarUsuarioIn in = new AlterarUsuarioIn();

				in.setLocale(getLocale());
				// in.setLogin(getLogin());
				in.setUsuarioTO(viewBean.getUsuarioTO());

				out = usuBean.alterar(in);

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
		ExcluirUsuarioIn in = new ExcluirUsuarioIn();

		try {

			in.setLocale(getLocale());
			//in.setLogin(getLogin());
			in.setUsuarioTO(viewBean.getUsuarioTO());
			
			ExcluirUsuarioOut out = usuBean.excluir(in);
			
			if (out.isResultado()) {
				initPage();
				addMessageInfo(bundle.getString("genericmessages.managedbean.excluir.sucesso"), null);
			} else {
				String msg = bundle.getString("genericmessages.managedbean.excluir.erro");
				addMessageError(msg, null);
			}
		} catch (Exception e) {
			String msg = bundle.getString("genericmessages.managedbean.excluir.erro");
			setStackTrace(e);
			addMessageError(msg, null);
		}
	}
	
	public void bloquearUsuario(){
		
		BloqUsuarioIn in = new BloqUsuarioIn();
		ResourceBundle bundle = getResourceBundle();
		
		try {
			
			if ( viewBean.getUsuarioTO().getUsuBloq() )
				viewBean.getUsuarioTO().setUsuBloq(false);
			else
				viewBean.getUsuarioTO().setUsuBloq(true);
			
			in.setLocale(getLocale());
			// in.setLogin(getLogin());
			in.setUsuarioTO(viewBean.getUsuarioTO());
			
			BloqUsuarioOut out = usuBean.bloqueio(in);
			
			if (out.isResultado()) {
				initPage();
				
				if(out.getUsuarioTO().getUsuBloq()){
					
					addMessageInfo(
							bundle.getString("usuario.managedbean.bloqueado.sucesso"),
							null);
				}else{
					addMessageInfo(
							bundle.getString("usuario.managedbean.desbloqueado.sucesso"),
							null);
				}
				
			} else {
				String msg = bundle
						.getString("genericmessages.managedbean.salvar.erro")
						+ " " + out.getMensagem();
				addMessageError(msg, null);
			}
			
		} catch(Exception e) {
			String msg = bundle
					.getString("genericmessages.managedbean.salvar.erro");
			setStackTrace(e);
			addMessageError(msg, null);
		}
		
	}

}
