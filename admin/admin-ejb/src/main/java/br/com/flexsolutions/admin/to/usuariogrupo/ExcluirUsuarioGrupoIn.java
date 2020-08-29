/**  
 * admin-ejb - ExcluirUsuarioGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class ExcluirUsuarioGrupoIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private UsuarioSistemaGrupoTO usgTO;

	/**
	 * @return the usgTO
	 */
	public UsuarioSistemaGrupoTO getUsgTO() {
		return usgTO;
	}

	/**
	 * @param usgTO the usgTO to set
	 */
	public void setUsgTO(UsuarioSistemaGrupoTO usgTO) {
		this.usgTO = usgTO;
	}
}
