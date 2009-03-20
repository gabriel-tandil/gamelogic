/**
 * RTaskGetPlayerResponse.java
 * @author Castillo/Santos
 * Dada una respuesta sobre un evento especifíco a un player, se agrega dicho player al<br>
 * mundo actual seteandole los parametros provisto por el mensaje de respuesta<br>
 * que pertenece a dicha clase.
 * Está se compone de tres metodos:<br>
 *     - <b>RTaskGetPlayerResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */


package client.communication.tasks;
import java.util.HashMap;

import client.communication.tasks.TaskCommunication;
import client.game.entity.Player;
import client.game.task.ChangeStateTask;
import client.manager.EntityManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;

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
	 * Este método crea un player {@link Player}, setea todos sus atributos y se agrega al
	 * mundo actual.
	 * @see client.game.task.ITask#execute()
	 *@see MsgGetPlayerResponse
	 *@see Player
	 *@see ChangeStateTask
	 *@see  TaskManager
	 * @author Castillo/Santos
	 */
	@SuppressWarnings("unchecked")
	public void execute() {

		MsgGetPlayerResponse msg = (MsgGetPlayerResponse) this.getMessage();

		Player player = (Player) EntityManager.getInstance().createEntity(
				"PlayerFactory", msg.getIdPlayer());

		// obtengo el id del mundo con el que se mapea el id del servidor
		// contenido en el mensaje.
		String idClientWorld = WorldsMaper.SERVER_TO_CLIENT.get(msg
				.getActualWorld());

		// Se carga la estructura del nodo correspondiente al mundo actual
		// obtenido desde el servidor.
		ChangeStateTask task = new U3DChangeToFirstPlace(idClientWorld);
		TaskManager.getInstance().enqueue(task);

		// Obtengo la traslacion de la posicion y la seteo
		Vector3f clientPosition = PositionsTranslator.clientPositionServerWorld(msg
				.getActualWorld(), msg.getPosition());
		if (player!=null)
			player.initPlayer(Vector3f.ZERO, 8f, (HashMap) msg.getProperties(),
					null, Vector3f.ZERO, msg.getAngle().x, idClientWorld, msg
							.getSkin(), msg.getPlayerState(), clientPosition);

	}

}
