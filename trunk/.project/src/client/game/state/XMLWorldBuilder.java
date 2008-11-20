/**
 * 
 */
package client.game.state;

import client.game.entity.U3DBuildingEntity;
import client.manager.EntityManager;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingSphere;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Sphere;
import com.jme.scene.shape.Pyramid;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class XMLWorldBuilder implements IWorldBuilder {

    
    
    
	public void buildWorld(Node node) {
		Node World=null;
		Node Player=null;
		getWorld(World);
	//TODO:	View hay que hacer el view manager.
		getPlayer(Player);
		node.attachChild(World);
		node.attachChild(Player);
	}
	
	private void getWorld(Node node)
	{
		Box box;
	    Sphere sphere;
	    Pyramid pyramid;
		//create and position the box
        box = new Box("TestBox", new Vector3f(0f,0f,0f), new Vector3f(10f,10f,10f));
        box.setLocalTranslation(new Vector3f(-5f, -5f, 15f));
        box.setModelBound(new BoundingBox());
        box.updateModelBound();
        
        //create and position the sphere
        sphere = new Sphere("TestSphere", new Vector3f(0f,0f,40f), 10, 10, 5f);
        sphere.setModelBound(new BoundingSphere());
        sphere.updateModelBound();
        
        //create and position the pyramid
        pyramid = new Pyramid("TestPyramid", 10f, 10f);
        pyramid.setLocalTranslation(new Vector3f(0f, -5f, 55f));
        pyramid.setModelBound(new BoundingBox());
        pyramid.updateModelBound();
        
        //create the world and add the objects to it
        node = new Node("TestWorld");
        node.attachChild(box);
        node.attachChild(sphere);
        node.attachChild(pyramid);
        
        //update the bounding geometry for the world
        node.setModelBound(new BoundingBox());
        node.updateModelBound();
        node.updateWorldBound();
		
	}
	
	private void getPlayer(Node node)
	{
		Box player;
		
		//create and position the box
		player = new Box("TestBox", new Vector3f(0f,0f,0f), new Vector3f(10f,10f,10f));
		player.setLocalTranslation(new Vector3f(-5f, -5f, 15f));
		player.setModelBound(new BoundingBox());
		player.updateModelBound();
        
        
        
        //create the world and add the objects to it
        node = new Node("TestWorld");
        node.attachChild(player);
        
        //update the bounding geometry for the world
        node.setModelBound(new BoundingBox());
        node.updateModelBound();
        node.updateWorldBound();
		
	}
	
}