package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TelaMenu.class)
public abstract class TelaMenu_ {

	public static volatile SingularAttribute<TelaMenu, Integer> tmeId;
	public static volatile SingularAttribute<TelaMenu, Integer> tmeNivel;
	public static volatile SingularAttribute<TelaMenu, TelaMenu> tmeMenuSuperior;
	public static volatile SetAttribute<TelaMenu, TelaMenu> tmeMenuFilhos;
	public static volatile SingularAttribute<TelaMenu, String> tmeLegenda;
	public static volatile SingularAttribute<TelaMenu, Sistema> tmeSistema;
	public static volatile SingularAttribute<TelaMenu, Boolean> tmeUtilizado;
	public static volatile SingularAttribute<TelaMenu, Integer> tmeOrdem;
	public static volatile SingularAttribute<TelaMenu, String> tmeAcao;
	public static volatile SingularAttribute<TelaMenu, String> tmeCamada;
	public static volatile SetAttribute<TelaMenu, TelaMenuGrupo> tmeSistemaTelaMenuGrupo;

}

