package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.game.state.U3dLoginState;
import com.jmex.game.state.GameStateManager;
import common.messages.IMessage;

/**
 * TaskLogginFailed.java
 * 
 * Esta clase implementa una {@link TaskCommunication}:
 * {@link TaskLogginFailed} Al fallar el Loggin del jugador, se crea una tarea
 * {@link TaskLogginFailed} La misma, al ser ejecutada, envia una respuesta al
 * {@link GameStateManager} indicando error de Logueo.
 * 
 * @author Castillo/Santos
 */
public class TaskLogginFailed extends TaskCommunication {

	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String LOGGIN_FAILED_TASK_TYPE = "loginFailed";

	/**
	 * 
	 */
	public TaskLogginFailed() {
		super(null);
	}

	/**
	 * Para crear una tarea TaskLogginFailed, se invoca a este método, el cual
	 * devuelve una instacia de la misma. Retorna una instancia de
	 * TaskLogginFailed.
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskLogginFailed();
	}

	/**
	 * 
	 * Envia una respuesta de loggin failed al {@link GameStateManager}, donde
	 * esa respuesta es tomada por el <b>HUD manager</b> y en el cual se
	 * mostrará el HUD de logueo incorrecto con el error.<br>
	 * 
	 * @author Castillo/Santos
	 */
	public void execute() {
		String respuesta = U3dLoginState.LOGUEO_ERROR;
		((U3dLoginState) GameStateManager.getInstance().getChild("login"))
				.setRespuestaLogueo(respuesta);
	}

	/**
	 * Retorna el tipo de mensaje de la tarea.
	 * 
	 * @return {@link #LOGGIN_FAILED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return LOGGIN_FAILED_TASK_TYPE;
	}

}
