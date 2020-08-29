/**  
 * jass-ejb - AlterarGrupoOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.grupo;

import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class AlterarGrupoOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private GrupoTO grupTO;

	/**
	 * @return the grupTO
	 */
	public GrupoTO getGrupTO() {
		return grupTO;
	}

	/**
	 * @param grupTO
	 *            the grupTO to set
	 */
	public void setGrupTO(GrupoTO grupTO) {
		this.grupTO = grupTO;
	}

}
