/**  
 * admin-ejb - TipoRoleBO.java
 * 
 * Data de criacao 11/09/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.bo;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.flexsolutions.admin.constants.TipoRoleEnum;
import br.com.flexsolutions.admin.dao.jpa.TipoRoleDAOJpa;
import br.com.flexsolutions.security.pojo.TipoRole;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class TipoRoleBO {
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;
	
	
	/**
	 * Este metodo ira fazer a varredura do TipoRoleEnum atualizando as informacoes no banco de dados.
	 * 
	 * @throws {@link AtlasException}
	 */
	public void atualizarTipoRole() {
		TipoRoleEnum[] tipoRoleEnums = TipoRoleEnum.values();

		TipoRoleDAOJpa tipoRoleDaoJpa = new TipoRoleDAOJpa(em);

		for (TipoRoleEnum tipoRoleEnum : tipoRoleEnums) {
			TipoRole tipoRole = tipoRoleDaoJpa.findByPK(tipoRoleEnum.getId());

			if (tipoRole == null) {
				tipoRole = new TipoRole();

				tipoRole.setTprId(tipoRoleEnum.getId());
				tipoRole.setTprDescricao(tipoRoleEnum.getDescricao());

				tipoRoleDaoJpa.create(tipoRole);
			} else {
				tipoRole.setTprDescricao(tipoRoleEnum.getDescricao());

				tipoRoleDaoJpa.update(tipoRole);
			}
		}
	}
}
