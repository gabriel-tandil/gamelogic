package client.game.task;

/**
 * <code>U3DCargandoTaskFactory</code> Proporciona la abstraccion necesaria en
 * la creacion de tareas para mostrar al usuario el mensaje de que se esta
 * cargando, del tipo <code>U3DCargandoTask</code>. Implementa la interfaz
 * ITaskFactory. Por defecto asigna por identificador el valor "7"
 * 
 * @author Gabriel Alvarez
 */
public class U3DCargandoTaskFactory implements ITaskFactory {

	/**
	 * El identificador de esta factoria
	 */
	private String id = "7";

	/**
	 * Crea una nueva <code>U3DCargandoTask</code>
	 * 
	 * @return <code>ITask</code> La tarea generada
	 */
	public ITask createTask() {
		return new U3dCargandoTask();
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
