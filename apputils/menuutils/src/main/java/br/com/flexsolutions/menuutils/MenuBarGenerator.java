/**  
 *menuutils - MenuBarGenerator.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.menuutils;

import br.com.flexsolutions.menuutils.exception.SystemNameNotFoundException;

/**
 * @author Thiago Augusto
 *
 */
public interface MenuBarGenerator {
	public abstract Object getBarraMenu(String paramString)
			throws SystemNameNotFoundException;
}
