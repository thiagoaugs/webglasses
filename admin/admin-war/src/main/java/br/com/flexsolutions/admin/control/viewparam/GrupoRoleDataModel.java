/**  
 * admin-war - GrupoRoleDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanLocal;
import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleOut;

/**
 * @author Thiago Augusto
 *
 */
public class GrupoRoleDataModel extends LazyDataModel<GrupoRoleTO> {
	private static final long serialVersionUID = 1L;

	
	private Integer idSistema;
	private Integer idGrupo;
	private Integer idRole;
	
	private boolean fazerCount;
	private IGrupoRoleBeanLocal grupoRoleBean;

	public GrupoRoleDataModel(IGrupoRoleBeanLocal grupoRoleBean) {
		super();
		this.grupoRoleBean = grupoRoleBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 * @param descricaoPesquisa
	 */
	public void realizarPesquisa(Integer idSistema, Integer idGrupo, Integer idRole) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		
		this.idSistema = idSistema;
		this.idGrupo =  idGrupo;
		this.idRole = idRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<GrupoRoleTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<GrupoRoleTO> lista = null;

		FiltrarGrupoRoleIn in = new FiltrarGrupoRoleIn();
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);
		
		in.setIdSistema(idSistema);
		in.setIdGrupo(idGrupo);
		in.setIdRole(idRole);

		FiltrarGrupoRoleOut out = grupoRoleBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaGrupoRoleTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}


}
