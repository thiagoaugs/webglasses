/**  
 * jass-ejb - AlterarGrupoIn.java
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

import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class RecuperarGruposOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private List<String> gruposUsuario;

	public List<String> getGruposUsuario() {
		return gruposUsuario;
	}

	public void setGruposUsuario(List<String> gruposUsuario) {
		this.gruposUsuario = gruposUsuario;
	}

}
