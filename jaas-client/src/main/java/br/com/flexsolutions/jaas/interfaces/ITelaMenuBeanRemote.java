/**  
 * admin-ejb  - ITelaMenuRemote.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.interfaces;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Remote;

import br.com.flexsolutions.jaas.pojo.TelaMenu;

/**
 * @author Thiago Augusto
 *
 */
@Remote
public interface ITelaMenuBeanRemote extends Serializable {

	/**
	 * Interface para carregar o menu do sistema.
	 * 
	 * @param in
	 * @return {@link GetMenuSistemaOut}
	 */
	public List<TelaMenu> getMenuSistema(String appName, String usuario);
}
