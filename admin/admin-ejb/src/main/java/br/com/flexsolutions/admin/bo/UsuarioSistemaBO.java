/**  
 * admin-ejb - UsuarioSistemaBO.java
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
import br.com.flexsolutions.admin.dao.UsuarioSistemaDAO;
import br.com.flexsolutions.admin.dao.jpa.UsuarioSistemaDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.SistemaTO;
import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.CadastrarUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.ExcluirUsuarioSistemaOut;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaIn;
import br.com.flexsolutions.admin.to.usuariosistema.FiltrarUsuarioSistemaOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.admin.util.ConverterTOEntidade;
import br.com.flexsolutions.persistenceutils.exception.ConstraintViolationException;
import br.com.flexsolutions.security.pojo.Usuario;
import br.com.flexsolutions.security.pojo.UsuarioSistema;

/**
 * Classe que encapsula as regras de negocio para o UsuarioSistema
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class UsuarioSistemaBO {

	private final static Logger LOGGER = Logger.getLogger(Usuario.class);
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	/**
	 * Faz o cadastro de um UsuarioSistema
	 * 
	 * @param {@link CadastrarUsuarioSistemaIn}
	 * @return {@link CadastrarUsuarioSistemaOut}
	 * @throws {@link WebglassException}
	 */
	public CadastrarUsuarioSistemaOut cadastrar(CadastrarUsuarioSistemaIn in)
			throws WebglassException {

		UsuarioSistema usi;
		UsuarioSistemaTO usiTO;

		CadastrarUsuarioSistemaOut out = new CadastrarUsuarioSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		// valida os campos obrigatorios
		if (validarCampos(in.getSisTO(), in.getListaUsuario(), bundle)) {

			UsuarioSistemaDAOJpa jpaDao = new UsuarioSistemaDAOJpa(em);

			for (UsuarioTO usuario : in.getListaUsuario()) {
				usiTO = new UsuarioSistemaTO();
				usiTO.setUsiSistema(in.getSisTO());
				usiTO.setUsiUsuario(usuario);

				// valida os campos com unique
				if (validarUnique(usiTO, bundle)) {

					// converte o TO em Entidade
					usi = ConverterTOEntidade.criarUsuarioSistema(usiTO);

					try {
						usi = jpaDao.create(usi);
						usiTO = ConverterEntidadeTO.criarUsuarioSistemaTO(usi);
					} catch (Exception e) {
						LOGGER.error(
								bundle.getString("usuarioSistema.cadastrar.errosalvar"),
								e);
						throw new WebglassException(
								bundle.getString("usuarioSistema.cadastrar.errosalvar"));
					}
				} else {
					String msg = MessageFormat.format(bundle
							.getString("usuariosistema.cadastrar.errounique"),
							usiTO.getUsiUsuario().getUsuNome());

					throw new WebglassException(msg);
				}
				out.setSuccess();
				out.setUsiTO(usiTO);
			}
		}
		return out;
	}


	/**
	 * Faz a exclusão de um usiário
	 * 
	 * @param {@link ExcluirUsuarioSistemaIn}
	 * @return {@link ExcluirUsuarioSistemaOut}
	 * @throws WebglassException
	 */
	public ExcluirUsuarioSistemaOut excluir(ExcluirUsuarioSistemaIn in)
			throws WebglassException {

		ExcluirUsuarioSistemaOut out = new ExcluirUsuarioSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());

		UsuarioSistemaDAOJpa jpaDao = new UsuarioSistemaDAOJpa(em);

		try {
			jpaDao.delete(in.getUsiTO().getUsiId());
		} catch (ConstraintViolationException e) {
			String msg = MessageFormat.format(bundle
					.getString("usuariosistema.excluir.constraintviolation"), e
					.getMessage());
			LOGGER.error(msg, e);

			throw new WebglassException(msg, e);
		}

		out.setSuccess();

		return out;

	}

	/**
	 * 
	 * @param {@link FiltrarUsuarioSistemaIn}
	 * @return {@link FiltrarUsuarioSistemaOut}
	 * @throws WebglassException
	 */
	public FiltrarUsuarioSistemaOut filtrar(FiltrarUsuarioSistemaIn in)
			throws WebglassException {

		String nomeUsuario = null;
		Integer idSistema = null;

		List<UsuarioSistema> listaUsuarioSistema;
		List<UsuarioSistemaTO> listaUsuarioSistemaTO;

		FiltrarUsuarioSistemaOut out = new FiltrarUsuarioSistemaOut();
		ResourceBundle bundle = in
				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADOR
						.value());
		
		if (StringUtils.isBlank(in.getPesquisa())) {
			nomeUsuario = "";
		} else {
			nomeUsuario = in.getPesquisa();
		}
		
		if ( in.getIdSistema()!= null && in.getIdSistema() == 0 ){
			idSistema = null;
		}else{
			idSistema = in.getIdSistema();
		}

		UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);

		try {
			listaUsuarioSistema = usiJpa.filtrarPag(nomeUsuario, idSistema,
					in.getInicioRegistros(), in.getTotalRegistros());
		} catch (NoResultException e) {
			// todo adcionar a mensagem de erro
			throw new WebglassException(
					bundle.getString("usuarioSistema.listar.naolocalizado"));
		}

		if (in.isFazerCount()) {
			Long count = usiJpa.retornarCount(nomeUsuario, idSistema);
			out.setCountTotal(count);
		}

		listaUsuarioSistemaTO = new ArrayList<UsuarioSistemaTO>();

		for (UsuarioSistema usi : listaUsuarioSistema) {
			UsuarioSistemaTO usiTO = ConverterEntidadeTO
					.criarUsuarioSistemaTO(usi);
			listaUsuarioSistemaTO.add(usiTO);

		}

		out.setSuccess();
		out.setListaUsiTO(listaUsuarioSistemaTO);

		return out;

	}

