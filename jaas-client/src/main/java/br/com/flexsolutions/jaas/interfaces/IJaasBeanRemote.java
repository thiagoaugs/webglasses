/**
 *jaas-client - IJaasBeanRemote.java
 * 
 * Data de criacao 13/11/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.interfaces;

import java.io.Serializable;

import javax.ejb.Remote;

/**
 * @author desenv.flexsolutions
 *
 */
@Remote
public abstract interface IJaasBeanRemote  extends Serializable {
	
	  /**
	 * @param paramString  - nomeSistema
	 * @param paramString2 - login usuario
	 * @param paramsrString3 - senha usuario
	 * @return
	 */
	public Boolean doLogin(String paramString1, String paramsrString2 );

}
