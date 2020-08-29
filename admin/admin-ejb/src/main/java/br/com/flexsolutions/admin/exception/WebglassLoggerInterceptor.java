/**  
 * jass-ejb - WebglassExceptionInterception.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.exception;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

/**
 * Classe para interceptar as chamadas ao metodos ejbs
 * 
 * @author Thiago Augusto
 * 
 */
public class WebglassLoggerInterceptor {


	@AroundInvoke
	public Object intercept(InvocationContext invocationContext)
			throws Exception {
		Logger log = Logger.getLogger("Administrador");
		String methodName = invocationContext.getMethod().getName();
		String className = invocationContext.getTarget().getClass().getName();

		log.debug("Iniciando processamento: " + className + "." + methodName);
		long timeBefore = System.currentTimeMillis();

		try {
			return invocationContext.proceed();
		} catch (Exception e) {
			log.error("Erro ao processar método " + className + "." + methodName);
			log.error("Causa Principal: ", e);
			throw e;
		} finally {
			long timeAfter = System.currentTimeMillis();
			log.info("Processando " + className + "." + methodName + " iniciado em "
					+ (timeAfter - timeBefore) + "ms");
		}
	}
}
