/**  
 * admin-ejb - GrupoRoleDAO.java
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
import br.com.flexsolutions.security.pojo.GrupoRole;

/**
 * @author Thiago Augusto
 *
 */
public interface GrupoRoleDAO extends GenericDAO<GrupoRole, Serializable> {
	/**
	 * Faz o count da pesquisa de listarPag, para utilizacao no lazy loading.
	 * 
	 * @param {@link textoPesquisa} Texto da pesquisa que pode ser descricao.
	 * @return {@link Long } Quantidade de registros localizados na pesquisa.
	 */
	public Long retornarCount(Integer idSistema, Integer idGrupo, Integer idRole);

	/**
	 * Retorna a lista de grupos localizados
	 * 
	 * @param {@link firstResult} Inicio da pesquisa no bd.
	 * @param {@link maxResults} Quantidade maxima de resultadoss
	 * @return {@link List<GrupoRole> } lista Grupo localizadas
	 */
	public List<GrupoRole> filtrarPag(Integer idSistema, Integer idGrupo,
			Integer idRole, Integer firstResult, Integer maxResults);

	/**
	 * Busca
	 * 
	 * @return{@link Boolean }
	 */
	public GrupoRole buscarPor(Integer idGrupo, Integer idRole);
	
	
	/**
	 * Lista todos os registros pelo idgrupo
	 * 
	 * @param IdGrupo
	 * @return {@link List<GrupoRole>}
	 */
	public List<GrupoRole> listarPorGrupo(Integer IdGrupo);
	
	
	/**
	 * Retorna uma lista de gruporole, baseado na lista de grupos passada
	 * 
	 * @param listaIdGrupo
	 * @return {@link List<GrupoRole>}
	 */
	public List<GrupoRole> retornarRolesGrupos(List<Integer> listaIdGrupo);

}
