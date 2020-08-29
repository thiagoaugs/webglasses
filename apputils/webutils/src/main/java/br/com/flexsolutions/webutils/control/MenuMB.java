package br.com.flexsolutions.webutils.control;

/**  
 * webUtils - BaseMB.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import br.com.flexsolutions.menuutils.MenuBarGenerator;
import br.com.flexsolutions.menuutils.exception.MenuException;
import br.com.flexsolutions.menuutils.exception.SystemNameNotFoundException;

/**
 * Classe que controla a criacao do menu
 *
 * @author Thiago Augusto
 *
 */
@ManagedBean(name = "menuMB")
@RequestScoped
public class MenuMB extends BaseMB {
	private static final long serialVersionUID = 1L;
	private static final String PRIMEFACES_UILIBRARY = "Primefaces";
	private static final String JAASMENU_PACKAGE = "br.com.flexsolutions.menuutils.";
	private static final Logger logger = Logger.getLogger(MenuMB.class);
	private String uiLibrary;
	private MenuBarGenerator menuBarGenerator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.webutils.control.BaseMB#initPage()
	 */
	@Override
	public void initPage() {
		// TODO Auto-generated method stub

	}

	private MenuBarGenerator getMenuBarGenerator() throws MenuException {
		this.uiLibrary = getInitParameter("uiLibrary");

		if (StringUtils.isBlank(this.uiLibrary)) {
			this.uiLibrary = "Primefaces";
		} else {
			this.uiLibrary = StringUtils.capitalize(this.uiLibrary
					.toLowerCase());
			if (!this.uiLibrary.equals("Primefaces")) {
				this.uiLibrary = "Primefaces";
			}
		}
		try {
			Class<?> clazz = Class.forName(JAASMENU_PACKAGE
					+ PRIMEFACES_UILIBRARY + "MenuBarGenerator");
			this.menuBarGenerator = ((MenuBarGenerator) clazz.newInstance());
		} catch (ClassNotFoundException e) {
			logger.error(
					"Classe nao localizada no Factory do MenuBarGenerator!", e);
			throw new MenuException(e);
		} catch (Exception e) {
			logger.error("Houve um erro no Factory do MenuBarGenerator!", e);
			throw new MenuException(e);
		}
		return this.menuBarGenerator;
	}

	public Object getBarraMenu() throws MenuException {
		if (this.menuBarGenerator == null) {
			this.menuBarGenerator = getMenuBarGenerator();
		}
		Object menu;
		try {
			menu = this.menuBarGenerator.getBarraMenu(getApplicationName());
		} catch (SystemNameNotFoundException e) {
			logger.error("Houve um erro na montagem do menu!", e);
			throw new MenuException(e);
		}
		return menu;
	}

}
