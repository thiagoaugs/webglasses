/**  
 * admin-ejb - TelaMenuGrupoBean.java
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

import br.com.flexsolutions.admin.bo.TelaMenuGrupoBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanLocal;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class TelaMenuGrupoBean implements ITelaMenuGrupoBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TelaMenuGrupoBO tmgBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanRemote#cadastrar
	 * (br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoIn)
	 */
	@Override
	public CadastrarTelaMenuGrupoOut cadastrar(CadastrarTelaMenuGrupoIn in) {

		CadastrarTelaMenuGrupoOut out;

		try {
			out = tmgBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarTelaMenuGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanRemote#alterar
	 * (br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoIn)
	 */
	@Override
	public AlterarTelaMenuGrupoOut alterar(AlterarTelaMenuGrupoIn in) {
		AlterarTelaMenuGrupoOut out;

		try {
			out = tmgBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarTelaMenuGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanRemote#excluir
	 * (br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoIn)
	 */
	@Override
	public ExcluirTelaMenuGrupoOut excluir(ExcluirTelaMenuGrupoIn in) {
		ExcluirTelaMenuGrupoOut out;

		try {
			out = tmgBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirTelaMenuGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanRemote#filtrar
	 * (br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoIn)
	 */
	@Override
	public FiltrarTelaMenuGrupoOut filtrar(FiltrarTelaMenuGrupoIn in) {
		FiltrarTelaMenuGrupoOut out;

		try {
			out = tmgBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarTelaMenuGrupoOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

}
