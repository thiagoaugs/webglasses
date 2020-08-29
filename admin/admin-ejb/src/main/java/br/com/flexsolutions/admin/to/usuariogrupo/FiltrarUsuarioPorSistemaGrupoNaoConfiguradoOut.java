/**  
 * admin-ejb - FiltrarUsuarioPorSistemaNaoConfiguradoOut.java
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

import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private List<UsuarioSistemaTO> listaUsuarioSistemaTO;

	/**
	 * @return the listaUsuarioSistemaTO
	 */
	public List<UsuarioSistemaTO> getListaUsuarioSistemaTO() {
		return listaUsuarioSistemaTO;
	}

	/**
	 * @param listaUsuarioSistemaTO
	 *            the listaUsuarioSistemaTO to set
	 */
	public void setListaUsuarioSistemaTO(
			List<UsuarioSistemaTO> listaUsuarioSistemaTO) {
		this.listaUsuarioSistemaTO = listaUsuarioSistemaTO;
	}

}
