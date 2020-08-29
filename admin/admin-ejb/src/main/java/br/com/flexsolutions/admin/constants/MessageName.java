/**  
 * webglass-ejb - MessageName.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.constants;

/**
 * Nomes dos arquivos de mensagens existentes
 * 
 * @author Thiago Augusto
 *
 */
public enum MessageName {

	MESSAGES_ADMINISTRADOR("messages_administrador");

	private String value;

	private MessageName(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
