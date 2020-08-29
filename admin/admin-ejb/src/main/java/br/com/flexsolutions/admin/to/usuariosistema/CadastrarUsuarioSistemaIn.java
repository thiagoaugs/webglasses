/**  
 * admin-ejb - CadastrarUsuarioSistemaIn.java
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

import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarUsuarioSistemaIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private SistemaTO sisTO;
	private List<UsuarioTO> listaUsuario;

	/**
	 * @return the sisTO
	 */
	public SistemaTO getSisTO() {
		return sisTO;
	}

	/**
	 * @param sisTO
	 *            the sisTO to set
	 */
	public void setSisTO(SistemaTO sisTO) {
		this.sisTO = sisTO;
	}

	/**
	 * @return the listaUsuario
	 */
	public List<UsuarioTO> getListaUsuario() {
		return listaUsuario;
	}

	/**
	 * @param listaUsuario
	 *            the listaUsuario to set
	 */
	public void setListaUsuario(List<UsuarioTO> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

}
