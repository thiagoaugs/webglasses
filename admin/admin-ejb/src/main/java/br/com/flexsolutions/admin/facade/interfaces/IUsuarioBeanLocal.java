/**  
 * jass-ejb - IUsuarioBeanRemote.java
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

import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IUsuarioBeanLocal extends Serializable{

	/**
	 * JNDI do SessionBean IUsuarioBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String USUARIO_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/UsuarioBean!br.com.flexsolutions.admin.facade.interfaces.IUsuarioBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarUsuarioOut}
	 * @return {@link CadastrarUsuarioOut}
	 */
	public CadastrarUsuarioOut cadastrar(CadastrarUsuarioIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarUsuarioIn}
	 * @return {@link AlterarUsuarioOut}
	 */
	public AlterarUsuarioOut alterar(AlterarUsuarioIn in);

	/**
	 * Metodo responsavél por fazer o bloqueio e desbloqueio do usuario
	 * @param  {@link BloqUsuarioIn}
	 * @return {@link BloqUsuarioOut}
	 */
	public BloqUsuarioOut bloqueio(BloqUsuarioIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirUsuarioIn}
	 * @return {@link ExcluirUsuarioOut}
	 */
	public ExcluirUsuarioOut excluir(ExcluirUsuarioIn in);

	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarUsuarioIn}
	 * @return {@link ListarTipoUsuario}
	 */
	public FiltrarUsuarioOut filtrar(FiltrarUsuarioIn in);
	
	
	/**
	 * @param {@link ListarUsuarioIn}
	 * @return {@link ListarUsuarioOut}
	 */
	public ListarUsuarioOut listar(ListarUsuarioIn in);
}
