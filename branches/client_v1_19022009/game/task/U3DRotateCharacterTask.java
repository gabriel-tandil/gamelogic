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

	public void execute() {
		character.setAngle(angle);
	    
	  //mensaje al servidor
	    MsgRotate msg;
		try {
			msg = (MsgRotate) MessageFactory.getInstance().createMessage(MsgTypes.MSG_ROTATE_SEND_TYPE);
			msg.setIdDynamicEntity(this.character.getId());
			//El angulo es un float, entonces usamos la dimension x del vector
			msg.setAngle(new Vector3f(angle,angle,angle));		
			ITask task = TaskCommFactory.getInstance().createComTask(msg);
			TaskManager.getInstance().submit(task);
		} catch (UnsopportedMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void initTask(Player theCharacter, float theangle)
	{
		character=theCharacter;
		angle=theangle;
	}
}