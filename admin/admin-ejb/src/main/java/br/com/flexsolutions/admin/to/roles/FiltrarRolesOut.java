/**  
 * admin-ejb - ListarRoleOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.roles;

import java.util.List;

import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarRolesOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<RolesTO> listaRolesTO;

	/**
	 * @return the listaRolesTO
	 */
	public List<RolesTO> getListaRolesTO() {
		return listaRolesTO;
	}

	/**
	 * @param listaRolesTO
	 *            the listaRolesTO to set
	 */
	public void setListaRolesTO(List<RolesTO> listaRolesTO) {
		this.listaRolesTO = listaRolesTO;
	}

}
