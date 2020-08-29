/**  
 * jaas - MenuBarGenerator.java
 * 
 * Data de criacao 15/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menu;

import br.com.systemglass.menu.exception.SystemNameNotFoundException;

/**
 * @author Thiago Augusto
 *
 */
public abstract interface MenuBarGenerator {
	public abstract Object getBarraMenu(String paramString)
			throws SystemNameNotFoundException;
}
