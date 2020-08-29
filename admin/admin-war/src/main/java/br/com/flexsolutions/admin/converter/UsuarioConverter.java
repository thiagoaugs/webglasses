/**  
 * admin-war - UsuarioConverter.java
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

import br.com.flexsolutions.admin.to.UsuarioTO;

/**
 * @author Thiago Augusto
 *
 */
@FacesConverter(value = "usuarioConverter")  
public class UsuarioConverter implements Converter {

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
			return (UsuarioTO) component.getAttributes().get(value);
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
		if (value instanceof UsuarioTO) {
			UsuarioTO usuarioTO = (UsuarioTO) value;
			if (usuarioTO != null && usuarioTO instanceof UsuarioTO
					&& usuarioTO.getUsuId() != null) {
				component.getAttributes().put(usuarioTO.getUsuId().toString(),
						usuarioTO);
				return usuarioTO.getUsuId().toString();
			}
		}
		return "";
	}

}
