/**  
 * admin-ejb - ListarGrupoOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.grupo;

import java.util.List;

import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class ListarGrupoOut extends GenericLazyReturnOut{
	private static final long serialVersionUID = 1L;

	private List<GrupoTO> listaGrupoTO;

	/**
	 * @return the listaGrupoTO
	 */
	public List<GrupoTO> getListaGrupoTO() {
		return listaGrupoTO;
	}

	/**
	 * @param listaGrupoTO the listaGrupoTO to set
	 */
	public void setListaGrupoTO(List<GrupoTO> listaGrupoTO) {
		this.listaGrupoTO = listaGrupoTO;
	}
}
