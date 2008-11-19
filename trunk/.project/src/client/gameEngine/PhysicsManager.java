/**
 * 
 */
package client.gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.jme.math.Vector3f;
import com.sun.sgs.app.ObjectNotFoundException;

import client.game.entity.IDynamicEntity;
import client.game.view.View;
import client.manager.EntityManager;
import client.manager.ViewManager;

/** 
 * @author Santiago Michielotto
 * @version Created: 30-10-2008
 */
public class PhysicsManager {
	
	/**
	 * The <code>Logger</code> instance.
	 */
	protected final Logger logger;
	
	/**
	 * The fixed physics update rate in seconds.
	 */
	private final float rate;
	
	/**
	 * The gravity of the world.
	 */
	private static float gravedad;
	
	/**
	 * Constructor of <code>PhysicsManager</code>.
	 */
	protected PhysicsManager() {
		this.rate = 0.01f;
		this.entities =new ArrayList();
		this.logger = Logger.global;
		this.gravedad=0.1f;
	}
	
	/**
	 * The <code>PhysicsManager</code> instance.
	 */
	protected static PhysicsManager instance;
	
	/** 
	 * Retrieve the Singleton instance PhysicsManager.
	 * @return the instance of the <code>PhysicsManager</code>.
	 */
	public static PhysicsManager getInstance() {
		if(PhysicsManager.instance == null) {
			PhysicsManager.instance = new PhysicsManager();
		}
		return PhysicsManager.instance;
	}
	/** 
	 * Contains the <code>IDinamicEntity's</code> of the Game to be updated in the next iteration.
	 */
	private ArrayList<IDynamicEntity> entities;

	/** 
	 * Retrieve the <code>IDynamicEntity's</code> ArrayList of the Game to be updated in the next iteration.
	 * @return the <code>IDynamicEntity's</code> ArrayList of the Game to be updated in the next iteration.
	 */
	public ArrayList<IDynamicEntity> getEntities() {
		return entities;
	}

	/** 
	 * Apply a <code>ArrayList</code>< IDynamicEntity > to the <code>PhysicsManager</code>.
	 * @param theController <code>ArrayList</code>< IDynamicEntity > to aplly.
	 */
	public void setEntities(ArrayList<IDynamicEntity> theController) {
		entities = theController;
	}

	/** 
	 * Update all properties for a <code>IDynamicEntity</code>.
	 * @param entity the <code>IDynamicEntity</code> to be updated
	 */
	public void updatePhysics(IDynamicEntity entity) {
		this.applyForce(entity);
		this.updateVelocity(entity);
		this.updateTranslation(entity);
	}

	/**
	 * Mark the given dynamic entity for update during the next physics update cycle.
	 * @param entity The <code>IDynamicEntity</code> needs to be updated.
	 */
	public void markForUpdate(IDynamicEntity entity) {
		if (entity!=null)
			entities.add(entity);
	}

	/**
	 * Update the translation based on its current velocity.
	 * @param entity The <code>IDynamicEntity</code> to be updated.
	 */
	private void updateTranslation(IDynamicEntity entity) {
		Vector3f tempVector = new Vector3f();
		tempVector.set(entity.getVelocity()).multLocal(this.rate);
		try {
			View view = (View)ViewManager.getInstance().getView(entity);
			view.getLocalTranslation().addLocal(tempVector);
		} catch (ObjectNotFoundException e) {
			//this.logger.warning("Entity " + entity.toString() + " does not exist.");
		}
		entity.resetForce();
	}
	
	/**
	 * Update the velocity of the given dynamic entity based on its current force.
	 * @param entity The <code>IDynamicEntity</code> to be updated.
	 */
	private void updateVelocity(IDynamicEntity entity) {
		Vector3f velocity = entity.getForce().divideLocal(entity.getMass()).multLocal(this.rate);
		entity.addVelocity(velocity);
	}

	/**
	 * Apply the forces on the given dynamic entity.
	 * @param entity The <code>IDynamicEntity</code> to be applied to.
	 */
	private void applyForce(IDynamicEntity entity) {
		// Apply gravity and air friction when the entity is moving vertically.
		Vector3f tempVector = new Vector3f();
		if(entity.getVelocity().y != 0) {
			tempVector.y = -1;
			tempVector.multLocal(this.gravedad);
			entity.addForce(tempVector);
		}
	}

	/**
	 * Update the <code>PhysicsManager</code>.
	 */
	public void update() {
		// Return if there is no entities need to be updated.
		if(this.entities.size() <= 0) return;
		// Perform as many iterations as needed.
		for(IDynamicEntity entity : this.entities) 
		{
			updatePhysics(entity);
		}

		// Clear update list.
		this.entities.clear();
	}
}