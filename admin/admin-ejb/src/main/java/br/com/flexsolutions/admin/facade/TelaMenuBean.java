/**  
 * jass-ejb - TelaMenuBean.java
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

import br.com.flexsolutions.admin.bo.TelaMenuBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanLocal;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class TelaMenuBean implements ITelaMenuBeanLocal{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TelaMenuBO tmeBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ITelaMenuBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.tmeario.CadastrarTelaMenuIn)
	 */
	@Override
	public CadastrarTelaMenuOut cadastrar(CadastrarTelaMenuIn in) {
		CadastrarTelaMenuOut out;

		try {
			out = tmeBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ITelaMenuBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.tmeario.AlterarTelaMenuIn)
	 */
	@Override
	public AlterarTelaMenuOut alterar(AlterarTelaMenuIn in) {
		AlterarTelaMenuOut out;

		try {
			out = tmeBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ITelaMenuBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.tmeario.ExcluirTelaMenuIn)
	 */
	@Override
	public ExcluirTelaMenuOut excluir(ExcluirTelaMenuIn in) {
		ExcluirTelaMenuOut out;

		try {
			out = tmeBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jass.facade.interfaces.ITelaMenuBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.tmeario.ListarTelaMenuIn)
	 */
	@Override
	public FiltrarTelaMenuOut filtrar(FiltrarTelaMenuIn in) {
		FiltrarTelaMenuOut out;

		try {
			out = tmeBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanRemote#listar()
	 */
	@Override
	public ListarTelaMenuOut listar() {
		ListarTelaMenuOut out;

		try {
			out = tmeBO.listar();
		} catch (WebglassException e) {
			out = new ListarTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanRemote#
	 * listarPorIdSistema(br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuIn)
	 */
	@Override
	public ListarTelaMenuOut listarPorIdSistemaGrupo(ListarTelaMenuIn in) {
		ListarTelaMenuOut out;

		try {
			out = tmeBO.listarPorIdSistemaGrupo(in);
		} catch (WebglassException e) {
			out = new ListarTelaMenuOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
