/**  
 * admin-war - SistemaConverter.java
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
import javax.faces.convert.FacesConverter;

import br.com.flexsolutions.admin.to.SistemaTO;

/**
 * @author Thiago Augusto
 *
 */
@FacesConverter(value = "sistemaConverter")  
public class SistemaConverter implements Converter {

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
			return (SistemaTO) component.getAttributes().get(value);
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
		if (value instanceof SistemaTO) {
			SistemaTO sistemaTO = (SistemaTO) value;
			if (sistemaTO != null && sistemaTO instanceof SistemaTO
					&& sistemaTO.getSisId() != null) {
				component.getAttributes().put(sistemaTO.getSisId().toString(),
						sistemaTO);
				return sistemaTO.getSisId().toString();
			}
		}
		return "";
	}

}
