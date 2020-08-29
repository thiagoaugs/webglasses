/**  
 * admin-ejb - FiltrarUsuarioGrupoOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import java.util.List;

import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioGrupoOut extends GenericLazyReturnOut {
	private static final long serialVersionUID = 1L;

	private List<UsuarioSistemaGrupoTO> listaUsgTO;

	/**
	 * @return the listaUsgTO
	 */
	public List<UsuarioSistemaGrupoTO> getListaUsgTO() {
		return listaUsgTO;
	}

	/**
	 * @param listaUsgTO
	 *            the listaUsgTO to set
	 */
	public void setListaUsgTO(List<UsuarioSistemaGrupoTO> listaUsgTO) {
		this.listaUsgTO = listaUsgTO;
	}

}
