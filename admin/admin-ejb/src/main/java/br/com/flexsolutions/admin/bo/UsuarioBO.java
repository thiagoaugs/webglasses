/**  
 * jass-ejb - UsuarioBO.java
 * 
 * Data de criacao 25/06/2015
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
import br.com.flexsolutions.admin.dao.UsuarioDAO;
import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.admin.dao.jpa.UsuarioDAOJpa;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.AlterarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.BloqUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.CadastrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ExcluirUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.FiltrarUsuarioOut;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioIn;
import br.com.flexsolutions.admin.to.usuario.ListarUsuarioOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.admin.util.DataUtils;
import br.com.flexsolutions.admin.util.SecurityUtil;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;

/**
 * Classe que encapsula as regras de negocio para o Usuario
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioBO {

	private final static Logger LOGGER = Logger.getLogger(Usuario.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um Usuario
	 * 
	 * @param {@link CadastrarUsuarioIn}
	 * @return {@link CadastrarUsuarioOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarUsuarioOut cadastrar(CadastrarUsuarioIn in)
			throws WebglassException {

		Usuario usu;
		UsuarioTO usuTO;

		CadastrarUsuarioOut out = new CadastrarUsuarioOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		// valida os campos obrigatorios
		if (validarCampos(in.getUsuarioTO(), bundle)) {

			UsuarioDAOJpa jpaDao = new UsuarioDAOJpa(em);
			usuTO = in.getUsuarioTO();

			// valida os campos com unique
			if (validarUnique(usuTO, bundle)) {

				// converte o TO em Entidade
				usu = ConverterTOEntidade.criarUsuario(usuTO);

				// obtem a data atual para setar na data de cadastro
				usu.setUsuDtCad(DataUtils.obterDataAtualFormatada());
				
				String senhaCripto = SecurityUtil.hash(usu.getUsuSenha());
				
				usu.setUsuSenha(senhaCripto);

				try {
					usu = jpaDao.create(usu);
					usuTO = ConverterEntidadeTO.criarUsuarioTO(usu);
				} catch (Exception e) {
					LOGGER.error(
							bundle.getString("usuario.cadastrar.errosalvar"), e);
					throw new WebglassException(
							bundle.getString("usuario.cadastrar.errosalvar"));
				}

			} else {
				throw new WebglassException(
						bundle.getString("usuario.cadastrar.errounique"));
			}

			out.setSuccess();
			out.setUsuarioTO(usuTO);

		}
		return out;
	}

	/**
	 * Faz a alteração de um usuário
	 *
	 * @param {@link AlterarUsuarioIn}
	 * @return {@link AlterarUsuarioOut}
	 * @throws WebglassException
	 */
	public AlterarUsuarioOut alterar(AlterarUsuarioIn in)
			throws WebglassException {
		Usuario usu;
		UsuarioTO usuTO;

		AlterarUsuarioOut out = new AlterarUsuarioOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		usuTO = in.getUsuarioTO();

		// valida os campos obrigatorios
		if (validarCampos(usuTO, bundle)) {
			UsuarioDAOJpa jpaDao = new UsuarioDAOJpa(em);
			try {
				usu = jpaDao.read(usuTO.getUsuId());
			} catch (NoResultException e) {
				String msg = MessageFormat.format(bundle
						.getString("usuario.alterar.registronaolocalizado"),
						usuTO.getUsuId());
				LOGGER.info(msg, e);

				throw new WebglassException(msg);
			}

			if (validarUnique(usuTO, bundle)) {
				usu = ConverterTOEntidade.criarUsuario(usuTO);
				// obtem a data atual para setar na data de cadastro
				usu.setUsuDtAlt(DataUtils.obterDataAtualFormatada());

				try {
					usu = jpaDao.update(usu);
					usuTO = ConverterEntidadeTO.criarUsuarioTO(usu);
				} catch (Exception e) {
					String msg = MessageFormat.format(
							bundle.getString("usuario.alterar.erroalterar"),
							usuTO.getUsuId());
					LOGGER.error(msg, e);
					throw new WebglassException(msg, e);
				}
			} else {
				throw new WebglassException(
						bundle.getString("usuario.cadastrar.errounique"));
			}

		}

		out.setUsuarioTO(usuTO);
		out.setSuccess();
		return out;
	}

	/**
	 * @param in
	 * @return
	 */
	public BloqUsuarioOut controlaBloqueio(BloqUsuarioIn in)
			throws WebglassException {
		Usuario usu;
		UsuarioTO usuTO;

		BloqUsuarioOut out = new BloqUsuarioOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		usuTO = in.getUsuarioTO();

		UsuarioDAOJpa jpaDao = new UsuarioDAOJpa(em);
		try {
			usu = jpaDao.read(usuTO.getUsuId());
		} catch (NoResultException e) {
			String msg = MessageFormat.format(
					bundle.getString("usuario.alterar.registronaolocalizado"),
					usuTO.getUsuId());
			LOGGER.info(msg, e);

			throw new WebglassException(msg);
		}

		usu = ConverterTOEntidade.criarUsuario(usuTO);
		// obtem a data atual para setar na data de cadastro
		usu.setUsuDtAlt(DataUtils.obterDataAtualFormatada());

		try {
			usu = jpaDao.update(usu);
			usuTO = ConverterEntidadeTO.criarUsuarioTO(usu);
		} catch (Exception e) {
			String msg = MessageFormat.format(
					bundle.getString("usuario.alterar.erroalterar"),
					usuTO.getUsuId());
			LOGGER.error(msg, e);
			throw new WebglassException(msg, e);
		}

		out.setUsuarioTO(usuTO);
		out.setSuccess();
		return out;
	}

	/**
	 * Faz a exclusão de um usuário
	 * 
	 * @param {@link ExcluirUsuarioIn}
	 * @return {@link ExcluirUsuarioOut}
	 * @throws WebglassException
	 */
	public ExcluirUsuarioOut excluir(ExcluirUsuarioIn in)
			throws WebglassException {

		ExcluirUsuarioOut out = new ExcluirUsuarioOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		UsuarioDAOJpa jpaDao = new UsuarioDAOJpa(em);

		try {
			jpaDao.delete(in.getUsuarioTO().getUsuId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(
					bundle.getString("usuario.excluir.constraintviolation"),
					e.getMessage());
			LOGGER.error(msg, e);

			throw new WebglassException(msg, e);
		}

		out.setSuccess();

		return out;

	}

	/**
	 * filtra todos os registros da tabela filtrado pelo campo login
	 * 
	 * @param {@link FiltrarUsuarioIn}
	 * @return {@link FiltrarUsuarioOut}
	 * @throws WebglassException
	 */
	public FiltrarUsuarioOut filtrar(FiltrarUsuarioIn in)
			throws WebglassException {

		String textoPesquisa;

		List<Usuario> listaUsuario;
		List<UsuarioTO> listaUsuarioTO;

		FiltrarUsuarioOut out = new FiltrarUsuarioOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		if (StringUtils.isBlank(in.getPesquisaLogin())) {
			textoPesquisa = "";
		} else {
			textoPesquisa = in.getPesquisaLogin();
		}

		UsuarioDAO usuJpa = new UsuarioDAOJpa(em);

		try {
			listaUsuario = usuJpa.filtrarPag(textoPesquisa,
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("usuario.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = usuJpa.retornarCount(textoPesquisa);
			out.setCountTotal(count);
		}

		listaUsuarioTO = new ArrayList<UsuarioTO>();

		for (Usuario usu : listaUsuario) {
			UsuarioTO usuTO = ConverterEntidadeTO.criarUsuarioTO(usu);
			listaUsuarioTO.add(usuTO);

		}

		out.setSuccess();
		out.setListaUsuarioTO(listaUsuarioTO);

		return out;

	}

	
	/**
	 * Lista todos os registros da tabela sem filtro
	 * 
	 * @param {@link ListarUsuarioIn}
	 * @return {@link ListarUsuarioOut}
	 * @throws WebglassException
	 */
	public ListarUsuarioOut listar(ListarUsuarioIn in)
			throws WebglassException {

		ResourceBundle bundle = ResourceBundle
				.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());
		
		List<Usuario> listaUsuario;
		List<UsuarioTO> listaUsuarioTO;
		
		List<UsuarioSistema> listaUsuarioSistema;

		ListarUsuarioOut out = new ListarUsuarioOut();

		UsuarioDAO usuJpa = new UsuarioDAOJpa(em);
		
		UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);

		try {
			
			listaUsuarioSistema = usiJpa.listarIdUsuariosPorSistema(in.getIdSistema());
			
			List<Integer> listaId = new ArrayList<Integer>();
			for (UsuarioSistema usi:listaUsuarioSistema){
				listaId.add(usi.getUsiUsuario().getUsuId());
			}
			
			
			listaUsuario = usuJpa.listarUsuariosNaoVinculadosAoSistema(listaId);
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("usuario.listar.naolocalizado"));
		}


		listaUsuarioTO = new ArrayList<UsuarioTO>();

		for (Usuario usu : listaUsuario) {
			UsuarioTO usuTO = ConverterEntidadeTO.criarUsuarioTO(usu);
			listaUsuarioTO.add(usuTO);
		}

		out.setSuccess();
		out.setListaUsuarioTO(listaUsuarioTO);

		return out;

	}
	
	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link CadastrarUsuarioIn}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(UsuarioTO usuTO, ResourceBundle bundle)
			throws WebglassException {
		if (StringUtils.isBlank(usuTO.getUsuCpf())) {
			throw new WebglassException(
					bundle.getString("usuario.cadastrar.camposobrigatorios"));
		}
		if (StringUtils.isBlank(usuTO.getUsuNome())) {
			throw new WebglassException(
					bundle.getString("usuario.cadastrar.camposobrigatorios"));
		}

		if (StringUtils.isBlank(usuTO.getUsuLogin())) {
			throw new WebglassException(
					bundle.getString("usuario.cadastrar.camposobrigatorios"));
		}
		if (StringUtils.isBlank(usuTO.getUsuSenha())) {
			throw new WebglassException(
					bundle.getString("usuario.cadastrar.camposobrigatorios"));
		}
		if (StringUtils.isBlank(usuTO.getUsuEmail())) {
			throw new WebglassException(
					bundle.getString("usuario.cadastrar.camposobrigatorios"));
		}
		// if (usuTO.getUsuPessoaTO().getPesId() == null) {
		// throw new WebglassException(
		// bundle.getString("usuario.cadastrar.camposobrigatorios"));
		// }

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
	public boolean validarUnique(UsuarioTO usuTO, ResourceBundle bundle)
			throws WebglassException {

		UsuarioDAO usuJpa = new UsuarioDAOJpa(em);
		Usuario usuario = null;
		Boolean unico = true;
		try {
			usuario = usuJpa.buscarPor(usuTO.getUsuCpf());

			if (usuario.getUsuId() != usuTO.getUsuId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
