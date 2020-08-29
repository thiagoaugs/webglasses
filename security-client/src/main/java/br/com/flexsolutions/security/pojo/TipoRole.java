/**  
 * admin-ejb - TipoRole.java
 * 
 * Data de criacao 11/09/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Thiago Augusto
 *
 */
@Entity
@Table(name = "ADM_TIPO_ROLE")
@NamedQuery(name = "TipoRole.findAll", query = "SELECT u FROM TipoRole u")
public class TipoRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_TIPO_ROLE_TPRID_GENERATOR", sequenceName = "ADMIN_TIPO_ROLE_TPR_ID_SEQ", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_TIPO_ROLE_TPRID_GENERATOR")
	@Column(name = "adm_tpr_id")
	private Integer tprId;

	/**
	 * @return the tprId
	 */
	public Integer getTprId() {
		return tprId;
	}

	/**
	 * @param tprId
	 *            the tprId to set
	 */
	public void setTprId(Integer tprId) {
		this.tprId = tprId;
	}

	/**
	 * @return the tprDescricao
	 */
	public String getTprDescricao() {
		return tprDescricao;
	}

	/**
	 * @param tprDescricao
	 *            the tprDescricao to set
	 */
	public void setTprDescricao(String tprDescricao) {
		this.tprDescricao = tprDescricao;
	}

	@Column(name = "adm_tpr_descricao")
	private String tprDescricao;

}
