/**  
 * admin-ejb - ITelaMenuTelaMenuGrupoBeanRemote.java
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

import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface ITelaMenuGrupoBeanLocal extends Serializable{

	/**
	 * JNDI do SessionBean ITelaMenuGrupoBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String TELAMENU_GRUPO_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/TelaMenuGrupoBean!br.com.flexsolutions.admin.facade.interfaces.ITelaMenuGrupoBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarTelaMenuGrupoOut}
	 * @return {@link CadastrarTelaMenuGrupoOut}
	 */
	public CadastrarTelaMenuGrupoOut cadastrar(CadastrarTelaMenuGrupoIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarTelaMenuGrupoIn}
	 * @return {@link AlterarTelaMenuGrupoOut}
	 */
	public AlterarTelaMenuGrupoOut alterar(AlterarTelaMenuGrupoIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirTelaMenuGrupoIn}
	 * @return {@link ExcluirTelaMenuGrupoOut}
	 */
	public ExcluirTelaMenuGrupoOut excluir(ExcluirTelaMenuGrupoIn in);
	
	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarTelaMenuGrupoIn}
	 * @return {@link ListarTipoTelaMenuGrupo}
	 */
	public FiltrarTelaMenuGrupoOut filtrar(FiltrarTelaMenuGrupoIn in);
	
}
