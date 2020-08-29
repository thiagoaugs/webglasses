/**  
 * jaasmenu - PersonalizedMenuGenerator.java
 * 
 * Data de criacao 16/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menu;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.systemglass.admin.facade.interfaces.ITelaMenuBeanRemote;
import br.com.systemglass.admin.to.TelaMenuGrupoTO;
import br.com.systemglass.admin.to.TelaMenuTO;
import br.com.systemglass.admin.to.telamenu.GetMenuSistemaIn;
import br.com.systemglass.admin.to.telamenu.GetMenuSistemaOut;
import br.com.systemglass.menu.exception.SystemNameNotFoundException;

/**
 * @author Thiago Augusto
 *
 */
public abstract class PersonalizedMenuGenerator {

	protected static Logger logger = Logger
			.getLogger(PersonalizedMenuGenerator.class);

	protected abstract Object parseGenericToCustomMenuBar(
			GenericMenuBar paramGenericMenuBar);

	@EJB(name = "telaMenuBean")
	private ITelaMenuBeanRemote tmeBean;

	public Object getBarraMenu(String appName)
			throws SystemNameNotFoundException {
		Object menuBar = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		GetMenuSistemaIn in = new GetMenuSistemaIn();

		try {
			if (fc.getExternalContext().getUserPrincipal() != null) {
				String usuario = fc.getExternalContext().getUserPrincipal()
						.getName();

				menuBar = session.getAttribute("menuSistema");
			
				if (menuBar == null) {
					in.setSistema(appName);
					in.setUsuario(usuario);
					GetMenuSistemaOut out = tmeBean.getMenuSistema(in);

					List<TelaMenuTO> menus = out.getListaTelaMenu();

					// FactoryJNDI.getFAASLogin()
					// .getMenuSistema(appName, usuario);
					menuBar = generateCustomMenuBar(menus);
					session.setAttribute("menuSistema", menuBar);
				}
			}
		} catch (Exception e) {
			logger.error("ERROR Geracao Menu!", e);
		}
		return menuBar;
	}

	protected Object generateCustomMenuBar(List<TelaMenuTO> menusCadastrados) {
		GenericMenuBar gmb = getBarWithPersonalizedGenericMenu(menusCadastrados);
		Object customMenuBar = parseGenericToCustomMenuBar(gmb);

		return customMenuBar;
	}

	protected GenericMenuBar getBarWithPersonalizedGenericMenu(
			List<TelaMenuTO> menusCadastrados) {
		TelaMenuTO todosMenus = new TelaMenuTO();
		todosMenus.setTmeMenuFilhos(new LinkedHashSet(menusCadastrados));

		TelaMenuTO menusDoUsuario = generatePersonalizedGenericMenu(todosMenus);
		GenericMenuBar menuBar = new GenericMenuBar();
		for (TelaMenuTO menu : menusDoUsuario.getTmeMenuFilhos()) {
			menuBar.addMenuItem(menu);
		}
		return menuBar;
	}

	protected void printStructure(Collection<TelaMenuTO> menus, String nivel) {
		if (logger.isDebugEnabled()) {
			nivel = nivel + "+";
			for (TelaMenuTO menu : menus) {
				logger.debug(nivel + " " + menu.getTmeLegenda());

				Set<TelaMenuTO> filhos = menu.getTmeMenuFilhos();
				if ((filhos != null) && (filhos.size() > 0)) {
					printStructure(filhos, nivel);
				}
			}
		}
	}

	private TelaMenuTO verificaPermissao(TelaMenuTO item) {
		for (TelaMenuGrupoTO itemGrupo : item.getSistemaTelaMenuGrupo()) {
			boolean permissao = FacesContext.getCurrentInstance()
					.getExternalContext()
					.isUserInRole(itemGrupo.getTmgGrupo().getGpoNome());
			if (permissao) {
				item.setTmeAtivado(Boolean.valueOf(true));
			}
		}
		Set<TelaMenuTO> setMenuFilho = item.getTmeMenuFilhos();
		for (TelaMenuTO menu : setMenuFilho) {
			verificaPermissao(menu);
		}
		return item;
	}

	private TelaMenuTO generatePersonalizedGenericMenu(TelaMenuTO menuPai) {
		TelaMenuTO x = new TelaMenuTO();

		x.setTmeMenuFilhos(new LinkedHashSet());

		Set<TelaMenuTO> subMenus = menuPai.getTmeMenuFilhos();
		if ((subMenus == null) || (subMenus.isEmpty())) {
			return menuPai;
		}
		Set<TelaMenuTO> nvFilhos = x.getTmeMenuFilhos();
		for (TelaMenuTO tela : subMenus) {
			TelaMenuTO n = verificaPermissao(tela);
			if (n.getTmeAtivado().booleanValue()) {
				nvFilhos.add(generatePersonalizedGenericMenu(n));
			}
		}
		menuPai.setTmeMenuFilhos(nvFilhos);

		return menuPai;
	}
}
