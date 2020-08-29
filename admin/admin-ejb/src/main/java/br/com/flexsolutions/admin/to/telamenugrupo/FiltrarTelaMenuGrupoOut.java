/**  
 * admin-ejb - FiltrarTelaMenuGrupoOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenugrupo;

import java.util.List;

import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarTelaMenuGrupoOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<TelaMenuGrupoTO> listaTelaMenuGrupoTO;

	/**
	 * @return the listaTelaMenuGrupoTO
	 */
	public List<TelaMenuGrupoTO> getListaTelaMenuGrupoTO() {
		return listaTelaMenuGrupoTO;
	}

	/**
	 * @param listaTelaMenuGrupoTO
	 *            the listaTelaMenuGrupoTO to set
	 */
	public void setListaTelaMenuGrupoTO(
			List<TelaMenuGrupoTO> listaTelaMenuGrupoTO) {
		this.listaTelaMenuGrupoTO = listaTelaMenuGrupoTO;
	}
}
