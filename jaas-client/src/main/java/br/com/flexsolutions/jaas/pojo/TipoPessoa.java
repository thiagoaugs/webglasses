/**  
 * admin-ejb - TipoPessoa.java
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
@Table(name = "ADM_TIPO_PESSOA")
@NamedQuery(name = "TipoPessoa.findAll", query = "SELECT u FROM TipoPessoa u")
public class TipoPessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADMIN_TIPO_PESSOA_TPSID_GENERATOR", sequenceName = "ADMIN_TIPO_PESSOA_TPS_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMIN_TIPO_PESSOA_TPSID_GENERATOR")
	@Column(name = "adm_tps_id")
	private Integer tpsId;

	@Column(name = "adm_tps_descricao")
	private String tpsDescricao;

	/**
	 * @return the tpsId
	 */
	public Integer getTpsId() {
		return tpsId;
	}

	/**
	 * @param tpsId
	 *            the tpsId to set
	 */
	public void setTpsId(Integer tpsId) {
		this.tpsId = tpsId;
	}

	/**
	 * @return the tpsDescricao
	 */
	public String getTpsDescricao() {
		return tpsDescricao;
	}

	/**
	 * @param tpsDescricao
	 *            the tpsDescricao to set
	 */
	public void setTpsDescricao(String tpsDescricao) {
		this.tpsDescricao = tpsDescricao;
	}

}
