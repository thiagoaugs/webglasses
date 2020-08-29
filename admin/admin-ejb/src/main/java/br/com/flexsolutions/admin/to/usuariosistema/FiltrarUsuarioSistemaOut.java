/**  
 * admin-ejb - FiltrarUsuarioSistemaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariosistema;

import java.util.List;

import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericLazyReturnOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioSistemaOut extends GenericLazyReturnOut{
	private static final long serialVersionUID = 1L;

	private List<UsuarioSistemaTO> listaUsiTO;

	/**
	 * @return the listaUsiTO
	 */
	public List<UsuarioSistemaTO> getListaUsiTO() {
		return listaUsiTO;
	}

	/**
	 * @param listaUsiTO the listaUsiTO to set
	 */
	public void setListaUsiTO(List<UsuarioSistemaTO> listaUsiTO) {
		this.listaUsiTO = listaUsiTO;
	}
}
