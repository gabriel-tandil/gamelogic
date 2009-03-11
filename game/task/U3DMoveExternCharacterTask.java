package client.game.task;

import java.util.ArrayList;

import client.communication.PositionsTranslator;
import client.communication.tasks.TaskCommFactory;
import client.game.entity.DynamicEntity;
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

public class U3DMoveExternCharacterTask extends Task {

	private static final int MOVIMIENTO_CAMINANDO = 70000;
	private static final int MOVIMIENTO_CORRIENDO = 270000;

	public U3DMoveExternCharacterTask() {
		super();
	}

	/**
	 * The <code>CharacterEntity</code> to be set.
	 */
	private DynamicEntity character;
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
		if (o instanceof U3DMoveExternCharacterTask) {
			U3DMoveExternCharacterTask given = (U3DMoveExternCharacterTask) o;
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
			Spatial view = (Spatial) ViewManager.getInstance().getView(this.character);
			Vector3f direction = view.getLocalRotation().getRotationColumn(0);
			if (adelante)
				direction = direction.mult(-1);
			Vector3f position = view.getLocalTranslation();

			origin.set(position.x, position.y, position.z);
			destine.set(this.character.getPosition());
			/*destine.set(direction.x + position.x, direction.y + position.y,
						direction.z + position.z);*/
			U3dState aux = ((U3dState) GameStateManager.getInstance()
							.getChild(U3dState.actualState));

			if (aux != null) {
				
				Vector3f lcoal = position.clone();
				direction = destine.subtract(lcoal);
				direction.normalizeLocal();

				Vector3f force = direction.multLocal(movement);
				this.character.getForce().addLocal(force);

				PhysicsManager.getInstance().markForUpdate(this.character);
				PlayerState ps = new PlayerState();
				ps.setState(PlayerState.STATE_MOVING);
				this.character.setState(ps);

				}
		}catch(Exception e) {
				e.printStackTrace();
			  }

	}

	public void initTask(DynamicEntity theCharacter, boolean isLocal,
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
