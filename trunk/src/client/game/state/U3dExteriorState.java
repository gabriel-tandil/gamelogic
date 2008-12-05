package client.game.state;

import java.util.HashMap;

import client.game.view.U3dPlayerView;

import com.jme.bounding.BoundingCapsule;
import com.jme.input.ChaseCamera;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.light.PointLight;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;
import com.jme.scene.state.LightState;
import com.jme.system.DisplaySystem;

public class U3dExteriorState extends WorldGameState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private ChaseCamera chaser;
	
	private Spatial player;


	public U3dExteriorState(String name) {
		super(name);
	}
	
	public void initialize() {
		this.initializeLight();
		this.initializeWorld(); 

		this.initializeCamera(null);
		
		this.initialized = true;
		
		rootNode.updateGeometricState(0.0f, true);
		rootNode.updateRenderState();
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
		builder=new XMLWorldBuilder();
		builder.buildWorld(this.rootNode);
	}

	public void initializeCamera(U3dPlayerView playerView) {
		Spatial player = this.rootNode.getChild("Player");
		player.setModelBound(new BoundingCapsule());
		player.updateModelBound();
		player.updateWorldBound();
		
		Vector3f targetOffset = new Vector3f();
		targetOffset.y = 10 * 1.5f;
		HashMap props = new HashMap();
		props.put(ThirdPersonMouseLook.PROP_MAXROLLOUT, "6");
		props.put(ThirdPersonMouseLook.PROP_MINROLLOUT, "3");
		props.put(ChaseCamera.PROP_TARGETOFFSET, targetOffset);
		props.put(ThirdPersonMouseLook.PROP_MAXASCENT, ""+45 * FastMath.DEG_TO_RAD);
		props.put(ChaseCamera.PROP_INITIALSPHERECOORDS, new Vector3f(15, 0, 
				30 * FastMath.DEG_TO_RAD));

		chaser = new ChaseCamera(DisplaySystem.getDisplaySystem().getRenderer().
				getCamera(), player, props);
		chaser.setMaxDistance(30);
		chaser.setMinDistance(10);	
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
 
	public void setPlayer(Spatial node) {
		player = node;
	}
}
