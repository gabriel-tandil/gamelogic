/**
 * RTaskArrived.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import common.messages.IMessage;
import common.messages.MsgPlainText;

import client.communication.tasks.TaskCommunication;
import client.manager.EntityManager;


public class RTaskArrived extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskArrived(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTashArrived</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskArrived
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskArrived(msg);
	}
	
	/**
	 * Este metodo crea una entidad en el {@link EntityManager}<BR/>
	 * el id de la entidad es obtenido desde el mensaje propio de<BR/>
	 * la tarea.<BR/>
	 * @see client.game.task.ITask#execute()
	 * 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		/*MsgPlainText msg = (MsgPlainText)this.getMessage();
		String idEntity = msg.toString();
		
		EntityManager.getInstance().createEntity(idEntity);*/
	}
	
}
