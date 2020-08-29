/**  
 * jass-ejb - ListarUsuarioIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuario;

import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioIn extends GenericLazyReturnIn{
	private static final long serialVersionUID = 1L;

	private UsuarioTO usuarioTO;
	
	private String pesquisaLogin;

	/**
	 * @return the usuarioTO
	 */
	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	/**
	 * @param usuarioTO the usuarioTO to set
	 */
	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

	/**
	 * @return the pesquisaLogin
	 */
	public String getPesquisaLogin() {
		return pesquisaLogin;
	}

	/**
	 * @param pesquisaLogin the pesquisaLogin to set
	 */
	public void setPesquisaLogin(String pesquisaLogin) {
		this.pesquisaLogin = pesquisaLogin;
	}
}
