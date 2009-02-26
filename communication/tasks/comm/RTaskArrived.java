/**
 * RTaskArrived.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.task.ITask;
import client.manager.EntityManager;
import client.manager.TaskManager;

public class RTaskArrived extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskArrived(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTashArrived</I> y setea el mensaje.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskArrived
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskArrived(msg);
	}

	/**
	 * Este metodo crea una entidad en el {@link EntityManager}<BR/> el id de
	 * la entidad es obtenido desde el mensaje propio de<BR/> la tarea.<BR/>
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		try {
			MsgPlainText msg = (MsgPlainText) this.getMessage();
			String idEntity = msg.getMsg();

			// Si el mensaje fue enviado originalmente por este jugador, se
			// termina
			// la tarea.
			if (idEntity.equalsIgnoreCase(GameContext.getUserName())) {
				return;
			}

			// se crea msg de tipo get_dynamic_entity
			MsgPlainText msgGDE = (MsgPlainText) MessageFactory.getInstance()
					.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
			// se setea en el msg el ID del Player
			msgGDE.setMsg(idEntity);
			// se crea una TaskComm con el msg anterior
			ITask task = TaskCommFactory.getInstance().createComTask(msgGDE);
			TaskManager.getInstance().submit(task);
		} catch (UnsopportedMessageException e) {
			e.printStackTrace();
		}
	}

}
