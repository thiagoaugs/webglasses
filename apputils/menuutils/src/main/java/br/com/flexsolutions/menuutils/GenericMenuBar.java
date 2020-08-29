/**  
 * menuutils - GenericMenuBar.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.menuutils;

import java.util.ArrayList;
import java.util.List;

import br.com.flexsolutions.security.pojo.TelaMenu;

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
