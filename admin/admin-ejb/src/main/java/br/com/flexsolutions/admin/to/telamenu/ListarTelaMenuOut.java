/**  
 * admin-ejb - FiltrarSistemaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenu;

import java.util.List;

import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class ListarTelaMenuOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<TelaMenuTO> listaTelaMenuTO;

	/**
	 * @return the listaTelaMenuTO
	 */
	public List<TelaMenuTO> getListaTelaMenuTO() {
		return listaTelaMenuTO;
	}

	/**
	 * @param listaTelaMenuTO
	 *            the listaTelaMenuTO to set
	 */
	public void setListaTelaMenuTO(List<TelaMenuTO> listaTelaMenuTO) {
		this.listaTelaMenuTO = listaTelaMenuTO;
	}

}
