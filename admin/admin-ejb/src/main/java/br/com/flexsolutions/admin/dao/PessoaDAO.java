/**  
 * admin-ejb - PessoaDAO.java
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
import br.com.flexsolutions.security.pojo.Pessoa;

/**
 * @author Thiago Augusto
 *
 */
public interface PessoaDAO extends GenericDAO<Pessoa, Serializable> {
	
	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser nome.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa);

	/**
	 * Retorna a lista de pessoas localizadas, baseando-se no texto da pesquisa,
	 * campo nome.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Pessoa> } lista pessoas localizadas
	 */
	public List<Pessoa> filtrarPag(String textoPesquisa, Integer firstResult,
			Integer maxResults);

	/**
	 * Busca por cpf/cnpj verificar se ja existe a pessoa passada por
	 * parametro
	 * 
	 * @param {@link cpfCnpj}
	 * @return{@link Boolean }
	 */
	public Pessoa buscarPor(String cpfCnpj);
	

}
