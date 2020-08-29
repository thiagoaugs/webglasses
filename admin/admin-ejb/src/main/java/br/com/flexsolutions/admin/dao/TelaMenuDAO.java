/**  
 * jass-ejb - TelaMenuDAO.java
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
import br.com.flexsolutions.security.pojo.TelaMenu;

/**
 * Interface para implementar os metodos para manipulacao na tabela tela_menu
 * 
 * @author Thiago Augusto
 *
 */
public interface TelaMenuDAO extends GenericDAO<TelaMenu, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser acao.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa, Integer idSistema);

	/**
	 * Retorna a lista de usuarios localizados, baseando-se no texto da
	 * pesquisa, campo acao.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Usuario> } lista Telas localizadas
	 */
	public List<TelaMenu> filtrarPag(String textoPesquisa, Integer idSistema,
			Integer firstResult, Integer maxResults);

	/**
	 * Busca por acao para verificar se ja existe a acao cadastrada
	 * 
	 * @param {@link acao}
	 * 
	 * @return {@link Boolean}
	 */
	TelaMenu buscarPor(String acao, Integer sisID);

	/**
	 * Metodo que retorna todos os registros para utilizar numa seleção
	 * 
	 * @return {@link List<TelaMenu>}
	 */
	public List<TelaMenu> listar();

	/**
	 * @param {@link idSistema}
	 * @return {@link List<TelaMenu>}
	 */
	public List<TelaMenu> listarPorIdSistema(List<Integer> listaId, Integer idSistema);
	
	
}