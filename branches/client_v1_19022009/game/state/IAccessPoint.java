/**
 * IAccessPoint es una interface para hacer el cambio de estado en el juego.
 */
package client.game.state;

/** 
 * IAccessPoint es una interface para hacer el cambio de estado en el juego.
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public interface IAccessPoint {
	/** 
	 * Es una interface para crear nuevos  <code>ChangeStateTask</code>, y 
	 * estos son encolados al principio de la lista de tareas a ser 
	 * ejecutadas llamandas por el singleton <code> TaskManager</code>
	 */
	public void show();
}