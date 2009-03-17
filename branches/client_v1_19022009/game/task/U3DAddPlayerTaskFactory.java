package client.game.task;
/**
 * <code>U3DAddPlayerTaskFactory</code> Proporciona 
 * la abstraccion necesaria en la creacion de tareas 
 * para agregar al player al mundo del tipo 
 * <code>U3DAddPlayerTask</code>. Implementa 
 * la interfaz ITaskFactory. Por defecto asigna por 
 * identificador el valor "8"
 * 
 * @author Guillermo Fiorini (Javadoc)
 * @version 1.0
 */
public class U3DAddPlayerTaskFactory implements ITaskFactory {
	/**
	 * El identificador de esta factoria
	 */
	private String id="8";
/**
	 * Crea una nueva <code>U3DAddPlayerTask</code>
	 * @return <code>ITask</code> La tarea generada 
	 */
	public ITask createTask() {
		return new U3DAddPlayerTask();
	}
	/**
	 * Retorna el identificador unico de la factoria
	 * @return <code>String</code> El identificador unico 
	 */
	public String getId() {
		return id;
	}

}
