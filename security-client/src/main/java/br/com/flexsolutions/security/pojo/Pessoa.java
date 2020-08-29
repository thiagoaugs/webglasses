/**  
 * jass-ejb - Pessoa.java
 * 
 * Data de criacao 26/06/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_PESSOA")
@NamedQuery(name = "Pessoa.findAll", query = "SELECT x FROM Pessoa x")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_PESSOA_PESID_GENERATOR", sequenceName = "ADMIN_PESSOA_PES_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_PESSOA_PESID_GENERATOR")
	@Column(name = "adm_pes_id")
	private Integer pesId;

	@Column(name = "adm_pes_cpf_cnpj")
	private String pesCpfCnpj;

	@Column(name = "adm_pes_nome_fantasia")
	private String pesNomeFantasia;

	@Column(name = "adm_pes_sobrenome_razao")
	private String pesSobrenomeRazao;

	@Column(name = "adm_pes_rg_ie")
	private String pesRgIe;

	@Temporal(TemporalType.DATE)
	@Column(name = "adm_pes_data_cad")
	private Date pesDtCad;

	@Column(name = "adm_pes_tipo")
	private String pesTipo;

	@Column(name = "adm_pes_tipo_pessoa")
	private String pesTipoPessoa;

	public Pessoa() {
	}

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
	 * @param pesDtCad the pesDtCad to set
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
