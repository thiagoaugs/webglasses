/**  
 * jass-war - SessionTimeoutPhaseListener.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.webutils.phaselistener;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Thiago Augusto
 *
 */
public class SessionTimeoutPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		String originalURL = (String) request
				.getAttribute("javax.servlet.forward.request_uri");
	
		if ((context.getPartialViewContext().isAjaxRequest())
				&& (originalURL != null)) {
			try {
				context.getExternalContext().redirect(originalURL);
			} catch (IOException e) {
				throw new FacesException(e);
			}
		}
	}

	public void beforePhase(PhaseEvent arg0) {
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
