package client.game.task;

import client.communication.tasks.TaskCommFactory;
import client.game.entity.Player;
import client.manager.CollisionManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

import common.exceptions.UnsopportedMessageException;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgRotate;
import common.util.ChannelNameParser;

/**
 * <Code>U3DRotateCharacterTask</code> es responsable de actualizar
 * la rotación actual del player. Además crea un mensaje de rotación
 * que es enviado al servidor.
 * <code>U3DRotateCharacterTask</code> extiende la funcionalidad de
 * <code>Task</code>.
 * 
 * @author Sebastian Sampaoli (Javadoc)
 *
 */

public class U3DRotateCharacterTask extends Task {
	/**
	 * The <code>CharacterEntity</code> to be set.
	 */
	private Player character;

	private float angle;

	public U3DRotateCharacterTask() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute() {
		float angle1 = character.getAngle();
		character.setAngle(angle1 + angle);
		float newAngle = character.getAngle();
		
		// mensaje al servidor
		MsgRotate msg;
		try {
			msg = (MsgRotate) MessageFactory.getInstance().createMessage(
					MsgTypes.MSG_ROTATE_SEND_TYPE);
			msg.setIdDynamicEntity(this.character.getId());
			// El angulo es un float, entonces usamos la dimension x del vector
			msg.setAngle(new Vector3f(newAngle, newAngle, newAngle));
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initTask(Player theCharacter, float theangle) {
		character = theCharacter;
		angle = theangle;
	}
}
