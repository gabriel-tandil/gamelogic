package client.game.task;

import client.game.IPersonaje;
import client.game.PersonaDae;
import client.game.PersonaMD5;
import client.game.controller.PlayerController;
import client.game.entity.IDynamicEntity;
import client.game.state.U3dState;
import client.game.view.U3dPlayerView;
import client.gameEngine.InputManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingBox;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jmex.game.state.GameStateManager;
import common.datatypes.Skin;
/**
 * <code>U3DAddPlayerTask</code> Tarea encargada de
 * agregar al jugador en el mundo 3D. 
 * Extiende la funcionalidad de <code>Task</code>
 * 
 * @author Guillermo Fiorini (Javadoc)
 * @version 1.0
 */
public class U3DAddPlayerTask extends Task {
	/**
	 * Escala utilizada para dibujar el personaje 3D
	 */
	private float scale = 0.8f;
	/**
	 * El nodo root del mundo 3D
	 */
	private Node rootNode = null;
	/**
	 * La entidad dinamica a representar
	 */
	private IDynamicEntity entity;
	/**
	 * El angulo que indica la direccion en 
	 * que se representa el modelo
	 */
	private float angle;
	/**
	 * La posicion del personaje en el mundo
	 */
	private Vector3f position;
	/**
	 * Mundo actual en el que se encuentra el personaje
	 */
	private String actualWorld;
	/**
	 * Constructor de <code>U3DAddPlayerTask</code>.
	 */
	public U3DAddPlayerTask() {
		super();
	}
	/**
	 * Inicializa una <code>U3DAddPlayerTask</code>.
	 * Este metodo es llamado antes de execute().
	 * @param root <code>Node</code> El nodo root del mundo 3D.
	 * @param e <code>IDynamicEntity</code> La entidad dinamica a representar.
	 * @param theAngle <code>float</code> El angulo que indica la rotacion de la entidad.
	 * @param thePosition <code>Vector3f</code> Posicion de la entidad en el mundo.
	 * @param theActualWorld <code>String</code> Mundo actual en el que se encuentra el personaje.
	 */
	public void initTask(Node root, IDynamicEntity e,float theAngle, Vector3f thePosition, String theActualWorld){
		rootNode = root;
		entity = e;
		angle = theAngle;
		position = thePosition;
		actualWorld = theActualWorld;
	}
	/**
	 * Define el comportamiento para la ejecucion de la tarea.
	 * Crea el nodo, setea el avatar, asigna una vista, crea el
	 * controlador para la entidad e inicializa la camara.
	 */
	public void execute() {
		Node node= new Node(entity.getId());
		entity.setPlayerAvatar(getPlayer(node));
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().
		createView(entity);
		playerView.attachChild(node);
		playerView.updateWorldBound();
		rootNode.attachChild(playerView);
		rootNode.updateGeometricState(0.0f, true);
		rootNode.updateRenderState();
		
		ViewManager.getInstance().addDirtyView(playerView);
		
		//crear el controller del player
		PlayerController controllerPlayer = (PlayerController) InputManager.
		getInstance().createController(entity);
		controllerPlayer.setActive(true);
		KeyInput.get().addListener(controllerPlayer);
		
		//inicializar la camara del player
		U3dState state = (U3dState)GameStateManager.getInstance().getChild(actualWorld);
		state.initializeCamera(playerView);
	}
	
	/**
	 * Redefinido para la comparacion entre objetos
	 * @param o <code>Object</code> El objeto contra el cual se compara
	 * @return <code>boolean</code> Verdadero si los objetos son iguales
	 */
	public boolean equals(Object o) {
		return false;
	}
	
	/**
	 * Obtiene el modelo del peronaje de la entidad 
	 * a partir de un nodo del tipo <code>Node</code>
	 * @param node <code>Node</code> El Nodo del personaje
	 * @return <code>IPersonaje</code> El personaje inicializado
	 */
	private IPersonaje getPlayer(Node node) 
	{
		if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_VIEJO_CON_PIPA))       
        {
            return getPlayerPipa(node);
        }
        if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_TIPO_CON_LA_10))       
        {
            return getPlayerDiez(node);
        }
		
		return getPlayer(node, entity.getSkin().getSkin());
	}
	/**
	 * Crea e inicializa un personaje dado un <code>Node</code>
	 * a partir de un modelo dado
	 * @param node <code>Node</code> El Nodo del personaje
	 * @param filename <code>String</code> nombre del archivo que contiene el modelo
	 * @return <code>IPersonaje</code> El personaje inicializado
	 */
	private IPersonaje getPlayer(Node node, String fileName)
    {
		IPersonaje p = new PersonaMD5(node);
		p.setPaquete("jmetest/data/model/MD5/");
		p.setPersonaje("jmetest/data/model/MD5/"+"CMan0002"+"/Mesh"+".md5mesh");
		p.setAnimaciones("jmetest/data/model/MD5/"+"CMan0002"+"/MeshAnim"+".md5anim");
		p.cargar();
		p.setModelBound(new BoundingBox());
		p.setLocalScale(scale);
		return p;
    }
	/**
	 * Crea e inicializa un personaje dado un <code>Node</code>
	 * utilizando el modelo del personaje con pipa
	 * @param node <code>Node</code> El Nodo del personaje
	 * @return <code>IPersonaje</code> El personaje inicializado
	 */
    private IPersonaje getPlayerPipa(Node node)
    {
        IPersonaje p = new PersonaDae(node);
        p.setPaquete("jmetest/data/model/collada/");
        p.setPersonaje("jmetest/data/model/collada/man.dae");
        p.setAnimaciones("jmetest/data/model/collada/man_walk.dae");
        p.cargar();
        p.setModelBound(new BoundingBox());
        p.setLocalScale(scale);
        return p;
    }
    /**
	 * Crea e inicializa un personaje dado un <code>Node</code>
	 * utilizando el modelo del personaje con camiseta 10
	 * @param node <code>Node</code> El Nodo del personaje
	 * @return <code>IPersonaje</code> El personaje inicializado
	 */
    private IPersonaje getPlayerDiez(Node node)
    {
        IPersonaje p = new PersonaMD5(node);
        p.setPaquete("jmetest/data/model/MD5/");
        p.setPersonaje("jmetest/data/model/MD5/Mesh.md5mesh");
        p.setAnimaciones("jmetest/data/model/MD5/MeshAnim.md5anim");
        p.cargar();
        p.setModelBound(new BoundingBox());
        p.setLocalScale(scale);
        return p;
    }
}
