/**  
 * admin-ejb - RolesDAO.java
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

import br.com.flexsolutions.admin.to.roles.ListarRolesOut;
import br.com.flexsolutions.persistenceutils.dao.generics.GenericDAO;
import br.com.flexsolutions.security.pojo.Roles;

/**
 * @author Thiago Augusto
 *
 */
public interface RolesDAO extends GenericDAO<Roles, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser nome.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa, Integer idSistema);

	/**
	 * Retorna a lista de roles localizadas, baseando-se no texto da pesquisa,
	 * campo nome.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Grupo> } lista roles localizadas
	 */
	public List<Roles> filtrarPag(String textoPesquisa, Integer idSistema,
			Integer firstResult, Integer maxResults);

	/**
	 * Busca por nome da role para verificar se ja existe a role passada por
	 * parametro
	 * 
	 * @param {@link nome}
	 * @return{@link Boolean }
	 */
	public Roles buscarPor(String nome, Integer idSistema);

	/**
	 * Lista todos os registros da tabela
	 * 
	 * @return {@link ListarRolesOut}
	 */
	public List<Roles> listarPorIdSistema(List<Integer> listaId, Integer idSistema);

}
