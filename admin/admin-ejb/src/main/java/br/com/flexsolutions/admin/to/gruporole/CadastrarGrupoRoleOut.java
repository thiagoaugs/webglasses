/**  
 * admin-ejb - CadastrarGrupoRoleOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.gruporole;

import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarGrupoRoleOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private GrupoRoleTO gprTO;

	/**
	 * @return the gprTO
	 */
	public GrupoRoleTO getGprTO() {
		return gprTO;
	}

	/**
	 * @param gprTO
	 *            the gprTO to set
	 */
	public void setGprTO(GrupoRoleTO gprTO) {
		this.gprTO = gprTO;
	}

}
