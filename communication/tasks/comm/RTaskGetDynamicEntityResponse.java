package client.communication.tasks.comm;

import com.jme.math.Vector3f;

import common.exceptions.UnsopportedMessageException;
import common.messages.IMessage;
import common.messages.MessageFactory;
import common.messages.MsgPlainText;
import common.messages.MsgTypes;
import common.messages.responses.MsgGetDynamicEntityResponse;

import client.communication.tasks.TaskCommFactory;
import client.communication.tasks.TaskCommunication;
import client.game.entity.DynamicEntity;
import client.game.entity.EntityManagerFactory;
import client.game.task.ITask;
import client.manager.EntityManager;
import client.manager.TaskManager;

/**
 * @author Castillo/Santos
 * 
 */
public class RTaskGetDynamicEntityResponse extends TaskCommunication {

	/**
	 * @param msg
	 */
	public RTaskGetDynamicEntityResponse(IMessage msg) {
		super(msg);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages.IMessage)
	 */
	@Override
	public TaskCommunication factoryMethod(IMessage msg) {
		// TODO Auto-generated method stub
		return null;
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
	 * 
	 * @see client.game.task.ITask#execute()
	 */
	@Override
	public void execute() {

		MsgGetDynamicEntityResponse msg = (MsgGetDynamicEntityResponse) this
				.getMessage();

		DynamicEntity entity = (DynamicEntity) EntityManager.getInstance()
				.createEntity("DynamicEntityFactory", msg.getIdDynamicEntity());

		entity.init(Vector3f.ZERO, 8f, Vector3f.ZERO, msg.getAngle().x, msg
				.getActualWorld(), msg.getSkin(), msg.getPosition());

	}

}
