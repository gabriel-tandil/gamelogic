package client.game.entity;

import java.io.IOException;

import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;

/**
 * Entidad que representa un edificio del juego
 * 
 *
 */
public class U3DBuildingEntity extends Entity {

	public U3DBuildingEntity(String theTipo)
	{
		super(theTipo);
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
