/**  
 * admin-ejb - ListarRolesOut.java
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
public class ListarRolesOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<RolesTO> listaRolesTO;

	/**
	 * @return the listRolesTO
	 */
	public List<RolesTO> getListRolesTO() {
		return listaRolesTO;
	}

	/**
	 * @param listRolesTO
	 *            the listRolesTO to set
	 */
	public void setListRolesTO(List<RolesTO> listRolesTO) {
		this.listaRolesTO = listRolesTO;
	}
}
