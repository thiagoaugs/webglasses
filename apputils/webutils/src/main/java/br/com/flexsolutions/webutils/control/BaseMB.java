/**  
 * webUtils - BaseMB.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.webutils.control;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 *  Classe abstrata para encapsular os metodos comuns aos ManagedBeans
 *  
 * @author Thiago Augusto
 *
 */
public abstract class BaseMB implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String stackTrace;

	@PostConstruct
	public abstract void initPage();

	public String getStackTrace() {
		return this.stackTrace;
	}

	public void setStackTrace(Throwable e) {
		this.stackTrace = ExceptionUtils.getStackTrace(e);
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public HttpSession getHttpSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	public boolean isUserInRole(String roleName) {
		return getExternalContext().isUserInRole(roleName.toUpperCase());
	}

	public Object getSessionAttribute(Object attribute) {
		return getFacesContext().getExternalContext().getSessionMap()
				.get(attribute);
	}

	public Object getRequestParameter(Object parameter) {
		return getFacesContext().getExternalContext().getRequestParameterMap()
				.get(parameter);
	}

	public String getInitParameter(String atributo) {
		return getExternalContext().getInitParameter(atributo);
	}

	public String getApplicationName() {
		return getExternalContext().getInitParameter("nomeAplicacao");
	}

	public Locale getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

	
	/**Retorna o login do usuario da sessao
	 * @return
	 */
	public String getLogin() {
		String login = "";
		if (getExternalContext().getUserPrincipal() != null) {
			login = getExternalContext().getUserPrincipal().getName();
		}
		return login;
	}

	public ResourceBundle getResourceBundle() {
		String messageBundleName = getFacesContext().getApplication()
				.getMessageBundle();

		return ResourceBundle.getBundle(messageBundleName, getLocale());
	}

	public void addMessageInfo(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, msg, detail);
	}

	public void addMessageWarning(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, msg, detail);
	}

	public void addMessageError(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
	}

	private void addMessage(FacesMessage.Severity severity, String msg,
			String detail) {
		getFacesContext().addMessage(null,
				new FacesMessage(severity, msg, detail));
	}
}
