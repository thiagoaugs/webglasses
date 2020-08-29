/**  
 * admin-ejb - PessoaBean.java
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

import br.com.flexsolutions.admin.bo.PessoaBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.facade.interfaces.IPessoaBeanLocal;
import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaOut;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Interceptors(WebglassException.class)
public class PessoaBean implements IPessoaBeanLocal {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaBO pesBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IPessoaBeanRemote#
	 * cadastrar(br.com.flexsolutions.jass.to.pesario.CadastrarPessoaIn)
	 */
	@Override
	public CadastrarPessoaOut cadastrar(CadastrarPessoaIn in) {
		CadastrarPessoaOut out;

		try {
			out = pesBO.cadastrar(in);
		} catch (WebglassException e) {
			out = new CadastrarPessoaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IPessoaBeanRemote#
	 * alterar(br.com.flexsolutions.jass.to.pesario.AlterarPessoaIn)
	 */
	@Override
	public AlterarPessoaOut alterar(AlterarPessoaIn in) {
		AlterarPessoaOut out;

		try {
			out = pesBO.alterar(in);
		} catch (WebglassException e) {
			out = new AlterarPessoaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IPessoaBeanRemote#
	 * excluir(br.com.flexsolutions.jass.to.pesario.ExcluirPessoaIn)
	 */
	@Override
	public ExcluirPessoaOut excluir(ExcluirPessoaIn in) {
		ExcluirPessoaOut out;

		try {
			out = pesBO.excluir(in);
		} catch (WebglassException e) {
			out = new ExcluirPessoaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.flexsolutions.jass.facade.interfaces.IPessoaBeanRemote#
	 * listar(br.com.flexsolutions.jass.to.pesario.ListarPessoaIn)
	 */
	@Override
	public FiltrarPessoaOut filtrar(FiltrarPessoaIn in) {
		FiltrarPessoaOut out;

		try {
			out = pesBO.filtrar(in);
		} catch (WebglassException e) {
			out = new FiltrarPessoaOut();
			out.setError(e.getMessage(), e);
		}

		return out;
	}
}
