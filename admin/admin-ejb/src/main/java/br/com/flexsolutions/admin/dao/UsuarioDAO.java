/**  
 * jass-ejb - UsuarioDAO.java
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
import br.com.flexsolutions.security.pojo.Usuario;

/**
 * Interface para implementar os metodos para manipulacao na tabela tipo_usuario
 * 
 * @author Thiago Augusto
 * 
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Serializable> {

	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser nome.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(String textoPesquisa);

	/**
	 * Retorna a lista de usuarios localizados, baseando-se no texto da
	 * pesquisa, campo nome.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa.
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<Usuario> } lista Usuario localizadas
	 */
	public List<Usuario> filtrarPag(String textoPesquisa, Integer firstResult,
			Integer maxResults);

	/**
	 * * Busca por login do usuario para verificar se ja existe o login passado
	 * por parametro
	 * 
	 * @param {@link descricao}
	 * @return {@link Boolean}
	 */
	public Usuario buscarPor(String cpf);

	/**
	 * Retorna todos os usuarios do banco
	 * 
	 * @return {@link List<Usuario> } lista Usuario localizadas
	 */
	public List<Usuario> listarUsuariosNaoVinculadosAoSistema(
			List<Integer> listaUsuariosJaCadastrados);

	/**
	 * Retorna um usaurio, baseando-se no nome do usuario
	 * 
	 * @param nome
	 * @return
	 */
	public Usuario buscarPorLogin(String login);

	/**
	 * Valida o login do usuario
	 * 
	 * @param usuario
	 * @param senha
	 * @return
	 */
	public Usuario validarLoginUsuario(String usuario, String senhaMD5);

}
