package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.manager.HudManager;

import common.messages.IMessage;

/**
 * TaskReconnected.java
 * 
 * Esta clase implementa una tarea de comunicación: {@link TaskReconnected}
 * Cuando se realiza una reconexión, se crea una tarea TaskReconnected. La
 * misma, al ser ejecutada, muestra al jugador el mensaje de reconexión.
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
	 * Para crear una tarea TaskReconnected, se invoca a este método, el cual
	 * devuelve una instacia de la misma. Retorna una instancia de
	 * TaskReconnected.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages
	 *      .IMessage)
	 */
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskReconnected();
	}

	/**
	 * Invoca al {@link HudManager} para mostrar el mensaje de reconexión.
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
	 * Retorna el tipo de mensaje de la tarea.
	 * 
	 * @return {@link #RECONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTED_TASK_TYPE;
	}
}
