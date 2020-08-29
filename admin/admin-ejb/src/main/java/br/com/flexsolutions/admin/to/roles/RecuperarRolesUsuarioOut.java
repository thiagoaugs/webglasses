/**
 * admin-ejb - RecuperarRolesUsuarioOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.roles;

import java.util.List;

import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class RecuperarRolesUsuarioOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private List<String> listaRolesUsuario;

	public List<String> getListaRolesUsuario() {
		return listaRolesUsuario;
	}

	public void setListaRolesUsuario(List<String> listaRolesUsuario) {
		this.listaRolesUsuario = listaRolesUsuario;
	}

}
