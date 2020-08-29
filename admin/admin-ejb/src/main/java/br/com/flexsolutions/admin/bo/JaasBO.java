/**  
 * admin-ejb - LoginBO.java
 * 
 * Data de criacao 27/05/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import br.com.flexsolutions.admin.constants.MessageName;
import br.com.flexsolutions.admin.dao.SistemaDAO;
import br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO;
import br.com.flexsolutions.admin.dao.UsuarioDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.SistemaDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.TelaMenuGrupoDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.telamenu.GetMenuSistemaIn;
import br.com.flexsolutions.admin.util.SecurityUtil;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.TelaMenu;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * Regras de negocio para o Login
 * 
 * @author Thiago Augusto
 * 
 */
public class JaasBO {
	private final static Logger LOGGER = Logger.getLogger(Usuario.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * @param usuario
	 * @param senha
	 * @return
	 * @throws WebglassException
	 */
	public Boolean doLogin(String usuario, String senha)
			throws WebglassException {

		Boolean validouUsuario = false;
		String senhaMD5 = null;
		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());
		UsuarioDAO usuJpa = new UsuarioDAOJpa(em);

		try {
			Usuario usuarioEncontrado = new Usuario();
			// pega a senha passada e converte para md5
			senhaMD5 = SecurityUtil.hash(senha);

			// valida o usuario no banco
			usuarioEncontrado = usuJpa.validarLoginUsuario(usuario, senhaMD5);

			if (usuarioEncontrado != null) {
				validouUsuario = true;
			}

		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException("erro ao validar usuario");
		}

		return validouUsuario;
	}

	/**
	 * @param in
	 * @return
	 * @throws WebglassException
	 */
	public List<TelaMenu> carregarMenuSistema(GetMenuSistemaIn in)
			throws WebglassException {

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		UsuarioDAO usuJpa = new UsuarioDAOJpa(em);
		SistemaDAO sisJpa = new SistemaDAOJpa(em);
		Sistema sistema = null;
		Usuario usuario = null;

		try {
			// recuperar o id do usuario e o id do sistema atraves das strings
			// passadas
			sistema = sisJpa.buscarPor(in.getSistema());
			System.out.println("id do sistema " + sistema.getSisId());

			usuario = usuJpa.buscarPorLogin(in.getUsuario());
			System.out.println("id do usuario " + usuario.getUsuId());

		} catch (NoResultException e) {
			throw new WebglassException(
					bundle.getString("telamenu.carregarmenu.erro"));
		}

		UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);
		UsuarioSistema usuarioSistema = null;

		try {
			usuarioSistema = usiJpa.buscarPor(usuario.getUsuId(),
					sistema.getSisId());
			System.out.println("usuario sistema com a id : "
					+ usuarioSistema.getUsiId());
		} catch (NoResultException e) {
			throw new WebglassException(
					bundle.getString("telamenu.carregarmenu.erro"));
		}

		UsuarioSistemaGrupoDAO usgJpa = new UsuarioSistemaGrupoDAOJpa(em);
		List<UsuarioSistemaGrupo> listaUsg = null;
		List<Integer> listaIdGrupos = null;

		try {
			listaUsg = usgJpa.listarUsgPorUsuarioSistema(usuarioSistema
					.getUsiId());
			if (listaUsg != null) {
				listaIdGrupos = new ArrayList<Integer>();
				for (UsuarioSistemaGrupo usg : listaUsg) {
					listaIdGrupos.add(usg.getUsgGrupo().getGpoId());

				}
			} else {
				System.out.println("nao encontrou registro");
				return null;
			}
			System.out.println(listaIdGrupos);
		} catch (NoResultException e) {
			throw new WebglassException(
					bundle.getString("telamenu.carregarmenu.erro"));
		}

		// pesquisar na telamenugrupo as telas que pertencem ao
		// grupo/sistema.
		TelaMenuGrupoDAO tmgJpa = new TelaMenuGrupoDAOJpa(em);
		List<TelaMenuGrupo> listaTelaMenuGrupo = null;
		List<TelaMenu> listaTelaMenu = null;
		try {
			listaTelaMenuGrupo = tmgJpa.listarTmgPorGrupoSistema(listaIdGrupos,
					sistema.getSisId());
			System.out.println(listaTelaMenuGrupo);

		} catch (NoResultException e) {
			throw new WebglassException(
					bundle.getString("telamenu.carregarmenu.erro"));
		}

		if (listaTelaMenuGrupo != null) {
			listaTelaMenu = new ArrayList<TelaMenu>();
			for (TelaMenuGrupo tmg : listaTelaMenuGrupo) {
				if(tmg.getTmgTelaMenu().getTmeSuperior() == null){
					listaTelaMenu.add(tmg.getTmgTelaMenu());
				}
				
			}
		}
		return listaTelaMenu;

	}

}
