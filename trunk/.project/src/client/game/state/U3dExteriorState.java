package client.game.state;

import com.jme.light.PointLight;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.state.LightState;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;

public class U3dExteriorState extends WorldGameState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;


	public U3dExteriorState(String name) {
		super(name);
	}

	public void cleanup() {

	}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void update(float arg0) {
		super.update(arg0);
	}
	
	public void initialize() {		
		
		PointLight light = new PointLight();
		light.setDiffuse(ColorRGBA.blue);
		light.setAmbient(new ColorRGBA(0.5f,0.5f,0.5f,1.0f));
		light.setLocation(new Vector3f(50,100,100));
		light.setEnabled(true);
		
		LightState lightState = DisplaySystem.getDisplaySystem().getRenderer().
			createLightState();
		lightState.setEnabled(true);
		lightState.attach(light);

		rootNode.setRenderState(lightState);
		
		this.initializeWorld();
		this.initialized = true;
		
		rootNode.updateGeometricState(0.0f, true);
		rootNode.updateRenderState();
	}

	private void initializeWorld() {
		//this.world = (World) DataManager.getInstance().getWorld(EWorld.Battle);
		//this.world.setModelBound(new BoundingBox());
		//this.world.updateModelBound();
		//this.world.updateWorldBound();
		builder=new XMLWorldBuilder();
		builder.buildWorld(this.rootNode);
	}
	@Override
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
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
}
