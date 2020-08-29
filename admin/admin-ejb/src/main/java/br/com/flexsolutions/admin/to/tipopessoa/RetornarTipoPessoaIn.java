/**  
 * admin-ejb - RetornarTipoPessoaIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.tipopessoa;

import br.com.flexsolutions.admin.to.TipoPessoaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class RetornarTipoPessoaIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private TipoPessoaTO tipoPessoaTO;

	/**
	 * @return the tipoPessoaTO
	 */
	public TipoPessoaTO getTipoPessoaTO() {
		return tipoPessoaTO;
	}

	/**
	 * @param tipoPessoaTO the tipoPessoaTO to set
	 */
	public void setTipoPessoaTO(TipoPessoaTO tipoPessoaTO) {
		this.tipoPessoaTO = tipoPessoaTO;
	}
}
