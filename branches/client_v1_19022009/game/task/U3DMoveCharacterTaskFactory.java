package client.game.task;

/**
 * <code>U3DMoveCharacterTaskFactory</code> proporciona la abstraccion
 * necesaria para la creacion de tareas que son instancias de la clase
 * <code>U3DMoveCharacterTask</code>. Implementa la interfaz
 * <code>ITaskFactory</code>.
 * 
 * @author Sebastián Sampaoli (Javadoc)
 *
 */
public class U3DMoveCharacterTaskFactory implements ITaskFactory {

	/**
	 * El identificador de la tarea.
	 */
	private String id="1";

	@Override
	public ITask createTask() {
		return new U3DMoveCharacterTask();
	}


	@Override
	public String getId() {
		return id;
	}

}
