/**  
 * ejbutils - Idioma.java
 * 
 * Data de criacao 09/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.ejbutils.constants;

/**
 * @author Thiago Augusto
 *
 */
public enum Idioma {

	PT_BR("pt_", "BR"), EN_US("en_", "US");

	private String idioma;
	private String pais;

	private Idioma(String idioma, String pais) {
		this.idioma = idioma;
		this.pais = pais;
	}

	/**
	 * @return the idioma
	 */
	public String idioma() {
		return idioma;
	}

	/**
	 * @return the pais
	 */
	public String pais() {
		return pais;
	}


}
