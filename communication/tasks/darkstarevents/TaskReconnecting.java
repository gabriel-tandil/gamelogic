package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.manager.HudManager;

import common.messages.IMessage;

/**
 * TaskReconnecting.java
 * 
 * @author Castillo/Santos
 */
public class TaskReconnecting extends TaskCommunication {

	/**
	 * String que identifica el mensaje escrito por pantalla para mostrar el
	 * mensaje de intencion de reconeccion.
	 */
	public static final String MENSAJE_RECONECTANDO = "RECONNECTING";

	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String RECONNECTING_TASK_TYPE = "reconnecting";

	/**
	 * 
	 */
	public TaskReconnecting() {
		super(null);
	}

	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */

	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskReconnecting();
	}

	/**
	 * La tarea que tenga el mensaje tendria que mostrar el dialogo y cambie de
	 * estado de ser necesario, invocando al {@link HudManager}
	 * 
	 * @author Castillo/Santos.
	 * @see client.game.task.ITask#execute()
	 */
	public void execute() {
		HudManager.getInstance().escribir("Intentando Reconectar",
				MENSAJE_RECONECTANDO);

		HudManager.getInstance().update();
	}

	/**
	 * @return {@link #RECONNECTING_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTING_TASK_TYPE;
	}

}
