package client.game;

import java.util.ArrayList;

import client.game.state.WorldGameState;

import com.jme.input.ChaseCamera;
import com.jme.input.RelativeMouse;
import com.jme.input.action.InputActionEvent;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;

public class U3DThirdPersonMouseLook extends ThirdPersonMouseLook {
	
	private Vector3f oldDirection = new Vector3f();

	public U3DThirdPersonMouseLook(RelativeMouse mouse, ChaseCamera camera, Spatial target) {
		super(mouse, camera, target);
	}
	
	public void performAction(InputActionEvent event) {
		Vector3f direction = new Vector3f(this.mouse.getLocalTranslation());
		Vector3f newDirection = direction.normalizeLocal().subtract(oldDirection);
		newDirection = newDirection.normalize().negate();
		
		ArrayList<GameState> states = GameStateManager.getInstance().getChildren();
		WorldGameState state = null;
		
		for(int i =0; i < states.size(); i++){
			state = (WorldGameState)states.get(i);
			if(state.isActive())
				break;
		}
		
		if(state != null) {
			state.updateCamera(new Vector3f(newDirection));
		}
		else {
			System.out.println(state.getName() + "no existe");
		}
		
		this.oldDirection = direction;
		
		super.performAction(event);
	}
}
