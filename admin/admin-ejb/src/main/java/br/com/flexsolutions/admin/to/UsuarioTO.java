/**  
 * jass-ejb - UsuarioTO.java
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
public class UsuarioTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer usuId;

	private String usuCpf;

	private String usuNome;

	private String usuLogin;

	private String usuSenha;

	private Date usuDtCad;

	private Date usuDtAlt;

	private Boolean usuBloq = false;

	private String usuEmail;

	private String usuTelefone;

	private String usuRamal;

	private String usuCelular;

	private String usuCep;

	private String usuRua;

	private String usuBairro;

	private Integer usuNumero;

	private String usuComplemento;

	private PessoaTO usuPessoaTO;

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
	 * @param usuBloq the usuBloq to set
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
	 * @return the usuPessoaTO
	 */
	public PessoaTO getUsuPessoaTO() {
		return usuPessoaTO;
	}

	/**
	 * @param usuPessoaTO the usuPessoaTO to set
	 */
	public void setUsuPessoaTO(PessoaTO usuPessoaTO) {
		this.usuPessoaTO = usuPessoaTO;
	}



}
