/**
 * RTaskMove.java
 * @author Castillo/Santos
 * Cada vez que una entidad realize un movimiento simple, se necesitará mantener consistente<br> 
 * los datos en el servidor y/o notificar a los otros clientes. Esta clase fue <br>
 * creada con el fin de proveer dicho servicio al juego.<br>
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskMove:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */

package client.communication.tasks.comm;

import javax.xml.soap.MessageFactory;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.task.ITask;
import client.manager.EntityManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;

public class RTaskMove extends TaskCommunication {

	/**
	 * @param msg
	 */
	
	public RTaskMove(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskMove</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskMove
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskMove(msg);
	}

	/**
	 * Este método se utiliza para proveer la funcionalidad <br>
	 * cuando una entidad realiza un movimiento localmente o externamente<br>
	 * al mundo actual.
	 * @see client.game.task.ITask#execute()
	 * @see DynamicEntitysSolicitations
	 * @see MsgMove
	 * @see MsgPlainText
	 * @see TaskManager
	 * @see DynamicEntity
	 * @author Castillo/Santos
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
			// Obtengo la traslacion de la posicion y la seteo
			Vector3f clientPosition = PositionsTranslator
					.clientPositionClientWorld(entity.getActualWorld(), thisMsg
							.getPosDestino());
			// "moverla" Tomando los datos desde el MsgMove.
			entity.setPosition(clientPosition);
		}
	}

}
