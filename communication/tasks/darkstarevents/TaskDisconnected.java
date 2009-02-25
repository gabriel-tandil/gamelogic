package client.communication.tasks.darkstarevents;

import java.util.HashMap;

import client.communication.tasks.TaskCommunication;
import client.communication.tasks.comm.RTaskChangeState;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;

/**
 * TaskDisconnected.java
 * @author Castillo/Santos
 */
public class TaskDisconnected extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	DISCONNECTED_TASK_TYPE	= "disconnected";
	
	/** La razon de la desconeccion. */
	private String razonDesconeccion;
	
	/**
	 * @param razonDesconeccion
	 */
	public TaskDisconnected(final String razonDesconeccion) {
		super(null);
		this.razonDesconeccion = razonDesconeccion;
	}
	
	/**
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages
	 *      .IMessage)
	 */
	
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskDisconnected(((MsgPlainText)msg).getMsg());
	}
	
	/**
	 * Se muestra el dialogo de consulta para volver a conectar, invocando al {@link HudManager}
	 * con los botones correspondientes y la acci�n de cambio de estado.
	 * @see client.game.task.ITask#execute()
	 * @author Castillo/Santos.	 
	 */	
	public void execute() {
		
		HashMap<String, String> botones =new HashMap<String, String>();
		botones.put("Conectar", "Conectar");
		HudManager.getInstance().muestraDialogo(
		 "Volver a conectar?",botones ,
		 new ActionListener() {
		   public void actionPerformed(ActionEvent event) {
			   try {
					MsgChangePlayerState msg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
					RTaskChangeState taskChangeState = new RTaskChangeState(msg);
					TaskManager.getInstance().submit(taskChangeState);
				} catch (UnsopportedMessageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
		   }
		});

		HudManager.getInstance().update();
	}
	
	/**
	 * @return {@link #DISCONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return DISCONNECTED_TASK_TYPE;
	}
	
	/**
	 * @return the razonDesconeccion
	 */
	public final String getRazonDesconeccion() {
		return razonDesconeccion;
	}
	
	/**
	 * @param razonDesconeccion the razonDesconeccion to set
	 */
	public final void setRazonDesconeccion(final String razonDesconeccion) {
		this.razonDesconeccion = razonDesconeccion;
	}
	
}
