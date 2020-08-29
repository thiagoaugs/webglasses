/**  
 * jass-ejb - GrupoDAO.java
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
import br.com.flexsolutions.security.pojo.Grupo;

/**
 * @author Thiago Augusto
 *
 */
public interface GrupoDAO extends GenericDAO<Grupo, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser descricao.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa, Integer idSistema);

	/**
	 * Retorna a lista de grupos localizados, baseando-se no texto da pesquisa,
	 * campo descricao.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Grupo> } lista Grupo localizadas
	 */
	public List<Grupo> filtrarPag(String textoPesquisa, Integer idSistema, Integer firstResult,
			Integer maxResults);

	/**
	 * Busca por descricao do grupo para verificar se ja existe a descricao
	 * passada por parametro
	 * 
	 * @param {@link descricao}
	 * @return{@link Boolean }
	 */
	public Grupo buscarPor(String nome, Integer idSistema);
	
	
	/**
	 * Lista todos os registros da tabela
	 * @return {@link List<Grupo>}
	 */
	public List<Grupo> listarPorIdSitema(Integer idSistema);
	
	
	/**
	 * Lista todos os grupos com base numa lista de id de grupo passada
	 * @param listaId
	 * @return {@link List<Grupo>}
	 */
	public List<Grupo> retornarGrupos(List<Integer> listaId); 
}
