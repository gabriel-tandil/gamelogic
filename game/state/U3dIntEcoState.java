package client.game.state;

import client.game.entity.U3DBuildingEntity;
import client.game.input.U3DChaseCamera;
import client.game.view.DynamicView;
import client.game.view.U3dBuildingView;
import client.game.view.U3dPlayerView;
import client.manager.EntityManager;
import client.manager.HudManager;
import client.manager.ViewManager;

import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;


public class U3dIntEcoState extends U3dState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private U3DChaseCamera chaser;
	
	private String url;
	
	private Vector3f translation;
	
	private U3dBuildingView worldView;
	

	public U3dIntEcoState(String name, String url) {
		super(name);
		this.url = url;
		translation = new Vector3f();
	}
	
	public void initialize() {
	//	if (!this.initialized) {
			
			actualState = getName();
		
			this.initializeWorld();
			this.initializeLight();
			//this.initializeCamera((U3dPlayerView) this.rootNode.getChild("player_View"));

			
			this.builder.getTranslationPoint(translation);
			this.initialized = true;
			
			//KeyBindingManager.getKeyBindingManager().set("change",
			//		KeyInput.KEY_L);
			
			inicializaHUD();
			rootNode.updateGeometricState(0.0f, true);
			rootNode.updateRenderState();
		//}
	}
	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
		HudManager.getInstance().update();
	}
	private void initializeLight() {	
		builder.buildLight(rootNode);
	}
	
	private void initializeWorld() {
		builder = new XMLWorldBuilder(url);
		
		U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.
		getInstance().createEntity("EntityFactory","World");
	
		worldEntity.init(this.name);
		worldView = (U3dBuildingView) ViewManager.getInstance().
		createView(worldEntity);

		builder.buildWorld(worldView);
		this.rootNode.attachChild(worldView);
	}

	public void initializeCamera(DynamicView playerView) {
		if (playerView != null)
			chaser = this.builder.buildCamera(playerView);
	}
	
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanup() {
		rootNode.detachChild(worldView);
		
		
		worldView.detachAllChildren();
		rootNode.clearRenderState(0);
		chaser.removeAllActions();
		chaser=null;
		
		this.builder.destroyWorld(rootNode);
		this.builder = null;
		HudManager.getInstance().update();
		
		System.gc();		
		System.runFinalization();
	}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void updateState(float interpolation) {
		if (chaser!=null)
		{
			chaser.update(interpolation);
	        //Skybox sb=(Skybox) this.getRootNode().getChild("cielo");
			//sb.getLocalTranslation().set(chaser.getCamera().getLocation().x, chaser.getCamera().getLocation().y,
	        //		chaser.getCamera().getLocation().z);
		}
		HudManager.getInstance().getRoot()// solo necesito actualizar los
		// nodos del hud
		.updateGeometricState(0.0f, true);
		HudManager.getInstance().getRoot().updateRenderState();

		/*Ya no va mas con los AccessPoints
		  if(KeyBindingManager.getKeyBindingManager().isValidCommand("change", false)){
		 
			U3dChangeToExterior task =(U3dChangeToExterior) TaskManager.getInstance().createTask("3");
			task.initTask();
			TaskManager.getInstance().enqueue(task);
		}*/
	}

	public WorldGameState getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	public void updateCamera() {
		boolean intersects = false;
		Spatial worldView = this.rootNode.getChild("World_View");
		Spatial campus = ((Node)worldView).getChild("Campus");
		Spatial world = ((Node)campus).getChild("TestWorld");
		intersects = chaser.verifyIntersection(world);
//		System.out.println(intersects);
	}

	public Vector3f getTranslation() {
		return this.translation;
	}

	@Override
	public boolean needClean() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getDialogText() {
		return "Est\u00E1s frente a la puerta de ingreso a "+getName()+". \u00BFQuer\u00E9s Entrar?";
	}
}
