/**
 * RTaskGetDynamicEntityResponse.java
 * @author Castillo/Santos
 * Dada una respuesta sobre algún evento a una entidad, se deberá acrualizar los datos<br>
 * de dicha entidad para mantener consistentes los datos en el servidor y/o demás<br>
 * clientes.<br>
 * Está se compone de tres metodos:<br>
 *     - <b>RTaskGetDynamicEntityResponse:</b> Constructor de la clase.<br>
 *     - <b>TaskCommunication:</b> Factory para crear una tarea de este tipo.<br>
 *     - <b>execute:</b> Funcionalidad principal de la clase.<br>
 */


package client.communication.tasks.comm;

import client.communication.GameContext;
import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.entity.Player;
import client.manager.EntityManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;

public class RTaskGetDynamicEntityResponse extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskGetDynamicEntityResponse(IMessage msg) {
		super(msg);
	}

	/**
	 * Crea una tarea de tipo <I>RTaskGetDynamicEntityResponse</I> y setea el mensaje.
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 * @param msg
	 * @return RTaskGetDynamicEntityResponse
	 */
	
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		return new RTaskGetDynamicEntityResponse(msg);
	}

	/**
	 * Este metodo obtiene del {@link EntityManager} la entidad, si existe esa
	 * entidad se actualizan<BR>
	 * todos sus atributos con los se encuentran en el mensaje de dicha tarea,
	 * en caso contrario,<BR>
	 * se crea un mensaje {@link MsgPlainText} del tipo
	 * "MSG_GET_DYNAMIC_ENTITY_TYPE", luego se crea<BR>
	 * una tarea especifica a la cual se le asigna el mensaje creado
	 * anteriormente y finalmente se envia esa<BR>
	 * tarea al taskManager {@link TaskManager}.
	 * @see client.game.task.ITask#execute()
	 */

	public void execute() {

		MsgGetDynamicEntityResponse msg = (MsgGetDynamicEntityResponse) this
				.getMessage();

		// Si el mensaje fue enviado originalmente por este jugador, se termina
		// la tarea.
		if (msg.getIdDynamicEntity()
				.equalsIgnoreCase(GameContext.getUserName())) {
			return;
		}

		if (DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.get(msg
				.getIdDynamicEntity()) == null
				|| !DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.get(
						msg.getIdDynamicEntity()).equals(
						DynamicEntitysSolicitations.EXISTS)) {

			// obtengo el id del mundo con el que se mapea el id del servidor
			// contenido en el mensaje.
			String idClientWorld = WorldsMaper.SERVER_TO_CLIENT.get(msg
					.getActualWorld());
		
			// Obtengo la traslacion de la posicion y la seteo
			Vector3f clientPosition = PositionsTranslator
					.clientPositionServerWorld(msg.getActualWorld(), msg
							.getPosition());

			DynamicEntity entity = (DynamicEntity) EntityManager.getInstance()
					.createEntity("DynamicEntityFactory",
							msg.getIdDynamicEntity());
			
			if (entity == null)
				entity = (DynamicEntity) EntityManager.getInstance()
							.getEntity(msg.getIdDynamicEntity());
			
			Player player = (Player) EntityManager.getInstance().getEntity(
					GameContext.getUserName());
			
			if (idClientWorld.compareTo(player.getActualWorld()) == 0){
				entity.init(Vector3f.ZERO, 8f, Vector3f.ZERO, msg.getAngle().x,
						idClientWorld, msg.getSkin(), clientPosition);
                // Marco el estado local de la entidad recien creada como existente.
				DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.put(msg
						.getIdDynamicEntity(), DynamicEntitysSolicitations.EXISTS);
				}
			else
				DynamicEntitysSolicitations.DYNAMIC_ENTITYS_STATES.remove(msg
						.getIdDynamicEntity());
				
		}	
	}
}
