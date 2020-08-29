/**  
 * admin-ejb - SistemaTO.java
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
import java.util.List;

/**
 * @author Thiago Augusto
 *
 */
public class SistemaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer sisId;

	private String sisNome;

	private List<TelaMenuTO> sistemaTelaMenu;

	public List<TelaMenuTO> getSistemaTelaMenu() {
		return sistemaTelaMenu;
	}

	public void setSistemaTelaMenu(List<TelaMenuTO> sistemaTelaMenu) {
		this.sistemaTelaMenu = sistemaTelaMenu;
	}

	public List<GrupoTO> getSistemaGrupo() {
		return sistemaGrupo;
	}

	public void setSistemaGrupo(List<GrupoTO> sistemaGrupo) {
		this.sistemaGrupo = sistemaGrupo;
	}

	private List<GrupoTO> sistemaGrupo;
//
//	private List<Role> sistemaRoles;
//
//	private List<UsuarioSistema> sistemaUsuario;

	/**
	 * @return the sisId
	 */
	public Integer getSisId() {
		return sisId;
	}

	/**
	 * @param sisId
	 *            the sisId to set
	 */
	public void setSisId(Integer sisId) {
		this.sisId = sisId;
	}

	/**
	 * @return the sisNome
	 */
	public String getSisNome() {
		return sisNome;
	}

	/**
	 * @param sisNome
	 *            the sisNome to set
	 */
	public void setSisNome(String sisNome) {
		this.sisNome = sisNome;
	}


}