//	/**
//	 * 
//	 * @param {@link FiltrarUsuarioSistemaIn}
//	 * @return {@link FiltrarUsuarioSistemaOut}
//	 * @throws WebglassException
//	 */
//	public FiltrarPorSistemaOut filtrarPorSistema(FiltrarPorSistemaIn in)
//			throws WebglassException {
//
//		Integer idSistema = null;
//
//		List<UsuarioSistema> listaUsuarioSistema;
//		List<UsuarioSistemaTO> listaUsuarioSistemaTO;
//
//		FiltrarPorSistemaOut out = new FiltrarPorSistemaOut();
//		ResourceBundle bundle = in
//				.getResourceBundle(MessageName.MESSAGES_ADMINISTRADORWEBGLASS
//						.value());
//
//		if (in.getIdSistema() != null) {
//				idSistema = in.getIdSistema();
//		}
//
//		UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);
//
//		try {
//			listaUsuarioSistema = usiJpa.listarPorSistema(idSistema);
//		} catch (NoResultException e) {
//			throw new WebglassException(
//					bundle.getString("usuarioSistema.listar.naolocalizado"));
//		}
//		listaUsuarioSistemaTO = new ArrayList<UsuarioSistemaTO>();
//
//		for (UsuarioSistema usi : listaUsuarioSistema) {
//			UsuarioSistemaTO usiTO = ConverterEntidadeTO
//					.criarUsuarioSistemaTO(usi);
//			listaUsuarioSistemaTO.add(usiTO);
//
//		}
//
//		out.setSuccess();
//		out.setListaUsuarioSistemaTO(listaUsuarioSistemaTO);
//
//		return out;
//
//	}

	/**
	 * Metodo responsavel por validar se os campos obrigatorios estao
	 * devidamente preenchidos
	 * 
	 * @param {@link usiTO}
	 * @param {@link bundle}
	 * @return {@link Boolean}
	 * @throws {@link WebglassException}
	 */
	private Boolean validarCampos(SistemaTO sisTO, List<UsuarioTO> lista,
			ResourceBundle bundle) throws WebglassException {

		if (sisTO.getSisId() == null) {
			throw new WebglassException(
					bundle.getString("usuariosistema.cadastrar.camposobrigatorios"));
		}

		if (lista != null) {
			if (lista.size() == 0) {
				throw new WebglassException(
						bundle.getString("usuariosistema.cadastrar.camposobrigatorios"));
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
	public boolean validarUnique(UsuarioSistemaTO usiTO, ResourceBundle bundle)
			throws WebglassException {

		UsuarioSistemaDAO usiJpa = new UsuarioSistemaDAOJpa(em);
		UsuarioSistema usuarioSistema = null;
		Boolean unico = true;
		try {
			usuarioSistema = usiJpa.buscarPor(usiTO.getUsiUsuario().getUsuId(),
					usiTO.getUsiSistema().getSisId());

			if (usuarioSistema.getUsiId() != usiTO.getUsiId()) {
				unico = false;
			}

		} catch (NoResultException e) {
			return true;
		}
		return unico;
	}

}
