/**
 * The AccessPoint is a type <code>Entity</code>. The <code>AccessPoint</code> represents a access point to a
 * new BasicGameState of the Game.
 * When a entity collision whit the <code>AccessPoint</code> <code>Entity</code>, the game change to a new state.
 */
package client.game.state;

import java.io.IOException;

import client.game.entity.Entity;
import client.game.task.ChangeStateTask;
import client.manager.TaskManager;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;

/** 
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 */
public class AccessPoint extends Entity implements IAccessPoint {
	/** 
	 * The constructor of the AccessPoint.
	 */
	public AccessPoint(String theId) {
		super(theId);
	}

	/** 
	 * The next state of the game.
	 */
	private BasicGameState basicgamestate;

	/** 
	 * Retrieve the BasicGameState associated to this <code>AccessPoint</code>.
	 * @return the BasicGameState associated to this <code>AccessPoint</code>.
	 */
	public BasicGameState getBasicgamestate() {
		return this.basicgamestate;
	}

	/** 
	 * Apply a <code>BasicGameState</code> to this <code>AccessPoint</code>.
	 * @param theBasicgamestate <code>BasicGameState</code> to apply.
	 */
	public void setBasicgamestate(BasicGameState theBasicgamestate) {
		this.basicgamestate=theBasicgamestate;
	}


	/** 
	 * Allow export the <code>AccessPoint</code> data to a specific location.
	 * @param arg0 <code>JMEExporter</code> to set the save location of data.
	 */
	public void write(JMEExporter arg0) {
		OutputCapsule oc = arg0.getCapsule(this);
		try 
		{
			oc.write(this.getId(), "ID", null);
			oc.write(basicgamestate.getName(),"BasicGameStateName",null);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 
	 * Allow import the <code>AccessPoint</code> data from a specific location.
	 * @param arg0 <code>JMEImporter</code> to set the save location of data.
	 */
	public void read(JMEImporter arg0) {
		InputCapsule ic = arg0.getCapsule(this);
		try 
		{
			this.setId(ic.readString("ID", null));
			this.basicgamestate.setName(ic.readString("BasicGameStateName", null));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/** 
	 * Retrieve the Java Class of this <code>AccessPoint</code>.
	 * @return the Java Class of this <code>AccessPoint</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/** 
	 * Get the <code>AccessPoint</code> id.
	 */
	public String getId() {
		return this.getId();
	}

	/** 
	 * This method create a new <code>ChangeStateTask</code>, and this is enqueue first in the list of  task
	 * to execute calling a singleton <code>TaskManager</code>
	 */
	public void show() {
		TaskManager.getInstance().enqueueFirst(new ChangeStateTask(this.basicgamestate));
	}
}