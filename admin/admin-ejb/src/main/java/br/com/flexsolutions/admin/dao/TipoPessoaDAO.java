/**  
 * admin-ejb - TipoPessoa.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.dao;

import java.io.Serializable;
import java.util.List;

import br.com.flexsolutions.persistenceutils.dao.generics.GenericDAO;
import br.com.flexsolutions.security.pojo.TipoPessoa;

/**
 *  Interface para implementar os metodos para manipulacao na tabela adm_tipo_pessoa.
 * 
 * @author Thiago Augusto
 *
 */
public interface TipoPessoaDAO extends GenericDAO<TipoPessoa, Serializable> {
	
	
	
	
	/**
	 * Retorna a lista de todos os tipos de pessoa localizados
	 * 
	 * @return Lista de tipos de pessoa
	 */
	public List<TipoPessoa> listar();

}
