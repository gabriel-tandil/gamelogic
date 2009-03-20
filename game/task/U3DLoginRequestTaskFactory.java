package client.game.task;

/**
 * <code>U3DLoginRequestTaskFactory</code> Proporciona la abstraccion necesaria en
 * la creacion de tareas para pedir al server el ingreso de un jugador, del tipo <code>U3DLoginRequestTask</code>. Implementa la interfaz
 * ITaskFactory. Por defecto asigna por identificador el valor ""
 * 
 * @author Gabriel Alvarez
 */
public class U3DLoginRequestTaskFactory implements ITaskFactory {

	/**
	 * El identificador de esta factoria
	 */
	private String id="8";

	/**
	 * Crea una nueva <code>U3DLoginRequestTask</code>
	 * 
	 * @return <code>ITask</code> La tarea generada
	 */
	public ITask createTask() {
		return new U3DLoginRequestTask();
	}

	/**
	 * Retorna el identificador unico de la factoria
	 * 
	 * @return <code>String</code> El identificador unico
	 */
	public String getId() {
		return id;
	}

}
