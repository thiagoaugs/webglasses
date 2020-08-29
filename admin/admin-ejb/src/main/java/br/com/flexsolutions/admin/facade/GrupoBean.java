/**  
 * jass-ejb - GrupoBean.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.flexsolutions.admin.bo.GrupoBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoOut;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;
import br.com.flexsolutions.security.interfaces.IGrupoBeanRemote;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class GrupoBean implements IGrupoBeanLocal, IGrupoBeanRemote{
	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoBO gpoBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IGrupoBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.gpoario.CadastrarGrupoIn)
	 */
	@Override
	public CadastrarGrupoOut cadastrar(CadastrarGrupoIn in) {
		CadastrarGrupoOut out;

		try {
			out = gpoBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IGrupoBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.gpoario.AlterarGrupoIn)
	 */
	@Override
	public AlterarGrupoOut alterar(AlterarGrupoIn in) {
		AlterarGrupoOut out;

		try {
			out = gpoBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IGrupoBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.gpoario.ExcluirGrupoIn)
	 */
	@Override
	public ExcluirGrupoOut excluir(ExcluirGrupoIn in) {
		ExcluirGrupoOut out;

		try {
			out = gpoBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IGrupoBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.gpoario.ListarGrupoIn)
	 */
	@Override
	public FiltrarGrupoOut filtrar(FiltrarGrupoIn in) {
		FiltrarGrupoOut out;

		try {
			out = gpoBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanRemote#listar()
	 */
	@Override
	public ListarGrupoOut listarPorIdSitema(ListarGrupoIn in) {
		ListarGrupoOut out;

		try {
			out = gpoBO.listarPorIdSitema(in);
		} catch (WebglassException e) {
			out = new ListarGrupoOut();
			out.setError(e.getMessage(), e);
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.security.interfaces.IGrupoBeanRemote#
	 * recuperarGruposUsuarioSistema(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> recuperarGruposUsuarioSistema(String appName,
			String usuario) {

		List<String> listaGrupos = null;
		try {
			listaGrupos = gpoBO.recuperarGruposUsuarioSistema(appName, usuario);
		} catch (WebglassException e) {
			e.printStackTrace();
		}
		return listaGrupos;
	}


}
