package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.713-0300")
@StaticMetamodel(Sistema.class)
public class Sistema_ {
	public static volatile SingularAttribute<Sistema, Integer> sisId;
	public static volatile SingularAttribute<Sistema, String> sisNome;
	public static volatile ListAttribute<Sistema, TelaMenu> sistemaTelaMenu;
	public static volatile ListAttribute<Sistema, Grupo> sistemaGrupo;
	public static volatile ListAttribute<Sistema, Roles> sistemaRoles;
}
