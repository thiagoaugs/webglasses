/**  
 *persistence - EntityUtils.java
 * 
 * Data de criacao 11/07/2017
 *
 * Criado por Thiago Augusto
 * 
 * Copyright - Todos os direitos reservados.
 *
 */
package br.com.utils.persitence.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

/**
 * @author Thiago Augusto
 *
 */
public class EntityUtils {

	public static String retornarPrimaryKeyEntidade(Class<?> clazz)
			throws EntityNotFoundException {
		if (!isJpaEntity(clazz)) {
			throw new EntityNotFoundException();
		}
		String pkColumnName = null;
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(Id.class)) {
				pkColumnName = field.getName();
				break;
			}
		}
		return pkColumnName;
	}

	public static Map<Class<?>, String> retornarRelacionamentosEntidade(
			Class<?> clazz) throws EntityNotFoundException {
		if (!isJpaEntity(clazz)) {
			throw new EntityNotFoundException();
		}
		Map<Class<?>, String> relacionamentos = null;

		Reflections reflections = new Reflections(
				new Object[] { ClasspathHelper.forPackage(clazz.getPackage()
						.getName(), new ClassLoader[0]) });

		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
		for (Class<?> classeAnalisada : classes) {
			Map<Class<?>, String> resultado = possuiRelacionamentoChaveEstrangeira(
					classeAnalisada, clazz);
			if (resultado != null) {
				if (relacionamentos == null) {
					relacionamentos = resultado;
				} else {
					relacionamentos.putAll(resultado);
				}
			}
		}
		return relacionamentos;
	}

	private static Map<Class<?>, String> possuiRelacionamentoChaveEstrangeira(
			Class<?> clazzAnalisada, Class<?> clazzPesquisada) {
		Map<Class<?>, String> relacionamentos = null;
		for (Field field : clazzAnalisada.getDeclaredFields()) {
			if (field.getType().isAssignableFrom(clazzPesquisada)) {
				if (relacionamentos == null) {
					relacionamentos = new HashMap();
				}
				if (!relacionamentos.containsKey(field.getClass())) {
					relacionamentos.put(clazzAnalisada, field.getName());
				}
			}
		}
		return relacionamentos;
	}

	private static boolean isJpaEntity(Class<?> clazz) {
		return clazz.isAnnotationPresent(Entity.class);
	}

}
