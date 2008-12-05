package client.game.task;

import client.game.entity.Player;
import client.game.state.U3dExteriorState;
import client.gameEngine.PhysicsManager;
import client.manager.CollisionManager;
import client.manager.ViewManager;

import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jmex.game.state.GameStateManager;
import common.datatypes.PlayerState;

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
	private boolean adelante;
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
			Spatial view = (Spatial)ViewManager.getInstance().getView(this.character);
			Vector3f direction=view.getLocalRotation().getRotationColumn(0);
			if(!adelante)direction=direction.mult(-1);
			Vector3f position=view.getLocalTranslation();
			
			origin.set(position.x,position.y, position.z);
			destine.set(direction.x+position.x,direction.y+position.y,direction.z+position.z);
			
			U3dExteriorState a=((U3dExteriorState)GameStateManager.getInstance().getChild("U3dExteriorState"));
			

			a.getRootNode().detachChild(view);
			Vector3f destination = CollisionManager.getInstace().getDestination(origin, destine,a.getRootNode());
			a.getRootNode().attachChild(view);
			if (destination != null) {
				if((origin.x==destination.x)&&(origin.z==destination.z)) destination=destine;
				if(((origin.x-destine.x>0)&&(destination.x-origin.x>0))) destination.x=origin.x;//X --->
				if(((origin.x-destine.x<0)&&(destination.x-origin.x<0))) destination.x=origin.x;//X <----
				if(((origin.z-destine.z>0)&&(destination.z-origin.z>0))) destination.z=origin.z;//Z --->
				if(((origin.z-destine.z<0)&&(destination.z-origin.z<0))) destination.z=origin.z;//Z <----
					
					Vector3f lcoal =position.clone();
					direction = destination.subtract(lcoal);
					direction.normalizeLocal();
					
					
					float movement=70000;
					Vector3f force = direction.multLocal(movement);
					this.character.getForce().addLocal(force);
					
					PhysicsManager.getInstance().markForUpdate(this.character);
					// Step 9.
					PlayerState ps= new PlayerState();
					ps.setState(PlayerState.STATE_MOVING);
					this.character.setState(ps);
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void initTask(Player theCharacter, boolean isLocal,boolean adelante)
	{

		character=theCharacter;
		local=isLocal;
		this.adelante=adelante;
		
	}

}