package br.com.flexsolutions.security.pojo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2020-08-11T19:14:07.698-0300")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ {
	public static volatile SingularAttribute<Pessoa, Integer> pesId;
	public static volatile SingularAttribute<Pessoa, String> pesCpfCnpj;
	public static volatile SingularAttribute<Pessoa, String> pesNomeFantasia;
	public static volatile SingularAttribute<Pessoa, String> pesSobrenomeRazao;
	public static volatile SingularAttribute<Pessoa, String> pesRgIe;
	public static volatile SingularAttribute<Pessoa, Date> pesDtCad;
	public static volatile SingularAttribute<Pessoa, String> pesTipo;
	public static volatile SingularAttribute<Pessoa, String> pesTipoPessoa;
}
