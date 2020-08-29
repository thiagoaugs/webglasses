/**  
* webglass-ejb - GenericLazyReturn.java
* 
* Data de criação 05/06/2015
*
* Criado por DESENV10
* 
* Copyright SystemGlass - Todos os direitos reservados.
*
*/
package br.com.systemglass.ejbutils.to.generics;


/**
 * @author DESENV10
 *
 */
public class GenericLazyReturnOut  extends GenericOut
{
	  private static final long serialVersionUID = 1L;
	  protected Long countTotal;
	  
	  public Long getCountTotal()
	  {
	    return this.countTotal;
	  }
	  
	  public void setCountTotal(Long countTotal)
	  {
	    this.countTotal = countTotal;
	  }
}
