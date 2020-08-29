/**  
 * webglass-web - UsuarioDataModel.java
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

import br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanLocal;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioOut;

/**
 * @author Thiago Augusto
 * 
 */
public class UsuarioDataModel extends LazyDataModel<UsuarioTO> {
	private static final long serialVersionUID = 1L;

	private String descricaoPesquisa;
	private boolean fazerCount;
	private IUsuarioBeanLocal usuarioBean;

	public UsuarioDataModel(IUsuarioBeanLocal usuarioBean) {
		super();
		this.usuarioBean = usuarioBean;
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
		this.descricaoPesquisa = descricaoPesquisa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	public List<UsuarioTO> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		List<UsuarioTO> lista = null;

		FiltrarUsuarioIn in = new FiltrarUsuarioIn();
		in.setPesquisaLogin(descricaoPesquisa);
		in.setFazerCount(fazerCount);
		in.setInicioRegistros(first);
		in.setTotalRegistros(pageSize);

		FiltrarUsuarioOut out = usuarioBean.filtrar(in);

		if (out.isResultado()) {
			lista = out.getListaUsuarioTO();
			if (out.getCountTotal() != null) {
				fazerCount = false;
				setRowCount(out.getCountTotal().intValue());
			}
		}

		return lista;
	}
}
