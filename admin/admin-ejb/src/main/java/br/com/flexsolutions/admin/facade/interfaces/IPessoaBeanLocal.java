/**  
 * admin-ejb - IPessoaBeanRemote.java
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

import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaOut;

/**
 * @author Thiago Augusto
 *
 */
@Local
public interface IPessoaBeanLocal extends Serializable{
	/**
	 * JNDI do SessionBean IPessoaBeanRemote, para o AS JBOSS.
	 * <p>
	 * <b>Modelo do JNDI:</b>
	 * <p>
	 * ejb:<app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified
	 * -classname-of-the-remote-interface>
	 */
	public static final String PESSOA_SSB_JBOSS_JNDI = "ejb:admin/admin-ejb/PessoaBean!br.com.flexsolutions.admin.facade.interfaces.IPessoaBeanLocal";

	/**
	 * Interaface para cadastrar um registro.
	 * 
	 * @param {@link CadastrarPessoaOut}
	 * @return {@link CadastrarPessoaOut}
	 */
	public CadastrarPessoaOut cadastrar(CadastrarPessoaIn in);

	/**
	 * Interface para alterar um registro.
	 * 
	 * @param {@link AlterarPessoaIn}
	 * @return {@link AlterarPessoaOut}
	 */
	public AlterarPessoaOut alterar(AlterarPessoaIn in);

	/**
	 * Interface para excluir um registro.
	 * 
	 * @param {@link ExcluirPessoaIn}
	 * @return {@link ExcluirPessoaOut}
	 */
	public ExcluirPessoaOut excluir(ExcluirPessoaIn in);
	
	/**
	 * Interface para filtrar.
	 * 
	 * @param {@link FiltrarPessoaIn}
	 * @return {@link ListarTipoPessoa}
	 */
	public FiltrarPessoaOut filtrar(FiltrarPessoaIn in);

}
