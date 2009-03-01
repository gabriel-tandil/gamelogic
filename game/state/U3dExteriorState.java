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


public class U3dExteriorState extends U3dState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private U3DChaseCamera chaser;
	
	private String url;
	
	private Vector3f translation;

	
	public U3dExteriorState(String name, String url) {
		super(name);
		this.url = url;
		translation = new Vector3f();
	}

	public void initialize() {
		if (!this.initialized) {		
			KeyBindingManager.getKeyBindingManager().set("change", KeyInput.KEY_L);

			this.initializeWorld();
			this.initializeLight();
			this.inicializaHUD();
			
			this.builder.getTranslationPoint(translation);
			this.initialized = true;
			
			rootNode.updateGeometricState(0.0f, true);
			rootNode.updateRenderState();
		}
	}

	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
		HudManager.getInstance().removeWindow("login");
		HudManager.getInstance().removeWindow("errorLogueo");
	}

	private void initializeLight() {
		this.builder.buildLight(rootNode);
	}
	
	private void initializeWorld() {
		builder = new XMLWorldBuilder(url);
		
		U3DBuildingEntity worldEntity = (U3DBuildingEntity) EntityManager.
		getInstance().createEntity("EntityFactory","World");
	
		worldEntity.init(this.name);
		U3dBuildingView worldView = (U3dBuildingView) ViewManager.getInstance().
		createView(worldEntity);

		builder.buildWorld(worldView);
		this.rootNode.attachChild(worldView);
		
		Skybox sb = this.builder.setupSky();
		this.rootNode.attachChild(sb);	
	}

	public void initializeCamera(DynamicView playerView) {
		chaser = this.builder.buildCamera(playerView);
	}
	
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanup() {

	}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void updateState(float interpolation) {
		if (chaser!=null)
		{
			chaser.update(interpolation);
			Skybox sb=(Skybox) this.getRootNode().getChild("cielo");
			sb.getLocalTranslation().set(chaser.getCamera().getLocation().x, chaser.getCamera().getLocation().y,
	        		chaser.getCamera().getLocation().z);
		}
        
		HudManager.getInstance().getRoot()// solo necesito actualizar los
		// nodos del hud
		.updateGeometricState(0.0f, true);
		HudManager.getInstance().getRoot().updateRenderState();
		/* Ya no va mas con los AccessPoints
		 * if(KeyBindingManager.getKeyBindingManager().isValidCommand("change", false)){
			U3dChangeToIntEco task =(U3dChangeToIntEco) TaskManager.getInstance().createTask("4");
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
		Spatial campus = ((Node)worldView).getChild(0);
		Spatial world = ((Node)campus).getChild("TestWorld");
		intersects = chaser.verifyIntersection(world);
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public Vector3f getTranslation() {
		return this.translation;
	}
}
