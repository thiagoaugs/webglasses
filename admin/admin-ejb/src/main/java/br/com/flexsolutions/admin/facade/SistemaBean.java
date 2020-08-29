/**  
 * admin-ejb - SistemaBean.java
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

import br.com.flexsolutions.admin.bo.SistemaBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaIn;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaOut;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class SistemaBean implements ISistemaBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SistemaBO sisBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ISistemaBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.sisario.CadastrarSistemaIn)
	 */
	@Override
	public CadastrarSistemaOut cadastrar(CadastrarSistemaIn in) {
		CadastrarSistemaOut out;

		try {
			out = sisBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ISistemaBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.sisario.AlterarSistemaIn)
	 */
	@Override
	public AlterarSistemaOut alterar(AlterarSistemaIn in) {
		AlterarSistemaOut out;

		try {
			out = sisBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ISistemaBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.sisario.ExcluirSistemaIn)
	 */
	@Override
	public ExcluirSistemaOut excluir(ExcluirSistemaIn in) {
		ExcluirSistemaOut out;

		try {
			out = sisBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ISistemaBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.sisario.ListarSistemaIn)
	 */
	@Override
	public FiltrarSistemaOut filtrar(FiltrarSistemaIn in) {
		FiltrarSistemaOut out;

		try {
			out = sisBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanRemote#listar()
	 */
	@Override
	public ListarSistemaOut listar() {
		ListarSistemaOut out;

		try {
			out = sisBO.listar();
		} catch (WebglassException e) {
			out = new ListarSistemaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
