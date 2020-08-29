/**  
 * ejbutils - GenericIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.to.generics;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import br.com.flexsolutions.ejbutils.constants.Idioma;

/**
 * @author Thiago Augusto
 * 
 */
public class GenericIn implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String login;
	protected Idioma idioma;
	protected Locale locale;

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Idioma getIdioma() {
		return this.idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getResourceBundle(String bundleName) {
		if (this.locale == null) {
			if (this.idioma == null) {
				this.idioma = Idioma.PT_BR;
			}
			switch (this.idioma) {
			case PT_BR:
				this.locale = new Locale(Idioma.PT_BR.idioma(),
						Idioma.PT_BR.pais());
				break;
			case EN_US:
				this.locale = new Locale(Idioma.EN_US.idioma(),
						Idioma.EN_US.pais());
				break;
			default:
				this.locale = new Locale(Idioma.PT_BR.idioma(),
						Idioma.PT_BR.pais());
			}
		}
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName,
				this.locale);

		return bundle;
	}
}
