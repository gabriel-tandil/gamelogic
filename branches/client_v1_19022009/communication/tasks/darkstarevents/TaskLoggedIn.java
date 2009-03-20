package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.game.state.U3dLoginState;

import com.jmex.game.state.GameStateManager;
import common.messages.IMessage;

/**
 * TaskLoggedIn.java
 * 
 * Esta clase implementa una {@link TaskCommunication}: {@link TaskLoggedIn} Cuando el
 * Loggin del jugador es exitoso, se crea una tarea {@link TaskLoggedIn} La
 * misma, al ser ejecutada, envía una respuesta al {@link GameStateManager}
 * indicando que se producido el Logueo con éxito.
 * 
 * @author Castillo/Santos
 */
public class TaskLoggedIn extends TaskCommunication {

	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String LOGGEDIN_TASK_TYPE = "loggedIn";

	/**
	 * 
	 */
	public TaskLoggedIn() {
		super(null);
	}

	/**
	 * Para crear una tarea TaskLoggedIn, se invoca a este método, el cual
	 * devuelve una instacia de la misma. Retorna una instancia de TaskLoggedIn.
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskLoggedIn();
	}

	/**
	 * Envia una respuesta de loggin al {@link GameStateManager}, donde esa
	 * respuesta es tomada por el <b>HUD manager</b> y en el cual se mostrara
	 * el HUD de loggin satisfatorio y se hará la transición de estado
	 * pertinente.
	 * 
	 * @author Castillo/Santos
	 */
	public void execute() {
		String respuesta = U3dLoginState.LOGUEO_OK;
		((U3dLoginState) GameStateManager.getInstance().getChild("login"))
				.setRespuestaLogueo(respuesta);
	}

	/**
	 * Retorna el tipo de mensaje de la tarea.
	 * 
	 * @return {@link #LOGGEDIN_TASK_TYPE}
	 */
	public final String getMsgType() {
		return LOGGEDIN_TASK_TYPE;
	}
}
