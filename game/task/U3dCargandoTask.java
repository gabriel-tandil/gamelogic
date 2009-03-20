package client.game.task;

import client.manager.HudManager;

/**
 * <code>U3dCargandoTask</code> Tarea encargada de invocar la muestra del
 * mensaje de que se esta cargando, via el hud manager. Extiende la
 * funcionalidad de <code>Task</code>
 * 
 * @author Gabriel Alvarez
 */
public class U3dCargandoTask extends ChangeStateTask {

	/**
	 * constructor por defecto
	 */
	public U3dCargandoTask() {
		super();
	}

	/**
	 * metodo que realiza la ejecucion de la tarea llamando a mostrar el mensaje
	 * de "Cargando..."
	 */
	public void execute() {
		HudManager.getInstance().setCargando();
	}

	/**
	 * Redefinido para la comparacion entre objetos
	 * 
	 * @param o
	 *            <code>Object</code> El objeto contra el cual se compara
	 * @return <code>boolean</code> Verdadero si los objetos son iguales
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * inicializacion de la tarea. No necesario en este caso
	 */
	public void initTask() {

	}

}
