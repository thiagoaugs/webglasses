/**  
 * jass-ejb - UsuarioGrupoDAO.java
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
import br.com.flexsolutions.security.pojo.UsuarioGrupo;

/**
 * Interface para implementar os metodos para manipulacao na tabela tipo_usuario
 * 
 * @author Thiago Augusto
 * 
 */
public interface UsuarioGrupoDAO extends GenericDAO<UsuarioGrupo, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser nome.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(Integer idUsuario, Integer idGrupo);

	/**
	 * Retorna a lista de usuarios localizados, baseando-se no texto da
	 * pesquisa, campo nome.
	 * 
	 * @param {@link idUsuario} Id do usuario.
	 * @param {@link idGrupo} Id do grupo.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<UsuarioGrupo> } lista UsuarioGrupo localizadas
	 */
	public List<UsuarioGrupo> filtrarPag(Integer idUsuario, Integer idGrupo,
			Integer firstResult, Integer maxResults);

	/**
	 * * Busca por login do usuario para verificar se ja existe o login passado
	 * por parametro
	 * 
	 * @param {@link idUsuario} Id do usuario.
	 * @param {@link idGrupo} Id do grupo.
	 * @return {@link Boolean}
	 */
	UsuarioGrupo buscarPor(Integer idUsuario, Integer idGrupo);
}
