/**  
 * admin-ejb - CadastrarPessoaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.pessoa;

import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarPessoaOut extends GenericOut {
	private static final long serialVersionUID = 1L;
	
	private PessoaTO pessoaTO;

	/**
	 * @return the pessoaTO
	 */
	public PessoaTO getPessoaTO() {
		return pessoaTO;
	}

	/**
	 * @param pessoaTO the pessoaTO to set
	 */
	public void setPessoaTO(PessoaTO pessoaTO) {
		this.pessoaTO = pessoaTO;
	}

}
