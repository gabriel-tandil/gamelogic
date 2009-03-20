/**
 * RTaskGetAvailable2DGamesResponse.java
 * @author Castillo/Santos
 * Cada vez que se solicité la opción de comprar un juego 2D mediante el uso de mensajería<br>
 * que provee este framework se dará uso a dicha clase<br> 
 * Está clase se compone de tres metodos:<br>
 *     - <b>RTaskGetBuyable2DGamesResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */
package client.communication.tasks.comm;

import client.communication.tasks.TaskCommunication;


public class RTaskGetBuyable2DGamesResponse extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskGetBuyable2DGamesResponse(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskArrived</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskArrived
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskArrived(msg);
	}
	
	/**
	 * Este método realiza la funcionalidad de devolver si un juego<br>
	 * se encuentra disponible para el cliente que envia la solicitud<br>
	 * <b>NO IMPLEMENTADO</b>
	 * @see client.game.task.ITask#execute()
	 * @author Castillo/Santos
	 */
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}
