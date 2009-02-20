/**
 * RTaskRotate.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgRotate;

import client.communication.GameContext;
import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.task.ITask;
import client.manager.TaskManager;

public class RTaskRotate extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskRotate(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskRotate</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskRotate
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskRotate(msg);
	}
	
	/**
	 * Se crea un mensaje de rotacion {@link MsgRotate}, se setean todos los atributos y<BR>
	 * con el se crea una tarea, la cual es enviada al TaskManager {@link TaskManager}
	 * @see client.game.task.ITask#execute()
	 * 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		MsgRotate thisMsg = (MsgRotate)this.getMessage();
		
		try {
			
			MsgRotate msg = (MsgRotate) MessageFactory.getInstance().createMessage(MsgTypes.MSG_ROTATE_SEND_TYPE);
			msg.setIdDynamicEntity(GameContext.getUserName());
			
			msg.setAngle(thisMsg.getAngle());
			
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);
			
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch blockf
			e.printStackTrace();
		}
		
	}
	
}
