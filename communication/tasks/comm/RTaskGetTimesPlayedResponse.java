/**
 * RTaskGetTimesPlayedResponse.java
 * @author Castillo/Santos
 * Cada vez que se solicit� el tiempo el cual un cliente jugo un juego 2D mediante el uso de mensajer�a<br>
 * que provee este framework se dar� uso a dicha clase<br> 
 * Est� clase se compone de tres metodos:<br>
 *     - <b>RTaskGetTimesPlayedResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */

package client.communication.tasks.comm;

import client.communication.tasks.TaskCommunication;

public class RTaskGetTimesPlayedResponse extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskGetTimesPlayedResponse(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskArrived</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskArrived(msg);
	}
	
	/**
	 * Este m�todo realiza la funcionalidad de devolver el tiempo de <br>
	 * juego de un cliente sobre un mini juego.<br>
	 * <b>NO IMPLEMENTADO</b>
	 * @see client.game.task.ITask#execute()
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
