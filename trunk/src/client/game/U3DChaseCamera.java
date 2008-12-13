package client.game;

import java.util.HashMap;

import com.jme.input.ChaseCamera;
import com.jme.renderer.Camera;
import com.jme.scene.Spatial;

public class U3DChaseCamera extends ChaseCamera {
	
	public U3DChaseCamera(Camera cam, Spatial target) {
		super(cam, target);
	}

	public U3DChaseCamera(Camera cam, Spatial target, HashMap props) {
		super(cam, target, props);
	}
	
	public void update(float time) {
		super.update(time);
	}
}
