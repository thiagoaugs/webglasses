/**  
 * jass-ejb - UsuarioSistemaDAO.java
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
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * Interface para implementar os metodos para manipulacao na tabela tipo_usuario
 * 
 * @author Thiago Augusto
 * 
 */
public interface UsuarioSistemaGrupoDAO extends
		GenericDAO<UsuarioSistemaGrupo, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link idSistema}
	 * @param {@link idGrupo}
	 * @param {@link pesqUsuario}
	 * @return{@link Long} Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(Integer idSistema, Integer idGrupo,
			String pesqUsuario);

	/**
	 * Retorna a lista de usuarios sistema grupo localizados, baseando-se nos id
	 * de sistema, grupo e ussuario
	 * 
	 * @param {@link idSistema}
	 * @param {@link idGrupo}
	 * @param {@link pesqUsuario}
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<UsuarioSistema> } lista UsuarioSistemaGrupo
	 *         localizadas
	 */
	public List<UsuarioSistemaGrupo> filtrarPag(Integer idSistema,
			Integer idGrupo, String pesqUsuario, Integer firstResult,
			Integer maxResults);

	/**
	 * * Busca por login do usuario para verificar se ja existe o login passado
	 * por parametro
	 * 
	 * @param {@link idSistema}
	 * @param {@link idGrupo}
	 * @param {@link idUsuario}
	 * @return {@link Boolean}
	 */
	UsuarioSistemaGrupo buscarPor(Integer idSistema, Integer idGrupo,
			Integer idUsuario);

	/**
	 * Lista todos os usuarios com o id do sistema passado
	 * 
	 * @param {@Link idSistema}
	 * @return {@link List<UsuarioSistemaGrupo> }
	 */
	List<UsuarioSistemaGrupo> listarPorIdSistemaGrupo(Integer idSistema, Integer idGrupo);
	
	
	
	/**
	 * Carrega todos os itens do menu para o usuario e o sitema passado
	 * 
	 * @param  {@link sistema}
	 * @param {@link usuario}
	 * @return
	 */
	List<UsuarioSistemaGrupo> listarPorSistemaUsuario(String sistema, String usuario);
	
	
	/**Retorna todos os usuario sistema grupo, com base no id sitema e id usuario.
	 * @param idUsuario
	 * @param idSistema
	 * @return
	 */
	List<UsuarioSistemaGrupo>  listarUsgPorUsuarioSistema(Integer idUsuarioSistema);
}
