/**
 * 
 */
package client.game.state;





import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import client.game.Game;
import client.game.PersonaDae;
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
import client.game.task.U3DRotateCharacterTaskFactory;
import client.game.view.U3DBuildingViewFactory;
import client.game.view.U3DPlayerViewFactory;
import client.game.view.U3dBuildingView;
import client.game.view.U3dPlayerView;
import client.game.view.ViewFactoryManager;
import client.gameEngine.InputManager;
import client.manager.EntityManager;
import client.manager.HudManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingCapsule;
import com.jme.bounding.BoundingSphere;
import com.jme.bounding.BoundingVolume;
import com.jme.bounding.OrientedBoundingBox;
import com.jme.image.Texture;
import com.jme.input.KeyInput;
import com.jme.math.LineSegment;
import com.jme.math.Matrix3f;
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.renderer.AbstractCamera;
import com.jme.renderer.Camera;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.renderer.jogl.JOGLCamera;
import com.jme.renderer.lwjgl.LWJGLCamera;
import com.jme.renderer.lwjgl.LWJGLRenderer;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;
import com.jme.scene.state.BlendState;
import com.jme.scene.state.ClipState;
import com.jme.scene.state.ColorMaskState;
import com.jme.scene.state.CullState;
import com.jme.scene.state.FogState;
import com.jme.scene.state.FragmentProgramState;
import com.jme.scene.state.LightState;
import com.jme.scene.state.MaterialState;
import com.jme.scene.state.RenderState;
import com.jme.scene.state.ShadeState;
import com.jme.scene.state.TextureState;
import com.jme.scene.state.VertexProgramState;
import com.jme.scene.state.WireframeState;
import com.jme.scene.state.ZBufferState;
import com.jme.scene.state.lwjgl.LWJGLBlendState;
import com.jme.scene.state.lwjgl.LWJGLClipState;
import com.jme.scene.state.lwjgl.LWJGLColorMaskState;
import com.jme.scene.state.lwjgl.LWJGLCullState;
import com.jme.scene.state.lwjgl.LWJGLFogState;
import com.jme.scene.state.lwjgl.LWJGLFragmentProgramState;
import com.jme.scene.state.lwjgl.LWJGLLightState;
import com.jme.scene.state.lwjgl.LWJGLMaterialState;
import com.jme.scene.state.lwjgl.LWJGLShadeState;
import com.jme.scene.state.lwjgl.LWJGLTextureState;
import com.jme.scene.state.lwjgl.LWJGLVertexProgramState;
import com.jme.scene.state.lwjgl.LWJGLWireframeState;
import com.jme.scene.state.lwjgl.LWJGLZBufferState;
import com.jme.system.DisplaySystem;
import com.jme.util.TextureManager;
import com.jme.util.export.Savable;
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

	private String url;
	
	private Vector3f initialPosition=null;
	
	private Quaternion Rotation=null;
	
	public XMLWorldBuilder(String urlxml){
		this.url = urlxml;
	}

	public void buildWorld(Node node) {

		EntityManagerFactory.getInstance().add(new U3DBuildingEntityFactory());
		EntityManagerFactory.getInstance().add(new U3DPlayerFactory());
		
		ViewFactoryManager.getInstance().add(new U3DBuildingViewFactory());
		ViewFactoryManager.getInstance().add(new U3DPlayerViewFactory());
		
		TaskManagerFactory.getInstance().add(new U3DMoveCharacterTaskFactory());
		TaskManagerFactory.getInstance().add(new U3DRotateCharacterTaskFactory());
		
		ControllerManagerFactory.getInstance().add(new U3DPlayerControllerFactory());
		
		//Edificio:1
        Node campus = new Node("Campus");
		getWorld(campus);	
		campus.setLocalScale(0.3f);
		U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.
			getInstance().createEntity("1");

		worldEntity.setId("world");
		U3dBuildingView worldView = (U3dBuildingView) ViewManager.getInstance().
			createView(worldEntity);

		//Player:2
		Node player = new Node("Player"); 
		PersonaDae p=getPlayer(player);
		U3DPlayer playerEntity = (U3DPlayer)EntityManager.getInstance().createEntity("2");
		playerEntity.initPlayer("player", Vector3f.ZERO.clone(), 8, new Hashtable<String,
				Object>(), new Hashtable<String,Object>(), Vector3f.ZERO.clone(), 
				Vector3f.ZERO.clone(), "ExteriorWorld", new Skin(), 
				new PlayerState());
		playerEntity.setDae(p);
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().
			createView(playerEntity);
		if (initialPosition!=null)
			playerView.setLocalTranslation(initialPosition);
		if(Rotation!=null)
			if(Rotation.w!=0 || Rotation.x!=0 || Rotation.y!=0 || Rotation.z!=0)
				playerView.setLocalRotation(Rotation);
		U3DPlayerController controllerPlayer = (U3DPlayerController) InputManager.
			getInstance().createController(playerEntity);
		controllerPlayer.setActive(true);
		worldView.attachChild(campus);
		KeyInput.get().addListener(controllerPlayer);
		playerView.attachChild(player);
		playerView.updateWorldBound();
		node.attachChild(worldView);
		node.attachChild(playerView);
		Skybox sb=setupSky();
		node.attachChild(sb);

	}
	
	private PersonaDae getPlayer(Node node) {
		PersonaDae p = new PersonaDae(node);
		p.setPaquete("jmetest/data/model/collada/");
		p.setPersonaje("jmetest/data/model/collada/man.dae");
		p.setAnimaciones("jmetest/data/model/collada/man_walk.dae");
		p.cargar();
		p.setModelBound(new BoundingBox());
		p.setLocalScale(0.8f);
		return p;
	}
	
    private Skybox setupSky() {
        Skybox sb = new Skybox( "cielo", 1200, 200, 1200 );
        try {
			ResourceLocatorTool.addResourceLocator(
			        ResourceLocatorTool.TYPE_TEXTURE,
			        new SimpleResourceLocator(Game.class.getClassLoader().getResource("cielo/")));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        sb.setTexture( Skybox.Face.North, TextureManager.loadTexture("cielo_1.jpg", Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor ) );
        sb.setTexture( Skybox.Face.West, TextureManager.loadTexture("cielo_4.jpg",  Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor ) );
        sb.setTexture( Skybox.Face.South, TextureManager.loadTexture("cielo_3.jpg", Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor  ) );
        sb.setTexture( Skybox.Face.East, TextureManager.loadTexture("cielo_2.jpg",  Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor  ) );
        sb.setTexture( Skybox.Face.Up, TextureManager.loadTexture("cielo_6.jpg",  Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor  ) );
        sb.setTexture( Skybox.Face.Down, TextureManager.loadTexture("cielo_5.jpg", Texture.MinificationFilter.NearestNeighborLinearMipMap, Texture.MagnificationFilter.NearestNeighbor  ) );
        sb.preloadTextures();
         
        sb.updateRenderState();
        return sb;
    }
    
    private void getWorld(Node nodeRoot){	
    	Node world = new Node("TestWorld");
    	URL filename=java.lang.ClassLoader.getSystemClassLoader().getSystemResource(url);			
		if (filename != null) {
			try {
				SAXBuilder builder = new SAXBuilder(false);
				//se carga el arbol xml en memoria
		        Document doc = builder.build(filename);
		        Element root = doc.getRootElement();
		        
		        int cant = 0;
		        String textures = null;
		        
		        List list=root.getChildren("texture");
				if(list!=null)
					for(Iterator i=list.iterator();i.hasNext(); ){
						Element e=(Element) i.next();
						Attribute a=e.getAttribute("text");
						textures = a.getValue();
						
						try {
							ResourceLocatorTool.addResourceLocator
							(ResourceLocatorTool.TYPE_TEXTURE, new SimpleResourceLocator(Game.class.getClassLoader().getResource(textures)));
						} catch (URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
		        
		        list=root.getChildren("cantPartes");
				if(list!=null)
					for(Iterator i=list.iterator();i.hasNext(); ){
						Element e=(Element) i.next();
						Attribute a=e.getAttribute("int");
						cant = Integer.valueOf(a.getValue());
				}
					        
		        for(Iterator<Element> i=root.getChildren("node").iterator();i.hasNext();){
		        	Element node=i.next();
		        	Attribute model=node.getAttribute("model");
	//        		HudManager.getInstance().getBarraProgreso().setMax(cant);		              	
		        	for(int k= 1; k<= cant;k++){
//		        		HudManager.getInstance().setProgreso(k);
		    			Node hijo = new Node("Hijo"+i);
		    			hijo=cargarModelo(textures + model.getValue()+"_parte"+k+".3ds");
		    			
		    			Quaternion q = hijo.getLocalRotation();
		    			q = q.fromAngleAxis((float)-Math.PI/2, new Vector3f(1,0,0));
		    			hijo.setLocalRotation(q);
		    		   	world.attachChild(hijo);		    			
		    		}  	
		        	Node hijo = new Node("Piso");
	    			hijo=cargarModelo(textures + model.getValue()+ "_Piso.3ds");
	    			Quaternion q = hijo.getLocalRotation();
	    			q = q.fromAngleAxis((float)-Math.PI/2, new Vector3f(1,0,0));
	    			hijo.setLocalRotation(q);
	    			world.attachChild(hijo);   			
	    			
		        	parseNode(world, node);
		        	world.setName("TestWorld");      	
		        	hijo.setModelBound(null);
		        	hijo.updateModelBound();
		        	
		        	BlendState as = DisplaySystem.getDisplaySystem().getRenderer().createBlendState();
		            as.setBlendEnabled(false);
		            as.setTestEnabled(true);
		            as.setTestFunction(BlendState.TestFunction.GreaterThan);
		            as.setReference(0.5f);
		            as.setEnabled(true);		                  
		            nodeRoot.setRenderState(as); 
		        	
		        	nodeRoot.attachChild(world);	        	
		        	
		        }
		  
			} catch (JDOMException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	
	private Node parseNode(Node child, Element node) throws DataConversionException {
		List list;
		Iterator i;
		Element e;
		Attribute a;		
		//cullMode
		list=node.getChildren("cullHint");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				if(param1.equals("Inherit"))
						child.setCullHint(Spatial.CullHint.Inherit);
				else if(param1.equals("Always"))
						child.setCullHint(Spatial.CullHint.Always);
				else if(param1.equals("Dynamic"))
						child.setCullHint(Spatial.CullHint.Dynamic);
				else if(param1.equals("Never"))
						child.setCullHint(Spatial.CullHint.Never);
			}
		list=node.getChildren("isCollidable");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				child.setIsCollidable(Boolean.valueOf(param1));
			}
		list=node.getChildren("lastFrustrumIntersection");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				if(param1.equals("Inside"))
					child.setLastFrustumIntersection(Camera.FrustumIntersect.Inside);
				else if(param1.equals("Intersects"))
					child.setLastFrustumIntersection(Camera.FrustumIntersect.Intersects);
				else if(param1.equals("Outside"))
					child.setLastFrustumIntersection(Camera.FrustumIntersect.Outside);
			}
		list=node.getChildren("lightCombineMode");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				if(param1.equals("CombineClosest"))
					child.setLightCombineMode(Spatial.LightCombineMode.CombineClosest);
				else if(param1.equals("CombineClosestEnabled"))
					child.setLightCombineMode(Spatial.LightCombineMode.CombineClosestEnabled);
				else if(param1.equals("CombineFirst"))
					child.setLightCombineMode(Spatial.LightCombineMode.CombineFirst);
				else if(param1.equals("Inherit"))
					child.setLightCombineMode(Spatial.LightCombineMode.Inherit);
				else if(param1.equals("Off"))
					child.setLightCombineMode(Spatial.LightCombineMode.Off);
				else if(param1.equals("Replace"))
					child.setLightCombineMode(Spatial.LightCombineMode.Replace);
			}
		list=node.getChildren("localRotationMatrix");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				Matrix3f param1=parseMatrix3f(e.getChildren("matrix3f"));
				child.setLocalRotation(param1);
			}
		list=node.getChildren("localRotationQuaternion");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				Rotation=parseQuaternion(e.getChildren("quaternion"));
				//Quaternion param1=parseQuaternion(e.getChildren("quaternion"));
				//child.setLocalRotation(param1);
			}
		list=node.getChildren("localScaleFloat");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				child.setLocalScale(Float.valueOf(param1));
			}
		list=node.getChildren("localScaleVector3f");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				Vector3f param1=parseVector3f(e.getChildren("vector3f"));
				child.setLocalScale(param1);
			}
		list=node.getChildren("localTranslationVector3f");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				initialPosition=parseVector3f(e.getChildren("vector3f"));
				child.setLocalTranslation(initialPosition);
			}
		list=node.getChildren("localTranslationFloat");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				child.setLocalTranslation(Float.valueOf(param1), Float.valueOf(param2), Float.valueOf(param3));
			}
		list=node.getChildren("locks");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				Renderer param2=null;
				param2=parseRenderer(e.getChildren("renderer"));
				if(param2==null)
					child.setLocks(Integer.valueOf(param1));
				else
					child.setLocks(Integer.valueOf(param1), param2);
			}
		list=node.getChildren("modelBound");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				BoundingVolume param1=parseBoundingVolume(e.getChildren("boundingVolume"));
				child.setModelBound(param1);
				child.updateModelBound();
			}
		list=node.getChildren("name");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				child.setName(param1);
			}
		list=node.getChildren("normalsMode");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				if(param1.equals("AlwaysNormalize"))
					child.setNormalsMode(Spatial.NormalsMode.AlwaysNormalize);
				else if(param1.equals("Inherit"))
					child.setNormalsMode(Spatial.NormalsMode.Inherit);
				else if(param1.equals("NormalizeIfScaled"))
					child.setNormalsMode(Spatial.NormalsMode.NormalizeIfScaled);
				else if(param1.equals("Off"))
					child.setNormalsMode(Spatial.NormalsMode.Off);
				else if(param1.equals("UseProvided"))
					child.setNormalsMode(Spatial.NormalsMode.UseProvided);
			}
		list=node.getChildren("renderQueueMode");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				child.setRenderQueueMode(Integer.valueOf(param1));
			}
		list=node.getChildren("renderState");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				RenderState param1=parseRenderState(e.getChildren("renderState"));
				child.setRenderState(param1);
			}
		list=node.getChildren("textureCombineMode");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				if(param1.equals("AlwaysNormalize"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.CombineClosest);
				else if(param1.equals("Inherit"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.CombineClosestEnabled);
				else if(param1.equals("NormalizeIfScaled"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.CombineFirst);
				else if(param1.equals("Off"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.Inherit);
				else if(param1.equals("UseProvided"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.Off);
				else if(param1.equals("UseProvided"))
					child.setTextureCombineMode(Spatial.TextureCombineMode.Replace);
			}
		list=node.getChildren("userData");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				Savable param2=parseSavable(e.getChildren("savable"));
				child.setUserData(param1, param2);
			}
		list=node.getChildren("zOrder");
		if(list!=null)
			for(i=list.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				String param2=null;
				List auxList=e.getChildren("param2");
				for(Iterator j=auxList.iterator(); j.hasNext(); ){
					Element auxElement=(Element) j.next();
					a=auxElement.getAttribute("param2");					
				}
				if(param2==null)
					child.setZOrder(Integer.valueOf(param1));
				else
					child.setZOrder(Integer.valueOf(param1), Boolean.valueOf(param2));
			}
		return child;
	}

	private Savable parseSavable(List children) {
		// TODO Auto-generated method stub
		return null;
	}

	private BoundingVolume parseBoundingVolume(List children) throws DataConversionException {
		List list;
		Iterator i;
		Element e, e2;
		Attribute a;		
		BoundingVolume bv=new BoundingBox();
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				list=e.getChildren("boundingBoxNull");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv=new BoundingBox();
					}
				list=e.getChildren("boundingBoxComposed");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						float f1=Float.valueOf(e2.getAttribute("param1").getValue());
						float f2=Float.valueOf(e2.getAttribute("param2").getValue());
						float f3=Float.valueOf(e2.getAttribute("param3").getValue());
						Vector3f v=parseVector3f(e2.getChildren("vector3f"));
						bv=new BoundingBox(v, f1, f2, f3);		
					}
				list=e.getChildren("boundingCapsuleNull");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv=new BoundingCapsule();
					}
				list=e.getChildren("boundingCapsuleComposed");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						float f1=Float.valueOf(e2.getAttribute("param1").getValue());
						Vector3f v=parseVector3f(e2.getChildren("vector3f"));
						LineSegment l=parseLineSegment(e2.getChildren("lineSegment"));
						bv=new BoundingCapsule(v, l, f1);			
					}
				list=e.getChildren("boundingSphereNull");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv=new BoundingSphere();			
					}
				list=e.getChildren("boundingSphereComposed");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						float f1=Float.valueOf(e2.getAttribute("param1").getValue());
						Vector3f v=parseVector3f(e2.getChildren("vector3f"));
						bv=new BoundingSphere(f1, v);			
					}
				list=e.getChildren("boundingOrientedBoxNull");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv=new OrientedBoundingBox();			
					}
				list=e.getChildren("checkPlane");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv.setCheckPlane(Integer.valueOf(e2.getAttribute("param1").getValue()));
					}
				list=e.getChildren("center");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						bv.setCenter(parseVector3f(e2.getChildren("vector3f")));
					}
			}
		return bv;
	}

	private LineSegment parseLineSegment(List children) {
		List list;
		Iterator i;
		Element e, e2;
		Attribute a;		
		LineSegment ls=new LineSegment();
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				list=e.getChildren("lineSegmentNull");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
					}
				list=e.getChildren("lineSegmentComposed");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						float f1=Float.valueOf(e2.getAttribute("param1").getValue());
						Vector3f v1=parseVector3f(e2.getChildren("vector3f1"));
						Vector3f v2=parseVector3f(e2.getChildren("vector3f2"));
						ls=new LineSegment(v1, v2, f1);		
					}
				list=e.getChildren("direction");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						ls.setDirection(parseVector3f(e2.getChildren("vector3f")));		
					}
				list=e.getChildren("extent");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						ls.setExtent(Float.valueOf(e2.getAttribute("param1").getValue()));		
					}
				list=e.getChildren("origin");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						ls.setOrigin(parseVector3f(e2.getChildren("vector3f")));		
					}
			}
		return ls;
	}
	private Renderer parseRenderer(List children) {
		List list;
		Iterator i;
		Element e, e2;
		Attribute a;		
		LWJGLRenderer lw=null;
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				int param1=Integer.valueOf(e.getAttribute("param1").getValue());
				int param2=Integer.valueOf(e.getAttribute("param2").getValue());
				lw=new LWJGLRenderer(param1, param2);
				
				list=e.getChildren("backgroundColor");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						float f1=Float.valueOf(e2.getAttribute("param1").getValue());
						float f2=Float.valueOf(e2.getAttribute("param2").getValue());
						float f3=Float.valueOf(e2.getAttribute("param3").getValue());
						float f4=Float.valueOf(e2.getAttribute("param4").getValue());
						lw.setBackgroundColor(new ColorRGBA(f1, f2, f3, f4));
					}
				lw.setCamera(parseCamera(e.getChildren("camera")));
				list=e.getChildren("headless");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
						e2=(Element) i.next();
						lw.setHeadless(Boolean.valueOf(e2.getAttribute("param1").getValue()));		
					}
				list=e.getChildren("ortho");
				if(list!=null)
					lw.setOrtho();
				list=e.getChildren("orthoCenter");
				if(list!=null)
					if(!lw.isInOrthoMode())
						lw.setOrthoCenter();
				list=e.getChildren("polygonOffset");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					float f1=Float.valueOf(e2.getAttribute("param1").getValue());
					float f2=Float.valueOf(e2.getAttribute("param2").getValue());
					lw.setPolygonOffset(f1, f2);
				}
			}
		return lw;
	}


	private Camera parseCamera(List children) {
		List list;
		Iterator i;
		Element e,e2;
		Attribute a;
		AbstractCamera lw=new LWJGLCamera();
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				String param1=e.getAttribute("param1").getValue();
				if(param1.equals("JOGLCamera"))
					lw=new JOGLCamera();
				list=e.getChildren("axesQuaternion");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setAxes(parseQuaternion(e2.getChildren("quaternion")));
					}
				list=e.getChildren("axesVector");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					Vector3f v1=parseVector3f(e2.getChildren("vector3f1"));
					Vector3f v2=parseVector3f(e2.getChildren("vector3f2"));
					Vector3f v3=parseVector3f(e2.getChildren("vector3f3"));
					lw.setAxes(v1, v2,v3);	
					}
				list=e.getChildren("dataOnly");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					a=e2.getAttribute("param1");
					lw.setDataOnly(Boolean.valueOf(e2.getAttribute("param1").getValue()));
					}
				
				list=e.getChildren("direction");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setDirection(parseVector3f(e2.getChildren("vector3f")));
					}
				list=e.getChildren("frameQuaternion");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					Vector3f v1=parseVector3f(e2.getChildren("vector3f"));
					Quaternion q1=parseQuaternion(e2.getChildren("quaternion"));
					lw.setFrame(v1, q1);
					}
				list=e.getChildren("frameVector");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					Vector3f v1=parseVector3f(e2.getChildren("vector3f1"));
					Vector3f v2=parseVector3f(e2.getChildren("vector3f2"));
					Vector3f v3=parseVector3f(e2.getChildren("vector3f3"));
					Vector3f v4=parseVector3f(e2.getChildren("vector3f4"));
					lw.setFrame(v1, v2,v3, v4);	
					}
				list=e.getChildren("frustum");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					float f1=Float.valueOf(e2.getAttribute("param1").getValue());
					float f2=Float.valueOf(e2.getAttribute("param2").getValue());
					float f3=Float.valueOf(e2.getAttribute("param3").getValue());
					float f4=Float.valueOf(e2.getAttribute("param4").getValue());
					float f5=Float.valueOf(e2.getAttribute("param5").getValue());
					float f6=Float.valueOf(e2.getAttribute("param6").getValue());
					lw.setFrustum(f1, f2, f3, f4, f5, f6);
				}
				list=e.getChildren("frustumBottom");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setFrustumBottom(Float.valueOf(e2.getAttribute("param1").getValue()));
					
				}
				list=e.getChildren("frustumFar");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setFrustumFar(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("frustumNear");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setFrustumNear(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("frustumPerspective");
				if(list!=null)	
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					float f1=Float.valueOf(e2.getAttribute("param1").getValue());
					float f2=Float.valueOf(e2.getAttribute("param2").getValue());
					float f3=Float.valueOf(e2.getAttribute("param3").getValue());
					float f4=Float.valueOf(e2.getAttribute("param4").getValue());
					lw.setFrustumPerspective(f1, f2, f3, f4);
				}
				list=e.getChildren("frustumRight");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setFrustumRight(Float.valueOf(e2.getAttribute("param1").getValue()));

				}
				list=e.getChildren("frustumTop");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setFrustumTop(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("left");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setLeft(parseVector3f(e2.getChildren("vector3f")));
				}
				list=e.getChildren("location");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setLocation(parseVector3f(e2.getChildren("vector3f")));
				}
				list=e.getChildren("parallelProjection");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setParallelProjection(Boolean.valueOf(e2.getAttribute("param1").getValue()));

				}
				list=e.getChildren("planeState");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setPlaneState(Integer.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("up");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setUp(parseVector3f(e2.getChildren("vector3f")));
				}
				list=e.getChildren("viewPort");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					float f1=Float.valueOf(e2.getAttribute("param1").getValue());
					float f2=Float.valueOf(e2.getAttribute("param2").getValue());
					float f3=Float.valueOf(e2.getAttribute("param3").getValue());
					float f4=Float.valueOf(e2.getAttribute("param4").getValue());
					lw.setViewPort(f1, f2, f3, f4);
				}
				list=e.getChildren("viewPortBottom");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setViewPortBottom(Float.valueOf(e2.getAttribute("param1").getValue()));
				}		
				list=e.getChildren("viewPortLeft");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setViewPortLeft(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("viewPortRigh");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setViewPortRight(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
				list=e.getChildren("viewPortTop");
				if(list!=null)
					for(i=list.iterator();i.hasNext(); ){
					e2=(Element) i.next();
					lw.setViewPortTop(Float.valueOf(e2.getAttribute("param1").getValue()));
				}
			}
		return lw;	
	}
