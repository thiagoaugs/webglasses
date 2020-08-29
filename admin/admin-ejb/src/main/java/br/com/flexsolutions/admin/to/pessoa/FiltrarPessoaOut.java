/**  
 * admin-ejb - ListarPessoaOu.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.pessoa;

import java.util.List;

import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarPessoaOut  extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;
	
	private List<PessoaTO> listaPessoaTO;

	/**
	 * @return the listaPessoaTO
	 */
	public List<PessoaTO> getListaPessoaTO() {
		return listaPessoaTO;
	}

	/**
	 * @param listaPessoaTO the listaPessoaTO to set
	 */
	public void setListaPessoaTO(List<PessoaTO> listaPessoaTO) {
		this.listaPessoaTO = listaPessoaTO;
	}

}
