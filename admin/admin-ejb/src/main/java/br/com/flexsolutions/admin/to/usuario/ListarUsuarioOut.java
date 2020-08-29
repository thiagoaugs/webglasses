/**  
 * admin-ejb - ListarUsuarioOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuario;

import java.util.List;

import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class ListarUsuarioOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<UsuarioTO> listaUsuarioTO;

	/**
	 * @return the listaUsuarioTO
	 */
	public List<UsuarioTO> getListaUsuarioTO() {
		return listaUsuarioTO;
	}

	/**
	 * @param listaUsuarioTO
	 *            the listaUsuarioTO to set
	 */
	public void setListaUsuarioTO(List<UsuarioTO> listaUsuarioTO) {
		this.listaUsuarioTO = listaUsuarioTO;
	}

}
