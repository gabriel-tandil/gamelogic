/**
 * RTaskLeft.java
 * @author Castillo/Santos
 * Cada vez que una entidad deje un mundo, se necesitará mantener consistente<br> 
 * los datos en el servidor y/o notificar a los otros clientes. Esta clase fue <br>
 * creada con el fin de proveer dicho servicio al juego.<br>
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskLeft:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */

package client.communication.tasks.comm;

import client.communication.GameContext;
import client.communication.tasks.TaskCommunication;
import client.game.entity.IDynamicEntity;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

public class RTaskLeft extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskLeft(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskLeft</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskLeft
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskLeft(msg);
	}

	/**
	 * Este método se utiliza para proveer la funcionalidad de una<br>
	 * salida de una entidad del mundo actual.
	 * @see DynamicEntitysSolicitations
	 * @see MsgPlainText
	 * @see ViewManager
	 * @see InputManager
	 * @see EntityManager
	 * @see GameContext
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {
		
		MsgPlainText msg = (MsgPlainText) this.getMessage();
		// Si el mensaje fue enviado originalmente por este jugador, se termina
		// la tarea.
		if (msg.getMsg().equalsIgnoreCase(
				GameContext.getUserName())) {
			return;
		}
		
		ViewManager.getInstance().removeView(EntityManager.getInstance().getEntity(msg.getMsg()));
		InputManager.getInstance().removeController((IDynamicEntity)EntityManager.getInstance().getEntity(msg.getMsg()));
		EntityManager.getInstance().removeEntity(msg.getMsg());
		
		
		// elimino el estado local de la entidad que se fue.
		DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.remove(msg.getMsg());
	}

}
