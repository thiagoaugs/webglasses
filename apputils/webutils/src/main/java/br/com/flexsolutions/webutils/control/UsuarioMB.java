/**  
 * webUtils - BaseMB.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */	
package br.com.flexsolutions.webutils.control;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import br.com.flexsolutions.security.interfaces.IGrupoBeanRemote;
import br.com.flexsolutions.security.pojo.Usuario;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@SessionScoped
public class UsuarioMB extends BaseMB {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UsuarioMB.class);
	private List<String> gruposUsuario;
	private List<String> rolesUsuario;

	private Usuario usuario;

	@PostConstruct
	@Override
	public void initPage() {
		getGruposUsuario();
//		getRolesUsuario();
	}

	/**
	 * @return
	 */
	public List<String> getGruposUsuario() {
		if (this.gruposUsuario == null) {
			this.gruposUsuario = getGrupos();
		}
		return this.gruposUsuario;
	}

	/**
	 * @param perfisUsuario
	 */
	public void setGruposUsuario(List<String> gruposUsuario) {
		this.gruposUsuario = gruposUsuario;
	}

	/**
	 * @return
	 */
	public List<String> getRolesUsuario() {
		if (this.rolesUsuario == null) {
			this.rolesUsuario = getRoles();
		}
		return this.rolesUsuario;
	}

	/**
	 * @param rolesUsuario
	 */
	public void setRolesUsuario(List<String> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	/**
	 * Retorna uma string com os perfis do usuario passado por parametro
	 * 
	 * @return
	 */
	public String getGruposUsuarioToString() {
		StringBuilder grupos = new StringBuilder();

		List<String> gruposList = getGruposUsuario();
		if (gruposList != null) {
			for (String grupo : getGruposUsuario() ) {
				if (!grupos.toString().equals("")) {
					grupos.append(", ");
				}
				grupos.append(grupo);
			}
		}
		return grupos.toString();
	}

	 
	/**
	 * Faz uma consulta no ejb para retornar os grupos do usuario
	 * 
	 * @return
	 */
	private List<String> getGrupos() {
		try {

			InitialContext ctx = new InitialContext();
			IGrupoBeanRemote ejb = (IGrupoBeanRemote) ctx
					.lookup("ejb:admin/admin-ejb/GrupoBean!br.com.flexsolutions.security.interfaces.IGrupoBeanRemote");
			this.gruposUsuario = ejb.recuperarGruposUsuarioSistema(
					getApplicationName(), getLogin());
		} catch (Exception e) {
			logger.error(
					"Houve um erro ao recuperar os perfis do usuario logado.",
					e);
		}
		return this.gruposUsuario;
	}

	/**
	 * Faz uma consulta no ejb para retornar as roles do usuario
	 * 
	 * @return
	 */
	private List<String> getRoles() {

		try {

//			InitialContext ctx = new InitialContext();
//			IRolesBeanRemote ejb = (IRolesBeanRemote) ctx
//					.lookup("ejb:admin/admin-ejb/RolesBean!br.com.flexsolutions.security.interfaces.IRolesBeanRemote");
//			this.rolesUsuario = ejb.recuperarRolesUsuarioSistema(
//					getApplicationName(), getLogin());
		} catch (Exception e) {
			logger.error(
					"Houve um erro ao recuperar as roles do usuário logado.", e);
		}
		return this.rolesUsuario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		  if (this.usuario == null)
		    {
		        this.usuario = new Usuario();
		        this.usuario.setUsuNome(getLogin());
		    }
		    return this.usuario;
	}


}
