/**  
 * admin-web - WebglassConfigurationProvider.java
 * 
 * Data de criacao 03/08/2020
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.admin.util;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.Join;


/**
 * @author Thiago Augusto
 * 
 * Faz a configuracao para a reescrita das URL's da aplicacao.
 * 
 */
@RewriteConfiguration
public class ConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(ServletContext arg0) {
	
		return ConfigurationBuilder.begin().
	
		addRule(Join.path("/login").to("/security/login.jsp")).
		addRule(Join.path("/naoautorizado").to("/security/403.xhtml")).
		addRule(Join.path("/pages/principal").to("/pages/principal.xhtml")).
		
		addRule(Join.path("/pages/grupo").to("/pages/cadastros/grupo/grupo.xhtml")).
		addRule(Join.path("/pages/regras").to("/pages/cadastros/regras/regras.xhtml")).
		addRule(Join.path("/pages/sistema").to("/pages/cadastros/sistema/sistema.xhtml")).
		addRule(Join.path("/pages/telamenu").to("/pages/cadastros/telamenu/telamenu.xhtml")).
		addRule(Join.path("/pages/usuario").to("/pages/cadastros/usuario/usuario.xhtml")).

		addRule(Join.path("/pages/autorizarregrasgrupo").to("/pages/autorizacao/autorizarregrasaogrupo/grupo_regra.xhtml")).
		addRule(Join.path("/pages/autorizartelagrupo").to("/pages/autorizacao/autorizartelaaogrupo/grupo_tela.xhtml")).
		addRule(Join.path("/pages/autorizarusuariogrupo").to("/pages/autorizacao/autorizarusuarioaogrupo/grupo_usuario.xhtml")).
		addRule(Join.path("/pages/autorizarusuariosistema").to("/pages/autorizacao/autorizarusuarioaosistema/sistema_usuario.xhtml"));
	}

	@Override
	public int priority() {
		return 10;
	}

}
