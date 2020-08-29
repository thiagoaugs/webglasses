/**  
 * admin-ejb - CadastrarRoleIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.roles;

import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarRolesIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private RolesTO rolesTO;

	/**
	 * @return the rolesTO
	 */
	public RolesTO getRolesTO() {
		return rolesTO;
	}

	/**
	 * @param rolesTO
	 *            the rolesTO to set
	 */
	public void setRolesTO(RolesTO rolesTO) {
		this.rolesTO = rolesTO;
	}

}
