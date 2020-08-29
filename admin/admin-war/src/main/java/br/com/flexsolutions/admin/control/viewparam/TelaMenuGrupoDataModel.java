/**  
 * admin-war - TelaMenuGrupoDataModel.java
 * 
 * Data de criacao 14/08/2015
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

import br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanLocal;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoOut;

/**
 * @author Thiago Augusto
 *
 */
public class TelaMenuGrupoDataModel extends LazyDataModel<TelaMenuGrupoTO> {
	private static final long serialVersionUID = 1L;

	private Integer idSistema;
	private Integer idGrupo;
	private Integer idTelamenu;

	private boolean fazerCount;
	private ITelaMenuGrupoBeanLocal tmgBean;

	public TelaMenuGrupoDataModel(ITelaMenuGrupoBeanLocal tmgBean) {
		super();
		this.tmgBean = tmgBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 */
	public void realizarPesquisa(Integer idSistema, Integer idGrupo,
			Integer idTelamenu) {
		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		
		this.idSistema = idSistema;
		this.idGrupo = idGrupo;
		this.idTelamenu = idTelamenu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<TelaMenuGrupoTO> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<TelaMenuGrupoTO> lista = null;

		FiltrarTelaMenuGrupoIn in = new FiltrarTelaMenuGrupoIn();
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);
		
		in.setIdSistema(idSistema);
		in.setIdGrupo(idGrupo);
		in.setIdTelamenu(idTelamenu);

		FiltrarTelaMenuGrupoOut out = tmgBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaTelaMenuGrupoTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}

}
