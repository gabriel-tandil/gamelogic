/**
 * RTaskChangeState.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;

import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.entity.EntityManagerFactory;
import client.game.task.ITask;
import client.game.entity.Player;
import client.manager.EntityManager;
import client.manager.TaskManager;

public class RTaskChangeState extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskChangeState(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskChangeState</I> y setea el mensaje.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskChangeState
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskChangeState(msg);
	}

	/**
	 * se cambia el estado del Player a partir de los datos<BR/> proporcionado
	 * por el mensaje {@link MsgChangePlayerState}. En caso de no encontrarse
	 * el Player en el EntityManager, se crea una TASK pidiendolo.
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {

		MsgChangePlayerState thisMsg = (MsgChangePlayerState) this.getMessage();

		DynamicEntity entity = (DynamicEntity) EntityManager.getInstance().getEntity(thisMsg.getIdPlayer());

		if (entity != null) {
			// cambio de estado para el Player
			entity.setState(thisMsg.getNewState());

		} else {
			// el Player no se encuentra en el EntityManager
			try {
				// se crea msg de tipo get_dynamic_entity
				MsgPlainText msg = (MsgPlainText) MessageFactory.getInstance()
						.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
				// se setea en el msg el ID del Player
				msg.setMsg(thisMsg.getIdPlayer());
				// se crea una TaskComm con el msg anterior
				ITask task = TaskCommFactory.getInstance().createComTask(msg);

				TaskManager.getInstance().submit(task);
			} catch (UnsopportedMessageException e) {
				e.printStackTrace();
			}
		}

	}
}

