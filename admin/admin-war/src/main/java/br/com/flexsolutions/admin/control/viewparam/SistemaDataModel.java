/**  
 * admin-war - SistemaDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
public class SistemaDataModel extends LazyDataModel<SistemaTO> {
	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private boolean fazerCount;
	private ISistemaBeanLocal sistemaBean;

	public SistemaDataModel(ISistemaBeanLocal sistemaBean) {
		super();
		this.sistemaBean = sistemaBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param pesquisa
	 */
	public void realizarPesquisa(String pesquisa) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		this.pesquisa = pesquisa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<SistemaTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<SistemaTO> lista = null;

		FiltrarSistemaIn in = new FiltrarSistemaIn();
		in.setPesquisa(pesquisa);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarSistemaOut out = sistemaBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaSistemaTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}
}
