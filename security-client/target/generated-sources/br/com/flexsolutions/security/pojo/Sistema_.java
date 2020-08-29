package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sistema.class)
public abstract class Sistema_ {

	public static volatile SingularAttribute<Sistema, String> sisNome;
	public static volatile ListAttribute<Sistema, TelaMenu> sistemaTelaMenu;
	public static volatile ListAttribute<Sistema, Grupo> sistemaGrupo;
	public static volatile ListAttribute<Sistema, Roles> sistemaRoles;
	public static volatile SingularAttribute<Sistema, Integer> sisId;

}

