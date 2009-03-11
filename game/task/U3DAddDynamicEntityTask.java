package client.game.task;

import client.game.IPersonaje;
import client.game.PersonaDae;
import client.game.PersonaMD5;
import client.game.controller.ExternPlayerController;
import client.game.controller.PlayerController;
import client.game.entity.IDynamicEntity;
import client.game.view.DynamicView;
import client.gameEngine.InputManager;
import client.manager.ViewManager;

import com.jme.bounding.BoundingBox;
import com.jme.input.KeyInput;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import common.datatypes.Skin;

public class U3DAddDynamicEntityTask extends Task {

	private float scale = 0.8f;

	private Node rootNode = null;

	private IDynamicEntity entity;

	private float angle;

	private Vector3f position;

	public U3DAddDynamicEntityTask() {
		super();
	}

	public void initTask(Node root, IDynamicEntity e, float theAngle,
			Vector3f thePosition) {
		rootNode = root;
		entity = e;
		angle = theAngle;
		position = thePosition;
	}

	public boolean equals(Object o) {
		return false;
	}

	public void execute() {
		Node node = new Node(entity.getId());
		IPersonaje player = getPlayer(node);
		player.setVelocity(8, 12);
		entity.setPlayerAvatar(player);
		DynamicView playerView = (DynamicView) ViewManager.getInstance()
				.createView(entity);
		playerView.attachChild(node);
		playerView.updateWorldBound();
		rootNode.attachChild(playerView);
		rootNode.updateGeometricState(0.0f, true);
		rootNode.updateRenderState();
		
		ViewManager.getInstance().addDirtyView(playerView);
		
		//crear el controller del player
		ExternPlayerController controllerPlayer = (ExternPlayerController) InputManager.
		getInstance().createController(entity);
		controllerPlayer.setActive(true);
		
	}

	private IPersonaje getPlayer(Node node) {
		if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_VIEJO_CON_PIPA)) {
			return getPlayerPipa(node);
		}
		if (entity.getSkin().getSkin().equals(Skin.PERSONAJE_TIPO_CON_LA_10)) {
			return getPlayerDiez(node);
		}
		// Si no se dignaron a ponerle un Skin como la gente va el 10
		return getPlayerDiez(node);
	}

	private IPersonaje getPlayerPipa(Node node) {
		IPersonaje p = new PersonaDae(node);
		p.setPaquete("jmetest/data/model/collada/");
		p.setPersonaje("jmetest/data/model/collada/man.dae");
		p.setAnimaciones("jmetest/data/model/collada/man_walk.dae");
		p.cargar();
		p.setModelBound(new BoundingBox());
		p.setLocalScale(scale);
		return p;
	}

	private IPersonaje getPlayerDiez(Node node) {
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
