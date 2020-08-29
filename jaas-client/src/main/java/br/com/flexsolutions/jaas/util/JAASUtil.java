/**  
 * jaas - JAASUtil.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.util;

import java.io.InputStream;
import java.security.Principal;
import java.util.Properties;

import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;

/**
 * @author Thiago Augusto
 *
 */
public class JAASUtil {

	private static final Logger log = Logger.getLogger(JAASUtil.class);

	public static void efetuarLogoff() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		try {
			session.setAttribute("menuSistema", null);
			request.logout();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("<<FAAS 2.5>> Efetuando logoff.. ");
	}

	public static Subject efetuarLogin(String chaveUsuario,
			String senhaUsuario, String loginModule) throws LoginException {
		Subject ret = null;
		LoginContext lc = new LoginContext(loginModule,
				new JAASCallBackHandler(chaveUsuario, senhaUsuario));
		lc.login();

		ret = lc.getSubject();
		return ret;
	}

	public static void atualizarContextoLoginModule(String nomePerfil) {
		SecurityContext se = SecurityContextAssociation.getSecurityContext();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

		FacesContext fc = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) fc
				.getExternalContext().getRequest();
		try {
			request.setAttribute("sistema", getNomeSistemaMenu());
			request.setAttribute("perfil", nomePerfil);
			session.setAttribute("perfilSelecionado", nomePerfil);
			Principal userPrincipal = request.getUserPrincipal();

			session.setAttribute("menuSistema", null);
			if (request.getUserPrincipal() != null) {
				request.logout();
			}
			request.login(se.getUtil().getUserName(), se.getUtil()
					.getCredential().toString());
			userPrincipal = request.getUserPrincipal();
		} catch (Exception e) {
			log.error("Erro ao atualizar o contexto");
			e.printStackTrace();
		}
	}

	private static String getNomeSistemaMenu() {
		Properties defaultProps = new Properties();
		try {
			InputStream in = JAASUtil.class.getClassLoader()
					.getResourceAsStream("smenu.properties");
			defaultProps.load(in);
			in.close();
			return defaultProps.getProperty("geramenupara");
		} catch (Exception localException) {
		}
		return defaultProps.getProperty("geramenupara");
	}

}
