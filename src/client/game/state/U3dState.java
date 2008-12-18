package client.game.state;

import com.jme.math.Vector3f;


public abstract class U3dState extends WorldGameState {

	public U3dState(String name) {
		super(name);
	}

	public abstract void initialize();
	
	public abstract void updateCamera(Vector3f direction);
}
