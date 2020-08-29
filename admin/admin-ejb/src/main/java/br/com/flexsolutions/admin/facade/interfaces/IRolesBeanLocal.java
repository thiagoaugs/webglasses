/**  
 * admin-ejb - IRolesBeanRemote.java
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

import javax.ejb.Local;

import br.com.flexsolutions.admin.to.roles.AlterarRolesIn;
import br.com.flexsolutions.admin.to.roles.AlterarRolesOut;
import br.com.flexsolutions.admin.to.roles.AtivaRolesIn;
import br.com.flexsolutions.admin.to.roles.AtivaRolesOut;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesIn;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesIn;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesOut;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesIn;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ListarRolesIn;
import br.com.flexsolutions.admin.to.roles.ListarRolesOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IRolesBeanLocal extends Serializable {

	/**
	 * JNDI do SessionBean IRolesBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String ROLES_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/RolesBean!br.com.flexsolutions.admin.facade.interfaces.IRolesBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarRolesOut}
	 * @return {@link CadastrarRolesOut}
	 */
	public CadastrarRolesOut cadastrar(CadastrarRolesIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarRolesIn}
	 * @return {@link AlterarRolesOut}
	 */
	public AlterarRolesOut alterar(AlterarRolesIn in);

	/**
	 * Metodo responsavél por fazer a ativação e desativação da role
	 * 
	 * @param {@link AtivaRolesIn}
	 * @return {@link AtivaRolesOut}
	 */
	public AtivaRolesOut controlaAtivacao(AtivaRolesIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirRolesIn}
	 * @return {@link ExcluirRolesOut}
	 */
	public ExcluirRolesOut excluir(ExcluirRolesIn in);

	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarRolesIn}
	 * @return {@link FiltrarRolesOut}
	 */
	public FiltrarRolesOut filtrar(FiltrarRolesIn in);

	/**
	 * Interface para o metood de listar
	 * 
	 * @return
	 */
	public ListarRolesOut listarPorIdSistema(ListarRolesIn in);


}
