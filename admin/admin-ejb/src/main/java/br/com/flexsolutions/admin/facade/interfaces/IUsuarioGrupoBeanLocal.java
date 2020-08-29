/**  
 * admin-ejb - IUsuarioGrupoSistemaRemote.java
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

import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IUsuarioGrupoBeanLocal extends Serializable{

	/**
	 * JNDI do SessionBean UsuarioGrupoBean, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String USUARIOGRUPO_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/UsuarioGrupoBean!br.com.flexsolutions.admin.facade.interfaces.IUsuarioGrupoBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarUsuarioGrupoOut}
	 * @return {@link CadastrarUsuarioGrupoOut}
	 */
	public CadastrarUsuarioGrupoOut cadastrar(CadastrarUsuarioGrupoIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirUsuarioGrupoIn}
	 * @return {@link ExcluirUsuarioGrupoOut}
	 */
	public ExcluirUsuarioGrupoOut excluir(ExcluirUsuarioGrupoIn in);

	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarUsuarioGrupoIn}
	 * @return {@link ListarTipoUsuarioGrupo}
	 */
	public FiltrarUsuarioGrupoOut filtrar(FiltrarUsuarioGrupoIn in);

	/**
	 * Metodo que recebe um id de sistema, e busca na tabela de
	 * usuariosgruposistema todos os usuariosgruposistema com o id ddo sistema
	 * passado por parametro, e depois busca na tablea de usuarios sistema todos
	 * os usuarios que nao tem configuracao na usuariosgruposistema
	 * 
	 * @param {@link FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn}
	 * @return {@link FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut}
	 */
	public FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut filtrarUsuarioPorSistemaNaoConfigurado(
			FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn in);
}
