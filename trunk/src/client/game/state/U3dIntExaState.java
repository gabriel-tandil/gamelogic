package client.game.state;

import client.game.input.U3DChaseCamera;
import client.game.view.U3dPlayerView;
import client.manager.HudManager;

import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;

/**
 * clase que representa el estado interior de la facultad de exactas del campus. La cual hereda de U3dState que 
 * a su vez hereda de WorldGameState
 * 
 *
 */
public class U3dIntExaState extends U3dState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private U3DChaseCamera chaser;


	public U3dIntExaState(String name) {
		super(name);
	}
	
	public void initialize() {
		//if (!this.initialized) {
		
			this.initializeWorld();
			this.initializeLight();
			this.initializeCamera((U3dPlayerView) this.rootNode
					.getChild("player_View"));

			// Habilitar esta opci�nn si se quierer probar la ejecuci�nn de la
			// tarea.
			// Deshabilitar el m�todo anterior initializeCamera(..)
			// Desabilitar el controlador del player (setActive(false)) en
			// XMLWorldBuilder.

			/*
			 * U3DAddPlayerTask newPlayer = new U3DAddPlayerTask("player1", 0,
			 * 800, true); newPlayer.execute();
			 */

			this.initialized = true;
			
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
		//this.world = (World) DataManager.getInstance().getWorld(EWorld.Battle);
		//this.world.setModelBound(new BoundingBox());
		//this.world.updateModelBound();
		//this.world.updateWorldBound();
		builder = new XMLWorldBuilder("protExaIntXML/data/ExaInt.xml");
		builder.buildWorld(this.rootNode);
	}

	public void initializeCamera(U3dPlayerView playerView) {
		chaser = this.builder.buildCamera(playerView);
		chaser.getCamera().setFrustumFar(400);
	}
	
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanup() {
		this.builder.destroyWorld(rootNode);
		this.builder = null;
		HudManager.getInstance().update();
	}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void updateState(float interpolation) {
		chaser.update(interpolation);
		Skybox sb = (Skybox) this.getRootNode().getChild("cielo");
		sb.getLocalTranslation().set(chaser.getCamera().getLocation().x,
				chaser.getCamera().getLocation().y,
				chaser.getCamera().getLocation().z);
		//se escala el cielo para que entre en el rango del frustrum
		sb.setLocalScale(0.9f);
		HudManager.getInstance().getRoot()// solo necesito actualizar los
				// nodos del hud
				.updateGeometricState(0.0f, true);
		HudManager.getInstance().getRoot().updateRenderState();
		
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
		Spatial worldView = this.rootNode.getChild("world_View");
		Spatial campus = ((Node)worldView).getChild("Campus");
		Spatial world = ((Node)campus).getChild("TestWorld");
		intersects = chaser.verifyIntersection(world);
//		System.out.println(intersects);
	}

}
