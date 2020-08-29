/**  
 * jass-ejb - TelaMenuBO.java
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
import br.com.flexsolutions.admin.dao.SistemaDAO;
import br.com.flexsolutions.admin.dao.TelaMenuDAO;
import br.com.flexsolutions.admin.dao.TelaMenuGrupoDAO;
import br.com.flexsolutions.admin.dao.UsuarioDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaGrupoDAO;
import br.com.flexsolutions.admin.dao.jpa.SistemaDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.TelaMenuDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.TelaMenuGrupoDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaGrupoDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.TelaMenuTO;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.AlterarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.CadastrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ExcluirTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.FiltrarTelaMenuOut;
import br.com.flexsolutions.admin.to.telamenu.GetMenuSistemaIn;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuIn;
import br.com.flexsolutions.admin.to.telamenu.ListarTelaMenuOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Sistema;
import br.com.flexsolutions.security.pojo.TelaMenu;
import br.com.flexsolutions.security.pojo.TelaMenuGrupo;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;
import br.com.flexsolutions.security.pojo.UsuarioSistemaGrupo;

/**
 * Classe que encapsula as regras de negocio para a telaMenu
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class TelaMenuBO {

	private final static Logger LOGGER = Logger.getLogger(TelaMenu.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um TelaMenu
	 * 
	 * @param {@link CadastrarTelaMenuIn}
	 * @return {@link CadastrarTelaMenuOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarTelaMenuOut cadastrar(CadastrarTelaMenuIn in)
			throws WebglassException {

		TelaMenu tme;
		TelaMenuTO tmeTO;

		CadastrarTelaMenuOut out = new CadastrarTelaMenuOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		// valida os campos obrigatorios
		if (validarCampos(in.getTelaMenuTO(), bundle)) {

			TelaMenuDAOJpa jpaDao = new TelaMenuDAOJpa(em);
			tmeTO = in.getTelaMenuTO();

			// valida os campos com unique
			// if (validarUnique(tmeTO, bundle)) {

			// converte o TO em Entidade
			tme = ConverterTOEntidade.criarTelaMenu(tmeTO);

			try {
				tme = jpaDao.create(tme);
			} catch (Exception e) {
				LOGGER.error(bundle.getString("telamenu.cadastrar.errosalvar"),
						e);
				throw new WebglassException(
						bundle.getString("telamenu.cadastrar.errosalvar"));
			}

			// } else {
			// throw new WebglassException(
			// bundle.getString("telamenu.cadastrar.errounique"));
			// }

			tmeTO = ConverterEntidadeTO.criarTelaMenuTO(tme);
			out.setSuccess();
			out.setTelaMenuTO(tmeTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um tmeário
	 *
	 * @param {@link AlterarTelaMenuIn}
	 * @return {@link AlterarTelaMenuOut}
	 * @throws WebglassException
	 */
	public AlterarTelaMenuOut alterar(AlterarTelaMenuIn in)
			throws WebglassException {
		TelaMenu tme;
		TelaMenuTO tmeTO;

		AlterarTelaMenuOut out = new AlterarTelaMenuOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		tmeTO = in.getTelaMenuTO();

		// valida os campos obrigatorios
		if (validarCampos(tmeTO, bundle)) {
			TelaMenuDAOJpa jpaDao = new TelaMenuDAOJpa(em);
			try {
				tme = jpaDao.read(tmeTO.getTmeId());
			} catch (NoResultException e) {
				String msg = MessageFormat.format(bundle
						.getString("telamenu.alterar.registronaolocalizado"),
						tmeTO.getTmeId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			// if (validarUnique(tmeTO, bundle)) {

			tme = ConverterTOEntidade.criarTelaMenu(tmeTO);
			try {
				tme = jpaDao.update(tme);
			} catch (Exception e) {
				String msg = MessageFormat.format(
						bundle.getString("telamenu.alterar.erroalterar"),
						tmeTO.getTmeId());
				LOGGER.error(msg, e);
				throw new WebglassException(msg, e);
			}
			// } else {
			// throw new WebglassException(
			// bundle.getString("telamenu.cadastrar.errounique"));
			// }
			tmeTO = ConverterEntidadeTO.criarTelaMenuTO(tme);
		}

		out.setTelaMenuTO(tmeTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um tmeário
	 * 
	 * @param {@link ExcluirTelaMenuIn}
	 * @return {@link ExcluirTelaMenuOut}
	 * @throws WebglassException
	 */
	public ExcluirTelaMenuOut excluir(ExcluirTelaMenuIn in)
			throws WebglassException {

		ExcluirTelaMenuOut out = new ExcluirTelaMenuOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		TelaMenuDAOJpa jpaDao = new TelaMenuDAOJpa(em);

		try {
			jpaDao.delete(in.getTelaMenuTO().getTmeId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("telamenu.excluir.constraintviolation"),
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
	 * @param {@link FiltrarTelaMenuIn}
	 * @return {@link FiltrarTelaMenuOut}
	 * @throws WebglassException
	 */
	public FiltrarTelaMenuOut filtrar(FiltrarTelaMenuIn in)
			throws WebglassException {

		List<TelaMenu> listaTelaMenu;
		List<TelaMenuTO> listaTelaMenuTO;

		FiltrarTelaMenuOut out = new FiltrarTelaMenuOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		TelaMenuDAO tmeJpa = new TelaMenuDAOJpa(em);

		try {
			listaTelaMenu = tmeJpa.filtrarPag(in.getPesquisa(),
					in.getIdSistema(), in.getInicioRegistros(),
					in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("telamenu.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = tmeJpa.retornarCount(in.getPesquisa(),
					in.getIdSistema());
			out.setCountTotal(count);
		}

		listaTelaMenuTO = new ArrayList<TelaMenuTO>();

		for (TelaMenu tme : listaTelaMenu) {
			TelaMenuTO tmeTO = ConverterEntidadeTO.criarTelaMenuTO(tme);
			listaTelaMenuTO.add(tmeTO);

		}

		out.setSuccess();
		out.setListaTelaMenuTO(listaTelaMenuTO);

		return out;

	}

	/**
	 * Metodo responsavel por listar todos os registros da classe.
	 * 
	 * @return {@link ListarTelaMenuOut}
	 * @throws WebglassException
	 */
	public ListarTelaMenuOut listar() throws WebglassException {

		List<TelaMenu> listaTelaMenu;
		List<TelaMenuTO> listaTelaMenuTO;

		ListarTelaMenuOut out = new ListarTelaMenuOut();

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		TelaMenuDAO tmeJpa = new TelaMenuDAOJpa(em);

		try {
			listaTelaMenu = tmeJpa.listar();
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("sistema.listar.naolocalizado"));
		}

		listaTelaMenuTO = new ArrayList<TelaMenuTO>();

		for (TelaMenu tme : listaTelaMenu) {
			TelaMenuTO tmeTO = ConverterEntidadeTO.criarTelaMenuTO(tme);
			listaTelaMenuTO.add(tmeTO);

		}

		out.setSuccess();
		out.setListaTelaMenuTO(listaTelaMenuTO);

		return out;

	}

	public ListarTelaMenuOut listarPorIdSistemaGrupo(ListarTelaMenuIn in)
			throws WebglassException {

		List<TelaMenu> listaTelaMenu;
		List<TelaMenuTO> listaTelaMenuTO;

		List<TelaMenuGrupo> listaTelaMenuGrupo;

		ListarTelaMenuOut out = new ListarTelaMenuOut();

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		TelaMenuDAO tmeJpa = new TelaMenuDAOJpa(em);
		TelaMenuGrupoDAO tmgJpa = new TelaMenuGrupoDAOJpa(em);

		try {
			listaTelaMenuGrupo = tmgJpa.listarTmgPorGrupo(in.getIdGrupo());

			List<Integer> listaId = new ArrayList<Integer>();

			if (listaTelaMenuGrupo.size() > 0) {
				for (TelaMenuGrupo tmg : listaTelaMenuGrupo) {
					listaId.add(tmg.getTmgTelaMenu().getTmeId());
				}
			} else {
				listaId = null;
			}

			listaTelaMenu = tmeJpa.listarPorIdSistema(listaId,
					in.getIdSistema());

		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("sistema.listar.naolocalizado"));
		}

		listaTelaMenuTO = new ArrayList<TelaMenuTO>();

		for (TelaMenu tme : listaTelaMenu) {
			TelaMenuTO tmeTO = ConverterEntidadeTO.criarTelaMenuTO(tme);
			listaTelaMenuTO.add(tmeTO);

		}

		out.setSuccess();
		out.setListaTelaMenuTO(listaTelaMenuTO);

		return out;

	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarTelaMenuIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(TelaMenuTO tmeTO, ResourceBundle bundle)
			throws WebglassException {

		if (StringUtils.isBlank(tmeTO.getTmeLegenda())) {
			throw new WebglassException(
					bundle.getString("telamenu.cadastrar.camposobrigatorios.legenda"));
		}
		if (tmeTO.getTmeNivel() != null && tmeTO.getTmeNivel().equals(0)) {
			throw new WebglassException(
					bundle.getString("telamenu.cadastrar.camposobrigatorios.nivel"));
		}
		if (StringUtils.isBlank(tmeTO.getTmeCamada())) {
			throw new WebglassException(
					bundle.getString("telamenu.cadastrar.camposobrigatorios.camada"));
		}
		if (tmeTO.getTmeSistemaTO().getSisId() != null
				&& tmeTO.getTmeSistemaTO().getSisId().equals(0)) {
			throw new WebglassException(
					bundle.getString("telamenu.cadastrar.camposobrigatorios.sistema"));
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
	public boolean validarUnique(TelaMenuTO tmeTO, ResourceBundle bundle)
			throws WebglassException {

		TelaMenuDAO tmeJpa = new TelaMenuDAOJpa(em);
		TelaMenu tme = null;
		Boolean unico = true;
		try {
			tme = tmeJpa.buscarPor(tmeTO.getTmeAcao(), tmeTO.getTmeSistemaTO()
					.getSisId());

			if (tme.getTmeId() != tmeTO.getTmeId()) {
				unico = false;
			}
		} catch (NoResultException e) {
			unico = true;
		}

		return unico;
	}

	// public List<TelaMenu> carregarMenuSistema(GetMenuSistemaIn in)
	// throws WebglassException {
	//
	// ResourceBundle bundle = ResourceBundle
	// .getBundle(MessageName.MESSAGES_ADMINISTRADORWEBGLASS.value());
	//
	//
	// UsuarioDAO usuJpa = new UsuarioDAOJpa(em);
	// SistemaDAO sisJpa = new SistemaDAOJpa(em);
	// Sistema sistema = null;
	// Usuario usuario = null;
	//
	// try {
	// // recuperar o id do usuario e o id do sistema atraves das strings
	// // passadas
	// sistema = sisJpa.buscarPor(in.getSistema());
	// System.out.println("id do sistema " + sistema.getSisId());
	//
	// usuario = usuJpa.buscarPorLogin(in.getUsuario());
	// System.out.println("id do usuario " + usuario.getUsuId());
	//
	// } catch (NoResultException e) {
	// throw new WebglassException(
	// bundle.getString("telamenu.carregarmenu.erro"));
	// }
	//
	// UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);
	// UsuarioSistema usuarioSistema = null;
	//
	// try {
	// usuarioSistema = usiJpa.buscarPor(usuario.getUsuId(),
	// sistema.getSisId());
	// System.out.println("usuario sistema com a id : "
	// + usuarioSistema.getUsiId());
	// } catch (NoResultException e) {
	// throw new WebglassException(
	// bundle.getString("telamenu.carregarmenu.erro"));
	// }
	//
	// UsuarioSistemaGrupoDAO usgJpa = new UsuarioSistemaGrupoDAOJpa(em);
	// List<UsuarioSistemaGrupo> listaUsg = null;
	// List<Integer> listaIdGrupos = null;
	//
	// try {
	// listaUsg = usgJpa.listarUsgPorUsuarioSistema(usuarioSistema
	// .getUsiId());
	// if (listaUsg != null) {
	// listaIdGrupos = new ArrayList<Integer>();
	// for (UsuarioSistemaGrupo usg : listaUsg) {
	// listaIdGrupos.add(usg.getUsgGrupo().getGpoId());
	//
	// }
	// } else {
	// System.out.println("nao encontrou registro");
	// return null;
	// }
	// System.out.println(listaIdGrupos);
	// } catch (NoResultException e) {
	// throw new WebglassException(
	// bundle.getString("telamenu.carregarmenu.erro"));
	// }
	//
	// // pesquisar na telamenugrupo as telas que pertencem ao
	// // grupo/sistema.
	// TelaMenuGrupoDAO tmgJpa = new TelaMenuGrupoDAOJpa(em);
	// List<TelaMenuGrupo> listaTelaMenuGrupo = null;
	// List<TelaMenu> listaTelaMenu = null;
	// try {
	// listaTelaMenuGrupo = tmgJpa.listarTmgPorGrupoSistema(listaIdGrupos,
	// sistema.getSisId());
	// System.out.println(listaTelaMenuGrupo);
	//
	//
	// }
	// catch (NoResultException e) {
	// throw new WebglassException(
	// bundle.getString("telamenu.carregarmenu.erro"));
	// }
	//
	// if (listaTelaMenuGrupo != null) {
	// listaTelaMenu = new ArrayList<TelaMenu>();
	// for (TelaMenuGrupo tmg : listaTelaMenuGrupo) {
	// listaTelaMenu.add(tmg.getTmgTelaMenu());
	// }
	// }
	// return listaTelaMenu;
	//
	// }

}