//Hecho por Carlitos
	private RenderState parseRenderState(List children) {
		List lis, lis2;
		Iterator i, it2, it3;
		Element e, elem2;
		Attribute a;	
		if(children!=null){
			i=children.iterator();
			if (i.hasNext()){
				e=(Element) i.next();
				lis=e.getChildren("blendState");
				if(lis!=null){
					BlendState bs = new LWJGLBlendState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("blendEnable");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setBlendEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("blendEquation");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Add"))
									bs.setBlendEquation(BlendState.BlendEquation.Add);
								else if(param1.equals("Max"))
									bs.setBlendEquation(BlendState.BlendEquation.Max);
								else if(param1.equals("Min"))
									bs.setBlendEquation(BlendState.BlendEquation.Min);
								else if(param1.equals("ReverseSubtract"))
									bs.setBlendEquation(BlendState.BlendEquation.ReverseSubtract);
								else if(param1.equals("Subtract"))
									bs.setBlendEquation(BlendState.BlendEquation.Subtract);
							}			
						lis2=elem2.getChildren("blendEquationAlpha");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Add"))
									bs.setBlendEquationAlpha(BlendState.BlendEquation.Add);
								else if(param1.equals("Max"))
									bs.setBlendEquationAlpha(BlendState.BlendEquation.Max);
								else if(param1.equals("Min"))
									bs.setBlendEquationAlpha(BlendState.BlendEquation.Min);
								else if(param1.equals("ReverseSubtract"))
									bs.setBlendEquationAlpha(BlendState.BlendEquation.ReverseSubtract);
								else if(param1.equals("Subtract"))
									bs.setBlendEquationAlpha(BlendState.BlendEquation.Subtract);
							}			
						lis2=elem2.getChildren("blendEquationRGB");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Add"))
									bs.setBlendEquationRGB(BlendState.BlendEquation.Add);
								else if(param1.equals("Max"))
									bs.setBlendEquationRGB(BlendState.BlendEquation.Max);
								else if(param1.equals("Min"))
									bs.setBlendEquationRGB(BlendState.BlendEquation.Min);
								else if(param1.equals("ReverseSubtract"))
									bs.setBlendEquationRGB(BlendState.BlendEquation.ReverseSubtract);
								else if(param1.equals("Subtract"))
									bs.setBlendEquationRGB(BlendState.BlendEquation.Subtract);
							}			
						lis2=elem2.getChildren("constantColor");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								bs.setConstantColor(param1);
							}
						lis2=elem2.getChildren("destinationFunction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setDestinationFunction(BlendState.DestinationFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.DestinationAlpha);
								else if(param1.equals("One"))
									bs.setDestinationFunction(BlendState.DestinationFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setDestinationFunction(BlendState.DestinationFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceAlpha);
								else if(param1.equals("OneMinusSourceColor"))
									bs.setDestinationFunction(BlendState.DestinationFunction.OneMinusSourceColor);
								else if(param1.equals("SourceAlpha"))
									bs.setDestinationFunction(BlendState.DestinationFunction.SourceAlpha);
								else if(param1.equals("SourceColor"))
									bs.setDestinationFunction(BlendState.DestinationFunction.SourceColor);
								else if(param1.equals("Zero"))
									bs.setDestinationFunction(BlendState.DestinationFunction.Zero);
							}			
						lis2=elem2.getChildren("destinationFunctionAlpha");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.DestinationAlpha);
								else if(param1.equals("One"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusSourceAlpha);
								else if(param1.equals("OneMinusSourceColor"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.OneMinusSourceColor);
								else if(param1.equals("SourceAlpha"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.SourceAlpha);
								else if(param1.equals("SourceColor"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.SourceColor);
								else if(param1.equals("Zero"))
									bs.setDestinationFunctionAlpha(BlendState.DestinationFunction.Zero);
							}			
						lis2=elem2.getChildren("destinationFunctionRGB");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.DestinationAlpha);
								else if(param1.equals("One"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.OneMinusSourceAlpha);
								else if(param1.equals("OneMinusSourceColor"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.OneMinusSourceColor);
								else if(param1.equals("SourceAlpha"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.SourceAlpha);
								else if(param1.equals("SourceColor"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.SourceColor);
								else if(param1.equals("Zero"))
									bs.setDestinationFunctionRGB(BlendState.DestinationFunction.Zero);
							}		
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("reference");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setReference(Float.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("sourceFunction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setSourceFunction(BlendState.SourceFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.DestinationAlpha);
								else if(param1.equals("DestinationColor"))
									bs.setSourceFunction(BlendState.SourceFunction.DestinationColor);
								else if(param1.equals("One"))
									bs.setSourceFunction(BlendState.SourceFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setSourceFunction(BlendState.SourceFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.OneMinusSourceAlpha);
								else if(param1.equals("SourceAlpha"))
									bs.setSourceFunction(BlendState.SourceFunction.SourceAlpha);
								else if(param1.equals("SourceAlphaSaturate"))
									bs.setSourceFunction(BlendState.SourceFunction.SourceAlphaSaturate);
								else if(param1.equals("Zero"))
									bs.setSourceFunction(BlendState.SourceFunction.Zero);
							}			
						lis2=elem2.getChildren("sourceFunctionAlpha");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.DestinationAlpha);
								else if(param1.equals("DestinationColor"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.DestinationColor);
								else if(param1.equals("One"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.OneMinusSourceAlpha);
								else if(param1.equals("SourceAlpha"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.SourceAlpha);
								else if(param1.equals("SourceAlphaSaturate"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.SourceAlphaSaturate);
								else if(param1.equals("Zero"))
									bs.setSourceFunctionAlpha(BlendState.SourceFunction.Zero);
							}			
						lis2=elem2.getChildren("sourceFunctionRGB");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ConstantAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.ConstantAlpha);
								else if(param1.equals("ConstantColor"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.ConstantColor);
								else if(param1.equals("DestinationAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.DestinationAlpha);
								else if(param1.equals("DestinationColor"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.DestinationColor);
								else if(param1.equals("One"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.One);
								else if(param1.equals("OneMinusConstantAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.OneMinusConstantAlpha);
								else if(param1.equals("OneMinusConstantColor"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.OneMinusConstantColor);
								else if(param1.equals("OneMinusDestinationAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.OneMinusDestinationAlpha);
								else if(param1.equals("OneMinusSourceAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.OneMinusSourceAlpha);
								else if(param1.equals("SourceAlpha"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.SourceAlpha);
								else if(param1.equals("SourceAlphaSaturate"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.SourceAlphaSaturate);
								else if(param1.equals("Zero"))
									bs.setSourceFunctionRGB(BlendState.SourceFunction.Zero);
							}			
						lis2=elem2.getChildren("testEnabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setTestEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("testFunction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Always"))
									bs.setTestFunction(BlendState.TestFunction.Always);
								else if(param1.equals("EqualTo"))
									bs.setTestFunction(BlendState.TestFunction.EqualTo);
								else if(param1.equals("GreaterThan"))
									bs.setTestFunction(BlendState.TestFunction.GreaterThan);
								else if(param1.equals("GreaterThanOrEqualTo"))
									bs.setTestFunction(BlendState.TestFunction.GreaterThanOrEqualTo);
								else if(param1.equals("LessThan"))
									bs.setTestFunction(BlendState.TestFunction.LessThan);
								else if(param1.equals("LessThanOrEqualTo"))
									bs.setTestFunction(BlendState.TestFunction.LessThanOrEqualTo);
								else if(param1.equals("Never"))
									bs.setTestFunction(BlendState.TestFunction.Never);
								else if(param1.equals("NotEqualTo"))
									bs.setTestFunction(BlendState.TestFunction.NotEqualTo);								
							}			
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								bs.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return bs;
					}
				}
				lis=e.getChildren("clipState");
				if(lis!=null){
					ClipState cs = new LWJGLClipState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("clipPlaneEquation");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param2");
								String param2=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param3");
								String param3=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param4");
								String param4=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param5");
								String param5=a.getValue();
                                cs.setClipPlaneEquation(Integer.valueOf(param1),Double.valueOf(param2),Double.valueOf(param3),Double.valueOf(param4),Double.valueOf(param5));
							}
						}
						lis2=elem2.getChildren("enableClipPlane");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param2");
								String param2=a.getValue();
                                cs.setEnableClipPlane(Integer.valueOf(param1),Boolean.valueOf(param2));
							}
						}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cs.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cs.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("planeEq");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param2");
								String param2=a.getValue();
								e=(Element) it3.next();
								a=e.getAttribute("param3");
								String param3=a.getValue();
								cs.setPlaneEq(Integer.valueOf(param1),Integer.valueOf(param2),Double.valueOf(param3));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cs.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return cs;
					}
				}
				lis=e.getChildren("colorMaskState");
				if(lis!=null){
					ColorMaskState cms = new LWJGLColorMaskState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("all");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
                                cms.setAll(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("alpha");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setAlpha(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("blue");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setBlue(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("green");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setGreen(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("red");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setRed(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cms.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return cms;
					}
				}
				lis=e.getChildren("cullState");
				if(lis!=null){
					CullState cus = new LWJGLCullState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
				      	lis2=elem2.getChildren("cullFace");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Back"))
									cus.setCullFace(CullState.Face.Back);
								else if(param1.equals("Front"))
									cus.setCullFace(CullState.Face.Front);
								else if(param1.equals("FrontAndBack"))
									cus.setCullFace(CullState.Face.FrontAndBack);
								else if(param1.equals("None"))
									cus.setCullFace(CullState.Face.None);
							}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cus.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								cus.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("polygonWind");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("ClockWise"))
									cus.setPolygonWind(CullState.PolygonWind.ClockWise);
								else if(param1.equals("CounterClockWise"))
									cus.setPolygonWind(CullState.PolygonWind.CounterClockWise);
							}
						return cus;
					}
				}
				lis=e.getChildren("fogState");
				if(lis!=null){
					FogState fs = new LWJGLFogState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("color");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								fs.setColor(param1);
							}
						}
						lis2=elem2.getChildren("density");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fs.setDensity(Float.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("densityFunction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Exponential"))
									fs.setDensityFunction(FogState.DensityFunction.Exponential);
								else if(param1.equals("ExponentialSquared"))
									fs.setDensityFunction(FogState.DensityFunction.ExponentialSquared);
								else if(param1.equals("Linear"))
									fs.setDensityFunction(FogState.DensityFunction.Linear);
							}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fs.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("end");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fs.setEnd(Float.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fs.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quality");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("PerPixel"))
									fs.setQuality(FogState.Quality.PerPixel);
								else if(param1.equals("PerVertex"))
									fs.setQuality(FogState.Quality.PerVertex);
							}
						lis2=elem2.getChildren("source");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Depth"))
									fs.setSource(FogState.CoordinateSource.Depth);
								else if(param1.equals("FogCoords"))
									fs.setSource(FogState.CoordinateSource.FogCoords);
							}						
						lis2=elem2.getChildren("start");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fs.setStart(Float.valueOf(param1));
							}
						}
						return fs;
					}
				}
				lis=e.getChildren("FragmentProgramState");
				if(lis!=null){
                    FragmentProgramState fps= new LWJGLFragmentProgramState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fps.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fps.setNeedsRefresh(Boolean.valueOf(param1));
								
							}
						}
						lis2=elem2.getChildren("parameter");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								List list3=e.getChildren("arreFloat");
								float[] arreFloat = parseArreFloat(list3);
								List list4=e.getChildren("param2");
								String param2="";
								for(Iterator it4=list4.iterator();it4.hasNext(); ){
									Element e2=(Element) it4.next();
									Attribute a2=e2.getAttribute("param");
									param2=a2.getValue();
								}
								fps.setParameter(arreFloat, Integer.valueOf(param2));
							}	
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								fps.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return fps;
					}
				}
				lis=e.getChildren("lightState");
				if(lis!=null){
					LightState ls = new LWJGLLightState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("globalAmbient");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								ls.setGlobalAmbient(param1);
							}
						}
						lis2=elem2.getChildren("lightMask");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setLightMask(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("localViewer");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setLocalViewer(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("separateSpecular");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setSeparateSpecular(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("twoSidedLighting");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setTwoSidedLighting(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ls.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return ls;
					}
				}
				lis=e.getChildren("materialState");
				if(lis!=null){
					MaterialState ms = new LWJGLMaterialState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
				      	lis2=elem2.getChildren("ambient");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								ms.setAmbient(param1);
							}
						}
						lis2=elem2.getChildren("colorMaterial");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Ambient"))
									ms.setColorMaterial(MaterialState.ColorMaterial.Ambient);
								else if(param1.equals("AmbientAndDiffuse"))
									ms.setColorMaterial(MaterialState.ColorMaterial.AmbientAndDiffuse);
								else if(param1.equals("Diffuse"))
									ms.setColorMaterial(MaterialState.ColorMaterial.Diffuse);
								else if(param1.equals("Emissive"))
									ms.setColorMaterial(MaterialState.ColorMaterial.Emissive);
								else if(param1.equals("None"))
									ms.setColorMaterial(MaterialState.ColorMaterial.None);
								else if(param1.equals("Specular"))
									ms.setColorMaterial(MaterialState.ColorMaterial.Specular);
							}						
				      	lis2=elem2.getChildren("diffuse");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								ms.setDiffuse(param1);
							}
						}
				      	lis2=elem2.getChildren("emissive");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								ms.setEmissive(param1);
							}
						}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ms.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("materialFace");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Ambient"))
									ms.setMaterialFace(MaterialState.MaterialFace.Back);
								else if(param1.equals("AmbientAndDiffuse"))
									ms.setMaterialFace(MaterialState.MaterialFace.Front);
								else if(param1.equals("Diffuse"))
									ms.setMaterialFace(MaterialState.MaterialFace.FrontAndBack);
							}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ms.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("shininess");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ms.setShininess(Float.valueOf(param1));
							}
						}
				      	lis2=elem2.getChildren("specular");
						if(lis2!=null){
							for(i=lis2.iterator();i.hasNext(); ){
								e=(Element) i.next();
								ColorRGBA param1=parseColor(e.getChildren("colorRGBA"));
								ms.setSpecular(param1);
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ms.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return ms;
					}
				}
				lis=e.getChildren("shadeState");
				if(lis!=null){
					ShadeState ss= new LWJGLShadeState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ss.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ss.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("shadeMode");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Flat"))
									ss.setShadeMode(ShadeState.ShadeMode.Flat);
								else if(param1.equals("Smooth"))
									ss.setShadeMode(ShadeState.ShadeMode.Smooth);
							}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ss.setQuickCompares(Boolean.valueOf(param1));
							}
						}

						return ss;
					}
				}
				/* proximamente
				lis=e.getChildren("stencilState");
				if(lis!=null){
					 StencilState sts= new LWJGLStencilState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilFunc");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilFuncMask");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilFuncMask(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilMask");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilMask(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilOpFail");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilOpFail(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilOpZFail");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilOpZFail(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilOpZPass");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilOpZPass(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilRef");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilRef(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("stencilWriteMask");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setStencilWriteMask(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								sts.setQuickCompares(Boolean.valueOf(param1));
							}
						}

						return sts;
					}
				}*/
				lis=e.getChildren("textureState");
				if(lis!=null){
					TextureState ts = new LWJGLTextureState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
				      	lis2=elem2.getChildren("correction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Affine"))
									ts.setCorrectionType(TextureState.CorrectionType.Affine);
								else if(param1.equals("Perspective"))
									ts.setCorrectionType(TextureState.CorrectionType.Perspective);
							}
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ts.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ts.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
	//	setTexture(texture);
      //    ts1.setTexture(texture, ent);
						lis2=elem2.getChildren("textureCoordinateOffset");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ts.setTextureCoordinateOffset(Integer.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ts.setQuickCompares(Boolean.valueOf(param1));
							}
						}

						return ts;
					}
				}
				lis=e.getChildren("vertexProgramState");
				if(lis!=null){
					VertexProgramState vs = new LWJGLVertexProgramState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								vs.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								vs.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("parameter");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								List list3=e.getChildren("arreFloat");
								float[] arreFloat = parseArreFloat(list3);
								List list4=e.getChildren("param2");
								String param2="";
								for(Iterator it4=list4.iterator();it4.hasNext(); ){
									Element e2=(Element) it4.next();
									Attribute a2=e2.getAttribute("param");
									param2=a2.getValue();
								}
								vs.setParameter(arreFloat, Integer.valueOf(param2));
							}	
						}
						lis2=elem2.getChildren("envParameter");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								List list3=e.getChildren("arreFloat");
								float[] arreFloat = parseArreFloat(list3);
								List list4=e.getChildren("param2");
								String param2="";
								for(Iterator it4=list4.iterator();it4.hasNext(); ){
									Element e2=(Element) it4.next();
									Attribute a2=e2.getAttribute("param");
									param2=a2.getValue();
								}
								vs.setEnvParameter(arreFloat, Integer.valueOf(param2));
							}	
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								vs.setQuickCompares(Boolean.valueOf(param1));
							}
						}

						return vs;
					}
				}
				lis=e.getChildren("wireframeState");
				if(lis!=null){
					WireframeState  ws = new LWJGLWireframeState ();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("antialiased");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ws.setAntialiased(Boolean.valueOf(param1));
							}
						}
				      	lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ws.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("face");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Back"))
									ws.setFace(WireframeState.Face.Back);
								else if(param1.equals("Front"))
									ws.setFace(WireframeState.Face.Front);
								else if(param1.equals("FrontAndBack"))
									ws.setFace(WireframeState.Face.FrontAndBack);
							}
						lis2=elem2.getChildren("lineWidth");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ws.setLineWidth(Float.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ws.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								ws.setQuickCompares(Boolean.valueOf(param1));
							}
						}

						return ws;
					}
				}
				lis=e.getChildren("zBufferState");
				if(lis!=null){
					ZBufferState   zs = new LWJGLZBufferState();
					it2=lis.iterator();
					if(it2.hasNext()){
				      	elem2=(Element)it2.next();
						lis2=elem2.getChildren("enabled");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								zs.setEnabled(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("testFunction");
						if(lis2!=null)
							for(Iterator i2=lis2.iterator();i.hasNext(); ){
								e=(Element) i2.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								if(param1.equals("Always"))
									zs.setFunction(ZBufferState.TestFunction.Always);
								else if(param1.equals("EqualTo"))
									zs.setFunction(ZBufferState.TestFunction.EqualTo);
								else if(param1.equals("GreaterThan"))
									zs.setFunction(ZBufferState.TestFunction.GreaterThan);
								else if(param1.equals("GreaterThanOrEqualTo"))
									zs.setFunction(ZBufferState.TestFunction.GreaterThanOrEqualTo);
								else if(param1.equals("LessThan"))
									zs.setFunction(ZBufferState.TestFunction.LessThan);
								else if(param1.equals("LessThanOrEqualTo"))
									zs.setFunction(ZBufferState.TestFunction.LessThanOrEqualTo);
								else if(param1.equals("Never"))
									zs.setFunction(ZBufferState.TestFunction.Never);
								else if(param1.equals("NotEqualTo"))
									zs.setFunction(ZBufferState.TestFunction.NotEqualTo);
							}
						lis2=elem2.getChildren("needsRefresh");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								zs.setNeedsRefresh(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("writable");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								zs.setWritable(Boolean.valueOf(param1));
							}
						}
						lis2=elem2.getChildren("quickCompares");  
						if(lis2!=null){
							for(it3=lis2.iterator();it3.hasNext(); ){
								e=(Element) it3.next();
								a=e.getAttribute("param1");
								String param1=a.getValue();
								zs.setQuickCompares(Boolean.valueOf(param1));
							}
						}
						return zs;
					}
				}
			}	
		}
		return null;
	}

