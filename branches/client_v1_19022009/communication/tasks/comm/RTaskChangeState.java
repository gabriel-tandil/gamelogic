/**
 * RTaskChangeState.java
 * @author Castillo/Santos
 * Cada vez que una entidad cambie de estado, se necesitará mantener consistente<br> 
 * los datos en el servidor y/o notificar a los otros clientes. Esta clase fue <br>
 * creada con el fin de proveer dicho servicio al juego.<br>
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskChangeState:</b> Constructor de la clase.<br>
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

public class RTaskChangeState extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskChangeState(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskChangeState</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskChangeState
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskChangeState(msg);
	}

	/**
	 * Este método se utiliza para proveer la funcionalidad de un cambio de estado sobre<br>
	 * alguna entidad. 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @see DynamicEntitysSolicitations
	 * @see MsgPlainText
	 * @see TaskManager
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {

		MsgChangePlayerState thisMsg = (MsgChangePlayerState) this.getMessage();

		// Si el mensaje fue enviado originalmente por este jugador, se termina
		// la tarea.
		if (thisMsg.getIdPlayer().equalsIgnoreCase(GameContext.getUserName())) {
			return;
		}
		// estado local de la entidad dinamica que roto
		String dEState = (String) DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES
				.get(thisMsg.getIdPlayer());
		if (dEState == null) {// la entidad no existe localmente
			try {
				// marco la entidad como solicitada.
				DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.put(thisMsg
						.getIdPlayer(), DynamicEntitysSolicitations.SOLICITED);
				// se crea msg de tipo get_dynamic_entity
				MsgPlainText msg = (MsgPlainText) MessageFactory.getInstance()
						.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
				// se setea en el msg el ID de la ENTITY
				msg.setMsg(thisMsg.getIdPlayer());
				// se crea una TaskComm con el msg anterior
				ITask task = TaskCommFactory.getInstance().createComTask(msg);
				TaskManager.getInstance().submit(task);
			} catch (UnsopportedMessageException e) {
				e.printStackTrace();
			}
		} else if (dEState.equals(DynamicEntitysSolicitations.EXISTS)) {
			// La entidad existe localmente
			DynamicEntity entity = (DynamicEntity) EntityManager.getInstance()
					.getEntity(thisMsg.getIdPlayer());
			// cambio de estado para el Player
			entity.setState(thisMsg.getNewState());
		}// else if (dEState.equals(DynamicEntitysSolicitations.SOLICITED){
	}
}
