/**
 * RTaskRotate.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.communication.DynamicEntitysSolicitations;
import client.communication.GameContext;
import client.game.entity.DynamicEntity;
import client.game.task.ITask;
import client.manager.EntityManager;
import client.manager.TaskManager;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.notify.MsgRotate;

public class RTaskRotate extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskRotate(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskRotate</I> y setea el mensaje.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskRotate
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskRotate(msg);
	}

	/**
	 * Actualiza la ENTITY correspondiente recuperandola del EntityManager con
	 * el angulo de rotación En caso de no encontrarla crea una TASKCOMM con MSG
	 * get_dynamic_entity
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		MsgRotate thisMsg = (MsgRotate) this.getMessage();

		// Si el mensaje fue enviado originalmente por este jugador, se termina
		// la tarea.
		if (thisMsg.getIdDynamicEntity().equalsIgnoreCase(
				GameContext.getUserName())) {
			return;
		}
		// estado local de la entidad dinamica que roto
		String dEState = (String) DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES
				.get(thisMsg.getIdDynamicEntity());
		if (dEState == null) {// la entidad no existe localmente
			try {
				// marco la entidad como solicitada.
				DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.put(thisMsg
						.getIdDynamicEntity(),
						DynamicEntitysSolicitations.SOLICITED);
				// se crea msg de tipo get_dynamic_entity
				MsgPlainText msg = (MsgPlainText) MessageFactory.getInstance()
						.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
				// se setea en el msg el ID de la ENTITY
				msg.setMsg(thisMsg.getIdDynamicEntity());
				// se crea una TaskComm con el msg anterior
				ITask task = TaskCommFactory.getInstance().createComTask(msg);
				TaskManager.getInstance().submit(task);
			} catch (UnsopportedMessageException e) {
				e.printStackTrace();
			}
		} else if (dEState.equals(DynamicEntitysSolicitations.EXISTS)) {
			// La entidad existe localmente
			DynamicEntity entity = (DynamicEntity) EntityManager.getInstance()
					.getEntity(thisMsg.getIdDynamicEntity());
			// se setea el ángulo de rotación
			entity.setAngle(thisMsg.getAngle().x);
		}// else if (dEState.equals(DynamicEntitysSolicitations.SOLICITED){
	}

}