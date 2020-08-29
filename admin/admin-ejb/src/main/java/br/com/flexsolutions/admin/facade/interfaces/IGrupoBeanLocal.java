/**  
 * jass-ejb - IGrupoBeanRemote.java
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

import br.com.flexsolutions.admin.to.grupo.AlterarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoOut;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;

/**
 * @author Thiago Augusto
 *
 */	
@Remote
public abstract interface IGrupoBeanLocal extends Serializable{
	/**
	 * JNDI do SessionBean IGrupoBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
//	public static final String GRUPO_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/GrupoBean!br.com.flexsolutions.admin.facade.interfaces.IGrupoBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarGrupoOut}
	 * @return {@link CadastrarGrupoOut}
	 */
	public CadastrarGrupoOut cadastrar(CadastrarGrupoIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarGrupoIn}
	 * @return {@link AlterarGrupoOut}
	 */
	public AlterarGrupoOut alterar(AlterarGrupoIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirGrupoIn}
	 * @return {@link ExcluirGrupoOut}
	 */
	public ExcluirGrupoOut excluir(ExcluirGrupoIn in);
	
	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarGrupoIn}
	 * @return {@link ListarTipoGrupo}
	 */
	public FiltrarGrupoOut filtrar(FiltrarGrupoIn in);
	
	
	/**
	 * Interface para listar todos os registros
	 * 
	 * @return {@link ListarGrupoOut}
	 */
	public ListarGrupoOut listarPorIdSitema(ListarGrupoIn in);
	
	
	
	
	
}