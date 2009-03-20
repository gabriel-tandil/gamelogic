/**
 * IAccessPoint es una interface para hacer el cambio de estado en el juego.
 */
package client.game.state;

/** 
 * IAccessPoint es una interface que representa un punto de acceso del 
 * modelo. Un punto de acceso es un objeto que genera 
 * <code>ChangeStateTask</code>, es decir, tareas que son agregadas al 
 * comienzo de la lista de tareas manejada por el <code> TaskManager</code>
 * y que se encargan de hacer el cambio de estado en el juego. Este cambio
 * de estado puede ser, por ejemplo, ingresar a algun edificio, mostrar un
 *  juego 2D,o mostrar un spot informativo.
 * @author Santiago Michielotto, Martin Sabatini (Javadoc)
 * @version Created: 19-11-2008
 */
public interface IAccessPoint {
	/** 
	 * Es una interface para crear nuevos  <code>ChangeStateTask</code>, y 
	 * estos son encolados al principio de la lista de tareas a ser 
	 * ejecutadas llamadas por el singleton <code> TaskManager</code>
	 */
	public void show();
}