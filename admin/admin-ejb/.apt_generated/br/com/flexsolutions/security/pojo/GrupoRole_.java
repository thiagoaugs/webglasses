package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.691-0300")
@StaticMetamodel(GrupoRole.class)
public class GrupoRole_ {
	public static volatile SingularAttribute<GrupoRole, Integer> gprId;
	public static volatile SingularAttribute<GrupoRole, Grupo> gprGrupo;
	public static volatile SingularAttribute<GrupoRole, Roles> gprRole;
}
