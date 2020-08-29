package br.com.flexsolutions.security.pojo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> usuSenha;
	public static volatile SingularAttribute<Usuario, String> usuCelular;
	public static volatile SingularAttribute<Usuario, Boolean> usuBloq;
	public static volatile SingularAttribute<Usuario, String> usuCep;
	public static volatile SingularAttribute<Usuario, String> usuTelefone;
	public static volatile SingularAttribute<Usuario, Date> usuDtCad;
	public static volatile SingularAttribute<Usuario, Pessoa> usuPessoa;
	public static volatile SingularAttribute<Usuario, String> usuLogin;
	public static volatile SingularAttribute<Usuario, String> usuCpf;
	public static volatile SingularAttribute<Usuario, Date> usuDtAlt;
	public static volatile SingularAttribute<Usuario, String> usuBairro;
	public static volatile SingularAttribute<Usuario, Integer> usuId;
	public static volatile SingularAttribute<Usuario, String> usuRua;
	public static volatile SingularAttribute<Usuario, Integer> usuNumero;
	public static volatile SingularAttribute<Usuario, String> usuEmail;
	public static volatile SingularAttribute<Usuario, String> usuNome;
	public static volatile SingularAttribute<Usuario, String> usuRamal;
	public static volatile SingularAttribute<Usuario, String> usuComplemento;

}

