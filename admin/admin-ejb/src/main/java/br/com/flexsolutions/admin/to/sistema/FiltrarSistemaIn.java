/**  
 * admin-ejb - FiltrarSistemaIn.java
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
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarSistemaIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;

	private SistemaTO sistemaTO;
	private String pesquisa;

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

	/**
	 * @return the pesquisa
	 */
	public String getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisa
	 *            the pesquisa to set
	 */
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
