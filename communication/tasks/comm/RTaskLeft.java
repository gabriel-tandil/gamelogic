/**
 * RTaskLeft.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import client.communication.DynamicEntitysSolicitations;
import client.communication.GameContext;
import client.communication.tasks.TaskCommunication;
import client.game.entity.IEntity;
import client.manager.EntityManager;
import client.manager.ViewManager;

import common.messages.IMessage;
import common.messages.MsgPlainText;

public class RTaskLeft extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskLeft(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskLeft</I> y setea el mensaje.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskLeft
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskLeft(msg);
	}

	/**
	 * Remueve una entidad del EntityManager {@link EntityManager}
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
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
		IEntity e = EntityManager.getInstance().getEntity(msg.getMsg());
		ViewManager.getInstance().removeView(e);
		
		EntityManager.getInstance().removeEntity(msg.getMsg());
		
		// elimino el estado local de la entidad que se fue.
		DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.remove(msg.getMsg());
	}

}
