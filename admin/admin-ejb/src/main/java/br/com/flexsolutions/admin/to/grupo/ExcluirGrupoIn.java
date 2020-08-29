/**  
 * jass-ejb - ExcluirGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.grupo;

import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class ExcluirGrupoIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private GrupoTO grupoTO;

	/**
	 * @return the grupoTO
	 */
	public GrupoTO getGrupoTO() {
		return grupoTO;
	}

	/**
	 * @param grupoTO the grupoTO to set
	 */
	public void setGrupoTO(GrupoTO grupoTO) {
		this.grupoTO = grupoTO;
	}
}
