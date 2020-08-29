/**  
 *persistence - SQLUtils.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persitence.util;

import java.util.List;

/**
 * @author Thiago Augusto
 *
 */
public class SQLUtils {
	public static String transformarListInNativeSQL(List valores) {
		if ((valores == null) || (valores.isEmpty())) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Object object : valores) {
			sb.append(object);
			sb.append(",");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append(")");

		return sb.toString();
	}
}