//Hecho por Carlitos
	private ColorRGBA parseColor(List children) {
		Iterator i;
		Element e;
		Attribute a;
		ColorRGBA color=null;
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				a=e.getAttribute("param4");
				String param4=a.getValue();
				color= new ColorRGBA(Float.valueOf(param1), Float.valueOf(param2), Float.valueOf(param3),Float.valueOf(param4));
			}
		return color;
	}
//Hecho por Carlitos
	private float[] parseArreFloat(List children){
		Iterator i;
		Element e;
		Attribute a;		
        float [] arr=new float[4];
		if(children!=null){
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				a=e.getAttribute("param4");
				String param4=a.getValue();
				arr[0]=Float.valueOf(param1);
				arr[1]=Float.valueOf(param2);
				arr[2]=Float.valueOf(param3);
				arr[4]=Float.valueOf(param4);
			}
		}	
		return arr;
	}
//Hecho por Carlitos
	private Vector3f parseVector3f(List children) {
		Iterator i;
		Element e;
		Attribute a;
		Vector3f v3f=null;
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				v3f= new Vector3f(Float.valueOf(param1), Float.valueOf(param2), Float.valueOf(param3));
			}
		return v3f;
	}
//Hecho por Carlitos
	private Quaternion parseQuaternion(List children) {
		Iterator i;
		Element e;
		Attribute a;	
		Quaternion q=null;
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				a=e.getAttribute("param4");
                        String param4=a.getValue();
				q= new Quaternion(Float.valueOf(param1), Float.valueOf(param2), Float.valueOf(param3), Float.valueOf(param4));
			}
		return q;
	}
//Hecho por Carlitos
	private Matrix3f parseMatrix3f(List children) {
		Iterator i;
		Element e;
		Attribute a;	
		Matrix3f m3f=null;
		if(children!=null)
			for(i=children.iterator();i.hasNext(); ){
				e=(Element) i.next();
				a=e.getAttribute("param1");
				String param1=a.getValue();
				a=e.getAttribute("param2");
				String param2=a.getValue();
				a=e.getAttribute("param3");
				String param3=a.getValue();
				a=e.getAttribute("param4");
                        String param4=a.getValue();
				a=e.getAttribute("param5");
                        String param5=a.getValue();
				a=e.getAttribute("param6");
                        String param6=a.getValue();
				a=e.getAttribute("param7");
                        String param7=a.getValue();
				a=e.getAttribute("param8");
                        String param8=a.getValue();
				a=e.getAttribute("param9");
                        String param9=a.getValue();
				m3f= new Matrix3f(Float.valueOf(param1), Float.valueOf(param2), Float.valueOf(param3), Float.valueOf(param4), Float.valueOf(param5), Float.valueOf(param6), Float.valueOf(param7), Float.valueOf(param8), Float.valueOf(param9));
			}
		return m3f;
	}

}