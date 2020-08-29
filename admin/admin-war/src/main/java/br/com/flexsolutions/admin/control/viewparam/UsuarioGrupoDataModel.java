/**  
 * admin-war - UsuarioGrupoDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanLocal;
import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoOut;

/**
 * @author Thiago Augusto
 *
 */
public class UsuarioGrupoDataModel extends LazyDataModel<UsuarioSistemaGrupoTO> {
	private static final long serialVersionUID = 1L;

	private boolean fazerCount;
	private IUsuarioGrupoBeanLocal usuarioGrupoBean;

	private Integer idSistema;
	private String pesqUsuario;
	private Integer idGrupo;

	public UsuarioGrupoDataModel(IUsuarioGrupoBeanLocal usuarioGrupoBean) {
		super();
		this.usuarioGrupoBean = usuarioGrupoBean;
	}

	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 */
	public void realizarPesquisa(Integer idSistema, Integer idGrupo,
			String pesqUsuario) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;

		this.idSistema = idSistema;
		this.idGrupo = idGrupo;
		this.pesqUsuario = pesqUsuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<UsuarioSistemaGrupoTO> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<UsuarioSistemaGrupoTO> lista = null;

		FiltrarUsuarioGrupoIn in = new FiltrarUsuarioGrupoIn();
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		in.setIdSistema(idSistema);
		in.setIdGrupo(idGrupo);
		in.setPesqUsuario(pesqUsuario);
		
		FiltrarUsuarioGrupoOut out = usuarioGrupoBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaUsgTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}
}
