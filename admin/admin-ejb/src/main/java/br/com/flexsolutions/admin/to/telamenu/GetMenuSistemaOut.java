/**
 * admin-ejb - GetMenuSistemaOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to.telamenu;

import java.util.List;

import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class GetMenuSistemaOut extends GenericOut {
	private static final long serialVersionUID = 1L;

	private List<TelaMenuTO> listaTelaMenu;

	public List<TelaMenuTO> getListaTelaMenu() {
		return listaTelaMenu;
	}

	public void setListaTelaMenu(List<TelaMenuTO> listaTelaMenu) {
		this.listaTelaMenu = listaTelaMenu;
	}
}
