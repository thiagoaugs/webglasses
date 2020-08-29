/**  
 * admin-ejb - ListarTipoPessoaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.tipopessoa;

import java.util.List;

import br.com.flexsolutions.admin.to.TipoPessoaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class ListarTipoPessoaOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<TipoPessoaTO> listaTipoPessoaTO;

	/**
	 * @return the listaTipoPessoaTO
	 */
	public List<TipoPessoaTO> getListaTipoPessoaTO() {
		return listaTipoPessoaTO;
	}

	/**
	 * @param listaTipoPessoaTO
	 *            the listaTipoPessoaTO to set
	 */
	public void setListaTipoPessoaTO(List<TipoPessoaTO> listaTipoPessoaTO) {
		this.listaTipoPessoaTO = listaTipoPessoaTO;
	}

}
