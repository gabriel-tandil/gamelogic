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
		Spatial view = ((Spatial)ViewManager.getInstance().getView(this.character));
		float[] angles=new float[3];
		Vector3f ltras=view.getLocalTranslation();
		view.getLocalRotation().toAngles(angles);
	    view.getLocalRotation().fromAngles(angles[0],angles[1]+ angle, angles[2]);
	    view.setLocalTranslation(ltras);
	    ViewManager.getInstance().markForUpdate(this.character);
	    
	  //mensaje al servidor
	    MsgRotate msg;
		try {
			msg = (MsgRotate) MessageFactory.getInstance().createMessage(MsgTypes.MSG_ROTATE_SEND_TYPE);
			msg.setIdDynamicEntity(this.character.getId());
			msg.setAngle(ltras);		
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
