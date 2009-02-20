/**
 * RTaskMove.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgMove;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.task.ITask;
import client.manager.TaskManager;

public class RTaskMove extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskMove(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskMove</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskMove
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskMove(msg);
	}
	
	/**
	 * Se crea un mensaje de movimiento {@link MsgMove}, se setean todos los atributos y<BR>
	 * con el se crea una tarea, la cual es enviada al TaskManager {@link TaskManager}
	 * @see client.game.task.ITask#execute()
	 * 04/02/2009
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {
		MsgMove thisMsg = (MsgMove)this.getMessage();
		
		try {
			
			MsgMove msg = (MsgMove) MessageFactory.getInstance().createMessage(MsgTypes.MSG_MOVE_SEND_TYPE);
			msg.setIdDynamicEntity(GameContext.getUserName());
			
			msg.setPosOrigen(thisMsg.getPosOrigen());
			msg.setPosDestino(thisMsg.getPosDestino());
			
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);
			
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
