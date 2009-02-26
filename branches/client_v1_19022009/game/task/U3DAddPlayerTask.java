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
import com.jme.math.Quaternion;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jmex.game.state.GameStateManager;

import common.datatypes.Skin;

public class U3DAddPlayerTask extends Task {
	
	private float scale = 0.8f;
	
	private Node rootNode = null;
	
	private IDynamicEntity entity;
	
	private float angle;
	
	private Vector3f position;
	
	private String actualWorld;
	
	public U3DAddPlayerTask() {
		super();
	}
	
	public void initTask(Node root, IDynamicEntity e,float theAngle, Vector3f thePosition, String theActualWorld){
		rootNode = root;
		entity = e;
		angle = theAngle;
		position = thePosition;
		actualWorld = theActualWorld;
	}
	
	public void execute() {
		Node node= new Node(entity.getId());
		entity.setPlayerAvatar(getPlayer(node));
		U3dPlayerView playerView = (U3dPlayerView) ViewManager.getInstance().
		createView(entity);
		playerView.attachChild(node);
		playerView.updateWorldBound();
		rootNode.attachChild(playerView);
		
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

	public boolean equals(Object o) {
		return false;
	}
	
	private IPersonaje getPlayer(Node node) 
	{
		/*if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_VIEJO_CON_PIPA))		
		{
			return getPlayerPipa(node);
		}
		if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_TIPO_CON_LA_10))		
		{
			return getPlayerDiez(node);
		}*/
		//Si no se dignaron a ponerle un Skin como la gente va el 10
		return getPlayerDiez(node);
	}
	
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
