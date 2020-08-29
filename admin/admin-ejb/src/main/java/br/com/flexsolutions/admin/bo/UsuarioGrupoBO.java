/**  
 * admin-ejb - UsuarioGrupoGrupoBO.java
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

import org.jboss.logging.Logger;

import br.com.flexsolutions.admin.constants.MessageName;
import br.com.flexsolutions.admin.dao.UsuarioGrupoDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.UsuarioGrupoDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioGrupoTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaGrupoTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.CadastrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.ExcluirUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioGrupoOut;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn;
import br.com.flexsolutions.admin.to.usuariogrupo.FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.UsuarioGrupo;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * Classe que encapsula as regras de negocio para o UsuarioGrupoGrupo
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioGrupoBO {

	private final static Logger LOGGER = Logger.getLogger(UsuarioGrupo.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um UsuarioGrupo
	 * 
	 * @param {@link CadastrarUsuarioGrupoIn}
	 * @return {@link CadastrarUsuarioGrupoOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarUsuarioGrupoOut cadastrar(CadastrarUsuarioGrupoIn in)
			throws WebglassException {

		UsuarioGrupo ugp;
		UsuarioGrupoTO ugpTO;

		UsuarioSistemaGrupo usg = null;
		UsuarioSistemaGrupoTO usgTO = null;

		CadastrarUsuarioGrupoOut out = new CadastrarUsuarioGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		UsuarioGrupoDAO ugpJpaDao = new UsuarioGrupoDAOJpa(em);
		UsuarioSistemaGrupoDAO usgJpaDao = new UsuarioSistemaGrupoDAOJpa(em);

		// valida os campos obrigatorios
		if (validarCampos(in.getSisTO(), in.getGpoTO(),
				in.getListaUsuarioSistema(), bundle)) {

			GrupoTO gpoTO = in.getGpoTO();
			UsuarioTO usuTO = null;

			for (UsuarioSistemaTO usiTO : in.getListaUsuarioSistema()) {

				usuTO = usiTO.getUsiUsuario();

				usgTO = new UsuarioSistemaGrupoTO();
				ugpTO = new UsuarioGrupoTO();

				usgTO.setUsgUsuarioSistema(usiTO);
				usgTO.setUsgGrupo(gpoTO);

				ugpTO.setUgpGrupo(gpoTO);
				ugpTO.setUgpUsuario(usuTO);

				if (validarUnique(ugpTO, usgTO, bundle)) {

					// converte o TO em Entidade
					ugp = ConverterTOEntidade.criarUsuarioGrupo(ugpTO);
					usg = ConverterTOEntidade.criarUsuarioSistemaGrupo(usgTO);

					try {

						ugp = ugpJpaDao.create(ugp);
						usg = usgJpaDao.create(usg);

					} catch (Exception e) {
						LOGGER.error(
								bundle.getString("ugparioGrupo.cadastrar.errosalvar"),
								e);
						throw new WebglassException(
								bundle.getString("ugparioGrupo.cadastrar.errosalvar"));
					}

				}

			}

			out.setSuccess();
		}
		return out;
	}

	/**
	 * Faz a exclusão de um ugpário
	 * 
	 * @param {@link ExcluirUsuarioGrupoIn}
	 * @return {@link ExcluirUsuarioGrupoOut}
	 * @throws WebglassException
	 */
	public ExcluirUsuarioGrupoOut excluir(ExcluirUsuarioGrupoIn in)
			throws WebglassException {

		ExcluirUsuarioGrupoOut out = new ExcluirUsuarioGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		UsuarioGrupoDAO ugpJpaDao = new UsuarioGrupoDAOJpa(em);
		UsuarioSistemaGrupoDAO usgJpaDao = new UsuarioSistemaGrupoDAOJpa(em);

		try {

			UsuarioGrupo ugp = ugpJpaDao.buscarPor(in.getUsgTO()
					.getUsgUsuarioSistema().getUsiUsuario().getUsuId(), in
					.getUsgTO().getUsgGrupo().getGpoId());

			ugpJpaDao.delete(ugp.getUgpId());
			usgJpaDao.delete(in.getUsgTO().getUsgId());

		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(bundle
					.getString("usuariogrupo.excluir.constraintviolation"), e
					.getMessage());
			LOGGER.error(msg, e);

			throw new WebglassException(msg, e);
		}

		out.setSuccess();

		return out;

	}

	/**
	 * Lista todos os registros da tabela filtrado pelo campo login
	 * 
	 * @param {@link FiltrarUsuarioGrupoIn}
	 * @return {@link FiltrarUsuarioGrupoOut}
	 * @throws WebglassException
	 */
	public FiltrarUsuarioGrupoOut filtrar(FiltrarUsuarioGrupoIn in)
			throws WebglassException {


		List<UsuarioSistemaGrupo> listaUsuarioSistemaGrupo;
		List<UsuarioSistemaGrupoTO> listaUsuarioSistemaGrupoTO;

		FiltrarUsuarioGrupoOut out = new FiltrarUsuarioGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		UsuarioSistemaGrupoDAOJpa usgJpa = new UsuarioSistemaGrupoDAOJpa(em);

		try {
			listaUsuarioSistemaGrupo = usgJpa.filtrarPag(in.getIdSistema(), in.getIdGrupo(),
					in.getPesqUsuario(), in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("ugparioGrupo.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = usgJpa.retornarCount(in.getIdSistema(), in.getIdGrupo(),
					in.getPesqUsuario());
			out.setCountTotal(count);
		}

		listaUsuarioSistemaGrupoTO = new ArrayList<UsuarioSistemaGrupoTO>();

		for (UsuarioSistemaGrupo usg : listaUsuarioSistemaGrupo) {
			UsuarioSistemaGrupoTO usgTO = ConverterEntidadeTO
					.criarUsuarioSistemaGrupoTO(usg);
			listaUsuarioSistemaGrupoTO.add(usgTO);
		}

		out.setSuccess();
		out.setListaUsgTO(listaUsuarioSistemaGrupoTO);

		return out;

	}

	public FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut filtrarUsuarioPorSistemaNaoConfigurado(
			FiltrarUsuarioPorSistemaGrupoNaoConfiguradoIn in)
			throws WebglassException {

		List<UsuarioSistemaTO> listaUsiTO = null;
		List<UsuarioSistema> listaUsi = null;

		List<UsuarioSistemaGrupo> listaUsg;

		// buscar na tabela usuariosistemagrupo todos os usuarios com conf para
		// o sistema
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut out = new FiltrarUsuarioPorSistemaGrupoNaoConfiguradoOut();

		UsuarioSistemaGrupoDAO usgJpaDao = new UsuarioSistemaGrupoDAOJpa(em);
		UsuarioSistemaDAO usiJpaDao = new UsuarioSistemaDAOJpa(em);

		try {

			listaUsg = usgJpaDao.listarPorIdSistemaGrupo(in.getIdSistema(), in.getIdGrupo());

			List<Integer> listaId = new ArrayList<Integer>();
			
			for (UsuarioSistemaGrupo usg : listaUsg) {
				listaId.add(usg.getUsgUsuarioSistema().getUsiUsuario()
						.getUsuId());
			}

			listaUsi = usiJpaDao.listarUsuariosNaoConfigurados(listaId, in.getIdSistema());

		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("ugparioGrupo.listar.naolocalizado"));
		}

		listaUsiTO = new ArrayList<UsuarioSistemaTO>();

		for (UsuarioSistema usi : listaUsi) {
			UsuarioSistemaTO usiTO = ConverterEntidadeTO
					.criarUsuarioSistemaTO(usi);
			listaUsiTO.add(usiTO);
		}

		out.setSuccess();
		out.setListaUsuarioSistemaTO(listaUsiTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link ugpTO}
	 * @param {@link bundle}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(SistemaTO sisTO, GrupoTO gpoTO,
			List<UsuarioSistemaTO> listaUsgTO, ResourceBundle bundle)
			throws WebglassException {

		if (sisTO.getSisId() == null) {
			throw new WebglassException(
					bundle.getString("usuariogrupo.cadastrar.camposobrigatorios"));
		}

		if (gpoTO.getGpoId() == null) {
			throw new WebglassException(
					bundle.getString("usuariogrupo.cadastrar.camposobrigatorios"));
		}

		if (listaUsgTO.size() <= 0) {
			throw new WebglassException(
					bundle.getString("usuariogrupo.cadastrar.camposobrigatorios"));
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
	public boolean validarUnique(UsuarioGrupoTO ugpTO,
			UsuarioSistemaGrupoTO usgTO, ResourceBundle bundle)
			throws WebglassException {

		UsuarioGrupoDAO ugpJpa = new UsuarioGrupoDAOJpa(em);
		UsuarioSistemaGrupoDAO usgJpa = new UsuarioSistemaGrupoDAOJpa(em);

		Boolean unico = false;
		try {

			UsuarioGrupo ugp = ugpJpa.buscarPor(ugpTO.getUgpUsuario().getUsuId(), ugpTO
					.getUgpGrupo().getGpoId());

			UsuarioSistemaGrupo	usg = usgJpa.buscarPor(usgTO.getUsgUsuarioSistema().getUsiSistema()
					.getSisId(), usgTO.getUsgGrupo().getGpoId(), usgTO
					.getUsgUsuarioSistema().getUsiUsuario().getUsuId());

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
