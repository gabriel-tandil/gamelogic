package client.game.task;
/**
 * <code>U3DAddDynamicEntityTaskFactory</code> Proporciona 
 * la abstraccion necesaria en la creacion de tareas 
 * para agregar entidades dinamicas del tipo 
 * <code>U3DAddDynamicEntityTask</code>. Implementa 
 * la interfaz ITaskFactory. Por defecto asigna por 
 * identificador el valor "9"
 * 
 * @author Guillermo Fiorini (Javadoc)
 * @version 1.0
 */
public class U3DAddDynamicEntityTaskFactory implements ITaskFactory {
	/**
	 * El identificador de esta factoria
	 */
	private String id="9";
	/**
	 * Crea una nueva <code>U3DAddDynamicEntityTask</code>
	 * @return <code>ITask</code> La tarea generada 
	 */
	public ITask createTask() {
		return new U3DAddDynamicEntityTask();
	}
	/**
	 * Retorna el identificador unico de la factoria
	 * @return <code>String</code> El identificador unico 
	 */
	public String getId() {
		return id;
	}

}
