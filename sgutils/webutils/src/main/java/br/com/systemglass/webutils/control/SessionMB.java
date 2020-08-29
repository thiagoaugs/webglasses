/**  
 * webUtils - Session.java
 * 
 * Data de criacao 14/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.webutils.control;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean(name="sessionMB")
@ViewScoped
public class SessionMB extends BaseMB {

	private static final long serialVersionUID = 1L;
	private Integer timeOut;

	@PostConstruct
	public void initPage() {
		this.timeOut = Integer.valueOf(getHttpSession()
				.getMaxInactiveInterval());
	}

	public Integer getTimeOut() {
		return this.timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public void manterSessaoAtiva() {
	}

}
