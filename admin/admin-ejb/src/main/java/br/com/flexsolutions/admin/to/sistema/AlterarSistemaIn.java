/**  
 * admin-ejb - AlterarSistemaIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.sistema;

import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class AlterarSistemaIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private SistemaTO sistemaTO;

	/**
	 * @return the sistemaTO
	 */
	public SistemaTO getSistemaTO() {
		return sistemaTO;
	}

	/**
	 * @param sistemaTO
	 *            the sistemaTO to set
	 */
	public void setSistemaTO(SistemaTO sistemaTO) {
		this.sistemaTO = sistemaTO;
	}

}
