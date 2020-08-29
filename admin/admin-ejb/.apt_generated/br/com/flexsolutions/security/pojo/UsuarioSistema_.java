package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.765-0300")
@StaticMetamodel(UsuarioSistema.class)
public class UsuarioSistema_ {
	public static volatile SingularAttribute<UsuarioSistema, Integer> usiId;
	public static volatile SingularAttribute<UsuarioSistema, Sistema> usiSistema;
	public static volatile SingularAttribute<UsuarioSistema, Usuario> usiUsuario;
	public static volatile ListAttribute<UsuarioSistema, UsuarioSistemaGrupo> usiUsuarioSistemaGrupo;
	public static volatile SingularAttribute<UsuarioSistema, Character> usiTipoUsuario;
	public static volatile SingularAttribute<UsuarioSistema, Boolean> usiSemRestricao;
}
