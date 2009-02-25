/**
 * RTaskMove.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.task.ITask;
import client.manager.EntityManager;
import client.manager.TaskManager;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.notify.MsgMove;

public class RTaskMove extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskMove(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskMove</I> y setea el mensaje.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskMove
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskMove(msg);
	}

	/**
	 * Actualiza la ENTITY correspondiente recuperandola del EntityManager En
	 * caso de no encontrarla crea una TASKCOMM con MSG get_dynamic_entity
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @author Castillo/Santos
	 * 
	 */

	@Override
	public void execute() {

		MsgMove thisMsg = (MsgMove) this.getMessage();

		// Si el mensaje fue enviado originalmente por este jugador, se termina
		// la tarea.
		if (thisMsg.getIdDynamicEntity().equalsIgnoreCase(
				GameContext.getUserName())) {
			return;
		}
		
		DynamicEntity entity = (DynamicEntity) EntityManager.getInstance()
				.getEntity(thisMsg.getIdDynamicEntity());

		if (entity != null) {
			// "moverla" Tomando los datos desde el MsgMove.
			entity.setPosition(thisMsg.getPosDestino());
		} else {
			// la entity no se encuentra en el EntityManager
			try {
				// se crea msg de tipo get_dynamic_entity
				MsgPlainText msg = (MsgPlainText) MessageFactory.getInstance()
						.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
				// se setea en el msg el ID de la ENTITY
				msg.setMsg(thisMsg.getIdDynamicEntity());
				// se crea una TaskComm con el msg anterior
				ITask task = TaskCommFactory.getInstance().createComTask(msg);
				TaskManager.getInstance().submit(task);
			} catch (UnsopportedMessageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
