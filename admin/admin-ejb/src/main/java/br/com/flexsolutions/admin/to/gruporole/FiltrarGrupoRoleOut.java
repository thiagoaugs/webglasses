/**  
 * admin-ejb - ListarGrupoRoleOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.gruporole;

import java.util.List;

import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarGrupoRoleOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<GrupoRoleTO> listaGrupoRoleTO;

	/**
	 * @return the listaGrupoRoleTO
	 */
	public List<GrupoRoleTO> getListaGrupoRoleTO() {
		return listaGrupoRoleTO;
	}

	/**
	 * @param listaGrupoRoleTO
	 *            the listaGrupoRoleTO to set
	 */
	public void setListaGrupoRoleTO(List<GrupoRoleTO> listaGrupoRoleTO) {
		this.listaGrupoRoleTO = listaGrupoRoleTO;
	}

}
