/**  
 * admin-ejb - CadastrarUsuarioSistemaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariosistema;

import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarUsuarioSistemaOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private UsuarioSistemaTO usiTO;

	/**
	 * @return the usiTO
	 */
	public UsuarioSistemaTO getUsiTO() {
		return usiTO;
	}

	/**
	 * @param usiTO
	 *            the usiTO to set
	 */
	public void setUsiTO(UsuarioSistemaTO usiTO) {
		this.usiTO = usiTO;
	}
}
