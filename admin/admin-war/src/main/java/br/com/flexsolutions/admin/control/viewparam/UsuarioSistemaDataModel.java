/**  
 * admin-war - UsuarioSistemaDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanLocal;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
public class UsuarioSistemaDataModel  extends LazyDataModel<UsuarioSistemaTO> {
	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private Integer idSistema;
	
	private boolean fazerCount;
	private IUsuarioSistemaBeanLocal usuarioSistemaBean;

	
	public UsuarioSistemaDataModel(IUsuarioSistemaBeanLocal usuarioSistemaBean) {
		super();
		this.usuarioSistemaBean = usuarioSistemaBean;
	}
	
	/**
	 * Executa a pesquisa, chamando o load.
	 * 
	 */
	public void realizarPesquisa(String pesquisa, Integer idSsitema) {

		setRowIndex(0);
		setRowCount(0);
		fazerCount = true;
		
		this.pesquisa = pesquisa;
		this.idSistema = idSsitema;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<UsuarioSistemaTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<UsuarioSistemaTO> lista = null;

		FiltrarUsuarioSistemaIn in = new FiltrarUsuarioSistemaIn();
		in.setIdSistema(idSistema);
		in.setPesquisa(pesquisa);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarUsuarioSistemaOut out = usuarioSistemaBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaUsiTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}
}
