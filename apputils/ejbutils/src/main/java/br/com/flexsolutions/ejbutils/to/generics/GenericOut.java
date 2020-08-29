/**  
 * webglass-ejb - GenericOut.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.ejbutils.to.generics;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author Thiago Augusto
 * 
 */
public class GenericOut implements Serializable {

	private static final long serialVersionUID = 1L;

	  protected boolean resultado;
	  protected String mensagem;
	  protected String stackTrace;
	  
	  public void setResultado(boolean resultado)
	  {
	    this.resultado = resultado;
	  }
	  
	  public void setMensagem(String mensagem)
	  {
	    this.mensagem = mensagem;
	  }
	  
	  public boolean isResultado()
	  {
	    return this.resultado;
	  }
	  
	  public String getMensagem()
	  {
	    return this.mensagem;
	  }
	  
	  public String getStackTrace()
	  {
	    return this.stackTrace;
	  }
	  
	  public void setStackTrace(String stackTrace)
	  {
	    this.stackTrace = stackTrace;
	  }
	  
	  public void setError(String mensagem)
	  {
	    this.resultado = false;
	    this.mensagem = mensagem;
	  }
	  
	  public void setError(String mensagem, Throwable t)
	  {
	    this.resultado = false;
	    this.mensagem = mensagem;
	    this.stackTrace = ("[Stacktrace]:\n" + ExceptionUtils.getStackTrace(t));
	  }
	  
	  public void setSuccess()
	  {
	    this.resultado = true;
	  }
	  
	  public void setSuccess(String mensagem)
	  {
	    this.resultado = true;
	    this.mensagem = mensagem;
	  }
}
