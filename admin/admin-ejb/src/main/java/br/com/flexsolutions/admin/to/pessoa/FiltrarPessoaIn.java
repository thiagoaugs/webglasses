/**  
 * admin-ejb - ListarPessoaIn.java
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
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnIn;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarPessoaIn extends GenericLazyReturnIn {
	private static final long serialVersionUID = 1L;
	
	private PessoaTO pessoaTO;
	
	private String pesquisa;

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

	/**
	 * @return the pesquisa
	 */
	public String getPesquisa() {
		return pesquisa;
	}

	/**
	 * @param pesquisa the pesquisa to set
	 */
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

}
