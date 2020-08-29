/**
 *jaas-loginmodule - AdminLogon.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.Principals;

import java.util.List;
import java.util.Map;

import javax.management.relation.Role;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import br.com.flexsolutions.security.interfaces.IGrupoBeanRemote;
import br.com.flexsolutions.security.interfaces.IJaasBeanRemote;

/**
 * @author Thiago Augusto
 *
 */							
public class AdminLogon implements LoginModule {

	private static final Logger log = Logger.getLogger(AdminLogon.class);

	private Subject subject = null;
	private CallbackHandler callbackHandler = null;

	boolean flag = false;

	private String loginInformado;
	private String senhaInformado;

	private HttpServletRequest request = null;
	private String appName;

	private GroupPrincipal group;
	private List<String> listaDeRoles;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {

		try {
			this.request = ((HttpServletRequest) PolicyContext
					.getContext("javax.servlet.http.HttpServletRequest"));
			this.appName = this.request.getParameter("sistema");

			log.info(this.appName + "[INFO] - initialize");

			this.subject = subject;
			this.callbackHandler = callbackHandler;

		} catch (PolicyContextException e) {

			log.error(e.getMessage());
		}

	}

	public boolean login() throws LoginException {

		log.info(this.appName + "[INFO] - login");

		getUsernamePassword();
		validaUsuario();

		return true;
	}

	public boolean commit() throws LoginException {

		log.info(this.appName + "[INFO] -  commit");

		this.subject.getPrincipals().add(new UserPrincipal(loginInformado));

		group = new GroupPrincipal("Roles");

		for (String role : listaDeRoles) {
			RolePrincipal roles = new RolePrincipal(role);
			group.addMember(roles);

		}

		this.subject.getPrincipals().add(group);

		return flag;
	}

	public boolean abort() throws LoginException {
		log.info(this.appName + "[INFO] -  abort");

		return false;
	}

	public boolean logout() throws LoginException {
		log.info(this.appName + "[INFO] -  logout");

		subject.getPrincipals().remove(new UserPrincipal(loginInformado));
		subject.getPrincipals().remove(group);

		return false;
	}

	/**
	 * Obtem o login e senha digitados
	 */
	protected void getUsernamePassword() throws LoginException {

		if (callbackHandler == null)
			throw new LoginException(
					"Error: no CallbackHandler available to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("Login");
		callbacks[1] = new PasswordCallback("Senha", false);

		try {
			// chama o handler passadno o NameCallback e o PasswordCallback
			callbackHandler.handle(callbacks);
			log.info(this.appName + "[INFO] - buscando o user name e senha");

			loginInformado = ((NameCallback) callbacks[0]).getName();
			log.info(this.appName + "[INFO] - quem esta logando : "
					+ loginInformado);

			char[] tmpPassword = ((PasswordCallback) callbacks[1])
					.getPassword();

			senhaInformado = new String(tmpPassword);
			((PasswordCallback) callbacks[1]).clearPassword();

		} catch (java.io.IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException(
					"Error: "
							+ uce.getCallback().toString()
							+ " not available to garner authentication information from the user");
		}
	}

	/**
	 * Valida login e senha no banco
	 */
	private void validaUsuario() throws LoginException {
		log.info(this.appName + "[INFO] - validando o usuario");

		// verifica se a senha informada esta em branco
		if (senhaInformado.compareTo("") == 0) {
			log.error(this.appName + "[ERROR] - usuario ["
					+ this.loginInformado
					+ "] Senha inválida, tente novamente.");
			this.request.setAttribute("msgLoginModule",
					"Senha inválida, tente novamente.");
		}

		try {
			InitialContext ctx = new InitialContext();
			IJaasBeanRemote ejb = (IJaasBeanRemote) ctx
					.lookup("ejb:admin/admin-ejb/JaasBean!br.com.flexsolutions.security.interfaces.IJaasBeanRemote");
			flag = ejb.doLogin(loginInformado, senhaInformado);

		} catch (NamingException e) {
			log.error(this.appName + "[ERROR] - Erro ao invocar EJB de login.");
			this.request.setAttribute("msgLoginModule",
					"Erro ao invocar EJB de login.");
			return;
		}

		if (flag == false) {
			log.error(this.appName
					+ "[ERROR] - Usuário ou senha inválida, tente novamente.");
			this.request.setAttribute("msgLoginModule",
					" Usuário ou senha inválida, tente novamente.");
		} else {

			// carrega a lista de grupos do usuario e verifica se o usuario tem
			// permissao para acessar este sistema
			this.listaDeRoles = getGrupos();
			if (this.listaDeRoles.size() == 0) {
				flag = false;
				log.error(this.appName + "[ERROR] - Usuário ["
						+ this.loginInformado
						+ "] não possuí permissão de acesso ao sistema "
						+ this.appName + ".");
				this.request.setAttribute("msgLoginModule", "Usuário ["
						+ this.loginInformado
						+ "] não possuí permissão de acesso ao sistema "
						+ this.appName + ".");
			}
		}

		return;
	}

	private List<String> getGrupos() {

		List<String> gruposUsuario = null;
		try {

			InitialContext ctx = new InitialContext();
			IGrupoBeanRemote ejb = (IGrupoBeanRemote) ctx
					.lookup("ejb:admin/admin-ejb/GrupoBean!br.com.flexsolutions.security.interfaces.IGrupoBeanRemote");
			gruposUsuario = ejb.recuperarGruposUsuarioSistema(appName,
					loginInformado);
		} catch (Exception e) {
			log.error(
					this.appName
							+ "[ERROR] - Houve um erro ao recuperar os perfis do usuário logado.",
					e);
		}
		return gruposUsuario;
	}

}
