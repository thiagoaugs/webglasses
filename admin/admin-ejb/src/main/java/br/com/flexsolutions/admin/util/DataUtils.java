/**  
 * jass-ejb - FormatarData.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Thiago Augusto
 *
 */
public class DataUtils {

	public static Date obterDataAtualFormatada() {
		// Exemplo utilizando date e simpledateformat e o método parse
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();
		sdf.format(data);
		return data;
	}
}
