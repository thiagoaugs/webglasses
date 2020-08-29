/**  
 * admin-ejb - GrupoRoleBean.java
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

import br.com.flexsolutions.admin.bo.GrupoRoleBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanLocal;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class GrupoRoleBean implements IGrupoRoleBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoRoleBO gprBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanRemote#cadastrar
	 * (br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleIn)
	 */
	@Override
	public CadastrarGrupoRoleOut cadastrar(CadastrarGrupoRoleIn in) {
		CadastrarGrupoRoleOut out;

		try {
			out = gprBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarGrupoRoleOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanRemote#alterar
	 * (br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleIn)
	 */
	@Override
	public AlterarGrupoRoleOut alterar(AlterarGrupoRoleIn in) {
		AlterarGrupoRoleOut out;

		try {
			out = gprBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarGrupoRoleOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanRemote#excluir
	 * (br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleIn)
	 */
	@Override
	public ExcluirGrupoRoleOut excluir(ExcluirGrupoRoleIn in) {
		ExcluirGrupoRoleOut out;

		try {
			out = gprBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirGrupoRoleOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanRemote#filtrar
	 * (br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleIn)
	 */
	@Override
	public FiltrarGrupoRoleOut filtrar(FiltrarGrupoRoleIn in) {
		FiltrarGrupoRoleOut out;

		try {
			out = gprBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarGrupoRoleOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
