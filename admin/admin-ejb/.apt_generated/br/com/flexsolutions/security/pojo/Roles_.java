package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.706-0300")
@StaticMetamodel(Roles.class)
public class Roles_ {
	public static volatile SingularAttribute<Roles, Integer> rolId;
	public static volatile SingularAttribute<Roles, Boolean> rolAtivo;
	public static volatile SingularAttribute<Roles, String> rolDescricao;
	public static volatile SingularAttribute<Roles, String> rolNome;
	public static volatile SingularAttribute<Roles, Sistema> rolSistema;
}
