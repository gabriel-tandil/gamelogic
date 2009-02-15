package client.game.input;

import java.util.ArrayList;

import client.game.state.U3dState;

import com.jme.input.ChaseCamera;
import com.jme.input.RelativeMouse;
import com.jme.input.action.InputActionEvent;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.math.Vector3f;
import com.jme.scene.Spatial;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;

public class U3DThirdPersonMouseLook extends ThirdPersonMouseLook {
	
	public boolean hasCollition = false;

	
	public U3DThirdPersonMouseLook(RelativeMouse mouse, ChaseCamera camera, Spatial target) {
		super(mouse, camera, target);
	}
	
	public void performAction(InputActionEvent event) {
		Vector3f position = this.mouse.getLocalTranslation();
		
		ArrayList<GameState> states = GameStateManager.getInstance().getChildren();
		U3dState state = null;
		
		for(int i =0; i < states.size(); i++){
			state = (U3dState)states.get(i);
			if(state.isActive())
				break;
		}

		if((state != null)) {
			if ((position.x != 0) || (position.y != 0) || (position.z != 0)){
				state.updateCamera();
			}
		}
		else {
			System.out.println(state.getName() + " no existe");
		}
		
		if(!hasCollition) {
			super.performAction(event);
		}
	}
}
