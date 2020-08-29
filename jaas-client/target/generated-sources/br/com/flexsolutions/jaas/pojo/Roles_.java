package br.com.flexsolutions.jaas.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Roles.class)
public abstract class Roles_ {

	public static volatile SingularAttribute<Roles, Integer> rolId;
	public static volatile SingularAttribute<Roles, String> rolDescricao;
	public static volatile SingularAttribute<Roles, Boolean> rolAtivo;
	public static volatile SingularAttribute<Roles, String> rolNome;
	public static volatile SingularAttribute<Roles, Sistema> rolSistema;

}

