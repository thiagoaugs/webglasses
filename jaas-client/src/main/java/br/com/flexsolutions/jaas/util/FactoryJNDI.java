/**
 *jaas-client - FactoryJNDI.java
 * 
 * Data de criacao 13/11/2015
 *
 * Criado por Thiago Augusto
 * 
 * Copyright flexsolutions - Todos os direitos reservados.
 *
 */
package br.com.flexsolutions.jaas.util;

import br.com.flexsolutions.jaas.interfaces.IJaasBeanRemote;

/**
 * @author desenv.flexsolutions
 *
 */
public class FactoryJNDI {


	public static IJaasBeanRemote getJaasBean() {
		IJaasBeanRemote ejb = null;
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>Invocando EJB >>>");
			try {
				ejb = (IJaasBeanRemote) (JAASServiceLocator
						.getInstance()
						.get("ejb:admin/admin-ejb/JaasBean!br.com.flexsolutions.jaas.interfaces.IJaasBeanRemote"));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(">>>>>>>>>>>>>>>>>>>>>> EJB >>>" + ejb);

		return ejb;
	}

}
