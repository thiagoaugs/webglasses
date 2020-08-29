/**  
 * admin-ejb - ExcluirUsuarioGrupoOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import br.com.flexsolutions.admin.to.UsuarioGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class ExcluirUsuarioGrupoOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private UsuarioGrupoTO ugpTO;

	/**
	 * @return the ugpTO
	 */
	public UsuarioGrupoTO getUgpTO() {
		return ugpTO;
	}

	/**
	 * @param ugpTO
	 *            the ugpTO to set
	 */
	public void setUgpTO(UsuarioGrupoTO ugpTO) {
		this.ugpTO = ugpTO;
	}
}
