/**  
 * admin-ejb  - Usuario.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ADM_USUARIO")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_USUARIO_USUID_GENERATOR", sequenceName = "ADMIN_USUARIO_USU_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_USUARIO_USUID_GENERATOR")
	@Column(name = "adm_usu_id")
	private Integer usuId;

	@Column(name = "adm_usu_cpf", unique = true, nullable = false)
	private String usuCpf;

	@Column(name = "adm_usu_nome")
	private String usuNome;

	@Column(name = "adm_usu_login", unique = true)
	private String usuLogin;

	@Column(name = "adm_usu_senha")
	private String usuSenha;

	@Temporal(TemporalType.DATE)
	@Column(name = "adm_usu_data_cad")
	private Date usuDtCad;

	@Temporal(TemporalType.DATE)
	@Column(name = "adm_usu_data_alt")
	private Date usuDtAlt;

	@Column(name = "adm_usu_bloq")
	private Boolean usuBloq;

	@Column(name = "adm_usu_email")
	private String usuEmail;

	@Column(name = "adm_usu_telefone")
	private String usuTelefone;

	@Column(name = "adm_usu_ramal")
	private String usuRamal;

	@Column(name = "adm_usu_celular")
	private String usuCelular;

	@Column(name = "adm_usu_cep")
	private String usuCep;

	@Column(name = "adm_usu_rua")
	private String usuRua;

	@Column(name = "adm_usu_bairro")
	private String usuBairro;

	@Column(name = "adm_usu_numero")
	private Integer usuNumero;

	@Column(name = "adm_usu_complemento")
	private String usuComplemento;

	@ManyToOne
	@JoinColumn(name = "adm_usu_pes_id", nullable = true)
	private Pessoa usuPessoa;

	/**
	 * @return the usuId
	 */
	public Integer getUsuId() {
		return usuId;
	}

	/**
	 * @param usuId
	 *            the usuId to set
	 */
	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	/**
	 * @return the usuCpf
	 */
	public String getUsuCpf() {
		return usuCpf;
	}

	/**
	 * @param usuCpf
	 *            the usuCpf to set
	 */
	public void setUsuCpf(String usuCpf) {
		this.usuCpf = usuCpf;
	}

	/**
	 * @return the usuNome
	 */
	public String getUsuNome() {
		return usuNome;
	}

	/**
	 * @param usuNome
	 *            the usuNome to set
	 */
	public void setUsuNome(String usuNome) {
		this.usuNome = usuNome;
	}

	/**
	 * @return the usuLogin
	 */
	public String getUsuLogin() {
		return usuLogin;
	}

	/**
	 * @param usuLogin
	 *            the usuLogin to set
	 */
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	/**
	 * @return the usuSenha
	 */
	public String getUsuSenha() {
		return usuSenha;
	}

	/**
	 * @param usuSenha
	 *            the usuSenha to set
	 */
	public void setUsuSenha(String usuSenha) {
		this.usuSenha = usuSenha;
	}

	/**
	 * @return the usuDtCad
	 */
	public Date getUsuDtCad() {
		return usuDtCad;
	}

	/**
	 * @param usuDtCad
	 *            the usuDtCad to set
	 */
	public void setUsuDtCad(Date usuDtCad) {
		this.usuDtCad = usuDtCad;
	}

	/**
	 * @return the usuDtAlt
	 */
	public Date getUsuDtAlt() {
		return usuDtAlt;
	}

	/**
	 * @param usuDtAlt
	 *            the usuDtAlt to set
	 */
	public void setUsuDtAlt(Date usuDtAlt) {
		this.usuDtAlt = usuDtAlt;
	}

	/**
	 * @return the usuBloq
	 */
	public Boolean getUsuBloq() {
		return usuBloq;
	}

	/**
	 * @param usuBloq
	 *            the usuBloq to set
	 */
	public void setUsuBloq(Boolean usuBloq) {
		this.usuBloq = usuBloq;
	}

	/**
	 * @return the usuEmail
	 */
	public String getUsuEmail() {
		return usuEmail;
	}

	/**
	 * @param usuEmail
	 *            the usuEmail to set
	 */
	public void setUsuEmail(String usuEmail) {
		this.usuEmail = usuEmail;
	}

	/**
	 * @return the usuTelefone
	 */
	public String getUsuTelefone() {
		return usuTelefone;
	}

	/**
	 * @param usuTelefone
	 *            the usuTelefone to set
	 */
	public void setUsuTelefone(String usuTelefone) {
		this.usuTelefone = usuTelefone;
	}

	/**
	 * @return the usuRamal
	 */
	public String getUsuRamal() {
		return usuRamal;
	}

	/**
	 * @param usuRamal
	 *            the usuRamal to set
	 */
	public void setUsuRamal(String usuRamal) {
		this.usuRamal = usuRamal;
	}

	/**
	 * @return the usuCelular
	 */
	public String getUsuCelular() {
		return usuCelular;
	}

	/**
	 * @param usuCelular
	 *            the usuCelular to set
	 */
	public void setUsuCelular(String usuCelular) {
		this.usuCelular = usuCelular;
	}

	/**
	 * @return the usuCep
	 */
	public String getUsuCep() {
		return usuCep;
	}

	/**
	 * @param usuCep
	 *            the usuCep to set
	 */
	public void setUsuCep(String usuCep) {
		this.usuCep = usuCep;
	}

	/**
	 * @return the usuRua
	 */
	public String getUsuRua() {
		return usuRua;
	}

	/**
	 * @param usuRua
	 *            the usuRua to set
	 */
	public void setUsuRua(String usuRua) {
		this.usuRua = usuRua;
	}

	/**
	 * @return the usuBairro
	 */
	public String getUsuBairro() {
		return usuBairro;
	}

	/**
	 * @param usuBairro
	 *            the usuBairro to set
	 */
	public void setUsuBairro(String usuBairro) {
		this.usuBairro = usuBairro;
	}

	/**
	 * @return the usuNumero
	 */
	public Integer getUsuNumero() {
		return usuNumero;
	}

	/**
	 * @param usuNumero
	 *            the usuNumero to set
	 */
	public void setUsuNumero(Integer usuNumero) {
		this.usuNumero = usuNumero;
	}

	/**
	 * @return the usuComplemento
	 */
	public String getUsuComplemento() {
		return usuComplemento;
	}

	/**
	 * @param usuComplemento
	 *            the usuComplemento to set
	 */
	public void setUsuComplemento(String usuComplemento) {
		this.usuComplemento = usuComplemento;
	}

	/**
	 * @return the usuPessoa
	 */
	public Pessoa getUsuPessoa() {
		return usuPessoa;
	}

	/**
	 * @param usuPessoa
	 *            the usuPessoa to set
	 */
	public void setUsuPessoa(Pessoa usuPessoa) {
		this.usuPessoa = usuPessoa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuId == null) ? 0 : usuId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (usuId == null) {
			if (other.usuId != null)
				return false;
		} else if (!usuId.equals(other.usuId))
			return false;
		return true;
	}

}