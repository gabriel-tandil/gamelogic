/**
 * RTaskGetPlayerResponse.java
 * @author Castillo/Santos
 */
package client.communication.tasks.comm;

import java.util.HashMap;

import client.communication.WorldsMaper;
import client.communication.tasks.TaskCommunication;
import client.game.entity.Player;
import client.manager.EntityManager;

import com.jme.math.Vector3f;
import common.messages.IMessage;
import common.messages.responses.MsgGetPlayerResponse;

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
	@SuppressWarnings("unchecked")
	public void execute() {

		MsgGetPlayerResponse msg = (MsgGetPlayerResponse) this.getMessage();

		Player player = (Player) EntityManager.getInstance().createEntity(
				"PlayerFactory", msg.getIdPlayer());

		// obtengo el id del mundo con el que se mapea el id del servidor
		// contenido en el mensaje.
		String idClientWorld = WorldsMaper.SERVER_TO_CLIENT.get(msg.getActualWorld());
		player.initPlayer(Vector3f.ZERO, 8f, (HashMap) msg.getProperties(),
				null, Vector3f.ZERO, msg.getAngle().x, idClientWorld, msg.getSkin(), msg.getPlayerState(), msg
						.getPosition());
	}

}
