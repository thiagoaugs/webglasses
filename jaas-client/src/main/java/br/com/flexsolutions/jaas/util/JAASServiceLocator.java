/**
 *jaas-client - JAASServiceLocator.java
 * 
 * Data de criacao 13/11/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.util;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

/**
 * @author desenv.flexsolutions
 *
 */
public class JAASServiceLocator {

	private static JAASServiceLocator locator;
	private InitialContext initialContext;
	private static final Logger log = Logger
			.getLogger(JAASServiceLocator.class);

	private void openContext() throws Exception {
		
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProps
				.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
						"false");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "userejb");
		jndiProps.put(Context.SECURITY_CREDENTIALS, "15glassJ@@$");
		jndiProps.put("jboss.naming.client.ejb.context", true);

		this.initialContext = new InitialContext(jndiProps);
	}

	private void openContextRestrict() throws Exception {
	
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProps
				.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",
						"false");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "userejb");
		jndiProps.put(Context.SECURITY_CREDENTIALS, "15glassJ@@$");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		
		this.initialContext = new InitialContext(jndiProps);
	}

	public static JAASServiceLocator getInstance() throws Exception {
		if (locator == null) {
			locator = new JAASServiceLocator();
			System.out.println(">>>>>>>>>>>>>>>>>> Locator = " + locator);
		}
		return locator;
	}

	public Object get(String jndiName) throws Exception {
		Object result = null;
		if (this.initialContext == null) {
			openContext();
		}
		result = this.initialContext.lookup(jndiName);
		if ((result == null) && (result == null)) {
			throw new NamingException();
		}
		return result;
	}

	public Object getRestrict(String jndiName) throws Exception {
		Object result = null;
		if (this.initialContext == null) {
			openContextRestrict();
		}
		result = this.initialContext.lookup(jndiName);
		if ((result == null) && (result == null)) {
			throw new NamingException();
		}
		return result;
	}

	public void closeContext() throws Exception {
		this.initialContext.close();
		this.initialContext = null;
	}

}
