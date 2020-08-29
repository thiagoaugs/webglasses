/**  
 * admin-ejb - CadastrarTelaMenuGrupo.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenugrupo;

import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarTelaMenuGrupoIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private TelaMenuGrupoTO tmgTO;

	/**
	 * @return the tmgTO
	 */
	public TelaMenuGrupoTO getTmgTO() {
		return tmgTO;
	}

	/**
	 * @param tmgTO
	 *            the tmgTO to set
	 */
	public void setTmgTO(TelaMenuGrupoTO tmgTO) {
		this.tmgTO = tmgTO;
	}

}
