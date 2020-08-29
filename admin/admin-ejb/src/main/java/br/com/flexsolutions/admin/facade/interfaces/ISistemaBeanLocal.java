/**  
 * admin-ejb - ISistemaBeanREmote.java
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

import br.com.flexsolutions.admin.to.sistema.AlterarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaIn;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaOut;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface ISistemaBeanLocal extends Serializable{

	/**
	 * JNDI do SessionBean ISistemaBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String SISTEMA_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/SistemaBean!br.com.flexsolutions.admin.facade.interfaces.ISistemaBeanLocal";

	
	
	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarSistemaIn}
	 * @return {@link CadastrarSistemaOut}
	 */
	public CadastrarSistemaOut cadastrar(CadastrarSistemaIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarSistemaIn}
	 * @return {@link AlterarSistemaOut}
	 */
	public AlterarSistemaOut alterar(AlterarSistemaIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirSistemaIn}
	 * @return {@link ExcluirSistemaOut}
	 */
	public ExcluirSistemaOut excluir(ExcluirSistemaIn in);
	
	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarSistemaIn}
	 * @return {@link FiltrarSistemaOut}
	 */
	public FiltrarSistemaOut filtrar(FiltrarSistemaIn in);
	
	
	/**
	 * @return {@link ListarSistemaOut}
	 */
	public ListarSistemaOut listar();
}
