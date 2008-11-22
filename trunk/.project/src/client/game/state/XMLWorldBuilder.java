/**
 * 
 */
package client.game.state;

import java.util.Hashtable;

import client.game.controller.ControllerManagerFactory;
import client.game.controller.U3DPlayerController;
import client.game.controller.U3DPlayerControllerFactory;
import client.game.entity.Entity;
import client.game.entity.EntityManagerFactory;
import client.game.entity.U3DBuildingEntity;
import client.game.entity.U3DBuildingEntityFactory;
import client.game.entity.U3DPlayer;
import client.game.entity.U3DPlayerFactory;
import client.game.view.U3DBuildingViewFactory;
import client.game.view.U3DPlayerViewFactory;
import client.game.view.U3dBuildingView;
import client.game.view.U3dPlayerView;
import client.game.view.ViewFactoryManager;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingSphere;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Sphere;
import com.jme.scene.shape.Pyramid;
import common.datatypes.PlayerState;
import common.datatypes.Skin;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class XMLWorldBuilder implements IWorldBuilder {

    
    
    
	public void buildWorld(Node node) {
		Node world=null;
		Node player=null;
		EntityManagerFactory.getInstance().add(new U3DBuildingEntityFactory());
		EntityManagerFactory.getInstance().add(new U3DPlayerFactory());
		ViewFactoryManager.getInstance().add(new U3DBuildingViewFactory());
		ViewFactoryManager.getInstance().add(new U3DPlayerViewFactory());
		ControllerManagerFactory.getInstance().add(new U3DPlayerControllerFactory());
		//Edificio:1
		getWorld(world);
		U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.getInstance().createEntity("1");
		U3dBuildingView worldView = (U3dBuildingView) ViewManager.getInstance().createView("1",worldEntity);
		worldEntity.setId("world");
		//Player:2
		getPlayer(player);
		U3DPlayer playerEntity = (U3DPlayer)EntityManager.getInstance().createEntity("2");
		playerEntity.initPlayer("player", Vector3f.ZERO, 8, new Hashtable<String,Object>(), new Hashtable<String,Object>(), Vector3f.ZERO, Vector3f.ZERO, "ExteriorWorld", Vector3f.ZERO, new Skin(), new PlayerState());
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().createView("2",playerEntity);
		U3DPlayerController controllerPlayer = (U3DPlayerController) InputManager.getInstance().createController("1", playerEntity);
		worldView.attachChild(world);
		playerView.attachChild(player);
		node.attachChild(worldView);
		node.attachChild(playerView);
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