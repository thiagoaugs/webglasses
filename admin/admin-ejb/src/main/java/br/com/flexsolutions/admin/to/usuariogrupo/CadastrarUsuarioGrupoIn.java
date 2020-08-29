/**  
 * admin-ejb - CadastrarUsuarioGrupoIn.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.usuariogrupo;

import java.util.List;

import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericIn;

/**
 * @author Thiago Augusto
 *
 */
public class CadastrarUsuarioGrupoIn extends GenericIn {
	private static final long serialVersionUID = 1L;

	private List<UsuarioSistemaTO> listaUsuarioSistema;
	private GrupoTO gpoTO;
	private SistemaTO sisTO;
	

	/**
	 * @return the listaUsuarioSistema
	 */
	public List<UsuarioSistemaTO> getListaUsuarioSistema() {
		return listaUsuarioSistema;
	}

	/**
	 * @param listaUsuarioSistema
	 *            the listaUsuarioSistema to set
	 */
	public void setListaUsuarioSistema(
			List<UsuarioSistemaTO> listaUsuarioSistema) {
		this.listaUsuarioSistema = listaUsuarioSistema;
	}

	/**
	 * @return the gpoTO
	 */
	public GrupoTO getGpoTO() {
		return gpoTO;
	}

	/**
	 * @param gpoTO
	 *            the gpoTO to set
	 */
	public void setGpoTO(GrupoTO gpoTO) {
		this.gpoTO = gpoTO;
	}

	

	/**
	 * @return the sisTO
	 */
	public SistemaTO getSisTO() {
		return sisTO;
	}

	/**
	 * @param sisTO
	 *            the sisTO to set
	 */
	public void setSisTO(SistemaTO sisTO) {
		this.sisTO = sisTO;
	}
}
