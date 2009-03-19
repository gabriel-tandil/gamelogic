/**
 * 
 */
package client.game.view;

/** 
 * <code>IStaticView</code> define la interface para 
 * vistas que representan entidades est�ticas en 
 * el mundo del juego. Estas vistas no cambian a lo
 * largo del juego, por eso se brinda un metodo Lock()
 * 
 * @author Maria Hansen, Sebastian Bisbal (Javadoc)
 * @version Creation date: 29-10-2008
 */
public interface IStaticView extends IView {
	/** 
     * Funci�n que traba la geometr�a, l�mites y transformaci�n de un elemento de la
     * escena. �sto permite optimizaciones para el motor del juego a la hora de 
     * actualizar elementos que no sufrieron cambios.
	 */
	public void lock();
}