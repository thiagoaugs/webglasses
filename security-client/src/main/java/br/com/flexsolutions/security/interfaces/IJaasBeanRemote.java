/**
 *jaas-client - IJaasBeanRemote.java
 * 
 * Data de criacao 13/11/2015
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

import br.com.flexsolutions.security.pojo.TelaMenu;

/**
 * @author Thiago Augusto
 *
 */
@Remote
public abstract interface IJaasBeanRemote extends Serializable {

	/**
	 * @param paramString
	 *            - nomeSistema
	 * @param paramString2
	 *            - login usuario
	 * @param paramsrString3
	 *            - senha usuario
	 * @return
	 */
	public Boolean doLogin(String paramString1, String paramsrString2);

	/**
	 * Interface para carregar o menu do sistema.
	 * 
	 * @param in
	 * @return {@link GetMenuSistemaOut}
	 */
	public List<TelaMenu> getMenuSistema(String appName, String usuario);
}
