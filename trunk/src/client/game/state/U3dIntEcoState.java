package client.game.state;

import java.util.HashMap;

import client.game.U3DChaseCamera;
import client.game.task.U3dChangeToExterior;
import client.game.view.U3dPlayerView;
import client.manager.TaskManager;

import com.jme.input.ChaseCamera;
import com.jme.input.KeyBindingManager;
import com.jme.input.KeyInput;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.light.PointLight;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;
import com.jme.scene.state.LightState;
import com.jme.system.DisplaySystem;

public class U3dIntEcoState extends U3dState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private U3DChaseCamera chaser;


	public U3dIntEcoState(String name) {
		super(name);
	}
	
	public void initialize() {
		if (!this.initialized) {
			this.initializeLight();
			this.initializeWorld();

			this.initializeCamera((U3dPlayerView) this.rootNode
					.getChild("player_View"));

			// Habilitar esta opción si se quierer probar la ejecución de la
			// tarea.
			// Deshabilitar el método anterior initializeCamera(..)
			// Desabilitar el controlador del player (setActive(false)) en
			// XMLWorldBuilder.

			/*
			 * U3DAddPlayerTask newPlayer = new U3DAddPlayerTask("player1", 0,
			 * 800, true); newPlayer.execute();
			 */

			this.initialized = true;
			
			KeyBindingManager.getKeyBindingManager().set("change",
					KeyInput.KEY_L);

			rootNode.updateGeometricState(0.0f, true);
			rootNode.updateRenderState();
		}
	}

	private void initializeLight() {	
		PointLight light = new PointLight();
		light.setDiffuse(ColorRGBA.white);
		light.setAmbient(new ColorRGBA(0.5f,0.5f,0.5f,1.0f));
		light.setLocation(new Vector3f(50,100,100));
		light.setEnabled(true);
		
		LightState lightState = DisplaySystem.getDisplaySystem().getRenderer().
			createLightState();
		lightState.setEnabled(true);
		lightState.attach(light);

		rootNode.setRenderState(lightState);
	}
	
	private void initializeWorld() {
		//this.world = (World) DataManager.getInstance().getWorld(EWorld.Battle);
		//this.world.setModelBound(new BoundingBox());
		//this.world.updateModelBound();
		//this.world.updateWorldBound();
		builder = new XMLWorldBuilder("protEconIntXML/data/EconInt.xml");
		builder.buildWorld(this.rootNode);
	}

	public void initializeCamera(U3dPlayerView playerView) {
		Vector3f targetOffset = new Vector3f();
		targetOffset.y = 10 * 1.5f;

		HashMap props = new HashMap();
		props.put(ThirdPersonMouseLook.PROP_MAXROLLOUT, "6");
		props.put(ThirdPersonMouseLook.PROP_MINROLLOUT, "3");
		props.put(ChaseCamera.PROP_TARGETOFFSET, targetOffset);
		props.put(ThirdPersonMouseLook.PROP_MAXASCENT, "" + 25 * FastMath.DEG_TO_RAD);
		props.put(ThirdPersonMouseLook.PROP_MINASCENT, "" + 0);
		props.put(ChaseCamera.PROP_INITIALSPHERECOORDS, new Vector3f(15, 0, 
				25 * FastMath.DEG_TO_RAD));

		chaser = new U3DChaseCamera(DisplaySystem.getDisplaySystem().getRenderer().
				getCamera(), playerView, props);
		chaser.setMaxDistance(60);
		chaser.setMinDistance(40);	
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
		chaser.update(interpolation);
        Skybox sb=(Skybox) this.getRootNode().getChild("cielo");
		sb.getLocalTranslation().set(chaser.getCamera().getLocation().x, chaser.getCamera().getLocation().y,
        		chaser.getCamera().getLocation().z);
		if(KeyBindingManager.getKeyBindingManager().isValidCommand("change", false)){
			U3dChangeToExterior task =(U3dChangeToExterior) TaskManager.getInstance().createTask("3");
			task.initTask();
			TaskManager.getInstance().enqueue(task);
		}
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
	
	public void updateCamera(Vector3f direction) {
		boolean intersects = false;
		Spatial worldView = this.rootNode.getChild("world_View");
		Spatial campus = ((Node)worldView).getChild("Campus");
		Spatial world = ((Node)campus).getChild("TestWorld");
		intersects = chaser.verifyIntersection(world, direction);
//		System.out.println(intersects);
	}
}
