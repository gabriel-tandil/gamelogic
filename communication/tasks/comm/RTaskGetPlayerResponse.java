/**
 * RTaskGetPlayerResponse.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import java.util.Hashtable;

import com.jme.math.Vector3f;

import common.messages.IMessage;
import common.messages.responses.MsgGetPlayerResponse;

import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.entity.Player;
import client.manager.EntityManager;
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
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskGetPlayerResponse
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskGetPlayerResponse(msg);
	}

	/**
	 * Crea un player {@link Player}, setea todos sus atributos y se agrega al
	 * mundo actual.
	 * 
	 * @see client.game.task.ITask#execute() 04/02/2009
	 * @author Castillo/Santos
	 */
	@Override
	public void execute() {

		MsgGetPlayerResponse msg = (MsgGetPlayerResponse) this.getMessage();

		Player player = (Player) EntityManager.getInstance().createEntity(
				"PlayerFactory", msg.getIdPlayer());

		player.initPlayer(Vector3f.ZERO, 8f, (Hashtable)msg.getProperties(), null,
				Vector3f.ZERO, msg.getAngle().x, msg.getActualWorld(), msg
						.getSkin(), msg.getPlayerState(), msg.getPosition());

		// TODO preguntar si con esto alcanza para inicializar el player.
	}
}
