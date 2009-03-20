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

/**
 * Clase que representa el estado en que el jugador se encuentra dentro
 * del edificio de la facultad de Economicas. Hereda de U3DState.
 * @author kike
 *
 */
public class U3dIntEcoState extends U3dState {
	
	/**
	 * Indica si el estado esta inicializado.
	 */
	private boolean initialized;
	
	/**
	 * Camara que sigue al personaje.
	 */
	private U3DChaseCamera chaser;
	
	/**
	 * La ruta del XML que se va a cargar
	 */
	private String url;
	
	/**
	 * Vector que se establecera como nueva posicion del mundo.
	 */
	private Vector3f translation;
	
	/**
	 * Nodo que contiene el modelo.
	 */
	private U3dBuildingView worldView;
	
	/**
	 * La entidad que representa el mundo actual.
	 */
	private U3DBuildingEntity worldEntity;
	
	/**
	 * Constructor de la clase <code>U3dIntEcoState</code>
	 * @param name el nombre del estado representado por la clase
	 * @param url la direccion del XML a cargar.
	 */
	public U3dIntEcoState(String name, String url) {
		super(name);
		this.url = url;
		translation = new Vector3f();
	}
	
	
	/**
	 * Este metodo es el encargado de inicializar todo lo correspondiente 
	 * al estado(mundo, luz, HUD, posicion de inicio), invocando a los 
	 * metodos necesarios.
	 */
	public void initialize() {
	//	if (!this.initialized) {
			
			actualState = getName();
		
			this.initializeWorld();
			this.initializeLight();
			//this.initializeCamera((U3dPlayerView) this.rootNode.getChild("player_View"));

			
			this.iworldbuilder.getTranslationPoint(translation);
			this.initialized = true;
			
			//KeyBindingManager.getKeyBindingManager().set("change",
			//		KeyInput.KEY_L);
			
			inicializaHUD();
			rootNode.updateGeometricState(0.0f, true);
			rootNode.updateRenderState();
		//}
	}
	
	
	/**
	 * Inicializa el HUD correspondiente a este estado. Elimina las ventanas de
	 * login, crea el mapa y le setea sus parametros.
	 */
	private void inicializaHUD() {
		HudManager.getInstance().unSetCargando();
		HudManager.getInstance().removeWindow("login");
		HudManager.getInstance().removeWindow("errorLogueo");
		HudManager.getInstance().muestraControl();
		HudManager.getInstance().setMuestraMapa(false);
		HudManager.getInstance().update();
	}
	
	/**
	 * Llama al metodo buildLight del <code>iworldbuilder</code> para
	 * cargar las luces al mundo.
	 */
	private void initializeLight() {	
		this.iworldbuilder.buildLight(rootNode);
	}
	
	
	/**
	 * Crea la <code>U3DBuildingEntity</code> y la <code>U3dBuildingView</code>
	 * a las que se aplica el mundo, crea el mundo y lo agrega a la vista
	 * y por ultimo setea el cielo.
	 */
	private void initializeWorld() {
		this.iworldbuilder = new XMLWorldBuilder(url);
		
		worldEntity = (U3DBuildingEntity) EntityManager.
		getInstance().createEntity("EntityFactory",this.name);
	
		worldEntity.init(this.name);
		worldView = (U3dBuildingView) ViewManager.getInstance().
		createView(worldEntity);

		this.iworldbuilder.buildWorld(worldView);
		this.rootNode.attachChild(worldView);
	}

	
	/**
	 * Este metodo crea una <code>U3DChaseCamera</code> para la 
	 * <code>U3DPlayerView</code> y le setea el frustrum.
	 * @param playerView <code>DynamicView</code> es la vista del jugador 
	 * requerida para la Chasecamera
	 */
	public void initializeCamera(DynamicView playerView) {
		if (playerView != null){
			chaser = this.iworldbuilder.buildCamera(playerView);
			chaser.getCamera().setFrustumFar(400);
		}
		
	}
	
	public void initializeState() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Metodo que limpia todo lo creado para el estado con el objetivo
	 * de optimizar el uso de memoria.
	 */
	public void cleanup() {
		rootNode.detachChild(worldView);
		
		//FIXME No va, pero por hora lo dejamos para que saque todo
		rootNode.detachAllChildren();
		
		worldView.detachAllChildren();
		rootNode.clearRenderState(0);
		chaser.removeAllActions();
		chaser=null;
		
		ViewManager.getInstance().removeView(worldEntity);
		
		this.iworldbuilder.destroyWorld(rootNode);
		this.iworldbuilder = null;
		HudManager.getInstance().update();
		
		System.gc();		
		System.runFinalization();
	}

	/**
	 * Se encarga de dibujar el nodo raiz. Es llamado siempre por el 
	 * <code>GameStateManager</code> despues del update(float).
	 */
	public void render(float arg0) {
		super.render(arg0);
	}

	/**
	 * Actualiza la camara asociada a la vista del player y el hud.
	 */
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
	
	/**
	 * Metodo que dice si el estado esta inicializado 
	 * @return la variable que indica si el estado se encuentra inicializado.
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Setea la propiedad initialized del estado
	 * @param initialized 
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	/**
	 * Se encarga de actualizar la camara de manera que siempre apunte al
	 * personaje y verifica las posibles colisiones de esta con los distintos
	 * objetos del mundo.
	 */
	public void updateCamera() {
		boolean intersects = false;
		Spatial worldView = this.rootNode.getChild(this.name + "_View");
		Spatial campus = ((Node)worldView).getChild("campus");
		Spatial world = ((Node)campus).getChild("TestWorld");
		intersects = chaser.verifyIntersection(world);
//		System.out.println(intersects);
	}

	/**
	 * Retorna el vector que se establecera como nueva posicion del mundo. 
	 */
	public Vector3f getTranslation() {
		return this.translation;
	}

	@Override
	public boolean needClean() {
		// TODO Auto-generated method stub
		return true;
	}

	/** 
	 * @return el dialogo correspondiente segun el estado al que se este 
	 * cambiando, preguntando si se quiere ingresar o no.
	 */
	public String getDialogText() {
		return "Est\u00E1s frente a la puerta de ingreso a "+getName()+". \u00BFQuer\u00E9s Entrar?";
	}
}
