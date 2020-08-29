package br.com.flexsolutions.security.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.649-0300")
@StaticMetamodel(Grupo.class)
public class Grupo_ {
	public static volatile SingularAttribute<Grupo, Integer> gpoId;
	public static volatile SingularAttribute<Grupo, String> gpoDescricao;
	public static volatile SingularAttribute<Grupo, String> gpoNome;
	public static volatile SingularAttribute<Grupo, Boolean> gpoDeMenu;
	public static volatile SingularAttribute<Grupo, Sistema> gpoSistema;
}
