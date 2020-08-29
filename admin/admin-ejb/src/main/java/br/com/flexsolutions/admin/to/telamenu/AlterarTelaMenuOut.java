/**  
 * jass-ejb - CadastrarTelaMenuOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenu;

import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class AlterarTelaMenuOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private TelaMenuTO telaMenuTO;

	/**
	 * @return the telaMenuTO
	 */
	public TelaMenuTO getTelaMenuTO() {
		return telaMenuTO;
	}

	/**
	 * @param telaMenuTO
	 *            the telaMenuTO to set
	 */
	public void setTelaMenuTO(TelaMenuTO telaMenuTO) {
		this.telaMenuTO = telaMenuTO;
	}
}
