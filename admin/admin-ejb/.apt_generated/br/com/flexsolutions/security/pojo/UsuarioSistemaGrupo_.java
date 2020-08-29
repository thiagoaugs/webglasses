package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.772-0300")
@StaticMetamodel(UsuarioSistemaGrupo.class)
public class UsuarioSistemaGrupo_ {
	public static volatile SingularAttribute<UsuarioSistemaGrupo, Integer> usgId;
	public static volatile SingularAttribute<UsuarioSistemaGrupo, UsuarioSistema> usgUsuarioSistema;
	public static volatile SingularAttribute<UsuarioSistemaGrupo, Grupo> usgGrupo;
}
