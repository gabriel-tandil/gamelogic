package client.game.task;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jmex.game.state.GameStateManager;
import common.datatypes.PlayerState;

import client.game.Game;
import client.game.entity.Player;
import client.game.state.U3dExteriorState;
import client.gameEngine.PhysicsManager;
import client.manager.CollisionManager;
import client.manager.ViewManager;

public class U3DMoveCharacterTask extends Task {
	
	public U3DMoveCharacterTask() {
		super();
	}

	/**
	 * The <code>CharacterEntity</code> to be set.
	 */
	private Player character;
	/**
	 * The flag indicates if the entity being set is locally controlled.
	 */
	private boolean local;
	/**
	 * The x coordinate of the destination position.
	 */
	private float endX;
	/**
	 * The y coordinate of the destination position.
	 */
	private float endY;
	/**
	 * The z coordinate of the destination position.
	 */
	private float endZ;
	@Override
	public boolean equals(Object o) {
		if(o instanceof U3DMoveCharacterTask) {
			U3DMoveCharacterTask given = (U3DMoveCharacterTask)o;
			if (given.character != null)
				//faltaria definir el metodo equals en Player
				return given.character.equals(this.character);
			else
				return this.character == null;
		}
		return false;
	}


	public void execute() {
		if (this.character == null) return;
		try {
			this.character.getVelocity().zero();
			this.character.resetForce();
			Vector3f origin=new Vector3f();
			Vector3f destine=new Vector3f();
			origin.set(this.character.getPosition().x,this.character.getPosition().y, this.character.getPosition().z);
			destine.set(endX,endY,endZ);
			U3dExteriorState a=((U3dExteriorState)GameStateManager.getInstance().getChild("U3dExteriorState"));
			Vector3f destination = CollisionManager.getInstace().getDestination(origin, destine,a.getRootNode());
			if (destination != null) {
				//this.character.setPosition(thePosition).setDestination(destination);
				Spatial view = (Spatial)ViewManager.getInstance().getView(this.character);
				if(!this.local) {
					//view.getLocalTranslation().x = this.character.getPosition().x;
					//view.getLocalTranslation().y = this.character.getPosition().y;
					//view.getLocalTranslation().z = this.character.getPosition().z;
				}
				destination.y = 0;
				Vector3f lcoal =character.getPosition().clone();
				lcoal.y = 0;
				Vector3f direction = destination.subtract(lcoal);
				direction.y = 0;
				direction.normalizeLocal();
				//view.getLocalRotation().lookAt(direction, Vector3f.UNIT_Y);
				float movement=50000;
				Vector3f force = direction.multLocal(movement);
				this.character.getForce().addLocal(force);
				//character.addVelocity(new Vector3f(0.001f,0,0.001f));
				PhysicsManager.getInstance().markForUpdate(this.character);
				// Step 9.
				PlayerState ps= new PlayerState();
				ps.setState(PlayerState.STATE_MOVING);
				this.character.setState(ps);
				//ViewManager.getInstance().markForUpdate(this.character);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void initTask(Player theCharacter, boolean isLocal,float eX,float eY,float eZ)
	{

		character=theCharacter;
		local=isLocal;
		endY=eY;
		endX=eX;
		endZ=eZ;
		
	}

}
