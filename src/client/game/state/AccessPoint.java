/**
 * El AccessPoint es del tipo <code>Entity</code>. El <code>AccessPoint</code> representa los 
 * nuevos puntos de acceso BasicGameState del juego.
 * Cuado una entidad colisiona con el <code>AccessPoint</code> <code>Entity</code>, El juego cambia a un nuevo estado.
 */
package client.game.state;

import java.io.IOException;

import client.game.entity.Entity;
import client.game.task.ChangeStateTask;
import client.game.task.Task;
import client.game.task.U3dChangeToExterior;
import client.game.task.U3dChangeToIntEco;
import client.manager.TaskManager;

import com.jmex.game.state.BasicGameState;
import com.jmex.game.state.GameStateManager;
import com.jme.scene.Node;
import com.jme.scene.Spatial;
import com.jme.util.export.InputCapsule;
import com.jme.util.export.JMEExporter;
import com.jme.util.export.JMEImporter;
import com.jme.util.export.OutputCapsule;
import com.sun.corba.se.spi.orbutil.fsm.State;

/** 
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 * @version Modified: 06-02-2009
 */
public class AccessPoint implements IAccessPoint {
	
	/**
	 * Nodo al que esta asociado el AccessPoint
	 */
	private Node nodo;
	
	/**
	 * Proximo Estado
	 */
	private String nextState;
	
	/** 
	 * Constructor de AccessPoint.
	 */
	public AccessPoint(Node N, BasicGameState next) {
		nodo=N;
		proxEstado=next;
	}
	
	public Node getNodo()
	{
		return nodo;
	}

	/** 
	 * Poximo estado del juego.
	 */
	private BasicGameState proxEstado;

	public BasicGameState getProxEstado() {
		return this.proxEstado;
	}

	/** 
	 * Aplica un <code>BasicGameState</code> al <code>AccessPoint</code>.
	 * @param theBasicgamestate <code>BasicGameState</code> a aplicar.
	 */
	public void setBasicgamestate(BasicGameState state) {
		proxEstado=state;
	}

	/** 
	 * Retorna la clase de java de dicho <code>AccessPoint</code>.
	 * @return la clase de java del <code>AccessPoint</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/** 
	 * This method create a new <code>ChangeStateTask</code>, and this is enqueue first in the list of  task
	 * to execute calling a singleton <code>TaskManager</code>
	 */
	public void show() 
	{
		//if (!GameStateManager.getInstance().getChild("Eco").isActive())
		if(!proxEstado.isActive())
		{
			if (proxEstado.getClass().equals(U3dIntEcoState.class))
				{
					U3dChangeToIntEco task =(U3dChangeToIntEco) TaskManager.getInstance().createTask("4");
					task.initTask();
					TaskManager.getInstance().enqueue(task);
				}
			else
				if (proxEstado.getClass().equals(U3dExteriorState.class))
				{	
					U3dChangeToExterior task =(U3dChangeToExterior) TaskManager.getInstance().createTask("3");
					task.initTask();
					TaskManager.getInstance().enqueue(task);
				}
		}
		
	}
}