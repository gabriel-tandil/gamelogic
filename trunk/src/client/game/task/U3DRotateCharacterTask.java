package client.game.task;

import client.game.entity.Player;
import client.manager.CollisionManager;
import client.manager.U3dHudManager;
import client.manager.ViewManager;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;

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
	    U3dHudManager.getInstance().getMap().setRotacion(angles[1]+ angle);
	    U3dHudManager.getInstance().getMap().redrawMap();
	    ViewManager.getInstance().markForUpdate(this.character);
	}
	
	public void initTask(Player theCharacter, float theangle)
	{
		character=theCharacter;
		angle=theangle;
	}
}
