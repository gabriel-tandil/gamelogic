package client.game.task;

import client.game.entity.Player;
import client.game.entity.U3DPlayer;
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
	
	private boolean run;
	/**
	 * The z coordinate of the destination position.
	 */
	private float endZ;
	private float startY;
	private float startX;
	private float startZ;
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
			origin.set(startX,startY, startZ);
			destine.set(endX,endY,endZ);
			
			System.out.println("------------------------------Inicio de la tarea MOV");
			System.out.println("Movimiento desde pos: X:"+startX+" Y:"+startY+" Z:"+startZ);
			System.out.println("Movimiento hasta pos: X:"+destine.x+" Y:"+destine.y+" Z:"+destine.z);
			
			U3dExteriorState a=((U3dExteriorState)GameStateManager.getInstance().getChild("U3dExteriorState"));
			Vector3f destination = CollisionManager.getInstace().getDestination(origin, destine,a.getRootNode());
			if (destination != null) {
				((U3DPlayer)this.character).setDestino(destination);
				Spatial view = (Spatial)ViewManager.getInstance().getView(this.character);
				if(!this.local) {
					view.getLocalTranslation().x = this.startX;
					view.getLocalTranslation().y = this.startY;
					view.getLocalTranslation().z = this.startZ;
				}
				destination.y = 0;
				Vector3f lcoal =view.getLocalTranslation().clone();
				lcoal.y = 0;
				Vector3f direction = destination.subtract(lcoal);
				direction.y = 0;
				direction.normalizeLocal();
				view.getLocalRotation().lookAt(direction, Vector3f.UNIT_Y);
				float movement=5000;
				if(run) movement*=2;
				Vector3f force = direction.multLocal(movement);
				this.character.getForce().addLocal(force);

				PhysicsManager.getInstance().markForUpdate(this.character);
				// Step 9.
				PlayerState ps= new PlayerState();
				ps.setState(PlayerState.STATE_MOVING);
				this.character.setState(ps);
				ViewManager.getInstance().markForUpdate(this.character);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------FIN de la tarea MOV");
	}
	
	public void initTask(Player theCharacter, boolean isLocal,float sX,float sY,float sZ,float eX,float eY,float eZ, boolean run)
	{
		this.run=run;
		character=theCharacter;
		local=isLocal;
		endY=eY;
		endX=eX;
		endZ=eZ;
		startY=sY;
		startX=sX;
		startZ=sZ;
		
	}

}
