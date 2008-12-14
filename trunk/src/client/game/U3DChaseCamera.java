package client.game;

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
	
	private Sphere esphere;
	
	public U3DChaseCamera(Camera cam, Spatial target) {
		super(cam, target);
	}

	public U3DChaseCamera(Camera cam, Spatial target, HashMap props) {
		super(cam, target, props);
		
		esphere = new Sphere("esphere",new Vector3f(),20,20,2);
		esphere.setModelBound(new BoundingSphere());
		esphere.updateModelBound();
		esphere.setLocalTranslation(cam.getLocation());
		esphere.setIsCollidable(true);
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
		esphere.setLocalTranslation(cam.getLocation());
		esphere.updateModelBound();
		esphere.updateWorldBound();

		super.update(time);
	}
	
	public boolean verifyIntersection(Spatial world, Vector3f direction) {
		boolean intersects = false;
		LinkedList results = new LinkedList();
		
		if(world != null) {
			this.calculateCollisions(world, results);
			if(results.size() > 0){
				intersects = true;
				Vector3f actual = this.cam.getLocation();
				Vector3f res = actual.add(direction.clone().mult(-1000));
//				System.out.println(results);
				cam.setLocation(res);
				cam.update();
			}
		}
		else
			System.out.println("world es null");
		return intersects;
	}
	
	private void calculateCollisions(Spatial world, LinkedList results) {
		 if (esphere.getWorldBound() != null && esphere.isCollidable()) {
			 Node node = (Node)world;
			 for (int i = 0; i < node.getQuantity(); i++) {
		         if (esphere.getWorldBound().intersects(node.getChild(i).getWorldBound())) {
		            	//results.add(node.getChild(i).getWorldBound());
		            	results.add(node.getChild(i).getName());
		         }
			 }
		 }
	}

}
