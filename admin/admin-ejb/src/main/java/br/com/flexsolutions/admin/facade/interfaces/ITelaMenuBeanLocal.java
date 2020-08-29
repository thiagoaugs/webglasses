/**  
 * jass-ejb - ITelaMenuRemote.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.facade.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;

import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuOut;

/**
 * @author Thiago Augusto
 *
 */
@Remote
public interface ITelaMenuBeanLocal extends Serializable {

	
	/**
	 * JNDI do SessionBean ITelaMenuBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String TELAMENU_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/TelaMenuBean!br.com.flexsolutions.admin.facade.interfaces.ITelaMenuBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarTelaMenuIn}
	 * @return {@link CadastrarTelaMenuOut}
	 */
	public CadastrarTelaMenuOut cadastrar(CadastrarTelaMenuIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarTelaMenuIn}
	 * @return {@link AlterarTelaMenuOut}
	 */
	public AlterarTelaMenuOut alterar(AlterarTelaMenuIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirTelaMenuIn}
	 * @return {@link ExcluirTelaMenuOut}
	 */
	public ExcluirTelaMenuOut excluir(ExcluirTelaMenuIn in);
	
	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarTelaMenuIn}
	 * @return {@link FiltrarTelaMenuOut}
	 */
	public FiltrarTelaMenuOut filtrar(FiltrarTelaMenuIn in);
	
	
	/**
	 * Interface para listar.
	 * 
	 * @return {@link ListarTelaMenuOut}
	 */
	public ListarTelaMenuOut listar();
	
	
	/**
	 * Interface para listar filtrando por id do sistema
	 * 
	 * @param {@link ListarTelaMenuIn}
	 * @return {@link ListarTelaMenuOut}
	 */
	public ListarTelaMenuOut listarPorIdSistemaGrupo(ListarTelaMenuIn in);
	
	
}
