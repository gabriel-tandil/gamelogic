/**
 * RTaskLeft.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.messages.IMessage;
import common.messages.MsgPlainText;

import client.communication.tasks.TaskCommunication;
import client.manager.EntityManager;

import client.game.entity.EntityManagerFactory;

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
		EntityManager.getInstance().removeEntity(msg.getMsg());
	}

}
