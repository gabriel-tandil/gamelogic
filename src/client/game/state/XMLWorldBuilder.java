/**
 * 
 */
package client.game.state;





import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import client.game.Game;
import client.game.controller.ControllerManagerFactory;
import client.game.controller.U3DPlayerController;
import client.game.controller.U3DPlayerControllerFactory;
import client.game.entity.EntityManagerFactory;
import client.game.entity.U3DBuildingEntity;
import client.game.entity.U3DBuildingEntityFactory;
import client.game.entity.U3DPlayer;
import client.game.entity.U3DPlayerFactory;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DMoveCharacterTaskFactory;
import client.game.view.U3DBuildingViewFactory;
import client.game.view.U3DPlayerViewFactory;
import client.game.view.U3dBuildingView;
import client.game.view.U3dPlayerView;
import client.game.view.ViewFactoryManager;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingCapsule;
import com.jme.bounding.BoundingSphere;
import com.jme.input.KeyInput;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.scene.shape.Pyramid;
import com.jme.scene.shape.Sphere;
import com.jme.util.export.binary.BinaryImporter;
import com.jme.util.resource.ResourceLocatorTool;
import com.jme.util.resource.SimpleResourceLocator;
import com.jmex.model.converters.AseToJme;
import com.jmex.model.converters.FormatConverter;
import com.jmex.model.converters.MaxToJme;
import com.jmex.model.converters.Md2ToJme;
import com.jmex.model.converters.Md3ToJme;
import com.jmex.model.converters.MilkToJme;
import com.jmex.model.converters.ObjToJme;
import com.jmex.model.util.ModelLoader;
import common.datatypes.PlayerState;
import common.datatypes.Skin;

/** 
 * @author Mara
 * @generated "De UML a Java V5.0 (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class XMLWorldBuilder implements IWorldBuilder {
    
	public void buildWorld(Node node) {

		EntityManagerFactory.getInstance().add(new U3DBuildingEntityFactory());
		EntityManagerFactory.getInstance().add(new U3DPlayerFactory());
		
		ViewFactoryManager.getInstance().add(new U3DBuildingViewFactory());
		ViewFactoryManager.getInstance().add(new U3DPlayerViewFactory());
		
		TaskManagerFactory.getInstance().add(new U3DMoveCharacterTaskFactory());
		
		ControllerManagerFactory.getInstance().add(new U3DPlayerControllerFactory());
		
		//Edificio:1
		//******texturas: anda lento
		try{
      	 ResourceLocatorTool.addResourceLocator(ResourceLocatorTool.TYPE_TEXTURE, new SimpleResourceLocator(Game.class.getClassLoader().getResource("protCampus/images/")));
        }catch(Exception e){}
		//**********
		Node campus = new Node("Campus");
		getWorld(campus);	
		U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.
			getInstance().createEntity("1");

		worldEntity.setId("world");
		U3dBuildingView worldView = (U3dBuildingView) ViewManager.getInstance().
			createView(worldEntity);

		//Player:2
		Node player = new Node("Player");
		getPlayer(player);
		U3DPlayer playerEntity = (U3DPlayer)EntityManager.getInstance().createEntity("2");
		playerEntity.initPlayer("player", Vector3f.ZERO.clone(), 8, new Hashtable<String,
				Object>(), new Hashtable<String,Object>(), Vector3f.ZERO.clone(), 
				Vector3f.ZERO.clone(), "ExteriorWorld",new Vector3f(0.000000f, 0.500000f, 850.00000f), new Skin(), 
				new PlayerState());
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().
			createView(playerEntity);
		U3DPlayerController controllerPlayer = (U3DPlayerController) InputManager.
			getInstance().createController(playerEntity);
		controllerPlayer.setActive(true);
		worldView.attachChild(campus);
		KeyInput.get().addListener(controllerPlayer);
		playerView.attachChild(player);
		node.attachChild(worldView);
		node.attachChild(playerView);
	}
	
	private void getWorld(Node campus){	
	 for(int i= 1; i<= 1087;i=i+1){
		 Node hijo = new Node("Hijo"+i);
		 hijo=cargarModelo("protCampus/data/campus_parte"+i+".3ds");
		 Quaternion q = hijo.getLocalRotation();
		 q = q.fromAngleAxis((float)-(Math.PI/2), new Vector3f(1,0,0));
		 hijo.setLocalRotation(q);
		 hijo.setModelBound(new BoundingBox());
		 hijo.setLocalScale(0.3f);
		 hijo.updateModelBound();		   	
		 hijo.lock();
		 campus.attachChild(hijo);
		 }
	}
	
	private void getPlayer(Node node)
	{
		Box player;
		
		player = new Box("TestBox", new Vector3f(0f,0f,0f), new Vector3f(10f,20f,10f));
		player.setLocalTranslation(new Vector3f(0f, 0f, 0f));
		player.setModelBound(new BoundingCapsule());
				
		player.updateModelBound();
        node.attachChild(player);	
	}
	
	public Node cargarModelo(String modelFile){
		Node			loadedModel	= null;
		FormatConverter		formatConverter = null;		
		ByteArrayOutputStream 	BO 		= new ByteArrayOutputStream();
		String			modelFormat 	= modelFile.substring(modelFile.lastIndexOf(".") + 1, modelFile.length());
		String			modelBinary	= modelFile.substring(0, modelFile.lastIndexOf(".") + 1) + "jbin";
		URL			modelURL	= ModelLoader.class.getClassLoader().getResource(modelBinary);
 
		//verify the presence of the jbin model
		if (modelURL == null){
 
			modelURL		= ModelLoader.class.getClassLoader().getResource(modelFile);
 
			//evaluate the format
			if (modelFormat.equals("3ds")){
				formatConverter = new MaxToJme();
			} else if (modelFormat.equals("md2")){
				formatConverter = new Md2ToJme();
			} else if (modelFormat.equals("md3")){
				formatConverter = new Md3ToJme();
			} else if (modelFormat.equals("ms3d")){
				formatConverter = new MilkToJme();
			} else if (modelFormat.equals("ase")){
				formatConverter = new AseToJme();
			} else if (modelFormat.equals("obj")){
				formatConverter = new ObjToJme();
			}
			formatConverter.setProperty("mtllib", modelURL);
 
			try {
				formatConverter.convert(modelURL.openStream(), BO);
				loadedModel = (Node) BinaryImporter.getInstance().load(new ByteArrayInputStream(BO.toByteArray()));
 
				//save the jbin format
			//	BinaryExporter.getInstance().save((Savable)loadedModel, new File(modelBinary));
			} catch (IOException e) {				
				e.printStackTrace();
				return null;
			}
		}else{
			try {
				//load the jbin format
				loadedModel = (Node) BinaryImporter.getInstance().load(modelURL.openStream());
			} catch (IOException e) {
				return null;
			}
		}
 
		return loadedModel;
	}  
}