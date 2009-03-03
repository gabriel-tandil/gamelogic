package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.manager.HudManager;

import common.messages.IMessage;

/**
 * TaskReconnected.java
 * 
 * @author Castillo/Santos
 */
public class TaskReconnected extends TaskCommunication {

	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String RECONNECTED_TASK_TYPE = "reconnected";

	/**
	 * 
	 */
	public TaskReconnected() {
		super(null);
	}

	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages
	 *      .IMessage)
	 */

	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskReconnected();
	}

	/**
	 * Se invoca al {@link HudManager} para mostrar el dialogo de reconexi�n,
	 * con los botones correspondientes y la acci�n de cambio de estado.
	 * 
	 * @author Castillo/Santos.
	 * @see client.game.task.ITask#execute()
	 */
	public void execute() {
		HudManager.getInstance().quitarEscrito(
				TaskReconnecting.MENSAJE_RECONECTANDO);

		HudManager.getInstance().update();

	}

	/**
	 * @return {@link #RECONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTED_TASK_TYPE;
	}

}
