/**  
 * ejbutils - ExceptionInterceptor.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.interceptor;


import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

import br.com.flexsolutions.ejbutils.to.generics.GenericOut;

/**
 * @author Thiago Augusto
 *
 */
public class ExceptionInterceptor {

	  private final Logger logger = Logger.getLogger(ExceptionInterceptor.class);
	  @Resource
	  private EJBContext context;
	  
	  @AroundInvoke
	  public Object handleException(InvocationContext ctx)
	    throws Exception
	  {
	    try
	    {
	      return ctx.proceed();
	    }
	    catch (Exception e)
	    {
	      GenericOut out;
	      try
	      {
	        if (!ctx.getMethod().getReturnType().toString().equals("void")) {
	          try
	          {
	            out = (GenericOut)ctx.getMethod().getReturnType().newInstance();
	          }
	          catch (InstantiationException e1)
	          {
	            this.logger.error("Erro ao instanciar a classe:" + ctx.getMethod().getReturnType(), e1);
	            throw new Exception(e1);
	          }
	          catch (IllegalAccessException e1)
	          {
	            this.logger.error("Erro de acesso indevido ao instanciar o objeto da classe:" + ctx.getMethod().getReturnType(), e1);
	            throw new Exception(e1);
	          }
	        } else {
	          out = new GenericOut();
	        }
	      }
	      catch (Exception e2)
	      {
	        out = new GenericOut();
	      }
	      this.context.setRollbackOnly();
	      
	      out.setError(e.getMessage(), e);
	      
	      return out;
	    }
	  }
}
