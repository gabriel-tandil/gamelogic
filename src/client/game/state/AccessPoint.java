/**
 * El AccessPoint es del tipo <code>Entity</code>. El <code>AccessPoint</code> representa los 
 * nuevos puntos de acceso BasicGameState del juego.
 * Cuado una entidad colisiona con el <code>AccessPoint</code> <code>Entity</code>, El juego cambia a un nuevo estado.
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
	 * Constructor de AccessPoint.
	 */
	public AccessPoint(String theTipo) {
		super(theTipo);
	}

	/** 
	 * Poximo estado del juego.
	 */
	private BasicGameState basicgamestate;


	/** 
	 * Devuelve el BasicGameState asociado al <code>AccessPoint</code>.
	 * @return el BasicGameState asociado al <code>AccessPoint</code>.
	 */
	public BasicGameState getState() {
		return this.basicgamestate;
	}

	/** 
	 * Aplica un <code>BasicGameState</code> al <code>AccessPoint</code>.
	 * @param theBasicgamestate <code>BasicGameState</code> a aplicar.
	 */
	public void setBasicgamestate(BasicGameState theBasicgamestate) {
		this.basicgamestate=theBasicgamestate;
	}


	/** 
	 * Permite exportar los datos del <code>AccessPoint</code> a un localizacion espesificada.
	 * @param arg0 <code>JMEExporter</code> conjunto de datos a guardar.
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
	 * Permite importa los datos del <code>AccessPoint</code> desde una localizacion especifica.
	 * @param arg0 <code>JMEImporter</code>conjunto de datos guardado.
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
	 * Retorna la clase de java de dicho <code>AccessPoint</code>.
	 * @return la clase de java del <code>AccessPoint</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/** 
	 * Retorna el identificador del <code>AccessPoint</code>.
	 * @return el identificador del <code>AccessPoint</code>.
	 */
	public String getId() {
		return this.getId();
	}

	/** 
	 * This method create a new <code>ChangeStateTask</code>, and this is enqueue first in the list of  task
	 * to execute calling a singleton <code>TaskManager</code>
	 */
	public void show() {
		//TaskManager.getInstance().enqueueFirst(new ChangeStateTask(this.basicgamestate));
	}
}