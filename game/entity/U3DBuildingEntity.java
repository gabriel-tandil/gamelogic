/**
 * La clase <code>U3DBuildingEntity</code> hereda
 * de la clase <code>Entity</code>, esta
 * clase es la que representa un edificio
 * en el juego.
 * 
 * @author kike
 */

package client.game.entity;

import java.io.IOException;

import client.manager.EntityManager;

import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;


public class U3DBuildingEntity extends Entity {

	public U3DBuildingEntity(String id)
	{
		this.setId(id);
		this.setTipo("Entity");
	}
	
	public void init(String theActualWord)
	{
		this.setActualWorld(theActualWord);
	}

	/** 
	 * Allow export the <code>U3DBuildingEntity</code> data to a specific location.
	 * @param arg0 <code>JMEExporter</code> to set the save location of data.
	 */
	public void write(JMEExporter arg0) throws IOException {
		OutputCapsule oc = arg0.getCapsule(this);
		oc.write(this.getId(), "ID", null);
	}

	/** 
	 * Allow import the <code>U3DBuildingEntity</code> data from a specific location.
	 * @param arg0 <code>JMEImporter</code> to set the save location of data.
	 */
	public void read(JMEImporter arg0)throws IOException {
		InputCapsule ic = arg0.getCapsule(this);
		this.setId(ic.readString("ID", null));
	}

	/** 
	 * Retrieve the Java Class of this <code>U3DBuildingEntity</code>.
	 * @return the Java Class of this <code>U3DBuildingEntity</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}
}
