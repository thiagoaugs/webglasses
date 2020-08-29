/**  
 *menuutils - GenericMenuBar.java
 * 
 * Data de criacao 16/12/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menuutils;

import java.util.ArrayList;
import java.util.List;

import br.com.systemglass.security.pojo.TelaMenu;

/**
 * @author Thiago Augusto
 *
 */
public class GenericMenuBar {

	private List<TelaMenu> itensMenu = new ArrayList<TelaMenu>();

	public List<TelaMenu> getItensMenu() {
		return this.itensMenu;
	}

	public void addMenuItem(TelaMenu menuItem) {
		this.itensMenu.add(menuItem);
	}

}
