/**
 *<code>PhysicsManager</code> is responsible for updating all properties of the DinamicEntities
 * of the Game
 */
package client.gameEngine;

import java.util.ArrayList;

import client.game.entity.IDynamicEntity;
import client.game.entity.Player;
import client.game.view.IDynamicView;
import client.game.view.View;
import client.manager.ViewManager;

import com.jme.math.Vector3f;
import com.jmex.game.state.GameStateManager;

/** 
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 * 
 */
public class PhysicsManager {
	
	/**
	 * The time elapsed since last physics iteration.
	 */
	private float time;
	/**
	 * The flag indicates if the marked entities have been updated.
	 */
	private boolean updated;
	/**
	 * The gravity of the world.
	 */
	private Float gravedad;

	/** 
	 * Retrieve the Float magnitude of the Gravity of the World.
	 * @return the Float magnitude of the Gravity of the World.
	 */
	public Float getGravedad() {
		return gravedad;
	}

	/** 
	 * Set the Float magnitude of the Gravity of the World.
	 * @param theGravedad the Float magnitude of the Gravity of the World to be set.
	 */
	public void setGravedad(Float theGravedad) {
		gravedad = theGravedad;
	}

	/**
	 * The <code>PhysicsManager</code> instance.
	 */
	private static PhysicsManager instance;

	/** 
	 * Contains the <code>IDinamicEntity's</code> of the Game to be updated in the next iteration.
	 */
	private ArrayList <IDynamicEntity> entities;

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
	public void setEntities(ArrayList<IDynamicEntity> theEntities) {
		entities = theEntities;
	}

	/**
	 * The fixed physics update rate in seconds.
	 */
	private float rate;

	/**
	 * Constructor of <code>PhysicsManager</code>.
	 */
	protected PhysicsManager() {
		this.rate = 0.005f;
		this.entities =new ArrayList();
		this.gravedad=0.1f;
		this.updated=true;
	}

	/** 
	 * Retrieve the Singleton instance <code>PhysicsManager</code>.
	 * @return the instance of the <code>PhysicsManager</code>.
	 */
	public static PhysicsManager getInstance() {
		if(PhysicsManager.instance == null) {
			PhysicsManager.instance = new PhysicsManager();
		}
		return PhysicsManager.instance;
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
		View view = (View)ViewManager.getInstance().getView(entity);
	//	view.getLocalTranslation().addLocal(tempVector);
		//Vector3f rrr=view.getLocalTranslation().addLocal(tempVector);
		((Player)entity).getPosition().addLocal(tempVector);
		ViewManager.getInstance().addDirtyView((IDynamicView) view);
		//System.out.println(((Player)entity).getPosition());
		//((Player)entity).updateTimeStamp();
		//entity.resetForce();
	}

	/**
	 * Update the velocity of the given dynamic entity based on its current force.
	 * @param entity The <code>IDynamicEntity</code> to be updated.
	 */
	private void updateVelocity(IDynamicEntity entity) {
		Vector3f velocity = entity.getForce().divideLocal(entity.getMass()).multLocal(this.rate);
		entity.addVelocity(velocity);
		//System.out.println(velocity.x+" "+velocity.z+" "+entity.getMass()+" "+entity.getVelocity().z);
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
	public void update(float interpolation) {
		// Return if there is no entities need to be updated.
		if(this.entities.size() <= 0) return;
		// Perform as many iterations as needed.
		this.time += interpolation;
		if(this.time >= this.rate) this.updated = true;
		while(this.time >= this.rate) {
			for(IDynamicEntity entity : this.entities) {
				this.applyForce(entity);
				this.updateVelocity(entity);
				this.updateTranslation(entity);
			}
			this.time -= this.rate;
		}
		// Clear update list.
		if(this.updated) {
			this.entities.clear();
			this.updated = false;
		}
	}
	/*
	public void update(float interpolation) {
		// Return if there is no entities need to be updated.
		if(this.entities.size() <= 0) return;
		// Perform as many iterations as needed.
		this.time += interpolation;
		
		if(this.time >= this.rate) this.updated = true;
		else this.updated = false;
		while(this.time >= this.rate) {
			for(IDynamicEntity entity : this.entities) {
				this.applyForce(entity);
				this.updateVelocity(entity);
				this.updateTranslation(entity);
				ViewManager.getInstance().markForUpdate(entity);
				
			}
			this.time -= this.rate;
		}
		System.out.println(((Player)this.entities.get(0)).getPosition());
		if(!this.updated) {
			this.entities.clear();
			this.updated = false;
		}
	}*/
}