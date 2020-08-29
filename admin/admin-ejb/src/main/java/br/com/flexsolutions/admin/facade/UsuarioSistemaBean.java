/**  
 * admin-ejb - UsuarioSistemaBean.java
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

import br.com.flexsolutions.admin.bo.UsuarioSistemaBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanLocal;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class UsuarioSistemaBean implements IUsuarioSistemaBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioSistemaBO usiBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanRemote#cadastrar
	 * (br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaIn)
	 */
	@Override
	public CadastrarUsuarioSistemaOut cadastrar(CadastrarUsuarioSistemaIn in) {
		CadastrarUsuarioSistemaOut out;

		try {
			out = usiBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarUsuarioSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanRemote#alterar
	 * (br.com.flexsolutions.admin.to.usuariosistema.AlterarUsuarioSistemaIn)
	 */
//	@Override
//	public AlterarUsuarioSistemaOut alterar(AlterarUsuarioSistemaIn in) {
//		AlterarUsuarioSistemaOut out;
//		try {
//			out = usiBO.alterar(in);
//		} catch (WebglassException e) {
//			out = new AlterarUsuarioSistemaOut();
//			out.setError(e.getMessage(), e);
//		}
//
//		return out;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanRemote#excluir
	 * (br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaIn)
	 */
	@Override
	public ExcluirUsuarioSistemaOut excluir(ExcluirUsuarioSistemaIn in) {
		ExcluirUsuarioSistemaOut out;

		try {
			out = usiBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirUsuarioSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanRemote#filtrar
	 * (br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaIn)
	 */
	@Override
	public FiltrarUsuarioSistemaOut filtrar(FiltrarUsuarioSistemaIn in) {
		FiltrarUsuarioSistemaOut out;

		try {
			out = usiBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarUsuarioSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanRemote#filtrarPorSistema(br.com.flexsolutions.admin.to.usuariosistema.FiltrarPorSistemaIn)
	 */
//	@Override
//	public FiltrarPorSistemaOut filtrarPorSistema(FiltrarPorSistemaIn in) {
//		FiltrarPorSistemaOut out;
//		try {
//			out = usiBO.filtrarPorSistema(in);
//		} catch (WebglassException e) {
//			out = new FiltrarPorSistemaOut();
//			out.setError(e.getMessage(), e);
//		}
//
//		return out;
//	}

}
