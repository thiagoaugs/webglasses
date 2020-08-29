/**
 *admin-ejb - JaasBean.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.facade;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.flexsolutions.admin.bo.JaasBO;
import br.com.flexsolutions.admin.exception.WebglassException;
import br.com.flexsolutions.admin.to.telamenu.GetMenuSistemaIn;
import br.com.flexsolutions.security.interfaces.IJaasBeanRemote;
import br.com.flexsolutions.security.pojo.TelaMenu;

/**
 * @author Thiago Augusto
 *
 */
@Stateless
@Remote(IJaasBeanRemote.class)
@Interceptors(WebglassException.class)
public class JaasBean implements IJaasBeanRemote {
	private static final long serialVersionUID = 1L;

	@Inject
	private JaasBO jaasBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jaas.interfaces.IJaasBeanRemote#getLogin(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean doLogin(String paramString1, String paramString2) {
		Boolean validouUsuario = false;
		try {
			// valida o usuario no banco
			validouUsuario = jaasBO.doLogin(paramString1, paramString2);

		} catch (WebglassException e) {
			// todo adcionar a mensagem de erro
			e.printStackTrace();
		}

		return validouUsuario;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.flexsolutions.jaas.interfaces.ITelaMenuBeanRemote#getMenuSistema
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public List<TelaMenu> getMenuSistema(String appName, String usuario) {
		System.out.println("Carregando MENU para o sistema: " + appName + " e usuário: " + usuario );

		List<TelaMenu> listaTelaMenu = null;

		GetMenuSistemaIn in = new GetMenuSistemaIn();
		in.setSistema(appName);
		in.setUsuario(usuario);

		try {
			listaTelaMenu = jaasBO.carregarMenuSistema(in);
		} catch (WebglassException e) {
			e.printStackTrace();
		}

		return listaTelaMenu;
	}
}
