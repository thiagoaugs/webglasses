/**  
 * admin-ejb - RolesBean.java
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

import br.com.flexsolutions.admin.bo.RolesBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IRolesBeanLocal;
import br.com.flexsolutions.admin.to.roles.AlterarRolesIn;
import br.com.flexsolutions.admin.to.roles.AlterarRolesOut;
import br.com.flexsolutions.admin.to.roles.AtivaRolesIn;
import br.com.flexsolutions.admin.to.roles.AtivaRolesOut;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesIn;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesIn;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesOut;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesIn;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ListarRolesIn;
import br.com.flexsolutions.admin.to.roles.ListarRolesOut;
import br.com.flexsolutions.security.interfaces.IRolesBeanRemote;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class RolesBean implements IRolesBeanLocal, IRolesBeanRemote{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private RolesBO rolBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IRolesBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.rolario.CadastrarRolesIn)
	 */
	@Override
	public CadastrarRolesOut cadastrar(CadastrarRolesIn in) {
		CadastrarRolesOut out;

		try {
			out = rolBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarRolesOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IRolesBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.rolario.AlterarRolesIn)
	 */
	@Override
	public AlterarRolesOut alterar(AlterarRolesIn in) {
		AlterarRolesOut out;

		try {
			out = rolBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarRolesOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	
	/* (non-Javadoc)
	 * @see br.com.flexsolutions.admin.facade.interfaces.IRolesBeanRemote#bloqueio(br.com.flexsolutions.admin.to.roles.AtivaRolesIn)
	 */
	@Override
	public AtivaRolesOut controlaAtivacao(AtivaRolesIn in) {
		AtivaRolesOut out;
		try {
			out = rolBO.controlaAtivacao(in);
		} catch (WebglassException e) {
			out = new AtivaRolesOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IRolesBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.rolario.ExcluirRolesIn)
	 */
	@Override
	public ExcluirRolesOut excluir(ExcluirRolesIn in) {
		ExcluirRolesOut out;

		try {
			out = rolBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirRolesOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IRolesBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.rolario.ListarRolesIn)
	 */
	@Override
	public FiltrarRolesOut filtrar(FiltrarRolesIn in) {
		FiltrarRolesOut out;

		try {
			out = rolBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarRolesOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.admin.facade.interfaces.IRolesBeanRemote#listar()
	 */
	@Override
	public ListarRolesOut listarPorIdSistema(ListarRolesIn in) {
		
		ListarRolesOut out;
		try {
			out = rolBO.listarPorIdSistema(in);
		} catch (WebglassException e) {
			out = new ListarRolesOut();
			out.setError(e.getMessage(), e);	
		}
		
		return out;
	}

//	@Override
//	public RecuperarRolesUsuarioOut recuperarRolesUsuarioSistema(
//			RecuperarRolesUsuarioIn in) {
//		
//		RecuperarRolesUsuarioOut out;
//		try {
//			out = rolBO.recuperarRolesUsuarioSistema(in);
//		} catch (WebglassException e) {
//			out = new RecuperarRolesUsuarioOut();
//			out.setError(e.getMessage(), e);	
//		}
//		
//		return out;
//		
//	}

	/* (non-Javadoc)
	 * @see br.com.flexsolutions.security.interfaces.IRolesBeanRemote#recuperarRolesUsuarioSistema(java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> recuperarRolesUsuarioSistema(String appName,
			String usuario) {
		
		List<String> listaRoles = null;
		try {
			listaRoles = rolBO.recuperarRolesUsuarioSistema(appName, usuario);
		} catch (WebglassException e) {
			e.printStackTrace();
		}
		return listaRoles;
	}


}
