package client.game.state;

import client.game.input.U3DChaseCamera;
import client.game.task.TaskManagerFactory;
import client.game.task.U3DChangeToIntACITaskFactory;
import client.game.task.U3DChangeToIntBuffetTaskFactory;
import client.game.task.U3DChangeToIntEcoTaskFactory;
import client.game.task.U3DChangeToIntExaTaskFactory;
import client.game.task.U3DChangeToIntIsistanTaskFactory;
import client.game.task.U3DChangeToWordChallengeTaskFactory;
import client.game.view.U3dPlayerView;
import client.manager.HudManager;

import com.jme.scene.Node;
import com.jme.scene.Skybox;
import com.jme.scene.Spatial;

/**
 * clase que representa el estado exterior del juego. La cual hereda de U3dState que 
 * a su vez hereda de WorldGameState
 * @author kike
 *
 */
public class U3dExteriorState extends U3dState {
	
	private XMLWorldBuilder builder;

	private boolean initialized;
	
	private U3DChaseCamera chaser;


	public U3dExteriorState(String name) {
		super(name);
	}
	
	public void initialize() {
		
		
		if (!this.initialized) {
			TaskManagerFactory.getInstance().add(new U3DChangeToIntEcoTaskFactory());
			TaskManagerFactory.getInstance().add(new U3DChangeToIntExaTaskFactory());
			TaskManagerFactory.getInstance().add(new U3DChangeToIntIsistanTaskFactory());
			TaskManagerFactory.getInstance().add(new U3DChangeToIntBuffetTaskFactory());
			TaskManagerFactory.getInstance().add(new U3DChangeToIntACITaskFactory());
			TaskManagerFactory.getInstance().add(new U3DChangeToWordChallengeTaskFactory());
		}
		
		this.initializeWorld();
		this.initializeLight();

		this.initializeCamera((U3dPlayerView)this.rootNode.getChild("player_View"));

		// Habilitar esta opci�n si se quierer probar la ejecuci�n de la
		// tarea.
		// Deshabilitar el m�todo anterior initializeCamera(..)
		// Desabilitar el controlador del player (setActive(false)) en
		// XMLWorldBuilder.

		/*
		 * U3DAddPlayerTask newPlayer = new U3DAddPlayerTask("player1", 0,
		 * 800, true); newPlayer.execute();
		 */
		inicializaHUD();
		this.initialized = true;
		rootNode.updateGeometricState(0.0f, true);
		rootNode.updateRenderState();
		
	}

	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
		HudManager.getInstance().removeWindow("login");
		HudManager.getInstance().removeWindow("errorLogueo");
		HudManager.getInstance().muestraControl();
		HudManager.getInstance().setMuestraMapa(true);
		HudManager.getInstance().update();
	}

	private void initializeLight() {
		this.builder.buildLight(rootNode);
	}
	
	private void initializeWorld() {
		//this.world = (World) DataManager.getInstance().getWorld(EWorld.Battle);
		//this.world.setModelBound(new BoundingBox());
		//this.world.updateModelBound();
		//this.world.updateWorldBound();
		
		//builder = new XMLWorldBuilder("protCampusXML/data/campus.xml", new Vector3f(-1170.1987f, 1.5f, 3935.198f),new Quaternion(0.0f,0.707106781f,0.0f,0.707106781f));
		builder = new XMLWorldBuilder("protCampusXML/data/campus.xml");
		builder.buildWorld(this.rootNode);
	}

	public void initializeCamera(U3dPlayerView playerView) {
		chaser = this.builder.buildCamera(playerView);
	}
	
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanup(){
		this.builder.destroyWorld(rootNode);
		this.builder = null;
		HudManager.getInstance().update();
		}

	public void render(float arg0) {
		super.render(arg0);
	}

	public void updateState(float interpolation) {
		chaser.update(interpolation);
        Skybox sb=(Skybox) this.getRootNode().getChild("cielo");
		sb.getLocalTranslation().set(chaser.getCamera().getLocation().x, 
				chaser.getCamera().getLocation().y,
        		chaser.getCamera().getLocation().z);
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
	}

}
