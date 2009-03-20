package client.game.task;

import client.communication.GameContext;

/**
 * <code>U3DLoginRequestTask</code> Tarea encargada de invocar pedir al server
 * el logueo del usuario, lo hace via <code>gameContext</code>. Extiende la
 * funcionalidad de <code>Task</code>
 * 
 * @author Gabriel Alvarez
 */
public class U3DLoginRequestTask extends Task {

	/**
	 * string que mantiene el nombre del usuario
	 */
	private String nombre = null;
	/**
	 * string que mantiene la clave del usuario
	 */
	private String clave = null;

	/**
	 * constructor por defecto
	 */
	public U3DLoginRequestTask() {
		super();
	}

	/**
	 * Redefinido para la comparacion entre objetos
	 * 
	 * @param o
	 *            <code>Object</code> El objeto contra el cual se compara
	 * @return <code>boolean</code> Verdadero si los objetos son iguales
	 */
	public boolean equals(Object o) {

		return false;
	}

	/**
	 * metodo que realiza la ejecucion de la tarea llamando setea en el context
	 * el nombre de usuario, la clave y luego pide el modulo de comunicaciones e
	 * invoca el login
	 */
	public void execute() {
		try {
			GameContext.setUserName(nombre);

			GameContext.setPassword(clave);

			GameContext.getClientCommunication().login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * inicializa la tarea con el nombre de usuario y clave
	 * 
	 * @param nombre
	 * @param clave
	 */
	public void initTask(String nombre, String clave) {
		this.nombre = nombre;
		this.clave = clave;
	}

}
