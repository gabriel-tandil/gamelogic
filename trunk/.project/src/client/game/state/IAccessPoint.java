/**
 * The IAccessPoint is the interface to make a change state in the Game.
 */
package client.game.state;

/** 
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public interface IAccessPoint {
	/** 
	 * This method is the interface to create a new <code>ChangeStateTask</code>,
	 * and this is enqueue first in the list of  task
	 * to execute calling a singleton <code>TaskManager</code>
	 */
	public void show();
}