/**  
 * admin-ejb - TelaMenuGrupoBO.java
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
import br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.TelaMenuGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.TelaMenuGrupoTO;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.AlterarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.CadastrarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.ExcluirTelaMenuGrupoOut;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoIn;
import br.com.flexsolutions.admin.to.telamenugrupo.FiltrarTelaMenuGrupoOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Roles;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class TelaMenuGrupoBO {

	private final static Logger LOGGER = Logger.getLogger(Roles.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um TelaMenuGrupo
	 * 
	 * @param {@link CadastrarTelaMenuGrupoIn}
	 * @return {@link CadastrarTelaMenuGrupoOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarTelaMenuGrupoOut cadastrar(CadastrarTelaMenuGrupoIn in)
			throws WebglassException {

		TelaMenuGrupo tmg;
		TelaMenuGrupoTO tmgTO;

		CadastrarTelaMenuGrupoOut out = new CadastrarTelaMenuGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		TelaMenuGrupoDAOJpa jpaDao = new TelaMenuGrupoDAOJpa(em);

		tmgTO = in.getTmgTO();

		// valida os campos obrigatorios
		if (validarCampos(tmgTO, bundle)) {

			// valida os campos com unique
			if (validarUnique(tmgTO, bundle)) {

				// converte o TO em Entidade
				tmg = ConverterTOEntidade.criarTelaMenuGrupo(tmgTO);

				try {
					tmg = jpaDao.create(tmg);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("telamenugrupo.cadastrar.errosalvar"), e);
					throw new WebglassException(
							bundle.getString("telamenugrupo.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("telamenugrupo.cadastrar.errounique"));
			}

			tmgTO = ConverterEntidadeTO.criarTelaMenuGrupoTO(tmg);
			out.setSuccess();
			out.setTmgTO(tmgTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um grupo
	 *
	 * @param {@link AlterarTelaMenuGrupoIn}
	 * @return {@link AlterarTelaMenuGrupoOut}
	 * @throws WebglassException
	 */
	public AlterarTelaMenuGrupoOut alterar(AlterarTelaMenuGrupoIn in)
			throws WebglassException {

		TelaMenuGrupo tmg;
		TelaMenuGrupoTO tmgTO;

		AlterarTelaMenuGrupoOut out = new AlterarTelaMenuGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		tmgTO = in.getTmgTO();

		// valida os campos obrigatorios
		if (validarCampos(tmgTO, bundle)) {
			TelaMenuGrupoDAOJpa jpaDao = new TelaMenuGrupoDAOJpa(em);
			try {
				tmg = jpaDao.read(tmgTO.getTmgId());
			} catch (NoResultException e) {
				String msg = MessageFormat
						.format(bundle
								.getString("telamenugrupo.alterar.registronaolocalizado"),
								tmgTO.getTmgId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(tmgTO, bundle)) {

				tmg = ConverterTOEntidade.criarTelaMenuGrupo(tmgTO);
				try {
					tmg = jpaDao.update(tmg);
					tmgTO = ConverterEntidadeTO.criarTelaMenuGrupoTO(tmg);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("telamenugrupo.alterar.erroalterar"),
							tmgTO.getTmgId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("telamenugrupo.cadastrar.errounique"));
			}

		}

		out.setTmgTO(tmgTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um tmgário
	 * 
	 * @param {@link ExcluirTelaMenuGrupoIn}
	 * @return {@link ExcluirTelaMenuGrupoOut}
	 * @throws WebglassException
	 */
	public ExcluirTelaMenuGrupoOut excluir(ExcluirTelaMenuGrupoIn in)
			throws WebglassException {
		ExcluirTelaMenuGrupoOut out = new ExcluirTelaMenuGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		TelaMenuGrupoDAOJpa jpaDao = new TelaMenuGrupoDAOJpa(em);

		try {
			jpaDao.delete(in.getTmgTO().getTmgId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("telamenugrupo.excluir.constraintviolation"),
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
	 * @param {@link FiltrarTelaMenuGrupoIn}
	 * @return {@link FiltrarTelaMenuGrupoOut}
	 * @throws WebglassException
	 */
	public FiltrarTelaMenuGrupoOut filtrar(FiltrarTelaMenuGrupoIn in)
			throws WebglassException {

		List<TelaMenuGrupo> listaTelaMenuGrupo;
		List<TelaMenuGrupoTO> listaTelaMenuGrupoTO;

		FiltrarTelaMenuGrupoOut out = new FiltrarTelaMenuGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		TelaMenuGrupoDAO tmgJpa = new TelaMenuGrupoDAOJpa(em);

		try {
			listaTelaMenuGrupo = tmgJpa.filtrarPag(in.getIdSistema(),
					in.getIdTelamenu(), in.getIdGrupo(),
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("telamenugrupo.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = tmgJpa.retornarCount(in.getIdSistema(),
					in.getIdTelamenu(), in.getIdGrupo());
			out.setCountTotal(count);
			
		}

		listaTelaMenuGrupoTO = new ArrayList<TelaMenuGrupoTO>();

		for (TelaMenuGrupo tmg : listaTelaMenuGrupo) {
			TelaMenuGrupoTO tmgTO = ConverterEntidadeTO
					.criarTelaMenuGrupoTO(tmg);
			listaTelaMenuGrupoTO.add(tmgTO);

		}

		out.setSuccess();
		out.setListaTelaMenuGrupoTO(listaTelaMenuGrupoTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarTelaMenuGrupoIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(TelaMenuGrupoTO gprTO, ResourceBundle bundle)
			throws WebglassException {

		if (gprTO == null) {
			throw new WebglassException(
					bundle.getString("telamenugrupo.cadastrar.camposobrigatorios"));
		} else {

			if (gprTO.getTmgGrupo() != null
					&& gprTO.getTmgGrupo().getGpoId() != null) {
				if (gprTO.getTmgGrupo().getGpoId() == 0) {
					throw new WebglassException(
							bundle.getString("telamenugrupo.cadastrar.camposobrigatorios"));
				}
			}
			if (gprTO.getTmgTelaMenu() != null
					&& gprTO.getTmgTelaMenu().getTmeId() != null) {
				if (gprTO.getTmgTelaMenu().getTmeId() == 0) {
					throw new WebglassException(
							bundle.getString("telamenugrupo.cadastrar.camposobrigatorios"));
				}
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
	public boolean validarUnique(TelaMenuGrupoTO tmgTO, ResourceBundle bundle)
			throws WebglassException {

		TelaMenuGrupoDAO tmgJpa = new TelaMenuGrupoDAOJpa(em);
		TelaMenuGrupo telaMenuGrupo = null;
		Boolean unico = true;
		try {
			// buscar por id do grupo e id da role
			telaMenuGrupo = tmgJpa.buscarPor(tmgTO.getTmgGrupo().getGpoId(),
					tmgTO.getTmgTelaMenu().getTmeId());

			if (telaMenuGrupo.getTmgId() != tmgTO.getTmgId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}
}
