/**  
 * admin-ejb - IUsuarioSistemaSistemaRemote.java
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

import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IUsuarioSistemaBeanLocal extends Serializable{

	/**
	 * JNDI do SessionBean IUsuarioSistemaBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String USUARIOSISTEMA_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/UsuarioSistemaBean!br.com.flexsolutions.admin.facade.interfaces.IUsuarioSistemaBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarUsuarioSistemaOut}
	 * @return {@link CadastrarUsuarioSistemaOut}
	 */
	public CadastrarUsuarioSistemaOut cadastrar(CadastrarUsuarioSistemaIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarUsuarioSistemaIn}
	 * @return {@link AlterarUsuarioSistemaOut}
	 */
	// public AlterarUsuarioSistemaOut alterar(AlterarUsuarioSistemaIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirUsuarioSistemaIn}
	 * @return {@link ExcluirUsuarioSistemaOut}
	 */
	public ExcluirUsuarioSistemaOut excluir(ExcluirUsuarioSistemaIn in);

	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarUsuarioSistemaIn}
	 * @return {@link ListarTipoUsuarioSistema}
	 */
	public FiltrarUsuarioSistemaOut filtrar(FiltrarUsuarioSistemaIn in);

	/**
	 * interface para filtrar por sistema
	 * 
	 * @param {@link FiltrarPorSistemaIn}
	 * @return {@link FiltrarPorSistemaOut}
	 */
//	public FiltrarPorSistemaOut filtrarPorSistema(FiltrarPorSistemaIn in);
}
