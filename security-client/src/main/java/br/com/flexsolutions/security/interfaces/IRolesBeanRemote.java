/**  
 *security-client - IRolesBeanRemote.java
 * 
 * Data de criacao 18/01/2016
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

/**
 * @author Thiago Augusto
 *
 */

@Remote
public abstract interface IRolesBeanRemote extends Serializable {

	/**
	 * interface para o metodo que recupera as roles do usuarios para manter na
	 * sessao
	 * 
	 * @param appName
	 * @param usuario
	 * @return Lista com as roles do usuario
	 */
	public List<String> recuperarRolesUsuarioSistema(String appName,
			String usuario);

}
