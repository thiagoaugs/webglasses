/**  
 * admin-war - PessoaDataModel.java
 * 
 * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.control.viewparam;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.flexsolutions.admin.facade.interfaces.IPessoaBeanLocal;
import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaOut;

/**
 * @author Thiago Augusto
 *
 */
public class PessoaDataModel extends LazyDataModel<PessoaTO> {
	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private boolean fazerCount;
	private IPessoaBeanLocal pessoaBean;

	public PessoaDataModel(IPessoaBeanLocal pessoaBean) {
		super();
		this.pessoaBean = pessoaBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param descricaoPesquisa
	 */
	public void realizarPesquisa(String descricaoPesquisa) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		this.pesquisa = descricaoPesquisa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<PessoaTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<PessoaTO> lista = null;

		FiltrarPessoaIn in = new FiltrarPessoaIn();
		in.setPesquisa(pesquisa);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarPessoaOut out = pessoaBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaPessoaTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}

}
