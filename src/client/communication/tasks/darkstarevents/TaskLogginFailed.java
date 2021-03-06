package client.communication.tasks.darkstarevents;

import client.communication.tasks.TaskCommunication;
import client.game.state.U3dLoginState;
import com.jmex.game.state.GameStateManager;
import common.messages.IMessage;

/**
 * TaskLogginFailed.java
 * @author Castillo/Santos
 */
public class TaskLogginFailed extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	LOGGIN_FAILED_TASK_TYPE	= "loginFailed";
	
	/**
	 * 
	 */
	public TaskLogginFailed() {
		super(null);
	}
	
	/**
	 * @see client.communication.tasks.TaskCommunication#
	 *      factoryMethod(common.messages.IMessage)
	 */
	
	public TaskCommunication factoryMethod(final IMessage msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * Envia una respuesta de loggin failed al {@link GameStateManager}, donde esa<br>
	 * respuesta es tomado por el <b>HUD manager</b> y en el cual se mostrara el HUD<br>
	 * de logueo incorrecto con el error.<br>
	 * @author Castillo/Santos
	 */	
	public void execute() {
		String respuesta = "Loggin Failed";
		
		((U3dLoginState)GameStateManager.getInstance().getChild("login")).setRespuestaLogueo(respuesta);
	}
	
	/**
	 * @return {@link #LOGGIN_FAILED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return LOGGIN_FAILED_TASK_TYPE;
	}
	
}
