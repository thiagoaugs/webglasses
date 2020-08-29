/**  
 * admin-ejb - IGrupoRoleRoleBeanRemote.java
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

import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IGrupoRoleBeanLocal extends Serializable {
	
	/**
	 * JNDI do SessionBean IGrupoRoleBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String GRUPO_ROLE_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/GrupoRoleBean!br.com.flexsolutions.admin.facade.interfaces.IGrupoRoleBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarGrupoRoleOut}
	 * @return {@link CadastrarGrupoRoleOut}
	 */
	public CadastrarGrupoRoleOut cadastrar(CadastrarGrupoRoleIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarGrupoRoleIn}
	 * @return {@link AlterarGrupoRoleOut}
	 */
	public AlterarGrupoRoleOut alterar(AlterarGrupoRoleIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirGrupoRoleIn}
	 * @return {@link ExcluirGrupoRoleOut}
	 */
	public ExcluirGrupoRoleOut excluir(ExcluirGrupoRoleIn in);

	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarGrupoRoleIn}
	 * @return {@link ListarTipoGrupoRole}
	 */
	public FiltrarGrupoRoleOut filtrar(FiltrarGrupoRoleIn in);

}
