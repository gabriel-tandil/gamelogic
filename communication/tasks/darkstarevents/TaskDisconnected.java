package client.communication.tasks.darkstarevents;

import java.util.HashMap;

import client.communication.tasks.TaskCommunication;
import client.manager.HudManager;

import com.jmex.bui.event.ActionEvent;
import com.jmex.bui.event.ActionListener;
import common.messages.IMessage;
import common.messages.MsgPlainText;

/**
 * TaskDisconnected.java
 * 
 * Esta clase implementa una TaskCommunication: {@link TaskDisconnected} Al
 * ocurrir una desconexión, se crea una tarea TaskDisconnected. La misma, al ser
 * ejecutada, muestra al jugador el dialogo de consulta para volver a conectar.
 * TaskDisconnected mantiene un String con la razón de desconexión en el
 * atributo razonDesconeccion
 * 
 * @author Castillo/Santos
 */
public class TaskDisconnected extends TaskCommunication {

	/**
	 * El tipo de mensaje de la tarea.
	 */
	public static final String DISCONNECTED_TASK_TYPE = "disconnected";

	/** La razón de la desconexión. */
	private String razonDesconeccion;

	/**
	 * Setea la razón de desconexión que es pasada como parámetro.
	 * 
	 * @param razonDesconeccion
	 */
	public TaskDisconnected(final String razonDesconeccion) {
		super(null);
		this.razonDesconeccion = razonDesconeccion;
	}

	/**
	 * Para crear una tarea TaskDisconnected, se invoca a este método, el cual
	 * devuelve una instacia de la misma. Retorna una instancia de
	 * TaskDisconnected.
	 * 
	 * @see client.communication.tasks.TaskCommunication#factoryMethod(common.messages
	 *      .IMessage)
	 */
	public TaskCommunication factoryMethod(final IMessage msg) {
		return new TaskDisconnected(((MsgPlainText) msg).getMsg());
	}

	/**
	 * 
	 * Invoca al {@link HudManager} para mostrar al jugador el dialogo de
	 * consulta para volver a conectar.
	 * 
	 * @see client.game.task.ITask#execute()
	 * @author Castillo/Santos.
	 */
	public void execute() {

		HashMap<String, String> botones = new HashMap<String, String>();
		botones.put("Salir", "Salir");
		HudManager.getInstance().muestraDialogo("Se perdió la conexión.",
				botones, new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						System.exit(1);
					}
				});

		HudManager.getInstance().update();
	}

	/**
	 * Retorna el tipo de mensaje de la tarea.
	 * 
	 * @return {@link #DISCONNECTED_TASK_TYPE}
	 */
	public final String getMsgType() {
		return DISCONNECTED_TASK_TYPE;
	}

	/**
	 * Retorna la Razón de desconexión.
	 * 
	 * @return the razonDesconeccion
	 */
	public final String getRazonDesconeccion() {
		return razonDesconeccion;
	}

	/**
	 * Setea al atributo razonDesconeccion con el string pasado como parámetro.
	 * 
	 * @param razonDesconeccion
	 *            the razonDesconeccion to set
	 */
	public final void setRazonDesconeccion(final String razonDesconeccion) {
		this.razonDesconeccion = razonDesconeccion;
	}

}
