/**  
 * admin-ejb - LogTO.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Thiago Augusto
 *
 */
public class LogTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer logId;

	private String logUsuario;

	private Date logData;

	private String logSistema;

	private String logMensagem;

	/**
	 * @return the logId
	 */
	public Integer getLogId() {
		return logId;
	}

	/**
	 * @param logId the logId to set
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	/**
	 * @return the logUsuario
	 */
	public String getLogUsuario() {
		return logUsuario;
	}

	/**
	 * @param logUsuario the logUsuario to set
	 */
	public void setLogUsuario(String logUsuario) {
		this.logUsuario = logUsuario;
	}

	/**
	 * @return the logData
	 */
	public Date getLogData() {
		return logData;
	}

	/**
	 * @param logData the logData to set
	 */
	public void setLogData(Date logData) {
		this.logData = logData;
	}

	/**
	 * @return the logSistema
	 */
	public String getLogSistema() {
		return logSistema;
	}

	/**
	 * @param logSistema the logSistema to set
	 */
	public void setLogSistema(String logSistema) {
		this.logSistema = logSistema;
	}

	/**
	 * @return the logMensagem
	 */
	public String getLogMensagem() {
		return logMensagem;
	}

	/**
	 * @param logMensagem the logMensagem to set
	 */
	public void setLogMensagem(String logMensagem) {
		this.logMensagem = logMensagem;
	}
	
	

}
