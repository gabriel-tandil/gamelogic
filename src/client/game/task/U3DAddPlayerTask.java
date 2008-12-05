package client.game.task;

import java.util.Hashtable;

import client.game.controller.ControllerManagerFactory;
import client.game.controller.U3DPlayerController;
import client.game.controller.U3DPlayerControllerFactory;
import client.game.entity.U3DPlayer;
import client.game.state.U3dExteriorState;
import client.game.view.U3dPlayerView;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingCapsule;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.scene.shape.Sphere;
import com.jmex.game.state.GameStateManager;
import common.datatypes.PlayerState;
import common.datatypes.Skin;

public class U3DAddPlayerTask extends Task {
	
	private final String id;
	
	private final boolean local;
	
	private final float x;
	
	private final float z;
	
	
	public U3DAddPlayerTask(String id, float x, float z, boolean local) {
		this.id = id;
		this.x = x;
		this.z = z;
		this.local = local;
	}
	
	public void execute() {
		if(this.local) {
			this.addPlayer();
		}
	}

	public void addPlayer() {
		Vector3f position = new Vector3f(this.x, 5, this.z);
		
		U3dExteriorState state = (U3dExteriorState)GameStateManager.getInstance().
			getChild("U3dExteriorState");
		
		U3DPlayer playerEntity = (U3DPlayer)EntityManager.getInstance().createEntity("2");
		playerEntity.initPlayer("player1", Vector3f.ZERO.clone(), 8, new Hashtable<String,
				Object>(), new Hashtable<String,Object>(), Vector3f.ZERO.clone(), 
				position, "ExteriorWorld", new Skin(), 
				new PlayerState());
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().
			createView(playerEntity);
		playerView.setLocalTranslation(position);
		playerView.attachChild(getPlayer());
		playerView.updateModelBound();
		playerView.updateWorldBound();
		
		state.getRootNode().attachChild(playerView);

/*		playerView.getLocalTranslation().x = this.x;
		playerView.getLocalTranslation().z = this.z;
*/	
		if(state.getRootNode().getWorldBound() == null) {
			state.getRootNode().updateGeometricState(0, true);
		}
		if(state.getRootNode().getWorldBound() != null){
			Vector3f center = state.getRootNode().getWorldBound().getCenter();
			if(center != null) {
				Vector3f direction = center.subtract(this.x, 0, this.z);
				direction.y = 0;
				direction.normalizeLocal();
				playerView.getLocalRotation().lookAt(direction, Vector3f.UNIT_Y);
			}
		}
		
		ControllerManagerFactory.getInstance().add(new U3DPlayerControllerFactory());
		U3DPlayerController controllerPlayer = (U3DPlayerController) InputManager.
			getInstance().createController(playerEntity);
		controllerPlayer.setActive(true);
		KeyInput.get().addListener(controllerPlayer);
		state.initializeCamera(playerView);
//		state.initialize();
	}

	private Sphere getPlayer()
	{
		Sphere player;
		//create and position the box
		player = new Sphere("Esfera", new Vector3f(0f,0f,0f), 40,40,2);

		player.setModelBound(new BoundingCapsule());
				
		player.updateModelBound();

		return player;
	}

	public boolean equals(Object o) {
		return false;
	}
}
