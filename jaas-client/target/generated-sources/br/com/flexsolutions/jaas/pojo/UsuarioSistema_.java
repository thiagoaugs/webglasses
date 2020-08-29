package br.com.flexsolutions.jaas.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuarioSistema.class)
public abstract class UsuarioSistema_ {

	public static volatile SingularAttribute<UsuarioSistema, Integer> usiId;
	public static volatile SingularAttribute<UsuarioSistema, Sistema> usiSistema;
	public static volatile SingularAttribute<UsuarioSistema, Boolean> usiSemRestricao;
	public static volatile SingularAttribute<UsuarioSistema, Usuario> usiUsuario;
	public static volatile ListAttribute<UsuarioSistema, UsuarioSistemaGrupo> usiUsuarioSistemaGrupo;
	public static volatile SingularAttribute<UsuarioSistema, Character> usiTipoUsuario;

}

