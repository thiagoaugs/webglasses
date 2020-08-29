/**  
 * jass-ejb - UsuarioBean.java
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

import br.com.flexsolutions.admin.bo.UsuarioBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanLocal;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class UsuarioBean implements IUsuarioBeanLocal {
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBO usuBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.IUsuarioBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.usuario.CadastrarUsuarioIn)
	 */
	@Override
	public CadastrarUsuarioOut cadastrar(CadastrarUsuarioIn in) {
		CadastrarUsuarioOut out;

		try {
			out = usuBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.IUsuarioBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.usuario.AlterarUsuarioIn)
	 */
	@Override
	public AlterarUsuarioOut alterar(AlterarUsuarioIn in) {
		AlterarUsuarioOut out;
		try {
			out = usuBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanRemote#bloqueio
	 * (br.com.flexsolutions.admin.to.usuario.BloqUsuarioIn)
	 */
	@Override
	public BloqUsuarioOut bloqueio(BloqUsuarioIn in) {

		BloqUsuarioOut out;
		try {
			out = usuBO.controlaBloqueio(in);
		} catch (WebglassException e) {
			out = new BloqUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.IUsuarioBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.usuario.ExcluirUsuarioIn)
	 */
	@Override
	public ExcluirUsuarioOut excluir(ExcluirUsuarioIn in) {
		ExcluirUsuarioOut out;

		try {
			out = usuBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.IUsuarioBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.usuario.ListarUsuarioIn)
	 */
	@Override
	public FiltrarUsuarioOut filtrar(FiltrarUsuarioIn in) {
		FiltrarUsuarioOut out;

		try {
			out = usuBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanRemote#listar(br.com.flexsolutions.admin.to.usuario.ListarUsuarioIn)
	 */
	@Override
	public ListarUsuarioOut listar(ListarUsuarioIn in) {
		ListarUsuarioOut out;

		try {
			out = usuBO.listar(in);
		} catch (WebglassException e) {
			out = new ListarUsuarioOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
