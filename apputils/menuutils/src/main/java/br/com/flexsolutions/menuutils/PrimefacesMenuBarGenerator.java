/**  
 *menuutils - PrimefacesMenuBarGenerator.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.menuutils;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;

import br.com.flexsolutions.security.pojo.TelaMenu;

/**
 * @author Thiago Augusto
 *
 */
public class PrimefacesMenuBarGenerator extends PersonalizedMenuGenerator
		implements MenuBarGenerator {
	
	
	 protected Object parseGenericToCustomMenuBar(GenericMenuBar paramGenericMenuBar)
	  {
	    MenuModel menuModel = new DefaultMenuModel();
	    if (paramGenericMenuBar.getItensMenu() == null)
	    {
	      logger.debug("Nenhum item de menu informado!!");
	      return menuModel;
	    }
	    for (TelaMenu itemMenu : paramGenericMenuBar.getItensMenu()) {
	      if ((itemMenu.getTmeFilhos() != null) && (!itemMenu.getTmeFilhos().isEmpty()))
	      {
	        Submenu subMenu = parseLModItemToPrimeSubMenu(itemMenu);
	        menuModel.addElement(subMenu);
	      }
	      else
	      {
	        MenuItem menuItem = parseLModItemToPrimeMenuItem(itemMenu);
	        menuModel.addElement(menuItem);
	      }
	    }
	    return menuModel;
	  }
	  
	  private DefaultSubMenu parseLModItemToPrimeSubMenu(TelaMenu lmi)
	  {
	    DefaultSubMenu subMenu = new DefaultSubMenu();
	    
	    subMenu.setLabel(lmi.getTmeLegenda());
//	    if (!StringUtils.isBlank(lmi.getTmeIcone())) {
//	      subMenu.setIcon(lmi.getMenuIcone());
//	    }
	    for (TelaMenu itemMenu : lmi.getTmeFilhos())
	    {
	      MenuItem menuItem = parseLModItemToPrimeMenuItem(itemMenu);
	      subMenu.addElement(menuItem);
	    }
	    return subMenu;
	  }
	  
	  private DefaultMenuItem parseLModItemToPrimeMenuItem(TelaMenu lmi)
	  {
	    DefaultMenuItem menuItem = new DefaultMenuItem();
	    
	    menuItem.setValue(lmi.getTmeLegenda());
	    menuItem.setUrl(lmi.getTmeAcao());
	    menuItem.setUpdate("@all");
	    menuItem.setAjax(true);
//	    if (!StringUtils.isBlank(lmi.getMenuIcone())) {
//	      menuItem.setIcon(lmi.getMenuIcone());
//	    }
	    return menuItem;
	  }
}
