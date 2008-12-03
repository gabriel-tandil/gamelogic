package client.game.state;

import java.util.HashMap;

import client.game.entity.EntityManagerFactory;
import client.game.entity.U3DPlayerFactory;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DAddPlayerTask;
import client.game.task.U3DMoveCharacterTaskFactory;
import client.game.view.DynamicView;
import client.game.view.U3DPlayerViewFactory;
import client.game.view.ViewFactoryManager;

import com.jme.bounding.BoundingBox;
import com.jme.bounding.BoundingCapsule;
import com.jme.input.ChaseCamera;
import com.jme.input.thirdperson.ThirdPersonMouseLook;
import com.jme.light.PointLight;
import com.jme.math.FastMath;
import com.jme.math.Vector3f;
import com.jme.renderer.ColorRGBA;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.scene.shape.Box;
import com.jme.scene.state.LightState;
import com.jme.scene.state.ZBufferState;
import com.jme.system.DisplaySystem;

public class U3dExteriorState extends WorldGameState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private ChaseCamera chaser;


	public U3dExteriorState(String name) {
		super(name);
	}
	
	public void initialize() {
		this.initializeLight();
		
		this.initializeWorld(); 
		
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
		builder = new XMLWorldBuilder();
		builder.buildWorld(this.rootNode);
		
		this.getWorldNode().setModelBound(new BoundingBox());
		this.getWorldNode().updateModelBound();
		this.getWorldNode().updateWorldBound();
		
		TaskManagerFactory.getInstance().add(new U3DMoveCharacterTaskFactory());		
		ViewFactoryManager.getInstance().add(new U3DPlayerViewFactory());
		EntityManagerFactory.getInstance().add(new U3DPlayerFactory());
		
//		U3DAddPlayerTask playerTask = new U3DAddPlayerTask("2",0,-10,true);
//		playerTask.execute();
	}

	public void initializeCamera(DynamicView view) {
		Vector3f targetOffset = new Vector3f();
		targetOffset.y = ((BoundingCapsule) view.getWorldBound()).getRadius() * 1.5f;
		HashMap props = new HashMap();
		props.put(ThirdPersonMouseLook.PROP_MAXROLLOUT, "6");
		props.put(ThirdPersonMouseLook.PROP_MINROLLOUT, "3");
		props.put(ChaseCamera.PROP_TARGETOFFSET, targetOffset);
		props.put(ThirdPersonMouseLook.PROP_MAXASCENT, ""+45 * FastMath.DEG_TO_RAD);
		props.put(ChaseCamera.PROP_INITIALSPHERECOORDS, new Vector3f(5, 0, 
				30 * FastMath.DEG_TO_RAD));

		chaser = new ChaseCamera(DisplaySystem.getDisplaySystem().getRenderer().
				getCamera(), view, props);
		chaser.setMaxDistance(30);
		chaser.setMinDistance(10);	
	}
	
	public void initializeState() {

	}
	
	public void cleanup() {

	}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void updateState(float interpolation) {
//		chaser.update(interpolation);	
	}

	public WorldGameState getWorld() {
		return null;
	}
	
	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public Node getWorldNode() {
		return (Node)this.rootNode.getChild("Campus");
	}
}
