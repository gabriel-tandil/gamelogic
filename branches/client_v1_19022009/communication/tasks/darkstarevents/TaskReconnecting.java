package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.manager.HudManager;

import common.messages.IMessage;

/**
 * TaskReconnecting.java
 * 
 * Implementa una {@link TaskCommunication}: {@link TaskReconnecting} Durante
 * un intento de reconexión, se crea una tarea TaskReconnecting. La misma, al
 * ser ejecutada, muestra al jugador el mensaje que indica que se está
 * intentando una reconexión.
 * 
 * @author Castillo/Santos
 */
public class TaskReconnecting extends TaskCommunication {

	/**
	 * String que identifica el mensaje escrito por pantalla para mostrar el
	 * mensaje de intención de reconexión.
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
	 * Para crear una tarea TaskReconnecting, se invoca a este método, el cual
	 * devuelve una instacia de la misma. Retorna una instancia de
	 * TaskReconnecting.
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskReconnecting();
	}

	/**
	 * 
	 * Invoca al {@link HudManager} para mostrar al jugador el mensaje que
	 * indica el intento de reconexión.
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
	 * Retorna el tipo de mensaje de la tarea. *
	 * 
	 * @return {@link #RECONNECTING_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTING_TASK_TYPE;
	}

}
