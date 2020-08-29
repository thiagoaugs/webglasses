/**  
 * admin-ejb - AtivaDesativaIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.pessoa;

import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class AtivaDesativaIn extends GenericIn {
	private static final long serialVersionUID = 1L;
	
	private RolesTO rolTO;

	/**
	 * @return the rolTO
	 */
	public RolesTO getRolTO() {
		return rolTO;
	}

	/**
	 * @param rolTO the rolTO to set
	 */
	public void setRolTO(RolesTO rolTO) {
		this.rolTO = rolTO;
	}

}
