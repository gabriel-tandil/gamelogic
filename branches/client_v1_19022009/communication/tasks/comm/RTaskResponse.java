/**
 * RTaskResponse.java
 * @author Castillo/Santos
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */


package client.communication.tasks.comm;

import client.communication.tasks.TaskCommunication;

public class RTaskResponse extends TaskCommunication {
	
	
	/**
	 * @param msg
	 */
	public RTaskResponse(IMessage msg) {
		super(msg);
	}
	
	/**
	 *  Crea una tarea de tipo <I>RTaskResponse</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskResponse
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskResponse(msg);
	}
	
	/**
	 * <b>NO IMPLEMENTADO</b>
	 * @see client.game.task.ITask#execute()
	 */
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
