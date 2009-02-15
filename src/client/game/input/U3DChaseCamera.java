package client.game.input;

import java.util.HashMap;
import java.util.LinkedList;

import com.jme.bounding.BoundingSphere;
import com.jme.input.ChaseCamera;
import com.jme.input.RelativeMouse;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Sphere;


public class U3DChaseCamera extends ChaseCamera {
	
	private Sphere sphere;
	
	public U3DChaseCamera(Camera cam, Spatial target) {
		super(cam, target);
	}

	public U3DChaseCamera(Camera cam, Spatial target, HashMap props) {
		super(cam, target, props);
		
		sphere = new Sphere("esphere",new Vector3f(),20,20,2);
		sphere.setModelBound(new BoundingSphere());
		sphere.updateModelBound();
		sphere.setLocalTranslation(cam.getLocation());
		sphere.setIsCollidable(true);
	}
	
    protected void setupMouse() {
        RelativeMouse mouse = new RelativeMouse("Mouse Input");
        mouse.registerWithInputHandler(this);

        if (mouseLook != null)
            removeAction(mouseLook);

        mouseLook = new U3DThirdPersonMouseLook(mouse, this, target);
        addAction(mouseLook);
    }
    
	public void update(float time) {
		sphere.setLocalTranslation(cam.getLocation());
		sphere.updateModelBound();
		sphere.updateWorldBound();

		super.update(time);
	}
	
	public boolean verifyIntersection(Spatial world) {
		boolean intersects = false;
		LinkedList results = new LinkedList();
		
		if(world != null) {
			this.calculateCollisions(world, results);
			if(results.size() > 0){
				intersects = true;
				Vector3f actual = this.cam.getLocation();
				Vector3f res = this.target.getWorldTranslation().subtract(actual);
				res.normalizeLocal().multLocal(10).addLocal(actual);
//				System.out.println(res);
//				System.out.println(results);
				cam.setLocation(res);
				cam.update();
				((U3DThirdPersonMouseLook)this.mouseLook).hasCollition = true;
			}
			else {
				((U3DThirdPersonMouseLook)this.mouseLook).hasCollition = false;
			}
		}
		else
			System.out.println("world es null");
		return intersects;
	}
	
	private void calculateCollisions(Spatial world, LinkedList results) {
		 if (sphere.getWorldBound() != null && sphere.isCollidable()) {
			 Node node = (Node)world;
			 for (int i = 0; i < node.getQuantity(); i++) {
		         if (sphere.getWorldBound().intersects(node.getChild(i).getWorldBound())) {
		            	//results.add(node.getChild(i).getWorldBound());
		            	results.add(node.getChild(i).getName());
		         }
			 }
		 }
	}

}
