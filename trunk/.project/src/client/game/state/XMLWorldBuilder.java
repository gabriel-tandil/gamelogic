package client.game.state;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

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
import com.jme.input.KeyInput;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.shape.Box;
import com.jme.util.export.binary.BinaryImporter;
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


public class XMLWorldBuilder {

	
	public void buildWorld(Node scene){
		// del 1 al 63 es samp
    	// del 64 a 174 es aulas comunes 3  ----- 174 es arriba de las ventanas, esta mal la textura
		// 175 y 176 es la torre q esta entre ac3 y ac1
        // 177 a 267 es aulas comunes 1
        // 268 a  276 es el lugar donde estan los servidores de la facultad
        // 278 a 331 edificio de fisica(ifas creo)
        //332 a 385 Isistan
        // 386 a 410 pladema
        // 411 a 430 el buffet
        // 431 a 445 edificio al lado de AC1
        // 446 a 472 edificio de veterinarias
        // 473 a 533 otro edifico de veterinarias
        // 534 a 568 otro mas de veterinarias
        // 535 a 620 otro de veterinarias
        // 621 a 671 edifico de fisica
        // 672 a 738   ifimat
        // 739 a 785 ifas
        // 786 a 873 exactas
        // 874 a 941 boxes de investigacion
        //942 a 995  gimnasio
        // 996 a 1087 economicas
		
		EntityManagerFactory.getInstance().add(new U3DBuildingEntityFactory());
		ViewFactoryManager.getInstance().add(new U3DBuildingViewFactory());
		
		Node campus = new Node("Campus");
		for(int i= 1; i<= 1087;i++){	
        	Node hijo = new Node("Hijo"+i);
        	hijo=cargarModelo("protCampus/data/campus_parte"+i+".3ds");
			Quaternion q = hijo.getLocalRotation();
			q = q.fromAngleAxis((float)-Math.PI/2, new Vector3f(1,0,0));
			hijo.setLocalRotation(q);
		    hijo.setModelBound(new BoundingBox());
        	hijo.updateModelBound();
		   	hijo.setLocalScale(0.3f);
    	    hijo.lock();
            campus.attachChild(hijo);
       	}
    	
    	U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.
		getInstance().createEntity("1");
		worldEntity.setId("world");
		U3dBuildingView worldView = (U3dBuildingView) ViewManager.getInstance().
		createView(worldEntity);
		worldView.attachChild(campus);
		worldView.setLocalTranslation(1, 0, 10);		
		//System.out.println("Mundo " +worldView.getLocalTranslation());
		//System.out.println("Yo " +playerView.getLocalTranslation());
    	scene.attachChild(worldView);	
	}
	
	
	public Node cargarModelo(String modelFile){
		Node loadedModel = null;
		FormatConverter	formatConverter = null;		
		ByteArrayOutputStream BO = new ByteArrayOutputStream();
		String modelFormat	= modelFile.substring(modelFile.lastIndexOf(".") + 1, modelFile.length());
		String modelBinary	= modelFile.substring(0, modelFile.lastIndexOf(".") + 1) + "jbin";
		URL	modelURL = ModelLoader.class.getClassLoader().getResource(modelBinary);
 
		//verify the presence of the jbin model
		if (modelURL == null){
 
			modelURL = ModelLoader.class.getClassLoader().getResource(modelFile);
 
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
