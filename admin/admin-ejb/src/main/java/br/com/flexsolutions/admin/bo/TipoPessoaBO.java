/**  
 * admin-ejb - TipoPessoaBO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.flexsolutions.admin.constants.MessageName;
import br.com.flexsolutions.admin.constants.TipoPessoaEnum;
import br.com.flexsolutions.admin.dao.jpa.TipoPessoaDAOJpa;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.TipoPessoaTO;
import br.com.flexsolutions.admin.to.tipopessoa.ListarTipoPessoaOut;
import br.com.flexsolutions.admin.to.tipopessoa.RetornarTipoPessoaIn;
import br.com.flexsolutions.admin.to.tipopessoa.RetornarTipoPessoaOut;
import br.com.flexsolutions.admin.util.ConverterEntidadeTO;
import br.com.flexsolutions.security.pojo.TipoPessoa;

/**
 * * Classe responsavel em fazer o CRUD do Tipo de pessoa.
 * 
 * @author Thiago Augusto
 *
 */
@ManagedBean
@RequestScoped
public class TipoPessoaBO {
	private final static String UNIT_NAME = "flexsolutionsDS";

	@PersistenceContext(name = UNIT_NAME)
	private EntityManager em;

	
	/**
	 * Este metodo ira fazer a varredura do TipoPessoaEnum atualizando as informacoes no banco de dados.
	 * 
	 * @throws {@link AtlasException}
	 */
	public void atualizarTiposPessoa() {
		TipoPessoaEnum[] tipoPessoaEnums = TipoPessoaEnum.values();

		TipoPessoaDAOJpa tipoPessoaDaoJpa = new TipoPessoaDAOJpa(em);

		for (TipoPessoaEnum tipoPessoaEnum : tipoPessoaEnums) {
			TipoPessoa tipoPessoa = tipoPessoaDaoJpa.findByPK(tipoPessoaEnum.getId());

			if (tipoPessoa == null) {
				tipoPessoa = new TipoPessoa();

				tipoPessoa.setTpsId(tipoPessoaEnum.getId());
				tipoPessoa.setTpsDescricao(tipoPessoaEnum.getDescricao());

				tipoPessoaDaoJpa.create(tipoPessoa);
			} else {
				tipoPessoa.setTpsDescricao(tipoPessoaEnum.getDescricao());

				tipoPessoaDaoJpa.update(tipoPessoa);
			}
		}
	}
	
	/**
	 * Este metodo retorna todos os tipos de pessoa 
	 * 
	 * @param in
	 * @return  {@link RetornarListaTipoPessoaOut}
	 * @throws {@link AtlasException}
	 */
	public ListarTipoPessoaOut retornarListaTipoPessoa() throws WebglassException {

		List<TipoPessoa> listaTipoPessoa;
		List<TipoPessoaTO> listaTipoPessoaTO;

		ListarTipoPessoaOut out = new ListarTipoPessoaOut();
		ResourceBundle bundle = ResourceBundle.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		TipoPessoaDAOJpa tpsDAOJpa = new TipoPessoaDAOJpa(em);

		try {
			listaTipoPessoa = tpsDAOJpa.listar();
		} catch (NoResultException e) {
			throw new WebglassException(bundle.getString("pessoa.listar.naolocalizado"));
		}

		listaTipoPessoaTO = new ArrayList<TipoPessoaTO>();

		for (TipoPessoa tps : listaTipoPessoa) {
			TipoPessoaTO tpsTO = ConverterEntidadeTO.criarTipoPessoaTO(tps);

			listaTipoPessoaTO.add(tpsTO);
		}

		out.setSuccess();
		out.setListaTipoPessoaTO(listaTipoPessoaTO);

		return out;
	}

	/**
	 * Retorna um tipo de pessoa, baseando-se no seu id.
	 * 
	 * @param in
	 * @return {@link RetornarTipoPessoaOut}
	 * @throws {@link AtlasException}
	 */  
	public RetornarTipoPessoaOut retornarTipoPessoa(RetornarTipoPessoaIn in) throws WebglassException{

		RetornarTipoPessoaOut out = null;

		ResourceBundle bundle = ResourceBundle.getBundle(MessageName.MESSAGES_ADMINISTRADOR.value());

		TipoPessoaDAOJpa tpsDAOJpa = new TipoPessoaDAOJpa(em);

		TipoPessoa tipoPessoa;
		try {
			tipoPessoa = tpsDAOJpa.read(in.getTipoPessoaTO().getTpsId());
		} catch (NoResultException e) {
			tipoPessoa = null;
		}

		if (tipoPessoa == null) {
			throw new WebglassException(bundle.getString("tipopessoa.retornar.naolocalizado"));
		}

		out = new RetornarTipoPessoaOut();

		TipoPessoaTO tipoPessoaTO = ConverterEntidadeTO.criarTipoPessoaTO(tipoPessoa);

		out.setSuccess();
		out.setTipoPessoaTO(tipoPessoaTO);

		return out;
	}

}
