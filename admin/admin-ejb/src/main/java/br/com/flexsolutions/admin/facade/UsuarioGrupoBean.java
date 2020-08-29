/**  
 * admin-ejb - UsuarioGrupoBean.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.flexsolutions.admin.bo.UsuarioGrupoBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanLocal;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class UsuarioGrupoBean implements IUsuarioGrupoBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioGrupoBO ugpBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanRemote#cadastrar
	 * (br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoIn)
	 */
	@Override
	public CadastrarUsuarioGrupoOut cadastrar(CadastrarUsuarioGrupoIn in) {
		CadastrarUsuarioGrupoOut out;

		try {
			out = ugpBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarUsuarioGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanRemote#excluir
	 * (br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoIn)
	 */
	@Override
	public ExcluirUsuarioGrupoOut excluir(ExcluirUsuarioGrupoIn in) {
		ExcluirUsuarioGrupoOut out;

		try {
			out = ugpBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirUsuarioGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanRemote#filtrar
	 * (br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoIn)
	 */
	@Override
	public FiltrarUsuarioGrupoOut filtrar(FiltrarUsuarioGrupoIn in) {
		FiltrarUsuarioGrupoOut out;

		try {
			out = ugpBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarUsuarioGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	@Override
	public FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut filtrarUsuarioPorSistemaNaoConfigurado(
			FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn in) {
		FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut out;

		try {
			out = ugpBO.filtrarUsuarioPorSistemaNaoConfigurado(in);
		} catch (WebglassException e) {
			out = new FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
