/**  
 * admin-ejb - PessoaBO.java
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
import br.com.flexsolutions.admin.dao.PessoaDAO;
import br.com.flexsolutions.admin.dao.jpa.PessoaDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.PessoaTO;
import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.AlterarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.CadastrarPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.ExcluirPessoaOut;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaIn;
import br.com.flexsolutions.admin.to.pessoa.FiltrarPessoaOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.admin.util.DataUtils;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Pessoa;

/**
 * Classe que encapsula as regras de negocio para a Pessoa
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class PessoaBO {

	private final static Logger LOGGER = Logger.getLogger(Pessoa.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um Pessoa
	 * 
	 * @param {@link CadastrarPessoaIn}
	 * @return {@link CadastrarPessoaOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarPessoaOut cadastrar(CadastrarPessoaIn in)
			throws WebglassException {

		Pessoa pes;
		PessoaTO pesTO;

		CadastrarPessoaOut out = new CadastrarPessoaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		// valida os campos obrigatorios
		if (validarCampos(in.getPessoaTO(), bundle)) {

			PessoaDAOJpa jpaDao = new PessoaDAOJpa(em);
			pesTO = in.getPessoaTO();

			// valida os campos com unique
			if (validarUnique(pesTO, bundle)) {

				// converte o TO em Entidade
				pes = ConverterTOEntidade.criarPessoa(pesTO);

				// obtem a data atual para setar na data de cadastro
				pes.setPesDtCad(DataUtils.obterDataAtualFormatada());

				try {
					pes = jpaDao.create(pes);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("pessoa.cadastrar.errosalvar"), e);
					throw new WebglassException(
							bundle.getString("pessoa.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("pessoa.cadastrar.errounique"));
			}

			pesTO = ConverterEntidadeTO.criarPessoaTO(pes);
			out.setSuccess();
			out.setPessoaTO(pesTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um pesário
	 *
	 * @param {@link AlterarPessoaIn}
	 * @return {@link AlterarPessoaOut}
	 * @throws WebglassException
	 */
	public AlterarPessoaOut alterar(AlterarPessoaIn in)
			throws WebglassException {
		Pessoa pes;
		PessoaTO pesTO;

		AlterarPessoaOut out = new AlterarPessoaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		pesTO = in.getPessoaTO();

		// valida os campos obrigatorios
		if (validarCampos(pesTO, bundle)) {
			PessoaDAOJpa jpaDao = new PessoaDAOJpa(em);
			try {
				pes = jpaDao.read(pesTO.getPesId());
			} catch (NoResultException e) {
				String msg = MessageFormat
						.format(bundle
								.getString("peses.alterar.registronaolocalizado"),
								pesTO.getPesId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(pesTO, bundle)) {

				pes = ConverterTOEntidade.criarPessoa(pesTO);
				try {
					pes = jpaDao.update(pes);
					pesTO = ConverterEntidadeTO.criarPessoaTO(pes);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("peses.alterar.erroalterar"),
							pesTO.getPesId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("peses.cadastrar.errounique"));
			}

		}

		out.setPessoaTO(pesTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um pesário
	 * 
	 * @param {@link ExcluirPessoaIn}
	 * @return {@link ExcluirPessoaOut}
	 * @throws WebglassException
	 */
	public ExcluirPessoaOut excluir(ExcluirPessoaIn in)
			throws WebglassException {

		ExcluirPessoaOut out = new ExcluirPessoaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		PessoaDAOJpa jpaDao = new PessoaDAOJpa(em);

		try {
			jpaDao.delete(in.getPessoaTO().getPesId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("pessoa.excluir.constraintviolation"),
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
	 * @param {@link FiltrarPessoaIn}
	 * @return {@link FiltrarPessoaOut}
	 * @throws WebglassException
	 */
	public FiltrarPessoaOut filtrar(FiltrarPessoaIn in)
			throws WebglassException {

		String textoPesquisa;

		List<Pessoa> listaPessoa;
		List<PessoaTO> listaPessoaTO;

		FiltrarPessoaOut out = new FiltrarPessoaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		if (StringUtils.isBlank(in.getPesquisa())) {
			textoPesquisa = "";
		} else {
			textoPesquisa = in.getPesquisa();
		}

		PessoaDAO pesJpa = new PessoaDAOJpa(em);

		try {
			listaPessoa = pesJpa.filtrarPag(textoPesquisa,
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("pessoa.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = pesJpa.retornarCount(textoPesquisa);
			out.setCountTotal(count);
		}

		listaPessoaTO = new ArrayList<PessoaTO>();

		for (Pessoa pes : listaPessoa) {
			PessoaTO pesTO = ConverterEntidadeTO.criarPessoaTO(pes);
			listaPessoaTO.add(pesTO);

		}

		out.setSuccess();
		out.setListaPessoaTO(listaPessoaTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarPessoaIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(PessoaTO pesTO, ResourceBundle bundle)
			throws WebglassException {
		
		if (StringUtils.isBlank(pesTO.getPesSobrenomeRazao())) {
			throw new WebglassException(
					bundle.getString("pessoa.cadastrar.camposobrigatorios"));
		}
		if (StringUtils.isBlank(pesTO.getPesNomeFantasia())) {
			throw new WebglassException(
					bundle.getString("pessoa.cadastrar.camposobrigatorios"));
		}

		if (StringUtils.isBlank(pesTO.getPesCpfCnpj())) {
			throw new WebglassException(
					bundle.getString("pessoa.cadastrar.camposobrigatorios"));
		}
		if (StringUtils.isBlank(pesTO.getPesRgIe())) {
			throw new WebglassException(
					bundle.getString("pessoa.cadastrar.camposobrigatorios"));
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
	public boolean validarUnique(PessoaTO pesTO, ResourceBundle bundle)
			throws WebglassException {

		PessoaDAO pesJpa = new PessoaDAOJpa(em);
		Pessoa pessoa = null;
		Boolean unico = true;
		try {
			pessoa =pesJpa.buscarPor(pesTO.getPesCpfCnpj());

			if (pessoa.getPesId() != pesTO.getPesId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
