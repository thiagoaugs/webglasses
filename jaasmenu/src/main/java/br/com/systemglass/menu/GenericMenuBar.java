/**  
 * jaasmenu - GenericMenuBar.java
 * 
 * Data de criacao 15/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menu;

import java.util.ArrayList;
import java.util.List;

import br.com.systemglass.admin.to.TelaMenuTO;

/**
 * @author Thiago Augusto
 *
 */
public class GenericMenuBar {
	 private List<TelaMenuTO> itensMenu = new ArrayList();

	 
	public List<TelaMenuTO> getItensMenu() {
		return itensMenu;
	}

	public void addMenuItem(TelaMenuTO menuItem) {
		this.itensMenu.add(menuItem);
	}
}
