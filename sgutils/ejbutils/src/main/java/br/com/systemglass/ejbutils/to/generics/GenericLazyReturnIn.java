/**  
 * ejbutils - GenericLazyReturnIn.java
 * 
 * Data de criacao 08/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright SystemGlass - Todos os direitos reservados.
 *
 */
package br.com.systemglass.ejbutils.to.generics;

/**
 * @author Thiago Augusto
 *
 */
public class GenericLazyReturnIn extends GenericIn {
	private static final long serialVersionUID = 1L;
	protected boolean fazerCount;
	protected Integer totalRegistros;
	protected Integer inicioRegistros;

	public boolean isFazerCount() {
		return this.fazerCount;
	}

	public void setFazerCount(boolean fazerCount) {
		this.fazerCount = fazerCount;
	}

	public Integer getTotalRegistros() {
		return this.totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public Integer getInicioRegistros() {
		return this.inicioRegistros;
	}

	public void setInicioRegistros(Integer inicioRegistros) {
		this.inicioRegistros = inicioRegistros;
	}
}
