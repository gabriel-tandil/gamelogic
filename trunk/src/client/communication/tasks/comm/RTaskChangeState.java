/**
 * RTaskChangeState.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgChangePlayerState;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.task.ITask;
import client.manager.TaskManager;

public class RTaskChangeState extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskChangeState(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskChangeState</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskChangeState
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskChangeState(msg);
	}
	
	/**
	 * Este metodo cambia el estado de un player a partir de los datos<BR/>
	 * proporcionado por el mensaje {@link MsgChangePlayerState} que dispara esta tarea.
	 * @see client.game.task.ITask#execute()
	 * 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		MsgChangePlayerState thisMsg = (MsgChangePlayerState)this.getMessage();
		
		try {
			
			MsgChangePlayerState msg = (MsgChangePlayerState) MessageFactory.getInstance().createMessage(MsgTypes.MSG_CHANGE_PLAYER_STATE_SEND_TYPE);
			
			msg.setIdPlayer(GameContext.getUserName());
			msg.setNewState(thisMsg.getNewState());
			
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);
			
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
