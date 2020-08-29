/**  
 * admin-war - RolesDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IRolesBeanLocal;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesIn;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesOut;

/**
 * @author Thiago Augusto
 *
 */
public class RolesDataModel  extends LazyDataModel<RolesTO> {
	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private Integer idSistema;
	
	private boolean fazerCount;
	private IRolesBeanLocal rolesBean;

	public RolesDataModel(IRolesBeanLocal rolesBean) {
		super();
		this.rolesBean = rolesBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param descricaoPesquisa
	 */
	public void realizarPesquisa(String pesquisa, Integer idSitema) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		this.pesquisa = pesquisa;
		this.idSistema = idSitema;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<RolesTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<RolesTO> lista = null;

		FiltrarRolesIn in = new FiltrarRolesIn();
		in.setPesquisa(pesquisa);
		in.setIdSistema(idSistema);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarRolesOut out = rolesBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaRolesTO();
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
