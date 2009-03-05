package client.game.task;

import java.util.ArrayList;

import client.communication.PositionsTranslator;
import client.communication.tasks.TaskCommFactory;
import client.game.entity.Player;
import client.game.state.U3dState;
import client.gameEngine.PhysicsManager;
import client.manager.CollisionManager;
import client.manager.HudManager;
import client.manager.TaskManager;
import client.manager.ViewManager;

import com.jme.math.Ray;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jmex.game.state.GameState;
import com.jmex.game.state.GameStateManager;
import common.datatypes.PlayerState;
import common.messages.MessageFactory;
import common.messages.MsgTypes;
import common.messages.notify.MsgMove;

public class U3DMoveCharacterTask extends Task {

	private static final int MOVIMIENTO_CAMINANDO = 70000;
	private static final int MOVIMIENTO_CORRIENDO = 270000;

	public U3DMoveCharacterTask() {
		super();
	}

	/**
	 * The <code>CharacterEntity</code> to be set.
	 */
	private Player character;
	/**
	 * The flag indicates if the entity being set is locally controlled.
	 */
	private boolean local;
	/**
	 * The x coordinate of the destination position.
	 */
	private float endX;
	/**
	 * The y coordinate of the destination position.
	 */
	private float endY;
	/**
	 * The z coordinate of the destination position.
	 */
	private float endZ;
	private boolean adelante;
	private float movement = MOVIMIENTO_CAMINANDO;

	@Override
	public boolean equals(Object o) {
		if (o instanceof U3DMoveCharacterTask) {
			U3DMoveCharacterTask given = (U3DMoveCharacterTask) o;
			if (given.character != null)
				// faltaria definir el metodo equals en Player
				return given.character.equals(this.character);
			else
				return this.character == null;
		}
		return false;
	}

	public void execute() {
		if (this.character == null)
			return;
		try {
			this.character.getVelocity().zero();
			this.character.resetForce();
			Vector3f origin = new Vector3f();
			Vector3f destine = new Vector3f();
			Spatial view = (Spatial) ViewManager.getInstance().getView(
					this.character);
			Vector3f direction = view.getLocalRotation().getRotationColumn(0);
			if (adelante)
				direction = direction.mult(-1);
			Vector3f position = view.getLocalTranslation();

			origin.set(position.x, position.y, position.z);
			destine.set(direction.x + position.x, direction.y + position.y,
					direction.z + position.z);

			ArrayList<GameState> states = GameStateManager.getInstance()
					.getChildren();
			U3dState aux = null;
			for (int i = 0; i < states.size(); i++) {
				aux = (U3dState) states.get(i);
				if (aux.isActive())
					break;
			}
			if (aux != null) {

				Vector3f destination = CollisionManager.getInstace()
						.getDestination(origin, destine,
								aux.getRootNode().getChild(0));

				if (destination != null) {
					
					Vector3f lcoal = position.clone();
					direction = destination.subtract(lcoal);
					direction.normalizeLocal();

					aux.updateCamera();

					Vector3f force = direction.multLocal(movement);
					this.character.getForce().addLocal(force);

					PhysicsManager.getInstance().markForUpdate(this.character);
					PlayerState ps = new PlayerState();
					ps.setState(PlayerState.STATE_MOVING);
					this.character.setState(ps);

					Node nodeIntersect = (Node) CollisionManager.getInstace()
							.getIntersectObject(new Ray(origin, direction),
									(Node) aux.getRootNode().getChild(0),
									Node.class, true);
					if (nodeIntersect != null)
						CollisionManager.getInstace().checkOverAccessPoint(
								nodeIntersect);

					// Chequear. Suma a los valores de origen y destino los
					// puntos
					// de traslacion correspondientes para enviarle al servidor
					// numeros positivos

					Vector3f newOrigin = PositionsTranslator.serverPosition(
							character.getActualWorld(), origin);
					Vector3f newDestine = PositionsTranslator.serverPosition(
							character.getActualWorld(), destination);

					if (newDestine.x < 0) {
						newDestine.x = 0;
					}
					if (newDestine.z < 0) {
						newDestine.z = 0;
					}

					// TODO borrar los system.out
					System.out
							.println("Creando tarea para enviar el movimiento: ");
					System.out.println("Origen: " + origin);
					System.out.println("Destino: " + destination);

					MsgMove msg = (MsgMove) MessageFactory.getInstance()
							.createMessage(MsgTypes.MSG_MOVE_SEND_TYPE);

					msg.setIdDynamicEntity(this.character.getId());
					msg.setPosOrigen(newOrigin);
					msg.setPosDestino(newDestine);

					ITask task = TaskCommFactory.getInstance().createComTask(
							msg);

					TaskManager.getInstance().submit(task);
					HudManager.getInstance().getMap().changeMapLocation(destine.x,destine.z);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void initTask(Player theCharacter, boolean isLocal,
			boolean adelante, boolean corriendo) {
		if (corriendo)
			movement = MOVIMIENTO_CORRIENDO;
		else
			movement = MOVIMIENTO_CAMINANDO;
		character = theCharacter;
		local = isLocal;
		this.adelante = adelante;

	}

	public float getMovement() {
		return movement;
	}

	public void setMovement(float movement) {
		this.movement = movement;
	}

}
