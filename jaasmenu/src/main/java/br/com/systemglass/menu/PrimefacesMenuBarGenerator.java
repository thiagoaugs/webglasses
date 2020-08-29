package br.com.systemglass.menu;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

import br.com.systemglass.admin.to.TelaMenuTO;

/**
 * webUtils - UsuarioMB.java
 * 
 * Data de criacao 26/10/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
public class PrimefacesMenuBarGenerator extends PersonalizedMenuGenerator
		implements MenuBarGenerator {

	protected Object parseGenericToCustomMenuBar(
			GenericMenuBar paramGenericMenuBar) {
		MenuModel menuModel = new DefaultMenuModel();
		if (paramGenericMenuBar.getItensMenu() == null) {
			logger.debug("Nenhum item de menu informado!!");
			return menuModel;
		}
		for (TelaMenuTO itemMenu : paramGenericMenuBar.getItensMenu()) {
			if ((itemMenu.getTmeMenuFilhos() != null)
					&& (!itemMenu.getTmeMenuFilhos().isEmpty())) {
				Submenu subMenu = parseTelaMenuToPrimeSubMenu(itemMenu);
				menuModel.addElement(subMenu);
			} else {
				MenuItem menuItem = parseLModItemToPrimeMenuItem(itemMenu);
				menuModel.addElement(menuItem);
			}
		}
		return menuModel;
	}

	private DefaultSubMenu parseTelaMenuToPrimeSubMenu(TelaMenuTO tme) {
		DefaultSubMenu subMenu = new DefaultSubMenu();

		subMenu.setLabel(tme.getTmeLegenda());
		// if (!StringUtils.isBlank(tme.getMenuIcone())) {
		// subMenu.setIcon(tme.getMenuIcone());
		// }
		for (TelaMenuTO itemMenu : tme.getTmeMenuFilhos()) {
			MenuItem menuItem = parseLModItemToPrimeMenuItem(itemMenu);
			subMenu.addElement(menuItem);
		}
		return subMenu;
	}

	private DefaultMenuItem parseLModItemToPrimeMenuItem(TelaMenuTO tme) {
		DefaultMenuItem menuItem = new DefaultMenuItem();

		menuItem.setValue(tme.getTmeLegenda());
		menuItem.setUrl(tme.getTmeAcao());
		menuItem.setUpdate("@all");
		menuItem.setAjax(true);
		// if (!StringUtils.isBlank(tme.getMenuIcone())) {
		// menuItem.setIcon(tme.getMenuIcone());
		// }
		//
		return menuItem;
	}

}
