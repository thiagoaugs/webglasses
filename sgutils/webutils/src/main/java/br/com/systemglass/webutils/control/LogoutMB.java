/**  
 * webUtils - LogoutMB.java
 * 
 * Data de criacao 14/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.webutils.control;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.jboss.logging.Logger;

/**
 * Classe para controlar o logout da app
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class LogoutMB extends BaseMB {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LogoutMB.class);

	public void initPage() {
	}

	public void executeLogout(ActionEvent event) {

		try {
			getExternalContext().invalidateSession();
			getExternalContext().redirect(
					"/" + getExternalContext().getRequestContextPath());
		} catch (IOException e) {
			logger.error(e);
		}
	}

}
