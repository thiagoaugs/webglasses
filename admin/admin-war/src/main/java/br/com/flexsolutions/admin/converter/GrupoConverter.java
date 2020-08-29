/**  
 * admin-war - GrupoTOConverter.java
 * 
  * Data de criacao 04/09/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.flexsolutions.admin.to.GrupoTO;


/**
 * @author Thiago Augusto
 *
 */
public class GrupoConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null && !value.isEmpty()) {
			return (GrupoTO) component.getAttributes().get(value);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value instanceof GrupoTO) {
			GrupoTO grupoTO = (GrupoTO) value;
			if (grupoTO != null && grupoTO instanceof GrupoTO
					&& grupoTO.getGpoId() != null) {
				component.getAttributes().put(
						grupoTO.getGpoId().toString(), grupoTO);
				return grupoTO.getGpoId().toString();
			}
		}
		return "";
	}

}
