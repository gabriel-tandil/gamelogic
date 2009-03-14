package client.communication.tasks.darkstarevents;

import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import java.util.HashMap;

import client.communication.tasks.TaskCommunication;
import client.communication.tasks.comm.RTaskChangeState;
import client.manager.U3dHudManager;
import client.manager.TaskManager;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;

/**
 * TaskReconnecting.java
 * @author Castillo/Santos
 */
public class TaskReconnecting extends TaskCommunication {
	
	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String	RECONNECTING_TASK_TYPE	= "reconnecting";
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * La tarea que tenga el mensaje tendria que mostrar el dialogo 
	 * y cambie de estado de ser necesario, invocando al {@link U3dHudManager}
	 * @author Castillo/Santos.
	 * @see client.game.task.ITask#execute()
	 */
	public void execute() {
		HashMap<String, String> botones =new HashMap<String, String>();
		botones.put("Reconnecting", "Reconnecting");
		U3dHudManager.getInstance().muestraDialogo(
		 "Reconnectando?",botones ,
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

		U3dHudManager.getInstance().update();
		
	}
	
	/**
	 * @return {@link #RECONNECTING_TASK_TYPE}
	 */
	public final String getMsgType() {
		return RECONNECTING_TASK_TYPE;
	}
	
}
