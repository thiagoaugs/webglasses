/**  
 * ejbutils - Idioma.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.constants;

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
