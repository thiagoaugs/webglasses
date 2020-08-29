/**  
 * admin-war - TelaMenuDataModel.java
 * 
 * Data de criacao 23/07/2015
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

import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanLocal;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuOut;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuDataModel extends LazyDataModel<TelaMenuTO> {
	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private Integer idSistema;
	
	private boolean fazerCount;
	private ITelaMenuBeanLocal telaMenuBean;

	public TelaMenuDataModel(ITelaMenuBeanLocal telaMenuBean) {
		super();
		this.telaMenuBean = telaMenuBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param pesquisa
	 */
	public void realizarPesquisa(String pesquisa, Integer idSistema) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		this.pesquisa = pesquisa;
		this.idSistema = idSistema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<TelaMenuTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<TelaMenuTO> lista = null;

		FiltrarTelaMenuIn in = new FiltrarTelaMenuIn();
		in.setPesquisa(pesquisa);
		in.setIdSistema(idSistema);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarTelaMenuOut out = telaMenuBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaTelaMenuTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}

	/**
	 * @return the idSistema
	 */
	public Integer getIdSistema() {
		return idSistema;
	}

	/**
	 * @param idSistema the idSistema to set
	 */
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}

}
