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

import br.com.flexsolutions.admin.to.UsuarioSistemaTO;
import br.com.flexsolutions.admin.to.UsuarioTO;

/**
 * @author Thiago Augusto
 *
 */
@FacesConverter(value = "usuarioSistemaConverter")  
public class UsuarioSistemaConverter implements Converter {

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
			return (UsuarioSistemaTO) component.getAttributes().get(value);
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
		if (value instanceof UsuarioSistemaTO) {
			UsuarioSistemaTO usuarioSistemaTO = (UsuarioSistemaTO) value;
			if (usuarioSistemaTO != null && usuarioSistemaTO instanceof UsuarioSistemaTO
					&& usuarioSistemaTO.getUsiId() != null) {
				component.getAttributes().put(usuarioSistemaTO.getUsiUsuario().getUsuNome(),
						usuarioSistemaTO);
				return usuarioSistemaTO.getUsiUsuario().getUsuNome();
			}
		}
		return "";
	}

}
