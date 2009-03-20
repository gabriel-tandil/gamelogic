/**
 * RTaskArrived.java
 * @author Castillo/Santos
 * Cada vez que una entidad entra a un "mundo" donde hay otros clientes, <br>
 * se necesitará mantener consistente los datos en el servidor y/o<br> 
 * notificar a los otros clientes. Esta clase fue creada con el fin<br> 
 * de proveer este servicio al juego.<br>
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskArrived:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */



package client.communication.tasks.comm;

import javax.xml.soap.MessageFactory;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.task.ITask;
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
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskArrived
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskArrived(msg);
	}

	/**
	 * Este método es el encargado de notificar al servidor y/o demás clientes la llegada<br>
	 * de una nueva entidad al mundo.
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @see DynamicEntitysSolicitations
	 * @see MsgPlainText
	 * @see TaskManager
	 * @author Castillo/Santos
	 */
	 
	@Override
	public void execute() {
		MsgPlainText thisMsg = (MsgPlainText) this.getMessage();
		String idEntity = thisMsg.getMsg();

		// Si el mensaje fue enviado originalmente por este jugador, se
		// termina la tarea.
		if (idEntity.equalsIgnoreCase(GameContext.getUserName())) {
			return;
		}

		// estado local de la entidad dinamica que roto
		String dEState = (String) DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES
				.get(thisMsg.getMsg());
		if (dEState == null) {// la entidad no existe localmente
			try {
				// marco la entidad como solicitada.
				DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.put(thisMsg
						.getMsg(), DynamicEntitysSolicitations.SOLICITED);
				// se crea msg de tipo get_dynamic_entity
				MsgPlainText msg = (MsgPlainText) MessageFactory.getInstance()
						.createMessage(MsgTypes.MSG_GET_DYNAMIC_ENTITY_TYPE);
				// se setea en el msg el ID de la ENTITY
				msg.setMsg(thisMsg.getMsg());
				// se crea una TaskComm con el msg anterior
				ITask task = TaskCommFactory.getInstance().createComTask(msg);
				TaskManager.getInstance().submit(task);
			} catch (UnsopportedMessageException e) {
				e.printStackTrace();
			}
		}

	}
}