/**  
 * jaas - FAASCallBackHandler.java
 * 
 * Data de criacao 15/07/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.security.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author Thiago Augusto
 *
 */
public class JAASCallBackHandler implements CallbackHandler {

	
	private String user;
	private String pass;

	public JAASCallBackHandler(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		System.out.println("JAAS -  handle");

		for (Callback cb : callbacks) {

			if ((cb instanceof NameCallback)) {

				((NameCallback) cb).setName(this.user);

			} else if ((cb instanceof PasswordCallback)) {
				((PasswordCallback) cb).setPassword(this.pass.toCharArray());

			} else {
				throw new UnsupportedCallbackException(cb, "Invalid CallBack");
			}
		}
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
