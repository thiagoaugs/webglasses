/**  
 * jass-ejb - PessoaTO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Thiago Augusto
 *
 */
public class PessoaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer pesId;

	private String pesCpfCnpj;

	private String pesNomeFantasia;

	private String pesSobrenomeRazao;

	private String pesRgIe;

	private Date pesDtCad;

	private String pesTipo;

	private String pesTipoPessoa;

	/**
	 * @return the pesId
	 */
	public Integer getPesId() {
		return pesId;
	}

	/**
	 * @param pesId
	 *            the pesId to set
	 */
	public void setPesId(Integer pesId) {
		this.pesId = pesId;
	}

	/**
	 * @return the pesCpfCnpj
	 */
	public String getPesCpfCnpj() {
		return pesCpfCnpj;
	}

	/**
	 * @param pesCpfCnpj
	 *            the pesCpfCnpj to set
	 */
	public void setPesCpfCnpj(String pesCpfCnpj) {
		this.pesCpfCnpj = pesCpfCnpj;
	}

	/**
	 * @return the pesNomeFantasia
	 */
	public String getPesNomeFantasia() {
		return pesNomeFantasia;
	}

	/**
	 * @param pesNomeFantasia
	 *            the pesNomeFantasia to set
	 */
	public void setPesNomeFantasia(String pesNomeFantasia) {
		this.pesNomeFantasia = pesNomeFantasia;
	}

	/**
	 * @return the pesSobrenomeRazao
	 */
	public String getPesSobrenomeRazao() {
		return pesSobrenomeRazao;
	}

	/**
	 * @param pesSobrenomeRazao
	 *            the pesSobrenomeRazao to set
	 */
	public void setPesSobrenomeRazao(String pesSobrenomeRazao) {
		this.pesSobrenomeRazao = pesSobrenomeRazao;
	}

	/**
	 * @return the pesRgIe
	 */
	public String getPesRgIe() {
		return pesRgIe;
	}

	/**
	 * @param pesRgIe
	 *            the pesRgIe to set
	 */
	public void setPesRgIe(String pesRgIe) {
		this.pesRgIe = pesRgIe;
	}

	/**
	 * @return the pesDtCad
	 */
	public Date getPesDtCad() {
		return pesDtCad;
	}

	/**
	 * @param pesDtCad
	 *            the pesDtCad to set
	 */
	public void setPesDtCad(Date pesDtCad) {
		this.pesDtCad = pesDtCad;
	}

	/**
	 * @return the pesTipo
	 */
	public String getPesTipo() {
		return pesTipo;
	}

	/**
	 * @param pesTipo
	 *            the pesTipo to set
	 */
	public void setPesTipo(String pesTipo) {
		this.pesTipo = pesTipo;
	}

	/**
	 * @return the pesTipoPessoa
	 */
	public String getPesTipoPessoa() {
		return pesTipoPessoa;
	}

	/**
	 * @param pesTipoPessoa
	 *            the pesTipoPessoa to set
	 */
	public void setPesTipoPessoa(String pesTipoPessoa) {
		this.pesTipoPessoa = pesTipoPessoa;
	}

}
