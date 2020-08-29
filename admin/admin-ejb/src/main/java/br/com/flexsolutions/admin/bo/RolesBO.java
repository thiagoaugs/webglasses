/**  
 * admin-ejb - RolesBO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.bo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import br.com.flexsolutions.admin.constants.MessageName;
import br.com.flexsolutions.admin.dao.GrupoRoleDAO;
import br.com.flexsolutions.admin.dao.RolesDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.GrupoRoleDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.RolesDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.RolesTO;
import br.com.flexsolutions.admin.to.roles.AlterarRolesIn;
import br.com.flexsolutions.admin.to.roles.AlterarRolesOut;
import br.com.flexsolutions.admin.to.roles.AtivaRolesIn;
import br.com.flexsolutions.admin.to.roles.AtivaRolesOut;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesIn;
import br.com.flexsolutions.admin.to.roles.CadastrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesIn;
import br.com.flexsolutions.admin.to.roles.ExcluirRolesOut;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesIn;
import br.com.flexsolutions.admin.to.roles.FiltrarRolesOut;
import br.com.flexsolutions.admin.to.roles.ListarRolesIn;
import br.com.flexsolutions.admin.to.roles.ListarRolesOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.GrupoRole;
import br.com.flexsolutions.security.pojo.Roles;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class RolesBO {

	private final static Logger LOGGER = Logger.getLogger(Roles.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um Roles
	 * 
	 * @param {@link CadastrarRolesIn}
	 * @return {@link CadastrarRolesOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarRolesOut cadastrar(CadastrarRolesIn in)
			throws WebglassException {

		Roles rol;
		RolesTO rolTO;

		CadastrarRolesOut out = new CadastrarRolesOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		RolesDAOJpa jpaDao = new RolesDAOJpa(em);

		rolTO = in.getRolesTO();

		// valida os campos obrigatorios
		if (validarCampos(rolTO, bundle)) {

			// valida os campos com unique
			if (validarUnique(rolTO, bundle)) {

				// converte o TO em Entidade
				rol = ConverterTOEntidade.criarRoles(rolTO);

				try {
					rol = jpaDao.create(rol);
				} catch (Exception e) {
					LOGGER.error(bundle.getString("role.cadastrar.errosalvar"),
							e);
					throw new WebglassException(
							bundle.getString("role.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("role.cadastrar.errounique"));
			}

			rolTO = ConverterEntidadeTO.criarRoleTO(rol);
			out.setSuccess();
			out.setRolesTO(rolTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um roles
	 *
	 * @param {@link AlterarRolesIn}
	 * @return {@link AlterarRolesOut}
	 * @throws WebglassException
	 */
	public AlterarRolesOut alterar(AlterarRolesIn in) throws WebglassException {

		Roles rol;
		RolesTO rolTO;

		AlterarRolesOut out = new AlterarRolesOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		rolTO = in.getRolesTO();

		// valida os campos obrigatorios
		if (validarCampos(rolTO, bundle)) {
			RolesDAOJpa jpaDao = new RolesDAOJpa(em);
			try {
				rol = jpaDao.read(rolTO.getRolId());
			} catch (NoResultException e) {
				String msg = MessageFormat.format(
						bundle.getString("role.alterar.registronaolocalizado"),
						rolTO.getRolId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(rolTO, bundle)) {

				rol = ConverterTOEntidade.criarRoles(rolTO);
				try {
					rol = jpaDao.update(rol);
					rolTO = ConverterEntidadeTO.criarRoleTO(rol);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("role.alterar.erroalterar"),
							rolTO.getRolId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("role.cadastrar.errounique"));
			}

		}

		out.setRolesTO(rolTO);
		out.setSuccess();
		return out;
	}

	public AtivaRolesOut controlaAtivacao(AtivaRolesIn in)
			throws WebglassException {

		Roles rol;
		RolesTO rolTO;

		AtivaRolesOut out = new AtivaRolesOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		rolTO = in.getRolTO();

		RolesDAOJpa jpaDao = new RolesDAOJpa(em);
		try {
			rol = jpaDao.read(rolTO.getRolId());
		} catch (NoResultException e) {
			String msg = MessageFormat.format(
					bundle.getString("roles.alterar.registronaolocalizado"),
					rolTO.getRolId());
			LOGGER.info(msg, e);

			throw new WebglassException(msg);
		}

		rol = ConverterTOEntidade.criarRoles(rolTO);

		try {
			rol = jpaDao.update(rol);
			rolTO = ConverterEntidadeTO.criarRoleTO(rol);
		} catch (Exception e) {
			String msg = MessageFormat.format(
					bundle.getString("roles.alterar.erroalterar"),
					rolTO.getRolId());
			LOGGER.error(msg, e);
			throw new WebglassException(msg, e);
		}

		out.setRolesTO(rolTO);
		out.setSuccess();
		return out;

	}

	/**
	 * Faz a exclusão de um rolário
	 * 
	 * @param {@link ExcluirRolesIn}
	 * @return {@link ExcluirRolesOut}
	 * @throws WebglassException
	 */
	public ExcluirRolesOut excluir(ExcluirRolesIn in) throws WebglassException {
		ExcluirRolesOut out = new ExcluirRolesOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		RolesDAOJpa jpaDao = new RolesDAOJpa(em);

		try {
			jpaDao.delete(in.getRolesTO().getRolId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("role.excluir.constraintviolation"),
					e.getMessage());
			LOGGER.error(msg, e);

			throw new WebglassException(msg, e);
		}

		out.setSuccess();

		return out;
	}

	/**
	 * Lista todos os registros da tabela filtrado pelo campo login
	 * 
	 * @param {@link FiltrarRolesIn}
	 * @return {@link FiltrarRolesOut}
	 * @throws WebglassException
	 */
	public FiltrarRolesOut filtrar(FiltrarRolesIn in) throws WebglassException {

		String textoPesquisa;
		Integer idSistema;
		List<Roles> listaRoles;
		List<RolesTO> listaRolesTO;

		FiltrarRolesOut out = new FiltrarRolesOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		if (StringUtils.isBlank(in.getPesquisa())) {
			textoPesquisa = "";
		} else {
			textoPesquisa = in.getPesquisa();
		}

		if (in.getIdSistema() != null && in.getIdSistema() == 0) {
			idSistema = null;
		} else {
			idSistema = in.getIdSistema();
		}

		RolesDAO rolJpa = new RolesDAOJpa(em);

		try {
			listaRoles = rolJpa.filtrarPag(textoPesquisa, idSistema,
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("role.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = rolJpa.retornarCount(textoPesquisa, idSistema);
			out.setCountTotal(count);
		}

		listaRolesTO = new ArrayList<RolesTO>();

		for (Roles rol : listaRoles) {
			RolesTO rolTO = ConverterEntidadeTO.criarRoleTO(rol);
			listaRolesTO.add(rolTO);

		}

		out.setSuccess();
		out.setListaRolesTO(listaRolesTO);

		return out;

	}

	public ListarRolesOut listarPorIdSistema(ListarRolesIn in)
			throws WebglassException {

		ListarRolesOut out = new ListarRolesOut();
		List<Roles> listaRoles;
		List<RolesTO> listaRolesTO;

		List<GrupoRole> listaGpr;

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());
		RolesDAO gpoJpa = new RolesDAOJpa(em);
		GrupoRoleDAO gprJpa = new GrupoRoleDAOJpa(em);

		try {
			listaGpr = gprJpa.listarPorGrupo(in.getIdGrupo());

			List<Integer> listaId = new ArrayList<Integer>();

			if (listaGpr.size() > 0) {
				for (GrupoRole gpr : listaGpr) {
					listaId.add(gpr.getGprRole().getRolId());
				}
			} else {
				listaId = null;
			}

			listaRoles = gpoJpa.listarPorIdSistema(listaId, in.getIdSistema());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("role.listar.naolocalizado"));
		}

		listaRolesTO = new ArrayList<RolesTO>();

		for (Roles rol : listaRoles) {
			RolesTO rolTO = ConverterEntidadeTO.criarRoleTO(rol);
			listaRolesTO.add(rolTO);

		}

		out.setSuccess();
		out.setListRolesTO(listaRolesTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarRolesIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(RolesTO rolTO, ResourceBundle bundle)
			throws WebglassException {

		if (rolTO == null) {
			throw new WebglassException(
					bundle.getString("role.cadastrar.camposobrigatorios"));
		} else {

			if (StringUtils.isBlank(rolTO.getRolDescricao())) {
				throw new WebglassException(
						bundle.getString("role.cadastrar.camposobrigatorios"));
			}

		}

		return true;
	}

	/**
	 * Metodo responsavel por validar se um registro com as mesmas propriedades
	 * ja existe no banco
	 * 
	 * @param {@link descricao}
	 * @return {@link boolean}
	 * @throws {@link WebglassException}
	 */
	public boolean validarUnique(RolesTO rolTO, ResourceBundle bundle)
			throws WebglassException {

		RolesDAO rolJpa = new RolesDAOJpa(em);
		Roles roles = null;
		Boolean unico = true;
		try {
			roles = rolJpa.buscarPor(rolTO.getRolNome(), rolTO
					.getRolSistemaTO().getSisId());

			if (roles.getRolId() != rolTO.getRolId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

	/**
	 * @param appName
	 * @param usuario
	 * @return
	 * @throws WebglassException
	 */
	public List<String> recuperarRolesUsuarioSistema(String appName,
			String usuario) throws WebglassException {


		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		List<String> listaRolesUsuario;
		List<UsuarioSistemaGrupo> listaUsg;
		List<GrupoRole> listaGrupoRole;

		GrupoRoleDAO gprJpa = new GrupoRoleDAOJpa(em);
		UsuarioSistemaGrupoDAO usgJpa = new UsuarioSistemaGrupoDAOJpa(em);

		try {

			listaUsg = usgJpa.listarPorSistemaUsuario(appName, usuario);

			List<Integer> listaGrupoId = new ArrayList<Integer>();
			for (UsuarioSistemaGrupo usuarioSistemaGrupo : listaUsg) {
				listaGrupoId.add(usuarioSistemaGrupo.getUsgGrupo().getGpoId());
			}

			listaGrupoRole = gprJpa.retornarRolesGrupos(listaGrupoId);

			listaRolesUsuario = new ArrayList<String>();
			for (GrupoRole gpr : listaGrupoRole) {

				if (!listaRolesUsuario.contains(gpr.getGprRole().getRolNome())) {
					listaRolesUsuario.add(gpr.getGprRole().getRolNome());
				}
			}

		} catch (NoResultException e) {

			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("role.listar.naolocalizado"));
		}

		return listaRolesUsuario;

	}

}
