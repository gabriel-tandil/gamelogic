/**
 * RTaskGetPlayerResponse.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import java.util.Hashtable;

import common.messages.IMessage;
import common.messages.responses.MsgGetPlayerResponse;

import client.communication.tasks.TaskCommunication;
import client.game.entity.Player;
import common.datatypes.IPlayerProperty;

public class RTaskGetPlayerResponse extends TaskCommunication {
	
	/**
	 * @param msg
	 */
	public RTaskGetPlayerResponse(IMessage msg) {
		super(msg);
	}
	
	/**
	 * Crea una tarea de tipo <I>RTaskGetPlayerResponse</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskGetPlayerResponse
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskGetPlayerResponse(msg);
	}
	
	/**
	 * Crea un player {@link Player}, setea todos sus atributos y se agrega al mundo actual.
	 * @see client.game.task.ITask#execute()
	 * 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {
		
		MsgGetPlayerResponse msg = (MsgGetPlayerResponse)this.getMessage();
		
		Player player = new Player(msg.getType());
		player.setAngle(msg.getAngle());
		player.setId(msg.getIdPlayer());
		player.setProperties((Hashtable<String, IPlayerProperty>) msg.getProperties());
		player.setSkin(msg.getSkin());
		player.setState(msg.getPlayerState());
		
		//Preguntar si de esta manera agregamos al jugador
		
		player.setActualWorld(msg.getActualWorld());
	}
	
}
