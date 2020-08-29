/**  
 * admin-ejb - GrupoRoleRoleBO.java
 * 
 * Data de criacao 10/08/2015
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

import org.jboss.logging.Logger;

import br.com.flexsolutions.admin.constants.MessageName;
import br.com.flexsolutions.admin.dao.GrupoRoleDAO;
import br.com.flexsolutions.admin.dao.jpa.GrupoRoleDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.GrupoRoleTO;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.AlterarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.CadastrarGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.ExcluirGrupoRoleOut;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleIn;
import br.com.flexsolutions.admin.to.gruporole.FiltrarGrupoRoleOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.GrupoRole;
import br.com.flexsolutions.security.pojo.Roles;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class GrupoRoleBO {

	private final static Logger LOGGER = Logger.getLogger(Roles.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um GrupoRole
	 * 
	 * @param {@link CadastrarGrupoRoleIn}
	 * @return {@link CadastrarGrupoRoleOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarGrupoRoleOut cadastrar(CadastrarGrupoRoleIn in)
			throws WebglassException {

		GrupoRole gpr;
		GrupoRoleTO gprTO;

		CadastrarGrupoRoleOut out = new CadastrarGrupoRoleOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		GrupoRoleDAOJpa jpaDao = new GrupoRoleDAOJpa(em);

		gprTO = in.getGprTO();

		// valida os campos obrigatorios
		if (validarCampos(gprTO, bundle)) {

			// valida os campos com unique
			if (validarUnique(gprTO, bundle)) {

				// converte o TO em Entidade
				gpr = ConverterTOEntidade.criarGrupoRoles(gprTO);

				try {
					gpr = jpaDao.create(gpr);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("gruporole.cadastrar.errosalvar"),
							e);
					throw new WebglassException(
							bundle.getString("gruporole.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("gruporole.cadastrar.errounique"));
			}

			gprTO = ConverterEntidadeTO.criarGrupoRoleTO(gpr);
			out.setSuccess();
			out.setGprTO(gprTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um grupoRole
	 *
	 * @param {@link AlterarGrupoRoleIn}
	 * @return {@link AlterarGrupoRoleOut}
	 * @throws WebglassException
	 */
	public AlterarGrupoRoleOut alterar(AlterarGrupoRoleIn in)
			throws WebglassException {

		GrupoRole gpr;
		GrupoRoleTO gprTO;

		AlterarGrupoRoleOut out = new AlterarGrupoRoleOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		gprTO = in.getGprTO();

		// valida os campos obrigatorios
		if (validarCampos(gprTO, bundle)) {
			GrupoRoleDAOJpa jpaDao = new GrupoRoleDAOJpa(em);
			try {
				gpr = jpaDao.read(gprTO.getGprId());
			} catch (NoResultException e) {
				String msg = MessageFormat.format(bundle
						.getString("gruporole.alterar.registronaolocalizado"),
						gprTO.getGprId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(gprTO, bundle)) {

				gpr = ConverterTOEntidade.criarGrupoRoles(gprTO);
				try {
					gpr = jpaDao.update(gpr);
					gprTO = ConverterEntidadeTO.criarGrupoRoleTO(gpr);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("gruporole.alterar.erroalterar"),
							gprTO.getGprId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("gruporole.cadastrar.errounique"));
			}

		}

		out.setGprTO(gprTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um gprário
	 * 
	 * @param {@link ExcluirGrupoRoleIn}
	 * @return {@link ExcluirGrupoRoleOut}
	 * @throws WebglassException
	 */
	public ExcluirGrupoRoleOut excluir(ExcluirGrupoRoleIn in)
			throws WebglassException {
		ExcluirGrupoRoleOut out = new ExcluirGrupoRoleOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		GrupoRoleDAOJpa jpaDao = new GrupoRoleDAOJpa(em);

		try {
			jpaDao.delete(in.getGprTO().getGprId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("gruporole.excluir.constraintviolation"),
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
	 * @param {@link FiltrarGrupoRoleIn}
	 * @return {@link FiltrarGrupoRoleOut}
	 * @throws WebglassException
	 */
	public FiltrarGrupoRoleOut filtrar(FiltrarGrupoRoleIn in)
			throws WebglassException {

		
		
		
		List<GrupoRole> listaGrupoRole;
		List<GrupoRoleTO> listaGrupoRoleTO;

		FiltrarGrupoRoleOut out = new FiltrarGrupoRoleOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		GrupoRoleDAO gprJpa = new GrupoRoleDAOJpa(em);

		try {
			listaGrupoRole = gprJpa.filtrarPag(in.getIdSistema(), in.getIdGrupo(), in.getIdRole(), in.getInicioRegistros(),
					in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("gruporole.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = gprJpa.retornarCount(in.getIdSistema(), in.getIdGrupo(), in.getIdRole());
			out.setCountTotal(count);
		}

		listaGrupoRoleTO = new ArrayList<GrupoRoleTO>();

		for (GrupoRole gpr : listaGrupoRole) {
			GrupoRoleTO gprTO = ConverterEntidadeTO.criarGrupoRoleTO(gpr);
			listaGrupoRoleTO.add(gprTO);

		}

		out.setSuccess();
		out.setListaGrupoRoleTO(listaGrupoRoleTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarGrupoRoleIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(GrupoRoleTO gprTO, ResourceBundle bundle)
			throws WebglassException {

		if (gprTO == null) {
			throw new WebglassException(
					bundle.getString("gruporole.cadastrar.camposobrigatorios"));
		} else {

			if (gprTO.getGprGrupo() != null
					&& gprTO.getGprGrupo().getGpoId() != null) {
				if (gprTO.getGprGrupo().getGpoId() == 0) {
					throw new WebglassException(
							bundle.getString("gruporole.cadastrar.camposobrigatorios"));
				}
			}

			if (gprTO.getGprRole() != null
					&& gprTO.getGprRole().getRolId() != null) {
				if (gprTO.getGprRole().getRolId() == 0) {
					throw new WebglassException(
							bundle.getString("gruporole.cadastrar.camposobrigatorios"));
				}
			}

		}

		return true;
	}

	/**
	 * Metodo responsavel por validar se um registro com as mesmas propriedades
	 * ja existe no banco
	 * 
	 * @param {@link GrupoRoleTO}
	 * @return {@link boolean}
	 * @throws {@link WebglassException}
	 */
	public boolean validarUnique(GrupoRoleTO gprTO, ResourceBundle bundle)
			throws WebglassException {

		GrupoRoleDAO gprJpa = new GrupoRoleDAOJpa(em);
		GrupoRole grupoRole = null;
		Boolean unico = true;
		try {

			// buscar por id do grupo e id da role
			grupoRole = gprJpa.buscarPor(gprTO.getGprGrupo().getGpoId(), gprTO
					.getGprRole().getRolId());

			if (grupoRole.getGprId() != gprTO.getGprId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
