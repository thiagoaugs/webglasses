/**  
 *security-client - IGrupoBeanRemote.java
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
public abstract interface IGrupoBeanRemote extends Serializable {

	/**
	 * Interface utilizada pelo login para recuperar os grupos do usuário
	 * logado.
	 * 
	 * @param appName
	 * @param usuario
	 * @return
	 */
	public List<String> recuperarGruposUsuarioSistema(String appName,
			String usuario);

}
