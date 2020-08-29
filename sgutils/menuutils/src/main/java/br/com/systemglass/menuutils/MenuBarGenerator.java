/**  
 *menuutils - MenuBarGenerator.java
 * 
 * Data de criacao 16/12/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menuutils;

import br.com.systemglass.menuutils.exception.SystemNameNotFoundException;

/**
 * @author Thiago Augusto
 *
 */
public interface MenuBarGenerator {
	public abstract Object getBarraMenu(String paramString)
			throws SystemNameNotFoundException;
}
