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
import br.com.flexsolutions.admin.dao.SistemaDAO;
import br.com.flexsolutions.admin.dao.jpa.SistemaDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.AlterarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.CadastrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaIn;
import br.com.flexsolutions.admin.to.sistema.ExcluirSistemaOut;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaIn;
import br.com.flexsolutions.admin.to.sistema.FiltrarSistemaOut;
import br.com.flexsolutions.admin.to.sistema.ListarSistemaOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Sistema;

/**  
 * admin-ejb - SistemaBO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class SistemaBO {

	private final static Logger LOGGER = Logger.getLogger(Sistema.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um Sistema
	 * 
	 * @param {@link CadastrarSistemaIn}
	 * @return {@link CadastrarSistemaOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarSistemaOut cadastrar(CadastrarSistemaIn in)
			throws WebglassException {

		Sistema sis;
		SistemaTO sisTO;

		CadastrarSistemaOut out = new CadastrarSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		SistemaDAOJpa jpaDao = new SistemaDAOJpa(em);

		sisTO = in.getSistemaTO();

		// valida os campos obrigatorios
		if (validarCampos(sisTO, bundle)) {

			// valida os campos com unique
			if (validarUnique(sisTO, bundle)) {

				// converte o TO em Entidade
				sis = ConverterTOEntidade.criarSistema(sisTO);

				try {
					sis = jpaDao.create(sis);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("sistema.cadastrar.errosalvar"), e);
					throw new WebglassException(
							bundle.getString("sistema.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("sistema.cadastrar.errounique"));
			}

			sisTO = ConverterEntidadeTO.criarSistemaTO(sis);
			out.setSuccess();
			out.setSistemaTO(sisTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um sistema
	 *
	 * @param {@link AlterarSistemaIn}
	 * @return {@link AlterarSistemaOut}
	 * @throws WebglassException
	 */
	public AlterarSistemaOut alterar(AlterarSistemaIn in)
			throws WebglassException {

		Sistema sis;
		SistemaTO sisTO;

		AlterarSistemaOut out = new AlterarSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		sisTO = in.getSistemaTO();

		// valida os campos obrigatorios
		if (validarCampos(sisTO, bundle)) {
			SistemaDAOJpa jpaDao = new SistemaDAOJpa(em);
			try {
				sis = jpaDao.read(sisTO.getSisId());
			} catch (NoResultException e) {
				String msg = MessageFormat.format(bundle
						.getString("sistema.alterar.registronaolocalizado"),
						sisTO.getSisId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(sisTO, bundle)) {

				sis = ConverterTOEntidade.criarSistema(sisTO);

				try {
					sis = jpaDao.update(sis);
					sisTO = ConverterEntidadeTO.criarSistemaTO(sis);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("sistema.alterar.erroalterar"),
							sisTO.getSisId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("sistema.cadastrar.errounique"));
			}

		}

		out.setSistemaTO(sisTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um sisário
	 * 
	 * @param {@link ExcluirSistemaIn}
	 * @return {@link ExcluirSistemaOut}
	 * @throws WebglassException
	 */
	public ExcluirSistemaOut excluir(ExcluirSistemaIn in)
			throws WebglassException {
		ExcluirSistemaOut out = new ExcluirSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		SistemaDAOJpa jpaDao = new SistemaDAOJpa(em);

		try {
			jpaDao.delete(in.getSistemaTO().getSisId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("sistema.excluir.constraintviolation"),
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
	 * @param {@link FiltrarSistemaIn}
	 * @return {@link FiltrarSistemaOut}
	 * @throws WebglassException
	 */
	public FiltrarSistemaOut filtrar(FiltrarSistemaIn in)
			throws WebglassException {

		String textoPesquisa;

		List<Sistema> listaSistema;
		List<SistemaTO> listaSistemaTO;

		FiltrarSistemaOut out = new FiltrarSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		if (StringUtils.isBlank(in.getPesquisa())) {
			textoPesquisa = "";
		} else {
			textoPesquisa = in.getPesquisa();
		}

		SistemaDAO sisJpa = new SistemaDAOJpa(em);

		try {
			listaSistema = sisJpa.filtrarPag(textoPesquisa,
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("sistema.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = sisJpa.retornarCount(textoPesquisa);
			out.setCountTotal(count);
		}

		listaSistemaTO = new ArrayList<SistemaTO>();

		for (Sistema sis : listaSistema) {
			SistemaTO sisTO = ConverterEntidadeTO.criarSistemaTO(sis);
			listaSistemaTO.add(sisTO);

		}

		out.setSuccess();
		out.setListaSistemaTO(listaSistemaTO);

		return out;

	}

	public ListarSistemaOut listar() throws WebglassException {

		List<Sistema> listaSistema;
		List<SistemaTO> listaSistemaTO;

		ListarSistemaOut out = new ListarSistemaOut();

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		SistemaDAO sisJpa = new SistemaDAOJpa(em);

		try {
			listaSistema = sisJpa.listar();
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("sistema.listar.naolocalizado"));
		}

		listaSistemaTO = new ArrayList<SistemaTO>();

		for (Sistema sis : listaSistema) {
			SistemaTO sisTO = ConverterEntidadeTO.criarSistemaTO(sis);
			listaSistemaTO.add(sisTO);

		}

		out.setSuccess();
		out.setListaSistemaTO(listaSistemaTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarSistemaIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(SistemaTO sisTO, ResourceBundle bundle)
			throws WebglassException {

		if (sisTO == null) {
			throw new WebglassException(
					bundle.getString("sistema.cadastrar.camposobrigatorios"));
		} else {

			if (StringUtils.isBlank(sisTO.getSisNome())) {
				throw new WebglassException(
						bundle.getString("sistema.cadastrar.camposobrigatorios"));
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
	public boolean validarUnique(SistemaTO sisTO, ResourceBundle bundle)
			throws WebglassException {

		SistemaDAO sisJpa = new SistemaDAOJpa(em);
		Sistema sis = null;
		Boolean unico = true;
		try {
			sis = sisJpa.buscarPor(sisTO.getSisNome());
			if (sis.getSisId() != sisTO.getSisId()) {
				unico = false;
			}
		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
