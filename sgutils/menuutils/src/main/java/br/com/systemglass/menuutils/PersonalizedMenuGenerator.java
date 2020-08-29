/**  
 *menuutils - PersonalizedMenuGenerator.java
 * 
 * Data de criacao 16/12/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.menuutils;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;

import br.com.systemglass.menuutils.exception.SystemNameNotFoundException;
import br.com.systemglass.security.interfaces.IJaasBeanRemote;
import br.com.systemglass.security.pojo.TelaMenu;
import br.com.systemglass.security.pojo.TelaMenuGrupo;

/**
 * @author Thiago Augusto
 *
 */
/**
 * @author Thiago Augusto
 *
 */
public abstract class PersonalizedMenuGenerator {

	protected static Logger logger = Logger
			.getLogger(PersonalizedMenuGenerator.class);

	protected abstract Object parseGenericToCustomMenuBar(
			GenericMenuBar paramGenericMenuBar);

	/**
	 * Recebe o nome da app como parametro para fazer a busca na tabela de
	 * telamenu, passando tbm o usuario para que seja retornada apenas as telas
	 * que o usuario possui acesso
	 * 
	 * @param appName
	 * @return
	 * @throws SystemNameNotFoundException
	 */
	public Object getBarraMenu(String appName)
			throws SystemNameNotFoundException {
		Object menuBar = null;

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);

		try {
			if (fc.getExternalContext().getUserPrincipal() != null) {
				String usuario = fc.getExternalContext().getUserPrincipal()
						.getName();

				menuBar = session.getAttribute("menuSistema");
				if (menuBar == null) {
					System.out.println("invocando ejb para carregar o menu");

					try {
						InitialContext ctx = new InitialContext();
						IJaasBeanRemote ejb = (IJaasBeanRemote) ctx
								.lookup("ejb:admin/admin-ejb/JaasBean!br.com.systemglass.security.interfaces.IJaasBeanRemote");
						List<TelaMenu> menus = ejb.getMenuSistema(appName,
								usuario);

						// apos carregar os itens do menu para o usaurio e
						// sistema, é chamado o metodo que ira montar o menu
						menuBar = generateCustomMenuBar(menus);
					} catch (NamingException ex) {
						ex.printStackTrace();
					}

					session.setAttribute("menuSistema", menuBar);
				}
			}
		} catch (Exception e) {
			logger.error("ERROR Geracao Menu!", e);
		}
		return menuBar;
	}

	/**
	 * Passa os menus cadastrados e chama o metodo que ira retornar um
	 * genericmenubar depois chama o custommenubar passando o genericmenubar
	 * 
	 * @param menusCadastrados
	 * @return
	 */
	protected Object generateCustomMenuBar(List<TelaMenu> menusCadastrados) {

		GenericMenuBar gmb = getBarWithPersonalizedGenericMenu(menusCadastrados);

		Object customMenuBar = parseGenericToCustomMenuBar(gmb);

		return customMenuBar;
	}

	/**
	 * @param menusCadastrados
	 * @return
	 */
	protected GenericMenuBar getBarWithPersonalizedGenericMenu(
			List<TelaMenu> menusCadastrados) {
		// instancia novo objeto
		TelaMenu todosMenus = new TelaMenu();

		// adciona todos as telas como filho do novo obj
		todosMenus.setTmeFilhos(new LinkedHashSet(menusCadastrados));

		// instancia novo objeto para ser o menu do usuario, e chama o metodo
		// para montar o menu
		TelaMenu menusDoUsuario = generatePersonalizedGenericMenu(todosMenus);

		GenericMenuBar menuBar = new GenericMenuBar();

		for (TelaMenu menu : menusDoUsuario.getTmeFilhos()) {
			menuBar.addMenuItem(menu);
		}

		return menuBar;
	}

	protected void printStructure(Collection<TelaMenu> menus, String nivel) {
		if (logger.isDebugEnabled()) {
			nivel = nivel + "+";
			for (TelaMenu menu : menus) {
				logger.debug(nivel + " " + menu.getTmeLegenda());

				Set<TelaMenu> filhos = menu.getTmeFilhos();
				if ((filhos != null) && (filhos.size() > 0)) {
					printStructure(filhos, nivel);
				}
			}
		}
	}

	private TelaMenu verificaPermissao(TelaMenu item) {
		// item.setTmeAtivado(Boolean.valueOf(true));
		for (TelaMenuGrupo itemGrupo : item.getSistemaTelaMenuGrupo()) {

			boolean permissao = FacesContext.getCurrentInstance()
					.getExternalContext()
					.isUserInRole(itemGrupo.getTmgGrupo().getGpoNome());
			if (permissao) {
				item.setTmeAtivado(Boolean.valueOf(true));
			}
		}
		Set<TelaMenu> setMenuFilho = item.getTmeFilhos();
		for (TelaMenu menu : setMenuFilho) {
			verificaPermissao(menu);
		}
		return item;
	}

	/**
	 * Recebe um menu pai, com todos as telas de filho
	 * 
	 * @param menuPai
	 * @return
	 */
	private TelaMenu generatePersonalizedGenericMenu(TelaMenu menuPai) {

		System.out.println("menu pai > " + menuPai.getTmeLegenda()
				+ "filhos > " + menuPai.getTmeFilhos().toString());

		TelaMenu x = new TelaMenu();
		x.setTmeFilhos(new LinkedHashSet());

		Set<TelaMenu> subMenus = menuPai.getTmeFilhos();

		if ((subMenus == null) || (subMenus.isEmpty())) {
			System.out.println(menuPai.getTmeLegenda()
					+ " (subMenus == null) || (subMenus.isEmpty() ");

			return menuPai;

		}

		Set<TelaMenu> nvFilhos = x.getTmeFilhos();

		System.out.println(" x.getTmeFilhos() " + x.getTmeFilhos().toString());

		for (TelaMenu tela : subMenus) {
			System.out
					.println("entrou no for - item - " + tela.getTmeLegenda());

			TelaMenu n = verificaPermissao(tela);
			System.out.println("submenu > " + tela.getTmeLegenda());
			if (n.getTmeAtivado().booleanValue()) {
				nvFilhos.add(generatePersonalizedGenericMenu(n));
			} 
			System.out.println("teste ");
		}

		System.out.println("menuPai > " + menuPai.getTmeLegenda());

		for (TelaMenu tela : nvFilhos) {
			System.out.println("filhos > " + tela.getTmeLegenda());
		}
		menuPai.setTmeFilhos(nvFilhos);

		return menuPai;
	}

}
