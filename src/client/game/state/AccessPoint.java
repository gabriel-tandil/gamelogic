/**
 * El AccessPoint es del tipo <code>Entity</code>. El <code>AccessPoint</code> representa los 
 * nuevos puntos de acceso BasicGameState del juego.
 * Cuado una entidad colisiona con el <code>AccessPoint</code> <code>Entity</code>, El juego cambia a un nuevo estado.
 */
package client.game.state;

import java.util.HashMap;

import client.game.task.U3dCargandoTask;
import client.game.task.U3dChangeToExterior;
import client.game.task.U3dChangeToIntACI;
import client.game.task.U3dChangeToIntBuffet;
import client.game.task.U3dChangeToIntEco;
import client.game.task.U3dChangeToIntExa;
import client.game.task.U3dChangeToIntIsistan;
import client.manager.HudManager;
import client.manager.TaskManager;

import com.jme.scene.Node;
import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import com.jmex.game.state.BasicGameState;

/**
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

	/**
	 * Constructor de AccessPoint.
	 */
	public AccessPoint(Node N, BasicGameState next) {
		nodo = N;
		proxEstado = next;
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
		// if (!GameStateManager.getInstance().getChild("Eco").isActive())
		if (!proxEstado.isActive()) {
			if (proxEstado.getClass().equals(U3dIntEcoState.class)) {
				U3dChangeToIntEco task = (U3dChangeToIntEco) TaskManager
						.getInstance().createTask("4");
				TaskManager.getInstance().enqueue(task);

			} else if (proxEstado.getClass().equals(U3dIntExaState.class)) {
				U3dChangeToIntExa task = (U3dChangeToIntExa) TaskManager
						.getInstance().createTask("5");
				TaskManager.getInstance().enqueue(task);
			} else if (proxEstado.getClass().equals(U3dIntIsistanState.class)) {
				U3dChangeToIntIsistan task = (U3dChangeToIntIsistan) TaskManager
						.getInstance().createTask("6");
				TaskManager.getInstance().enqueue(task);
			} else if (proxEstado.getClass().equals(U3dIntBuffetState.class)) {
				U3dChangeToIntBuffet task = (U3dChangeToIntBuffet) TaskManager
						.getInstance().createTask("9");
				TaskManager.getInstance().enqueue(task);
			} else if (proxEstado.getClass().equals(U3dIntACIState.class)) {
				U3dChangeToIntACI task = (U3dChangeToIntACI) TaskManager
						.getInstance().createTask("10");
				TaskManager.getInstance().enqueue(task);
			} else if (proxEstado.getClass().equals(U3dExteriorState.class)) {
				U3dChangeToExterior task = (U3dChangeToExterior) TaskManager
						.getInstance().createTask("3");
				TaskManager.getInstance().enqueue(task);
			}

		}

	}

	public void dialogoIngresar() {
		
		// if (!GameStateManager.getInstance().getChild("Eco").isActive())
		if (!proxEstado.isActive()) {
			if (System.currentTimeMillis()-timer>2000){
			timer=System.currentTimeMillis();
			String textoEdificio = "";
			if (proxEstado.getClass().equals(U3dIntEcoState.class)) {
				textoEdificio = "Estas frente a la puerta de ingreso a Economicas. ¿Queres Entrar?";
			}else if (proxEstado.getClass().equals(U3dIntExaState.class)) {
				textoEdificio = "Estas frente a la puerta de ingreso a Exactas. ¿Queres Entrar?";
			}else if (proxEstado.getClass().equals(U3dIntIsistanState.class)) {
				textoEdificio = "Estas frente a la puerta de ingreso al Isistan. ¿Queres Entrar?";
			}else if (proxEstado.getClass().equals(U3dIntACIState.class)) {
				textoEdificio = "Estas frente a la puerta de ingreso a Aulas Comunes I. ¿Queres Entrar?";
			}else if (proxEstado.getClass().equals(U3dIntBuffetState.class)) {
				textoEdificio = "Estas frente a la puerta de ingreso al Buffet. ¿Queres Entrar?";
			}else if (proxEstado.getClass().equals(U3dExteriorState.class)) {
				textoEdificio = "Estas frente a la puerta que sale al campus. ¿Queres Salir?";

			}
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
							}else{
								HudManager.getInstance().desvincula();
							}
								
						}
					});
			}
		}
	}
}