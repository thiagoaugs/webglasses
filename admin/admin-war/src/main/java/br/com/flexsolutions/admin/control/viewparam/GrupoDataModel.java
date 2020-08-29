/**  
 * jaas-war - GrupoDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoOut;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoDataModel extends LazyDataModel<GrupoTO> {
	private static final long serialVersionUID = 1L;

	private String descricaoPesquisa;
	private Integer idSistema;
	
	private boolean fazerCount;
	private IGrupoBeanLocal grupoBean;

	public GrupoDataModel(IGrupoBeanLocal grupoBean) {
		super();
		this.grupoBean = grupoBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param descricaoPesquisa
	 */
	public void realizarPesquisa(String descricaoPesquisa, Integer idSistema) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		this.descricaoPesquisa = descricaoPesquisa;
		this.idSistema = idSistema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<GrupoTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<GrupoTO> lista = null;

		FiltrarGrupoIn in = new FiltrarGrupoIn();
		in.setPesquisaDescricao(descricaoPesquisa);
		in.setIdSistema(idSistema);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarGrupoOut out = grupoBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaGrupoTO();
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
