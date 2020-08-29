/**  
 * admin-ejb - TipoRoleDAO.java
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
import br.com.flexsolutions.security.pojo.TipoRole;

/**
 * @author Thiago Augusto
 *
 */
public interface TipoRoleDAO extends GenericDAO<TipoRole, Serializable> {

	
	/**
	 * Retorna a lista de todos os tipos de role localizados
	 * 
	 * @return {@link List<TipoRole>} Lista de tipos de role
	 */
	public List<TipoRole> listar();
	
}
