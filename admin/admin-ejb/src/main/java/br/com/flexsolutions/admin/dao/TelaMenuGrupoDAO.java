/**  
 * admin-ejb - TelaMenuGrupoDAO.java
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
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;

/**
 * @author Thiago Augusto
 *
 */
public interface TelaMenuGrupoDAO extends
		GenericDAO<TelaMenuGrupo, Serializable> {
	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(Integer idSistema, Integer idTelaMenu,
			Integer idGrupo);

	/**
	 * Retorna a lista de grupos localizados, baseando-se no texto da pesquisa,
	 * campo descricao.
	 * 
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Grupo> } lista Grupo localizadas
	 */
	public List<TelaMenuGrupo> filtrarPag(Integer idSistema,
			Integer idTelaMenu, Integer idGrupo, Integer firstResult,
			Integer maxResults);

	/**
	 * Busca por
	 * 
	 * @return{@link Boolean }
	 */
	public TelaMenuGrupo buscarPor(Integer idGrupo, Integer idTelaMenu);

	/**
	 * Lista todos os registros que possuem o idsistema e idgrupo passados por
	 * parametros
	 * 
	 * @param idGrupo
	 * @return {@link List<TelaMenuGrupo> } lista TelaMenuGrupo localizadas
	 */
	public List<TelaMenuGrupo> listarTmgPorGrupo(Integer idGrupo);

	/**
	 * Lista todos os registros que possuem o idsistema e idgrupo passados por
	 * parametros
	 * 
	 * @param idGrupo
	 * @return {@link List<TelaMenuGrupo> } lista TelaMenuGrupo localizadas
	 */
	public List<TelaMenuGrupo> listarTmgPorGrupoSistema(
			List<Integer> listaGrupo, Integer idSistema);
}