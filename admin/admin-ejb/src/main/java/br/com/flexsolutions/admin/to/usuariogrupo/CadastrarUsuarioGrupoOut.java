/**  
 * admin-ejb - CadastrarUsuarioGrupoOut.java
 * 
 * Data de criacao 01/09/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import br.com.flexsolutions.admin.to.UsuarioGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */ 
public class CadastrarUsuarioGrupoOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private UsuarioGrupoTO ugpTO;

	/**
	 * @return the ugpTO
	 */
	public UsuarioGrupoTO getUgpTO() {
		return ugpTO;
	}

	/**
	 * @param ugpTO the ugpTO to set
	 */
	public void setUgpTO(UsuarioGrupoTO ugpTO) {
		this.ugpTO = ugpTO;
	} 
}
