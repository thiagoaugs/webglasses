/**  
 * jass-ejb - ExcluirUsuarioIn.java
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
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class ExcluirUsuarioIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private UsuarioTO usuarioTO;

	/**
	 * @return the usuarioTO
	 */
	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	/**
	 * @param usuarioTO
	 *            the usuarioTO to set
	 */
	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

}
