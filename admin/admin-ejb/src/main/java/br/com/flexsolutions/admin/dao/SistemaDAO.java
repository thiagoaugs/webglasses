/**  
 * admin-ejb - SistemaDAO.java
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
import br.com.flexsolutions.security.pojo.Sistema;

/**
 * @author Thiago Augusto
 *
 */
public interface SistemaDAO extends GenericDAO<Sistema, Serializable> {
	
	
	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy
	 * loading.
	 * 
	 * @param {@link textoPesquisa}
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa);

	/**
	 * Retorna a lista de Sistemas localizados, baseando-se no texto da pesquisa.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Sistema>} lista Sistema localizados
	 */
	public List<Sistema> filtrarPag(String textoPesquisa, Integer firstResult,
			Integer maxResults);

	
	/**
	 * Busca por nome do sistema para verificar se ja existe o nome passado por parametro
	 * 
	 * @param {@link nome}
	 * @return{@link Boolean }
	 */
	public Sistema buscarPor(String nome);

	/**
	 * Lista todos os registros 
	 * 
	 * @return {@link List<Sistema>}
	 */
	public List<Sistema> listar(); 

}
