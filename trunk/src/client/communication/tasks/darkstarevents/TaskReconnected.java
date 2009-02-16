package client.communication.tasks.darkstarevents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import client.communication.tasks.TaskCommunication;
import client.communication.tasks.comm.RTaskChangeState;
import client.manager.HudManager;
import client.manager.TaskManager;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;

/**
 * TaskReconnected.java
 * @author Castillo/Santos
 */
public class TaskReconnected extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	RECONNECTED_TASK_TYPE	= "reconnected";
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Se invoca al {@link HudManager} para mostrar el dialogo de reconexión, con los botones 
	 * correspondientes y la acción de cambio de estado.   
	 * @author Castillo/Santos.
	 * @see client.game.task.ITask#execute()
	  */	
	public void execute() {
		HashMap<String, String> botones =new HashMap<String, String>();
		botones.put("Reconectar", "Reconectar");
		HudManager.getInstance().muestraDialogo(
		 "Volver a reconectar?",botones ,
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
	 * @return {@link #RECONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTED_TASK_TYPE;
	}
	
}
