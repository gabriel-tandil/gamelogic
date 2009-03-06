/**
 * El AccessPoint es del tipo <code>Entity</code>. El <code>AccessPoint</code> representa los 
 * nuevos puntos de acceso BasicGameState del juego.
 * Cuado una entidad colisiona con el <code>AccessPoint</code> <code>Entity</code>, El juego cambia a un nuevo estado.
 */
package client.game.state;

import java.util.HashMap;

import client.game.task.ChangeStateTask;
import client.game.task.ChangeToGame;
import client.game.task.ChangeToPlace;
import client.game.task.U3dCargandoTask;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.game.state.BasicGameState;

/**
 * Representa los nuevos puntos de acceso BasicGameState del juego.
 * @author Santiago Michielotto
 * @version Created: 19-11-2008
 * @version Modified: 06-02-2009
 */
public class AccessPoint implements IAccessPoint {
long timer;
	/**
	 * Nodo al que esta asociado el AccessPoint
	 */
	private Node nodo;

	/**
	 * Proximo Estado
	 */
	private String nextState;
	
	private Vector3f nextPosition;

	/**
	 * Constructor de AccessPoint.
	 */
	public AccessPoint(Node N, BasicGameState next, Vector3f theNextPosition) {
		nodo = N;
		proxEstado = next;
		nextPosition = theNextPosition;
		timer=System.currentTimeMillis();
	}

	public Node getNodo() {
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
	 * 
	 * @param theBasicgamestate
	 *            <code>BasicGameState</code> a aplicar.
	 */
	public void setBasicgamestate(BasicGameState state) {
		proxEstado = state;
	}

	/**
	 * Retorna la clase de java de dicho <code>AccessPoint</code>.
	 * 
	 * @return la clase de java del <code>AccessPoint</code>.
	 */
	public Class getClassTag() {
		return this.getClass();
	}

	/**
	 * This method create a new <code>ChangeStateTask</code>, and this is
	 * enqueue first in the list of task to execute calling a singleton
	 * <code>TaskManager</code>
	 */
	public void show() {
		if (!proxEstado.isActive()) {
			ChangeStateTask task;
			if (((WorldGameState)proxEstado).needClean())
			{	
				task = new ChangeToPlace(this.proxEstado.getName(), this.nextPosition);
			}
			else
			{
				task = new ChangeToGame(this.proxEstado.getName());
			}
			TaskManager.getInstance().enqueue(task);
		}
	}

	public void dialogoIngresar() {
		
		// if (!GameStateManager.getInstance().getChild("Eco").isActive())
		if (!proxEstado.isActive()) {
			if (System.currentTimeMillis()-timer>2000){
			timer=System.currentTimeMillis();
			String textoEdificio = ((U3dState)proxEstado).getDialogText();
				
			HashMap<String, String> botones = new HashMap<String, String>();
			botones.put("abrirPuerta", "Abrir Puerta");
			botones.put("noAbrir", "No Abrir Puerta");
			HudManager.getInstance().update();
			HudManager.getInstance().muestraDialogo(textoEdificio, botones,
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							if (event.getAction().equals("abrirPuerta")) {
								U3dCargandoTask task = (U3dCargandoTask) TaskManager
								.getInstance().createTask("7"); // lo hago con un task poruqe sino gana la otra tarea y no llega a mostrar el cartel de cargando
						TaskManager.getInstance().enqueue(task);
								show();
							//}else{
							//	HudManager.getInstance().desvincula();
							}
								
						}
					});
			}
		}

		
		
		
	}

	public Vector3f getNextPosition() {
		return nextPosition;
	}
	
	
}