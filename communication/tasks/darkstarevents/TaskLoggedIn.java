package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.game.state.U3dLoginState;

import com.jmex.game.state.GameStateManager;
import common.messages.IMessage;

/**
 * TaskLoggedIn.java
 * @author Castillo/Santos
 */
public class TaskLoggedIn extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	LOGGEDIN_TASK_TYPE	= "loggedIn";
	
	/**
	 * 
	 */
	public TaskLoggedIn() {
		super(null);
	}
	
	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	
	public TaskCommunication factoryMethod(final IMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Envia una respuesta de loggin al {@link GameStateManager}, donde esa<br>
	 * respuesta es tomado por el <b>HUD manager</b> y en el cual se mostrara el HUD<br>
	 * de loggin satisfatorio y se hara la transicion de estado pertinente.<br>
	 * @author Castillo/Santos
	 */
	
	public void execute() {
		
		String respuesta = U3dLoginState.LOGUEO_OK;
		((U3dLoginState)GameStateManager.getInstance().getChild("login")).setRespuestaLogueo(respuesta);
		
	}
	
	/**
	 * @return {@link #LOGGEDIN_TASK_TYPE}
	 */
	public final String getMsgType() {
		return LOGGEDIN_TASK_TYPE;
	}
	
}
