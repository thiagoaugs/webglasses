/**  
 * jass-ejb - GrupoBO.java
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
import br.com.flexsolutions.admin.dao.GrupoDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.GrupoDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.GrupoTO;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.AlterarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.CadastrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ExcluirGrupoOut;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.FiltrarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoIn;
import br.com.flexsolutions.admin.to.grupo.ListarGrupoOut;
import br.com.flexsolutions.admin.to.grupo.RecuperarGruposOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Grupo;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class GrupoBO {

	private final static Logger LOGGER = Logger.getLogger(Grupo.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um Grupo
	 * 
	 * @param {@link CadastrarGrupoIn}
	 * @return {@link CadastrarGrupoOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarGrupoOut cadastrar(CadastrarGrupoIn in)
			throws WebglassException {

		Grupo gpo;
		GrupoTO gpoTO = null;

		CadastrarGrupoOut out = new CadastrarGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		GrupoDAOJpa jpaDao = new GrupoDAOJpa(em);

		gpoTO = in.getGrupoTO();

		// valida os campos obrigatorios
		if (validarCampos(gpoTO, bundle)) {

			// valida os campos com unique
			if (validarUnique(gpoTO, bundle)) {

				// converte o TO em Entidade
				gpo = ConverterTOEntidade.criarGrupo(gpoTO);

				try {
					gpo = jpaDao.create(gpo);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("grupo.cadastrar.errosalvar"), e);
					throw new WebglassException(
							bundle.getString("grupo.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("grupo.cadastrar.errounique"));
			}

			gpoTO = ConverterEntidadeTO.criarGrupoTO(gpo);
			out.setSuccess();
			out.setGrupoTO(gpoTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um grupo
	 *
	 * @param {@link AlterarGrupoIn}
	 * @return {@link AlterarGrupoOut}
	 * @throws WebglassException
	 */
	public AlterarGrupoOut alterar(AlterarGrupoIn in) throws WebglassException {

		Grupo gpo;
		GrupoTO gpoTO;

		AlterarGrupoOut out = new AlterarGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		gpoTO = in.getGrupoTO();

		// valida os campos obrigatorios
		if (validarCampos(gpoTO, bundle)) {
			GrupoDAOJpa jpaDao = new GrupoDAOJpa(em);
			try {
				gpo = jpaDao.read(gpoTO.getGpoId());
			} catch (NoResultException e) {
				String msg = MessageFormat
						.format(bundle
								.getString("grupo.alterar.registronaolocalizado"),
								gpoTO.getGpoId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(gpoTO, bundle)) {

				gpo = ConverterTOEntidade.criarGrupo(gpoTO);
				try {
					gpo = jpaDao.update(gpo);
					gpoTO = ConverterEntidadeTO.criarGrupoTO(gpo);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("grupo.alterar.erroalterar"),
							gpoTO.getGpoId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("grupo.cadastrar.errounique"));
			}

		}

		out.setGrupTO(gpoTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um gpoário
	 * 
	 * @param {@link ExcluirGrupoIn}
	 * @return {@link ExcluirGrupoOut}
	 * @throws WebglassException
	 */
	public ExcluirGrupoOut excluir(ExcluirGrupoIn in) throws WebglassException {
		ExcluirGrupoOut out = new ExcluirGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		GrupoDAOJpa jpaDao = new GrupoDAOJpa(em);

		try {
			jpaDao.delete(in.getGrupoTO().getGpoId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("grupo.excluir.constraintviolation"),
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
	 * @param {@link FiltrarGrupoIn}
	 * @return {@link FiltrarGrupoOut}
	 * @throws WebglassException
	 */
	public FiltrarGrupoOut filtrar(FiltrarGrupoIn in) throws WebglassException {

		List<Grupo> listaGrupo;
		List<GrupoTO> listaGrupoTO;

		FiltrarGrupoOut out = new FiltrarGrupoOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		GrupoDAO gpoJpa = new GrupoDAOJpa(em);

		try {
			listaGrupo = gpoJpa.filtrarPag(in.getPesquisaDescricao(),
					in.getIdSistema(), in.getInicioRegistros(),
					in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("grupo.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = gpoJpa.retornarCount(in.getPesquisaDescricao(),
					in.getIdSistema());
			out.setCountTotal(count);
		}

		listaGrupoTO = new ArrayList<GrupoTO>();

		for (Grupo gpo : listaGrupo) {
			GrupoTO gpoTO = ConverterEntidadeTO.criarGrupoTO(gpo);
			listaGrupoTO.add(gpoTO);

		}

		out.setSuccess();
		out.setListaGrupoTO(listaGrupoTO);

		return out;

	}

	public ListarGrupoOut listarPorIdSitema(ListarGrupoIn in)
			throws WebglassException {
		ListarGrupoOut out = new ListarGrupoOut();
		List<Grupo> listaGrupo;
		List<GrupoTO> listaGrupoTO;

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());
		GrupoDAO gpoJpa = new GrupoDAOJpa(em);

		try {
			listaGrupo = gpoJpa.listarPorIdSitema(in.getIdSistema());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("sistema.listar.naolocalizado"));
		}

		listaGrupoTO = new ArrayList<GrupoTO>();

		for (Grupo sis : listaGrupo) {
			GrupoTO sisTO = ConverterEntidadeTO.criarGrupoTO(sis);
			listaGrupoTO.add(sisTO);

		}

		out.setSuccess();
		out.setListaGrupoTO(listaGrupoTO);

		return out;
	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarGrupoIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(GrupoTO gpoTO, ResourceBundle bundle)
			throws WebglassException {

		if (gpoTO == null) {
			throw new WebglassException(
					bundle.getString("grupo.cadastrar.camposobrigatorios"));
		} else {

			if (StringUtils.isBlank(gpoTO.getGpoDescricao())) {
				throw new WebglassException(
						bundle.getString("grupo.cadastrar.camposobrigatorios"));
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
	public boolean validarUnique(GrupoTO gpoTO, ResourceBundle bundle)
			throws WebglassException {

		GrupoDAO gpoJpa = new GrupoDAOJpa(em);
		Grupo grupo = null;
		Boolean unico = true;
		try {
			grupo = gpoJpa.buscarPor(gpoTO.getGpoNome(), gpoTO
					.getGpoSistemaTO().getSisId());

			if (grupo.getGpoId() != gpoTO.getGpoId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

	/**
	 * Metodo responsavel por buscar todos os grupos pertencente ao usuario do
	 * sitema.
	 * 
	 * @param in
	 * @return {@link RecuperarGruposOut}
	 * @throws WebglassException
	 */
	public List<String> recuperarGruposUsuarioSistema(String appName,
			String usuario) throws WebglassException {

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		List<Grupo> listaGrupoUsuario;
		List<UsuarioSistemaGrupo> listaUsg;
		List<String> listaGrupos;

		UsuarioSistemaGrupoDAO usgJpa = new UsuarioSistemaGrupoDAOJpa(em);
		GrupoDAO gprJpa = new GrupoDAOJpa(em);

		try {

			listaUsg = usgJpa.listarPorSistemaUsuario(appName, usuario);

			// List<Integer> listaGrupoId = new ArrayList<Integer>();

			// for (UsuarioSistemaGrupo usuarioSistemaGrupo : listaUsg) {
			// listaGrupoId.add(usuarioSistemaGrupo.getUsgGrupo().getGpoId());
			// }
			//
			// listaGrupoUsuario = gprJpa.retornarGrupos(listaGrupoId);

			listaGrupos = new ArrayList<String>();
			if (listaUsg.size() > 0) {
				for (UsuarioSistemaGrupo usg : listaUsg) {
					if (!listaGrupos.contains(usg.getUsgGrupo().getGpoNome())) {
						listaGrupos.add(usg.getUsgGrupo().getGpoNome());
					}
				}
			}
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("role.listar.naolocalizado"));
		}

		return listaGrupos;

	}

}
